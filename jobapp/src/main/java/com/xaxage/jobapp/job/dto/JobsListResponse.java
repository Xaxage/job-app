package com.xaxage.jobapp.job.dto;

import java.util.List;

public class JobsListResponse {
    private int size;
    private List<JobResponse> items;

    public JobsListResponse(int size, List<JobResponse> items) {
        this.size = size;
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<JobResponse> getItems() {
        return items;
    }

    public void setItems(List<JobResponse> items) {
        this.items = items;
    }
}
