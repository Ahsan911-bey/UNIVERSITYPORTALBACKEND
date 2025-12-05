package com.universityportal.service;

import com.universityportal.dto.*;
import com.universityportal.entity.*;
import com.universityportal.mapper.CourseMapper;
import com.universityportal.mapper.StudentMapper;
import com.universityportal.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentSubmissionRepository submissionRepository;

    public StudentService(StudentRepository studentRepository,
                          StudentCourseEnrollmentRepository enrollmentRepository,
                          AssignmentRepository assignmentRepository,
                          StudentSubmissionRepository submissionRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
    }

    public StudentDto getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return StudentMapper.toDto(student);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getStudentCourses(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        
        List<CourseDto> courseDtos = student.getEnrollments().stream()
                .map(StudentCourseEnrollment::getCourse)
                .map(CourseMapper::toDto)
                .toList();
        
        // Add assignments with status for each course
        for (CourseDto courseDto : courseDtos) {
            List<Assignment> assignments = assignmentRepository.findByCourseId(courseDto.getId());
            List<AssignmentWithStatusDto> assignmentDtos = new ArrayList<>();
            
            for (Assignment assignment : assignments) {
                AssignmentWithStatusDto assignmentDto = new AssignmentWithStatusDto();
                assignmentDto.setId(assignment.getId());
                assignmentDto.setTitle(assignment.getTitle());
                assignmentDto.setDescription(assignment.getDescription());
                assignmentDto.setDueDate(assignment.getDueDate());
                
                // Teacher's assignment file
                assignmentDto.setTeacherFileUrl(assignment.getFileUrl());
                assignmentDto.setTeacherSubmitted(assignment.getFileUrl() != null && !assignment.getFileUrl().isEmpty());
                
                // Calculate status dynamically and get student's submission file
                Optional<StudentSubmission> submission = submissionRepository
                        .findByStudentIdAndAssignmentId(studentId, assignment.getId());
                
                if (submission.isEmpty()) {
                    assignmentDto.setStatus("pending");
                    assignmentDto.setStudentSubmissionFileUrl(null);
                } else {
                    StudentSubmission sub = submission.get();
                    // Student's submission file
                    assignmentDto.setStudentSubmissionFileUrl(sub.getFileUrl());
                    
                    LocalDate dueDate = assignment.getDueDate();
                    LocalDateTime submittedAt = sub.getSubmittedAt();
                    
                    if (submittedAt != null && dueDate != null) {
                        LocalDate submissionDate = submittedAt.toLocalDate();
                        if (submissionDate.isBefore(dueDate) || submissionDate.isEqual(dueDate)) {
                            assignmentDto.setStatus("submitted");
                        } else {
                            assignmentDto.setStatus("late");
                        }
                    } else {
                        assignmentDto.setStatus("submitted");
                    }
                }
                
                assignmentDtos.add(assignmentDto);
            }
            
            courseDto.setAssignments(assignmentDtos);
        }
        
        return courseDtos;
    }

    public List<StudentCourseMarksDto> getStudentMarks(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return student.getEnrollments().stream()
                .filter(e -> e.getMarks() != null)
                .map(e -> {
                    StudentCourseMarksDto dto = new StudentCourseMarksDto();
                    dto.setCourseId(e.getCourse().getId());
                    dto.setCourseName(e.getCourse().getCourseName());
                    dto.setQuizMarks(e.getMarks().getQuizMarks());
                    dto.setAssignmentMarks(e.getMarks().getAssignmentMarks());
                    dto.setMidsMarks(e.getMarks().getMidsMarks());
                    dto.setFinalMarks(e.getMarks().getFinalMarks());
                    return dto;
                })
                .toList();
    }

    public List<StudentCourseAttendanceSummaryDto> getStudentAttendance(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return student.getEnrollments().stream()
                .filter(e -> e.getAttendance() != null)
                .map(e -> {
                    StudentCourseAttendanceSummaryDto dto = new StudentCourseAttendanceSummaryDto();
                    dto.setCourseId(e.getCourse().getId());
                    dto.setCourseName(e.getCourse().getCourseName());
                    dto.setTotalClasses(e.getAttendance().getTotalClasses());
                    dto.setPresents(e.getAttendance().getPresents());
                    dto.setAbsents(e.getAttendance().getAbsents());
                    return dto;
                })
                .toList();
    }

    @Transactional
    public void submitAssignment(StudentSubmissionDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        
        // Check if submission already exists
        Optional<StudentSubmission> existingSubmission = submissionRepository
                .findByStudentIdAndAssignmentId(dto.getStudentId(), dto.getAssignmentId());
        
        StudentSubmission submission;
        if (existingSubmission.isPresent()) {
            submission = existingSubmission.get();
        } else {
            submission = new StudentSubmission();
            submission.setStudent(student);
            submission.setAssignment(assignment);
        }
        
        submission.setFileUrl(dto.getFileUrl());
        submission.setSubmittedAt(LocalDateTime.now());
        
        submissionRepository.save(submission);
    }

    // DEPRECATED: This endpoint is no longer used. Assignments are now returned in GET /student/{id}/courses
    // @Transactional(readOnly = true)
    // public List<StudentAssignmentStatusDto> getStudentAssignments(Long studentId) {
    //     Student student = studentRepository.findById(studentId)
    //             .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    //     List<StudentAssignmentStatusDto> result = new ArrayList<>();
    //     for (StudentCourseEnrollment enrollment : student.getEnrollments()) {
    //         enrollment.getAssignmentSubmissions().forEach(submission -> {
    //             StudentAssignmentStatusDto dto = new StudentAssignmentStatusDto();
    //             dto.setCourseName(enrollment.getCourse().getCourseName());
    //             dto.setAssignmentTitle(submission.getAssignment().getTitle());
    //             dto.setStatus(submission.getStatus());
    //             result.add(dto);
    //         });
    //     }
    //     return result;
    // }
}


