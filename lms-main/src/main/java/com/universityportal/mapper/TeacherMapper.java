package com.universityportal.mapper;

import com.universityportal.dto.TeacherDto;
import com.universityportal.entity.Teacher;

public class TeacherMapper {

    private TeacherMapper() {
    }

    public static TeacherDto toDto(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setRegNo(teacher.getRegNo());
        dto.setEmailAddress(teacher.getEmailAddress());
        dto.setContactNumber(teacher.getContactNumber());
        dto.setGuardianNumber(teacher.getGuardianNumber());
        dto.setFatherName(teacher.getFatherName());
        dto.setProgram(teacher.getProgram());
        dto.setSession(teacher.getSession());
        dto.setSemester(teacher.getSemester());
        dto.setCampus(teacher.getCampus());
        dto.setClassName(teacher.getClassName());
        dto.setNationality(teacher.getNationality());
        dto.setDob(teacher.getDob());
        dto.setProfilePic(teacher.getProfilePic());
        dto.setPassword(teacher.getPassword());
        dto.setQualification(teacher.getQualification());
        dto.setSpecialization(teacher.getSpecialization());
        return dto;
    }

    public static Teacher toEntity(TeacherDto dto) {
        if (dto == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        copy(dto, teacher);
        return teacher;
    }

    public static void copy(TeacherDto dto, Teacher teacher) {
        if (dto == null || teacher == null) {
            return;
        }
        teacher.setName(dto.getName());
        teacher.setRegNo(dto.getRegNo());
        teacher.setEmailAddress(dto.getEmailAddress());
        teacher.setContactNumber(dto.getContactNumber());
        teacher.setGuardianNumber(dto.getGuardianNumber());
        teacher.setFatherName(dto.getFatherName());
        teacher.setProgram(dto.getProgram());
        teacher.setSession(dto.getSession());
        teacher.setSemester(dto.getSemester());
        teacher.setCampus(dto.getCampus());
        teacher.setClassName(dto.getClassName());
        teacher.setNationality(dto.getNationality());
        teacher.setDob(dto.getDob());
        teacher.setProfilePic(dto.getProfilePic());
        teacher.setPassword(dto.getPassword());
        teacher.setQualification(dto.getQualification());
        teacher.setSpecialization(dto.getSpecialization());
    }
}


