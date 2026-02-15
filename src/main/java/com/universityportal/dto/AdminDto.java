package com.universityportal.dto;

import java.time.LocalDate;

public class AdminDto {

    private Long id;
    private String name;
    private String regNo;
    private String emailAddress;
    private String contactNumber;
    private String guardianNumber;
    private String fatherName;
    private String program;
    private String session;
    private String semester;
    private String campus;
    private String className;
    private String nationality;
    private LocalDate dob;
    private String profilePic;
    private String designation;
    private String department;

    public AdminDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getGuardianNumber() { return guardianNumber; }
    public void setGuardianNumber(String guardianNumber) { this.guardianNumber = guardianNumber; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }

    public String getSession() { return session; }
    public void setSession(String session) { this.session = session; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getProfilePic() { return profilePic; }
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
