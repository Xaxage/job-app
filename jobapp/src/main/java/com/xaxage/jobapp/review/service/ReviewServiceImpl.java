package com.xaxage.jobapp.review.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.xaxage.jobapp.company.repository.CompanyRepository;
import com.xaxage.jobapp.review.ReviewMapper;
import com.xaxage.jobapp.review.dto.CreateReviewRequest;
import com.xaxage.jobapp.review.dto.ReviewResponse;
import com.xaxage.jobapp.review.dto.ReviewsListResponse;
import com.xaxage.jobapp.review.dto.UpdateReviewRequest;
import com.xaxage.jobapp.review.entity.Review;
import com.xaxage.jobapp.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewsListResponse findAll(UUID companyId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        var mappedList = reviewMapper.toResponseList(reviews);

        return new ReviewsListResponse(mappedList.size(), mappedList);
    }

    @Override
    public ReviewResponse createReview(UUID companyId, CreateReviewRequest request) {
        if (!companyRepository.existsById(companyId)) {
            throw new RuntimeException("Company not found: " + companyId);
        }

        Review review = reviewMapper.toEntity(request);
        OffsetDateTime now = OffsetDateTime.now();
        review.setId(UuidCreator.getTimeOrderedEpoch());
        review.setCompanyId(companyId);
        review.setCreatedAt(now);
        review.setUpdatedAt(now);

        Review saved = reviewRepository.save(review);
        return reviewMapper.toResponse(saved);
    }

    @Override
    public ReviewResponse findReviewById(UUID companyId, UUID reviewId) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            throw new RuntimeException("Review not found: " + reviewId);
        }

        return reviewMapper.toResponse(review);
    }

    @Override
    public void deleteReviewById(UUID companyId, UUID reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found: " + reviewId);
        }

        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewResponse updateReview(UUID companyId, UUID reviewId, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            throw new RuntimeException("Review not found: " + reviewId);
        }

        reviewMapper.updateEntity(request, review);
        review.setUpdatedAt(OffsetDateTime.now());

        Review saved = reviewRepository.save(review);
        return reviewMapper.toResponse(saved);
    }
}