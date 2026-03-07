package com.xaxage.jobapp.company.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.xaxage.jobapp.company.CompanyMapper;
import com.xaxage.jobapp.company.dto.CompaniesListResponse;
import com.xaxage.jobapp.company.dto.CompanyResponse;
import com.xaxage.jobapp.company.dto.CreateCompanyRequest;
import com.xaxage.jobapp.company.dto.UpdateCompanyRequest;
import com.xaxage.jobapp.company.entity.Company;
import com.xaxage.jobapp.company.repository.CompanyRepository;
import com.xaxage.jobapp.job.entity.Job;
import com.xaxage.jobapp.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public CompaniesListResponse findAll() {
        List<Company> companies = companyRepository.findAll();
        var mappedList = companyMapper.toResponseList(companies);
        return new CompaniesListResponse(mappedList.size(), mappedList);
    }

    @Override
    public CompanyResponse createCompany(CreateCompanyRequest request) {
        Company company = companyMapper.toEntity(request);
        OffsetDateTime now = OffsetDateTime.now();

        company.setId(UuidCreator.getTimeOrderedEpoch());
        company.setCreatedAt(now);
        company.setUpdatedAt(now);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponse(savedCompany);
    }

    @Override
    public CompanyResponse findCompanyById(UUID id) {
        Company company = companyRepository.findById(id);
        if (company == null) {
            throw new RuntimeException("Company not found: " + id);
        }

        List<Job> jobsByCompanyId = jobRepository.findByCompanyId(id);
        company.setJobs(jobsByCompanyId);

        return companyMapper.toResponse(company);
    }

    @Override
    public void deleteCompanyById(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found: " + id);
        }

        companyRepository.deleteById(id);
    }

    @Override
    public CompanyResponse updateCompany(UUID companyId, UpdateCompanyRequest request) {
        Company company = companyRepository.findById(companyId);
        if (company == null) {
            throw new RuntimeException("Company not found: " + companyId);
        }

        companyMapper.updateEntity(request, company);
        company.setUpdatedAt(OffsetDateTime.now());

        Company saved = companyRepository.save(company);
        return companyMapper.toResponse(saved);
    }
}