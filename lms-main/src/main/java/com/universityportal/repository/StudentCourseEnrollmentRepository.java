package com.universityportal.repository;

import com.universityportal.entity.StudentCourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseEnrollmentRepository extends JpaRepository<StudentCourseEnrollment, Long> {

    Optional<StudentCourseEnrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<StudentCourseEnrollment> findByStudentId(Long studentId);

    List<StudentCourseEnrollment> findByCourseId(Long courseId);
}


