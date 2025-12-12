package com.universityportal.service;

import com.universityportal.dto.*;
import com.universityportal.entity.*;
import com.universityportal.mapper.CourseMapper;
import com.universityportal.mapper.AdminMapper;
import com.universityportal.mapper.StudentMapper;
import com.universityportal.mapper.TeacherMapper;
import com.universityportal.mapper.BatchMapper;
import com.universityportal.repository.BatchRepository;
import com.universityportal.repository.AdminRepository;
import com.universityportal.repository.CourseRepository;
import com.universityportal.repository.StudentCourseEnrollmentRepository;
import com.universityportal.repository.StudentRepository;
import com.universityportal.repository.TeacherRepository;
import com.universityportal.repository.AssignmentRepository;
import com.universityportal.repository.StudentSubmissionRepository;
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
    private final AdminRepository adminRepository;
    private final AssignmentRepository assignmentRepository;
    private final StudentSubmissionRepository submissionRepository;

    public AdminService(StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            CourseRepository courseRepository,
            StudentCourseEnrollmentRepository enrollmentRepository,
            BatchRepository batchRepository,
            AdminRepository adminRepository,
            AssignmentRepository assignmentRepository,
            StudentSubmissionRepository submissionRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.batchRepository = batchRepository;
        this.adminRepository = adminRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
    }

    public StudentDto createStudent(StudentDto dto) {
        Student student = StudentMapper.toEntity(dto);
        if (dto.getBatch() != null) {
            Batch batch = batchRepository.findByNameIgnoreCase(dto.getBatch())
                    .orElseThrow(() -> new IllegalArgumentException("Batch not found: " + dto.getBatch()));
            student.setBatchEntity(batch);
        }
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

    @Transactional(readOnly = true)
    public AdminDto getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        return AdminMapper.toDto(admin);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<BatchDto> getAllBatches() {
        return batchRepository.findAll().stream()
                .map(BatchMapper::toDto)
                .toList();
    }

    @Transactional
    public BatchDto createBatch(BatchDto dto) {
        Batch batch = new Batch();
        batch.setName(dto.getName());
        Batch saved = batchRepository.save(batch);
        return BatchMapper.toDto(saved);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void deleteTeacher(Long id) {
        // Detach teacher from courses to avoid FK on courses.teacher_id
        List<Course> coursesOfTeacher = courseRepository.findByTeacherId(id);
        for (Course c : coursesOfTeacher) {
            c.setTeacher(null);
        }
        courseRepository.saveAll(coursesOfTeacher);

        // Delete assignments created by this teacher (and their student submissions)
        List<Assignment> assignments = assignmentRepository.findByTeacherId(id);
        for (Assignment a : assignments) {
            submissionRepository.deleteAll(submissionRepository.findByAssignmentId(a.getId()));
        }
        assignmentRepository.deleteAll(assignments);

        // Finally delete the teacher
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
