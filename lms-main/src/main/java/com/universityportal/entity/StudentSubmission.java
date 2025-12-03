package com.universityportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_submissions")
public class StudentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private StudentCourseEnrollment studentCourseEnrollment;

    private String fileUrl;
    private String status; // submitted / pending / late

    public StudentSubmission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public StudentCourseEnrollment getStudentCourseEnrollment() {
        return studentCourseEnrollment;
    }

    public void setStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
        this.studentCourseEnrollment = studentCourseEnrollment;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


