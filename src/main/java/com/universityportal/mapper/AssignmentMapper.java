package com.universityportal.mapper;

import com.universityportal.dto.AssignmentDto;
import com.universityportal.entity.Assignment;

public class AssignmentMapper {

    private AssignmentMapper() {
    }

    public static AssignmentDto toDto(Assignment assignment) {
        if (assignment == null) {
            return null;
        }
        AssignmentDto dto = new AssignmentDto();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        if (assignment.getTeacher() != null) {
            dto.setTeacherId(assignment.getTeacher().getId());
        }
        if (assignment.getCourse() != null) {
            dto.setCourseId(assignment.getCourse().getId());
        }
        dto.setFileUrl(assignment.getFileUrl());
        return dto;
    }

    public static Assignment toEntity(AssignmentDto dto) {
        if (dto == null) {
            return null;
        }
        Assignment assignment = new Assignment();
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        return assignment;
    }

    public static void copyBasicFields(AssignmentDto dto, Assignment assignment) {
        if (dto == null || assignment == null) {
            return;
        }
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
    }
}


