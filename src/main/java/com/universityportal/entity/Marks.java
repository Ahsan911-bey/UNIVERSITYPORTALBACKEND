package com.universityportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quizMarks;
    private Double assignmentMarks;
    private Double midsMarks;
    private Double finalMarks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", unique = true)
    private StudentCourseEnrollment studentCourseEnrollment;

    public Marks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StudentCourseEnrollment getStudentCourseEnrollment() {
        return studentCourseEnrollment;
    }

    public void setStudentCourseEnrollment(StudentCourseEnrollment studentCourseEnrollment) {
        this.studentCourseEnrollment = studentCourseEnrollment;
    }
}
