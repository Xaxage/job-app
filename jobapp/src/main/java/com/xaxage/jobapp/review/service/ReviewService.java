package com.xaxage.jobapp.review.service;

import com.xaxage.jobapp.review.dto.CreateReviewRequest;
import com.xaxage.jobapp.review.dto.ReviewResponse;
import com.xaxage.jobapp.review.dto.ReviewsListResponse;
import com.xaxage.jobapp.review.dto.UpdateReviewRequest;

import java.util.UUID;

public interface ReviewService {
    ReviewsListResponse findAll(UUID companyId);

    ReviewResponse createReview(UUID companyId, CreateReviewRequest request);

    ReviewResponse findReviewById(UUID companyId, UUID reviewId);

    void deleteReviewById(UUID companyId, UUID reviewId);

    ReviewResponse updateReview(UUID companyId, UUID reviewId, UpdateReviewRequest request);
}
