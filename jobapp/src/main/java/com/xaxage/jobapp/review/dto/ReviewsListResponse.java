package com.xaxage.jobapp.review.dto;

import java.util.List;

public class ReviewsListResponse {
    private int size;
    private List<ReviewResponse> items;

    public ReviewsListResponse(int size, List<ReviewResponse> items) {
        this.size = size;
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ReviewResponse> getItems() {
        return items;
    }

    public void setItems(List<ReviewResponse> items) {
        this.items = items;
    }
}
