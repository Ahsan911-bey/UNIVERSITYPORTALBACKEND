package com.universityportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class StudentDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Registration number is required")
    private String regNo;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String emailAddress;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    private String guardianNumber;
    private String fatherName;

    @NotBlank(message = "Program is required")
    private String program;

    @NotBlank(message = "Session is required")
    private String session;

    @NotBlank(message = "Semester is required")
    private String semester;

    @NotBlank(message = "Campus is required")
    private String campus;

    @NotBlank(message = "Class name is required")
    private String className;

    private String nationality;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    private String profilePic;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private Double cgpa;
    private String wifiAccount;
    private String office365Email;
    private String office365Pass;
    private String batch;

    public StudentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGuardianNumber() {
        return guardianNumber;
    }

    public void setGuardianNumber(String guardianNumber) {
        this.guardianNumber = guardianNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}


