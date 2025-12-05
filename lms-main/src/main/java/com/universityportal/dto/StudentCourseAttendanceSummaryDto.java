package com.universityportal.dto;

public class StudentCourseAttendanceSummaryDto {

    private Long courseId;
    private String courseName;
    private Integer totalClasses;
    private Integer presents;
    private Integer absents;

    public StudentCourseAttendanceSummaryDto() {
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
}


