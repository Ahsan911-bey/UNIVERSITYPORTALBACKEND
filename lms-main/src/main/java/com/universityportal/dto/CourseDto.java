package com.universityportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {

    private Long id;

    @NotBlank(message = "Course number is required")
    private String courseNo;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotNull(message = "Credits are required")
    private Integer credits;

    private Long teacherId;
    private List<Long> studentIds = new ArrayList<>();
    private List<AnnouncementDto> announcements = new ArrayList<>();

    public CourseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public List<AnnouncementDto> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementDto> announcements) {
        this.announcements = announcements;
    }
}


