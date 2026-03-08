package com.xaxage.jobapp.review.dto;

import java.util.UUID;

public class ReviewResponse {
    private UUID id;
    private UUID companyId;
    private String title;
    private String description;
    private Float rating;

    public ReviewResponse(UUID id, UUID companyId, String title, String description, Float rating) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
