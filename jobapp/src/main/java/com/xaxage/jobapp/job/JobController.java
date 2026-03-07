package com.xaxage.jobapp.job;

import com.xaxage.jobapp.job.dto.CreateJobRequest;
import com.xaxage.jobapp.job.dto.JobResponse;
import com.xaxage.jobapp.job.dto.JobsListResponse;
import com.xaxage.jobapp.job.dto.UpdateJobRequest;
import com.xaxage.jobapp.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<JobsListResponse> findAll() {
        var jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> findById(@PathVariable UUID id) {
        var job = jobService.findJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody CreateJobRequest request) {
        var createdJob = jobService.createJob(request);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable UUID id, @RequestBody UpdateJobRequest request) {
        var updatedJob = jobService.updateJob(id, request);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id) {
        jobService.deleteJobById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
