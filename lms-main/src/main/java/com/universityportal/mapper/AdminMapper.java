package com.universityportal.mapper;

import com.universityportal.dto.AdminDto;
import com.universityportal.entity.Admin;

public class AdminMapper {

    private AdminMapper() {}

    public static AdminDto toDto(Admin admin) {
        if (admin == null) return null;
        AdminDto dto = new AdminDto();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setRegNo(admin.getRegNo());
        dto.setEmailAddress(admin.getEmailAddress());
        dto.setContactNumber(admin.getContactNumber());
        dto.setGuardianNumber(admin.getGuardianNumber());
        dto.setFatherName(admin.getFatherName());
        dto.setProgram(admin.getProgram());
        dto.setSession(admin.getSession());
        dto.setSemester(admin.getSemester());
        dto.setCampus(admin.getCampus());
        dto.setClassName(admin.getClassName());
        dto.setNationality(admin.getNationality());
        dto.setDob(admin.getDob());
        dto.setProfilePic(admin.getProfilePic());
        dto.setDesignation(admin.getDesignation());
        dto.setDepartment(admin.getDepartment());
        return dto;
    }
}
