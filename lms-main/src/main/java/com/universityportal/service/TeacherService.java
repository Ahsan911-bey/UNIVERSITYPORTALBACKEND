package com.universityportal.service;

import com.universityportal.dto.*;
import com.universityportal.entity.*;
import com.universityportal.mapper.*;
import com.universityportal.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universityportal.dto.StudentSubmissionResponseDto;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final AssignmentRepository assignmentRepository;
    private final MarksRepository marksRepository;
    private final AnnouncementRepository announcementRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final BatchRepository batchRepository;
    private final StudentSubmissionRepository submissionRepository;

    public TeacherService(CourseRepository courseRepository,
                          TeacherRepository teacherRepository,
                          StudentRepository studentRepository,
                          AttendanceRepository attendanceRepository,
                          AssignmentRepository assignmentRepository,
                          MarksRepository marksRepository,
                          AnnouncementRepository announcementRepository,
                          StudentCourseEnrollmentRepository enrollmentRepository,
                          BatchRepository batchRepository,
                          StudentSubmissionRepository submissionRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.assignmentRepository = assignmentRepository;
        this.marksRepository = marksRepository;
        this.announcementRepository = announcementRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.batchRepository = batchRepository;
        this.submissionRepository = submissionRepository;
    }

    public List<CourseDto> getCoursesForTeacher(Long teacherId) {
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        return courses.stream().map(CourseMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TeacherDto getTeacherById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        return TeacherMapper.toDto(teacher);
    }

    @Transactional(readOnly = true)
    public List<SimpleStudentDto> getStudentsByCourseAndBatch(String courseCode, String batchName) {
        Course course = courseRepository.findByCourseNo(courseCode)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        Batch batch = batchRepository.findByNameIgnoreCase(batchName)
                .orElseThrow(() -> new IllegalArgumentException("Batch not found"));

        return batch.getStudents().stream()
                .filter(student -> student.getEnrollments().stream()
                        .anyMatch(enrollment -> enrollment.getCourse().getId().equals(course.getId())))
                .map(student -> {
                    SimpleStudentDto dto = new SimpleStudentDto();
                    dto.setStudentId(student.getId());
                    dto.setName(student.getName());
                    dto.setRollNo(student.getRollNo());
                    dto.setRegNo(student.getRegNo());
                    dto.setEmail(student.getEmailAddress());
                    return dto;
                })
                .toList();
    }

    @Transactional
    public void markAttendanceForCourse(Long courseId, List<StudentAttendanceUpdateDto> updates) {
        for (StudentAttendanceUpdateDto update : updates) {
            StudentCourseEnrollment enrollment = enrollmentRepository
                    .findByStudentIdAndCourseId(update.getStudentId(), courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

            Attendance attendance = enrollment.getAttendance();
            if (attendance == null) {
                attendance = new Attendance();
                attendance.setTotalClasses(0);
                attendance.setPresents(0);
                attendance.setAbsents(0);
                attendance.setStudentCourseEnrollment(enrollment);
                enrollment.setAttendance(attendance);
            }

            attendance.setTotalClasses(attendance.getTotalClasses() + 1);
            if (update.isPresent()) {
                attendance.setPresents(attendance.getPresents() + 1);
            } else {
                attendance.setAbsents(attendance.getAbsents() + 1);
            }
            attendanceRepository.save(attendance);
        }
    }

    @Transactional
    public AnnouncementDto createAnnouncement(AnnouncementDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Announcement announcement = AnnouncementMapper.toEntity(dto);
        announcement.setCourse(course);
        if (announcement.getTimestamp() == null) {
            announcement.setTimestamp(LocalDateTime.now());
        }
        Announcement saved = announcementRepository.save(announcement);
        return AnnouncementMapper.toDto(saved);
    }

    @Transactional
    public AssignmentDto createAssignment(AssignmentDto dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Assignment assignment = AssignmentMapper.toEntity(dto);
        assignment.setTeacher(teacher);
        assignment.setCourse(course);
        assignment.setFileUrl(dto.getFileUrl());
        Assignment saved = assignmentRepository.save(assignment);
        return AssignmentMapper.toDto(saved);
    }

    @Transactional
    public void deleteAnnouncement(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("Announcement not found"));
        announcementRepository.delete(announcement);
    }

    @Transactional(readOnly = true)
    public List<StudentSubmissionResponseDto> getAssignmentSubmissions(Long assignmentId) {
        // Verify assignment exists
        assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        
        List<StudentSubmission> submissions = submissionRepository.findByAssignmentId(assignmentId);
        return submissions.stream().map(submission -> {
            StudentSubmissionResponseDto dto = new StudentSubmissionResponseDto();
            dto.setStudentId(submission.getStudent().getId());
            dto.setStudentName(submission.getStudent().getName());
            dto.setFileUrl(submission.getFileUrl());
            dto.setSubmittedAt(submission.getSubmittedAt());
            return dto;
        }).toList();
    }

    @Transactional
    public MarksDto recordMarks(MarksDto dto) {
        Marks marks = MarksMapper.toEntity(dto);
        StudentCourseEnrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        marks.setStudentCourseEnrollment(enrollment);
        enrollment.setMarks(marks);
        Marks saved = marksRepository.save(marks);
        return MarksMapper.toDto(saved);
    }
}


