package com.xaxage.jobapp.job.service;

import com.xaxage.jobapp.job.dto.CreateJobRequest;
import com.xaxage.jobapp.job.dto.JobResponse;
import com.xaxage.jobapp.job.dto.JobsListResponse;
import com.xaxage.jobapp.job.dto.UpdateJobRequest;

import java.util.UUID;

public interface JobService {
    JobsListResponse findAll();

    JobResponse createJob(CreateJobRequest request);

    JobResponse findJobById(UUID id);

    void deleteJobById(UUID id);

    JobResponse updateJob(UUID jobId, UpdateJobRequest request);
}
