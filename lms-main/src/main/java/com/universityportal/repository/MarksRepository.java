package com.universityportal.repository;

import com.universityportal.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    Optional<Marks> findByStudentCourseEnrollmentId(Long enrollmentId);
}


