package com.universityportal.dto;

import java.util.ArrayList;
import java.util.List;

public class BatchAssignmentRequest {

    private String batch;
    private List<Long> studentIds = new ArrayList<>();

    public BatchAssignmentRequest() {
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}


