package com.xaxage.jobapp.review.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Review {
    private UUID id;
    private UUID companyId;
    private String title;
    private String description;
    private Float rating;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Review(UUID id, UUID companyId, String title, String description, Float rating, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
