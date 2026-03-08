package com.xaxage.jobapp.company.dto;

import com.xaxage.jobapp.job.dto.JobResponse;
import com.xaxage.jobapp.review.dto.ReviewResponse;

import java.util.List;
import java.util.UUID;

public class CompanyResponse {
    private UUID id;
    private String name;
    private String description;
    private List<JobResponse> jobs;
    private List<ReviewResponse> reviews;

    public CompanyResponse(UUID id, String name, String description, List<JobResponse> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
        this.reviews = reviews;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JobResponse> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobResponse> jobs) {
        this.jobs = jobs;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }
}
