package com.xaxage.jobapp.job.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.xaxage.jobapp.job.JobMapper;
import com.xaxage.jobapp.job.dto.CreateJobRequest;
import com.xaxage.jobapp.job.dto.JobResponse;
import com.xaxage.jobapp.job.dto.JobsListResponse;
import com.xaxage.jobapp.job.dto.UpdateJobRequest;
import com.xaxage.jobapp.job.entity.Job;
import com.xaxage.jobapp.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobsRepository, JobMapper jobMapper) {
        this.jobRepository = jobsRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public JobsListResponse findAll() {
        List<Job> allJobs = jobRepository.findAll();
        var mappedList = jobMapper.toResponseList(allJobs);
        return new JobsListResponse(mappedList.size(), mappedList);
    }

    @Override
    public JobResponse createJob(CreateJobRequest request) {
        Job job = jobMapper.toEntity(request);
        job.setId(UuidCreator.getTimeOrderedEpoch());
        job.setCreatedAt(Instant.now());
        job.setUpdatedAt(Instant.now());
        Job savedJob = jobRepository.save(job);
        return jobMapper.toResponse(savedJob);
    }

    @Override
    public JobResponse findJobById(UUID id) {
        Job job = jobRepository.findById(id);

        return jobMapper.toResponse(job);
    }

    @Override
    public void deleteJobById(UUID id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found: " + id);
        }

        jobRepository.deleteById(id);
    }

    @Override
    public JobResponse updateJob(UUID jobId, UpdateJobRequest request) {
        Job existingJob = jobRepository.findById(jobId);

        jobMapper.updateEntity(request, existingJob);
        existingJob.setUpdatedAt(Instant.now());

        Job saved = jobRepository.save(existingJob);
        return jobMapper.toResponse(saved);
    }
}
