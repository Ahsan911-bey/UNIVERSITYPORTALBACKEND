package com.universityportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    private Double cgpa;
    private String wifiAccount;
    private String office365Email;
    private String office365Pass;

    private String rollNo;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batchEntity;

    @OneToMany(mappedBy = "student", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourseEnrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<StudentSubmission> submissions = new ArrayList<>();

    public Student() {
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public String getWifiAccount() {
        return wifiAccount;
    }

    public void setWifiAccount(String wifiAccount) {
        this.wifiAccount = wifiAccount;
    }

    public String getOffice365Email() {
        return office365Email;
    }

    public void setOffice365Email(String office365Email) {
        this.office365Email = office365Email;
    }

    public String getOffice365Pass() {
        return office365Pass;
    }

    public void setOffice365Pass(String office365Pass) {
        this.office365Pass = office365Pass;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Batch getBatchEntity() {
        return batchEntity;
    }

    public void setBatchEntity(Batch batchEntity) {
        this.batchEntity = batchEntity;
    }

    public List<StudentCourseEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<StudentCourseEnrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<StudentSubmission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<StudentSubmission> submissions) {
        this.submissions = submissions;
    }
}
