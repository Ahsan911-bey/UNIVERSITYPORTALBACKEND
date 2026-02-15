package com.universityportal.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseStudentsAssignmentRequest {

    private Long courseId;
    private List<Long> studentIds = new ArrayList<>();

    public CourseStudentsAssignmentRequest() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}


