package com.universityportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalClasses;
    private Integer presents;
    private Integer absents;

    @Column(length = 1000)
    private String previousAttendance;

    @OneToOne
    @JoinColumn(name = "enrollment_id", unique = true)
    private StudentCourseEnrollment studentCourseEnrollment;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StudentCourseEnrollment getStudentCourseEnrollment() {
        return studentCourseEnrollment;
    }

    public void setStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
        this.studentCourseEnrollment = studentCourseEnrollment;
    }
}


