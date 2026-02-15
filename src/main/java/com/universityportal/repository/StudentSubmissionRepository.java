package com.universityportal.repository;

import com.universityportal.entity.StudentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentSubmissionRepository extends JpaRepository<StudentSubmission, Long> {
    List<StudentSubmission> findByAssignmentId(Long assignmentId);
    Optional<StudentSubmission> findByStudentIdAndAssignmentId(Long studentId, Long assignmentId);
}


