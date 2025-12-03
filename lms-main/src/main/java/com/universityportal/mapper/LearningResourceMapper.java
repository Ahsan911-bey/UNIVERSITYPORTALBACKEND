package com.universityportal.mapper;

import com.universityportal.dto.LearningResourceDto;
import com.universityportal.entity.LearningResource;

public class LearningResourceMapper {

    private LearningResourceMapper() {
    }

    public static LearningResourceDto toDto(LearningResource resource) {
        if (resource == null) {
            return null;
        }
        LearningResourceDto dto = new LearningResourceDto();
        dto.setId(resource.getId());
        if (resource.getCourse() != null) {
            dto.setCourseId(resource.getCourse().getId());
        }
        dto.setTitle(resource.getTitle());
        dto.setFileUrl(resource.getFileUrl());
        return dto;
    }

    public static LearningResource toEntity(LearningResourceDto dto) {
        if (dto == null) {
            return null;
        }
        LearningResource resource = new LearningResource();
        resource.setTitle(dto.getTitle());
        resource.setFileUrl(dto.getFileUrl());
        return resource;
    }
}


