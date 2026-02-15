package com.universityportal.dto;

public class StudentAttendanceUpdateDto {

    private Long studentId;
    private boolean present;

    public StudentAttendanceUpdateDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}


