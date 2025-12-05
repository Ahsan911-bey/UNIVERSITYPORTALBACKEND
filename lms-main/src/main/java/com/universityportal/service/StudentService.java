package com.universityportal.service;

import com.universityportal.dto.*;
import com.universityportal.entity.Student;
import com.universityportal.entity.StudentCourseEnrollment;
import com.universityportal.mapper.CourseMapper;
import com.universityportal.mapper.StudentMapper;
import com.universityportal.repository.StudentCourseEnrollmentRepository;
import com.universityportal.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository,
                          StudentCourseEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
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
        return student.getEnrollments().stream()
                .map(StudentCourseEnrollment::getCourse)
                .map(CourseMapper::toDto)
                .toList();
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

    @Transactional(readOnly = true)
    public List<StudentAssignmentStatusDto> getStudentAssignments(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        List<StudentAssignmentStatusDto> result = new ArrayList<>();
        for (StudentCourseEnrollment enrollment : student.getEnrollments()) {
            enrollment.getAssignmentSubmissions().forEach(submission -> {
                StudentAssignmentStatusDto dto = new StudentAssignmentStatusDto();
                dto.setCourseName(enrollment.getCourse().getCourseName());
                dto.setAssignmentTitle(submission.getAssignment().getTitle());
                dto.setStatus(submission.getStatus());
                result.add(dto);
            });
        }
        return result;
    }
}


