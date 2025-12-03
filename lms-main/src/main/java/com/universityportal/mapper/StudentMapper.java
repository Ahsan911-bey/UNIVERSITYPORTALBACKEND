package com.universityportal.mapper;

import com.universityportal.dto.StudentDto;
import com.universityportal.entity.Student;

public class StudentMapper {

    private StudentMapper() {
    }

    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setRegNo(student.getRegNo());
        dto.setEmailAddress(student.getEmailAddress());
        dto.setContactNumber(student.getContactNumber());
        dto.setGuardianNumber(student.getGuardianNumber());
        dto.setFatherName(student.getFatherName());
        dto.setProgram(student.getProgram());
        dto.setSession(student.getSession());
        dto.setSemester(student.getSemester());
        dto.setCampus(student.getCampus());
        dto.setClassName(student.getClassName());
        dto.setNationality(student.getNationality());
        dto.setDob(student.getDob());
        dto.setProfilePic(student.getProfilePic());
        dto.setPassword(student.getPassword());
        dto.setCgpa(student.getCgpa());
        dto.setWifiAccount(student.getWifiAccount());
        dto.setOffice365Email(student.getOffice365Email());
        dto.setOffice365Pass(student.getOffice365Pass());
        dto.setBatch(student.getBatch());
        return dto;
    }

    public static Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        copy(dto, student);
        return student;
    }

    public static void copy(StudentDto dto, Student student) {
        if (dto == null || student == null) {
            return;
        }
        student.setName(dto.getName());
        student.setRegNo(dto.getRegNo());
        student.setEmailAddress(dto.getEmailAddress());
        student.setContactNumber(dto.getContactNumber());
        student.setGuardianNumber(dto.getGuardianNumber());
        student.setFatherName(dto.getFatherName());
        student.setProgram(dto.getProgram());
        student.setSession(dto.getSession());
        student.setSemester(dto.getSemester());
        student.setCampus(dto.getCampus());
        student.setClassName(dto.getClassName());
        student.setNationality(dto.getNationality());
        student.setDob(dto.getDob());
        student.setProfilePic(dto.getProfilePic());
        student.setPassword(dto.getPassword());
        student.setCgpa(dto.getCgpa());
        student.setWifiAccount(dto.getWifiAccount());
        student.setOffice365Email(dto.getOffice365Email());
        student.setOffice365Pass(dto.getOffice365Pass());
        student.setBatch(dto.getBatch());
    }
}


