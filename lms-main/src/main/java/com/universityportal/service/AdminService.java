package com.universityportal.service;

import com.universityportal.dto.*;
import com.universityportal.entity.*;
import com.universityportal.mapper.CourseMapper;
import com.universityportal.mapper.StudentMapper;
import com.universityportal.mapper.TeacherMapper;
import com.universityportal.repository.BatchRepository;
import com.universityportal.repository.CourseRepository;
import com.universityportal.repository.StudentCourseEnrollmentRepository;
import com.universityportal.repository.StudentRepository;
import com.universityportal.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;
    private final BatchRepository batchRepository;

    public AdminService(StudentRepository studentRepository,
                        TeacherRepository teacherRepository,
                        CourseRepository courseRepository,
                        StudentCourseEnrollmentRepository enrollmentRepository,
                        BatchRepository batchRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.batchRepository = batchRepository;
    }

    public StudentDto createStudent(StudentDto dto) {
        Student student = StudentMapper.toEntity(dto);
        Student saved = studentRepository.save(student);
        return StudentMapper.toDto(saved);
    }

    public TeacherDto createTeacher(TeacherDto dto) {
        Teacher teacher = TeacherMapper.toEntity(dto);
        Teacher saved = teacherRepository.save(teacher);
        return TeacherMapper.toDto(saved);
    }

    @Transactional
    public CourseDto createCourse(CourseDto dto) {
        Course course = CourseMapper.toEntity(dto);
        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
            course.setTeacher(teacher);
        }
        Course saved = courseRepository.save(course);
        return CourseMapper.toDto(saved);
    }

    @Transactional
    public List<StudentDto> assignStudentsToBatch(BatchAssignmentRequest request) {
        List<Student> students = studentRepository.findAllById(request.getStudentIds());
        Batch batch = batchRepository.findByNameIgnoreCase(request.getBatch())
                .orElseGet(() -> {
                    Batch b = new Batch();
                    b.setName(request.getBatch());
                    return batchRepository.save(b);
                });
        for (Student student : students) {
            student.setBatch(request.getBatch());
            student.setBatchEntity(batch);
        }
        List<Student> saved = studentRepository.saveAll(students);
        return saved.stream().map(StudentMapper::toDto).toList();
    }

    @Transactional
    public CourseDto assignTeacherToCourse(CourseTeacherAssignmentRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
        course.setTeacher(teacher);
        Course saved = courseRepository.save(course);
        return CourseMapper.toDto(saved);
    }

    @Transactional
    public CourseDto assignStudentsToCourse(CourseStudentsAssignmentRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        List<Student> students = studentRepository.findAllById(request.getStudentIds());
        for (Student student : students) {
            StudentCourseEnrollment enrollment = new StudentCourseEnrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollmentRepository.save(enrollment);
        }
        return CourseMapper.toDto(course);
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

    @Transactional(readOnly = true)
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(TeacherMapper::toDto)
                .toList();
    }
}


