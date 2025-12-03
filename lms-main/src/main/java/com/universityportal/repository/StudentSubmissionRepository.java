package com.universityportal.repository;

import com.universityportal.entity.StudentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSubmissionRepository extends JpaRepository<StudentSubmission, Long> {
}


