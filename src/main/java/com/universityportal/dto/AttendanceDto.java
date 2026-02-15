package com.universityportal.dto;

import jakarta.validation.constraints.NotNull;

public class AttendanceDto {

    private Long id;

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotNull(message = "Course id is required")
    private Long courseId;

    @NotNull(message = "Total classes is required")
    private Integer totalClasses;

    @NotNull(message = "Presents is required")
    private Integer presents;

    @NotNull(message = "Absents is required")
    private Integer absents;

    private String previousAttendance;

    public AttendanceDto() {
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

    public Integer getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(Integer totalClasses) {
        this.totalClasses = totalClasses;
    }

    public Integer getPresents() {
        return presents;
    }

    public void setPresents(Integer presents) {
        this.presents = presents;
    }

    public Integer getAbsents() {
        return absents;
    }

    public void setAbsents(Integer absents) {
        this.absents = absents;
    }

    public String getPreviousAttendance() {
        return previousAttendance;
    }

    public void setPreviousAttendance(String previousAttendance) {
        this.previousAttendance = previousAttendance;
    }
}


