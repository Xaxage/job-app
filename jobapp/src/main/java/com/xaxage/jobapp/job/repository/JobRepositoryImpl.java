package com.xaxage.jobapp.job.repository;

import com.xaxage.jobapp.generated.tables.records.JobRecord;
import com.xaxage.jobapp.job.entity.Job;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import static com.xaxage.jobapp.generated.Tables.JOB;

@Repository
public class JobRepositoryImpl implements JobRepository {
    private final DSLContext dsl;

    public JobRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public List<Job> findAll() {
        return dsl.selectFrom(JOB)
                .fetch()
                .map(this::toJob);
    }

    @Override
    public Job save(Job job) {
        dsl.insertInto(JOB)
                .set(JOB.ID, job.getId())
                .set(JOB.TITLE, job.getTitle())
                .set(JOB.DESCRIPTION, job.getDescription())
                .set(JOB.MIN_SALARY, job.getMinSalary())
                .set(JOB.MAX_SALARY, job.getMaxSalary())
                .set(JOB.LOCATION, job.getLocation())
                .set(JOB.CREATED_AT, job.getCreatedAt().atOffset(ZoneOffset.UTC))
                .set(JOB.UPDATED_AT, job.getUpdatedAt().atOffset(ZoneOffset.UTC))
                .onConflict(JOB.ID)
                .doUpdate()
                .set(JOB.TITLE, job.getTitle())
                .set(JOB.DESCRIPTION, job.getDescription())
                .set(JOB.MIN_SALARY, job.getMinSalary())
                .set(JOB.MAX_SALARY, job.getMaxSalary())
                .set(JOB.LOCATION, job.getLocation())
                .set(JOB.UPDATED_AT, job.getUpdatedAt().atOffset(ZoneOffset.UTC))
                .execute();
        return job;
    }

    @Override
    public Job findById(UUID id) {
        JobRecord record = dsl.selectFrom(JOB)
                .where(JOB.ID.eq(id))
                .fetchOne();
        return record == null ? null : toJob(record);
    }

    @Override
    public boolean existsById(UUID id) {
        return dsl.fetchExists(
                dsl.selectFrom(JOB)
                        .where(JOB.ID.eq(id))
        );
    }

    @Override
    public void deleteById(UUID id) {
        dsl.deleteFrom(JOB)
                .where(JOB.ID.eq(id))
                .execute();
    }

    private Job toJob(JobRecord r) {
        return new Job(
                r.getId(),
                r.getTitle(),
                r.getDescription(),
                r.getMinSalary(),
                r.getMaxSalary(),
                r.getLocation(),
                r.getCreatedAt().toInstant(),
                r.getUpdatedAt().toInstant()
        );
    }
}