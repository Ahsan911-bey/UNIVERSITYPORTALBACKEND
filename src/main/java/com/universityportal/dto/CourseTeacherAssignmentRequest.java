package com.universityportal.dto;

public class CourseTeacherAssignmentRequest {

    private Long courseId;
    private Long teacherId;

    public CourseTeacherAssignmentRequest() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}


