package com.universityportal.dto;

import jakarta.validation.constraints.NotNull;

public class StudentSubmissionDto {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Assignment id is required")
    private Long assignmentId;

    @NotNull(message = "File URL is required")
    private String fileUrl;

    public StudentSubmissionDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

