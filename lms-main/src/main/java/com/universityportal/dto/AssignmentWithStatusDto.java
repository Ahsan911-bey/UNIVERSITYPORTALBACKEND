package com.universityportal.dto;

import java.time.LocalDate;

public class AssignmentWithStatusDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String teacherFileUrl; // Teacher's assignment file
    private Boolean teacherSubmitted;
    private String studentSubmissionFileUrl; // Student's submission file (if submitted)
    private String status; // pending, submitted, late

    public AssignmentWithStatusDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTeacherFileUrl() {
        return teacherFileUrl;
    }

    public void setTeacherFileUrl(String teacherFileUrl) {
        this.teacherFileUrl = teacherFileUrl;
    }

    public Boolean getTeacherSubmitted() {
        return teacherSubmitted;
    }

    public void setTeacherSubmitted(Boolean teacherSubmitted) {
        this.teacherSubmitted = teacherSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentSubmissionFileUrl() {
        return studentSubmissionFileUrl;
    }

    public void setStudentSubmissionFileUrl(String studentSubmissionFileUrl) {
        this.studentSubmissionFileUrl = studentSubmissionFileUrl;
    }
}

