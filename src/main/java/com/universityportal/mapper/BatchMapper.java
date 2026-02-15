package com.universityportal.mapper;

import com.universityportal.dto.BatchDto;
import com.universityportal.entity.Batch;

public class BatchMapper {

    private BatchMapper() {}

    public static BatchDto toDto(Batch batch) {
        if (batch == null) return null;
        BatchDto dto = new BatchDto();
        dto.setId(batch.getId());
        dto.setName(batch.getName());
        return dto;
    }
}
