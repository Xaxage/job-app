package com.xaxage.jobapp.job.repository;

import com.xaxage.jobapp.generated.tables.records.JobRecord;
import com.xaxage.jobapp.job.entity.Job;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.xaxage.jobapp.generated.Tables.JOB;

@Repository
public class JobRepositoryImpl implements JobRepository {
    private final DSLContext context;

    public JobRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public List<Job> findAll() {
        return context.selectFrom(JOB)
                .fetch()
                .map(this::toJob);
    }

    @Override
    public Job save(Job job) {
        context.insertInto(JOB)
                .set(JOB.ID, job.getId())
                .set(JOB.COMPANY_ID, job.getCompanyId())
                .set(JOB.TITLE, job.getTitle())
                .set(JOB.DESCRIPTION, job.getDescription())
                .set(JOB.MIN_SALARY, job.getMinSalary())
                .set(JOB.MAX_SALARY, job.getMaxSalary())
                .set(JOB.LOCATION, job.getLocation())
                .set(JOB.CREATED_AT, job.getCreatedAt())
                .set(JOB.UPDATED_AT, job.getUpdatedAt())
                .onConflict(JOB.ID)
                .doUpdate()
                .set(JOB.COMPANY_ID, job.getCompanyId())
                .set(JOB.TITLE, job.getTitle())
                .set(JOB.DESCRIPTION, job.getDescription())
                .set(JOB.MIN_SALARY, job.getMinSalary())
                .set(JOB.MAX_SALARY, job.getMaxSalary())
                .set(JOB.LOCATION, job.getLocation())
                .set(JOB.UPDATED_AT, job.getUpdatedAt())
                .execute();
        return job;
    }

    @Override
    public Job findById(UUID id) {
        JobRecord record = context.selectFrom(JOB)
                .where(JOB.ID.eq(id))
                .fetchOne();
        return record == null ? null : toJob(record);
    }

    @Override
    public boolean existsById(UUID id) {
        return context.fetchExists(
                context.selectFrom(JOB)
                        .where(JOB.ID.eq(id))
        );
    }

    @Override
    public void deleteById(UUID id) {
        context.deleteFrom(JOB)
                .where(JOB.ID.eq(id))
                .execute();
    }

    @Override
    public List<Job> findByCompanyId(UUID companyId) {
        return context.selectFrom(JOB)
                .where(JOB.COMPANY_ID.eq(companyId))
                .fetch()
                .map(this::toJob);
    }

    private Job toJob(JobRecord r) {
        return new Job(
                r.getId(),
                r.getCompanyId(),
                r.getTitle(),
                r.getDescription(),
                r.getMinSalary(),
                r.getMaxSalary(),
                r.getLocation(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }
}