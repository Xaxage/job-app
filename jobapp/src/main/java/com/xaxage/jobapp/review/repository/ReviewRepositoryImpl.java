package com.xaxage.jobapp.review.repository;

import com.xaxage.jobapp.generated.tables.records.ReviewRecord;
import com.xaxage.jobapp.review.entity.Review;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.xaxage.jobapp.generated.Tables.REVIEW;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final DSLContext context;

    public ReviewRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public List<Review> findAllByCompanyId(UUID companyId) {
        return context.selectFrom(REVIEW)
                .where(REVIEW.COMPANY_ID.eq(companyId))
                .fetch()
                .map(this::toReview);
    }

    @Override
    public Review findById(UUID id) {
        ReviewRecord record = context.selectFrom(REVIEW)
                .where(REVIEW.ID.eq(id))
                .fetchOne();
        return record == null ? null : toReview(record);
    }

    @Override
    public Review save(Review review) {
        context.insertInto(REVIEW)
                .set(REVIEW.ID, review.getId())
                .set(REVIEW.COMPANY_ID, review.getCompanyId())
                .set(REVIEW.TITLE, review.getTitle())
                .set(REVIEW.DESCRIPTION, review.getDescription())
                .set(REVIEW.RATING, review.getRating())
                .set(REVIEW.CREATED_AT, review.getCreatedAt())
                .set(REVIEW.UPDATED_AT, review.getUpdatedAt())
                .onConflict(REVIEW.ID)
                .doUpdate()
                .set(REVIEW.TITLE, review.getTitle())
                .set(REVIEW.DESCRIPTION, review.getDescription())
                .set(REVIEW.RATING, review.getRating())
                .set(REVIEW.UPDATED_AT, review.getUpdatedAt())
                .execute();
        return review;
    }

    @Override
    public boolean existsById(UUID id) {
        return context.fetchExists(
                context.selectFrom(REVIEW)
                        .where(REVIEW.ID.eq(id))
        );
    }

    @Override
    public void deleteById(UUID id) {
        context.deleteFrom(REVIEW)
                .where(REVIEW.ID.eq(id))
                .execute();
    }

    private Review toReview(ReviewRecord r) {
        return new Review(
                r.getId(),
                r.getCompanyId(),
                r.getTitle(),
                r.getDescription(),
                r.getRating(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }
}