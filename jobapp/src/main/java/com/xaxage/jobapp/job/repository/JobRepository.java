package com.xaxage.jobapp.job.repository;

import com.xaxage.jobapp.job.entity.Job;

import java.util.List;
import java.util.UUID;

public interface JobRepository {
    List<Job> findAll();

    Job findById(UUID id);

    Job save(Job job);

    boolean existsById(UUID id);

    void deleteById(UUID id);

    List<Job> findByCompanyId(UUID companyId);
}
