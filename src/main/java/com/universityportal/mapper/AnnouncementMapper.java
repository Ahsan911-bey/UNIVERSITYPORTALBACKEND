package com.universityportal.mapper;

import com.universityportal.dto.AnnouncementDto;
import com.universityportal.entity.Announcement;

public class AnnouncementMapper {

    private AnnouncementMapper() {
    }

    public static AnnouncementDto toDto(Announcement announcement) {
        if (announcement == null) {
            return null;
        }
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(announcement.getId());
        if (announcement.getCourse() != null) {
            dto.setCourseId(announcement.getCourse().getId());
        }
        dto.setMessage(announcement.getMessage());
        dto.setTimestamp(announcement.getTimestamp());
        return dto;
    }

    public static Announcement toEntity(AnnouncementDto dto) {
        if (dto == null) {
            return null;
        }
        Announcement announcement = new Announcement();
        copyBasicFields(dto, announcement);
        return announcement;
    }

    public static void copyBasicFields(AnnouncementDto dto, Announcement announcement) {
        if (dto == null || announcement == null) {
            return;
        }
        announcement.setMessage(dto.getMessage());
        announcement.setTimestamp(dto.getTimestamp());
    }
}


