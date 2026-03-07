package com.xaxage.jobapp.company;

import com.xaxage.jobapp.company.dto.CompanyResponse;
import com.xaxage.jobapp.company.dto.CreateCompanyRequest;
import com.xaxage.jobapp.company.dto.UpdateCompanyRequest;
import com.xaxage.jobapp.company.entity.Company;
import com.xaxage.jobapp.job.JobMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {JobMapper.class})

public interface CompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company toEntity(CreateCompanyRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateCompanyRequest request, @MappingTarget Company company);

    CompanyResponse toResponse(Company company);

    List<CompanyResponse> toResponseList(List<Company> companies);
}