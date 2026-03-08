package com.xaxage.jobapp.review;

import com.xaxage.jobapp.review.dto.CreateReviewRequest;
import com.xaxage.jobapp.review.dto.ReviewResponse;
import com.xaxage.jobapp.review.dto.ReviewsListResponse;
import com.xaxage.jobapp.review.dto.UpdateReviewRequest;
import com.xaxage.jobapp.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<ReviewsListResponse> findAll(@PathVariable UUID companyId) {
        var reviews = reviewService.findAll(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> findById(@PathVariable UUID companyId, @PathVariable UUID reviewId) {
        var review = reviewService.findReviewById(companyId, reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable UUID companyId, @RequestBody CreateReviewRequest request) {
        var createdReview = reviewService.createReview(companyId, request);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@PathVariable UUID companyId, @PathVariable UUID reviewId, @RequestBody UpdateReviewRequest request) {
        var updatedReview = reviewService.updateReview(companyId, reviewId, request);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID companyId, @PathVariable UUID reviewId) {
        reviewService.deleteReviewById(companyId, reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}