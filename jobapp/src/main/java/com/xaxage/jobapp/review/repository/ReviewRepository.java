package com.xaxage.jobapp.review.repository;

import com.xaxage.jobapp.review.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository {
    List<Review> findAllByCompanyId(UUID companyId);

    Review findById(UUID id);

    Review save(Review review);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}