package com.universityportal.repository;

import com.universityportal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId(Long teacherId);
    java.util.Optional<Course> findByCourseNo(String courseNo);
}


