package com.universityportal.mapper;

import com.universityportal.dto.MarksDto;
import com.universityportal.entity.Marks;

public class MarksMapper {

    private MarksMapper() {
    }

    public static MarksDto toDto(Marks marks) {
        if (marks == null) {
            return null;
        }
        MarksDto dto = new MarksDto();
        dto.setId(marks.getId());
        dto.setQuizMarks(marks.getQuizMarks());
        dto.setAssignmentMarks(marks.getAssignmentMarks());
        dto.setMidsMarks(marks.getMidsMarks());
        dto.setFinalMarks(marks.getFinalMarks());
        return dto;
    }

    public static Marks toEntity(MarksDto dto) {
        if (dto == null) {
            return null;
        }
        Marks marks = new Marks();
        copyBasicFields(dto, marks);
        return marks;
    }

    public static void copyBasicFields(MarksDto dto, Marks marks) {
        if (dto == null || marks == null) {
            return;
        }
        marks.setQuizMarks(dto.getQuizMarks());
        marks.setAssignmentMarks(dto.getAssignmentMarks());
        marks.setMidsMarks(dto.getMidsMarks());
        marks.setFinalMarks(dto.getFinalMarks());
    }
}


