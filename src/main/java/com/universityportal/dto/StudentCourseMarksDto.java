package com.universityportal.dto;

public class StudentCourseMarksDto {

    private Long courseId;
    private String courseName;
    private Double quizMarks;
    private Double assignmentMarks;
    private Double midsMarks;
    private Double finalMarks;

    public StudentCourseMarksDto() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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


