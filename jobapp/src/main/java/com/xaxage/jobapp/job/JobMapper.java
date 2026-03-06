package com.xaxage.jobapp.job;

import com.xaxage.jobapp.job.dto.CreateJobRequest;
import com.xaxage.jobapp.job.dto.JobResponse;
import com.xaxage.jobapp.job.dto.UpdateJobRequest;
import com.xaxage.jobapp.job.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface JobMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Job toEntity(CreateJobRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateJobRequest request, @MappingTarget Job job);

    JobResponse toResponse(Job job);

    List<JobResponse> toResponseList(List<Job> jobs);
}