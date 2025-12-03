package com.universityportal.dto;

import jakarta.validation.constraints.NotNull;

public class MarksDto {

    private Long id;

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Course id is required")
    private Long courseId;

    @NotNull(message = "Quiz marks are required")
    private Double quizMarks;

    @NotNull(message = "Assignment marks are required")
    private Double assignmentMarks;

    @NotNull(message = "Mids marks are required")
    private Double midsMarks;

    @NotNull(message = "Final marks are required")
    private Double finalMarks;

    public MarksDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getQuizMarks() {
        return quizMarks;
    }

    public void setQuizMarks(Double quizMarks) {
        this.quizMarks = quizMarks;
    }

    public Double getAssignmentMarks() {
        return assignmentMarks;
    }

    public void setAssignmentMarks(Double assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }

    public Double getMidsMarks() {
        return midsMarks;
    }

    public void setMidsMarks(Double midsMarks) {
        this.midsMarks = midsMarks;
    }

    public Double getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(Double finalMarks) {
        this.finalMarks = finalMarks;
    }
}


