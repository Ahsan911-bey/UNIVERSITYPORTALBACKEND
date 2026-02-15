package com.universityportal.mapper;

import com.universityportal.dto.AttendanceDto;
import com.universityportal.entity.Attendance;

public class AttendanceMapper {

    private AttendanceMapper() {
    }

    public static AttendanceDto toDto(Attendance attendance) {
        if (attendance == null) {
            return null;
        }
        AttendanceDto dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setTotalClasses(attendance.getTotalClasses());
        dto.setPresents(attendance.getPresents());
        dto.setAbsents(attendance.getAbsents());
        dto.setPreviousAttendance(attendance.getPreviousAttendance());
        return dto;
    }

    public static Attendance toEntity(AttendanceDto dto) {
        if (dto == null) {
            return null;
        }
        Attendance attendance = new Attendance();
        copyBasicFields(dto, attendance);
        return attendance;
    }

    public static void copyBasicFields(AttendanceDto dto, Attendance attendance) {
        if (dto == null || attendance == null) {
            return;
        }
        attendance.setTotalClasses(dto.getTotalClasses());
        attendance.setPresents(dto.getPresents());
        attendance.setAbsents(dto.getAbsents());
        attendance.setPreviousAttendance(dto.getPreviousAttendance());
    }
}


