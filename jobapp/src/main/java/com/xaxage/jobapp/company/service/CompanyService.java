package com.xaxage.jobapp.company.service;

import com.xaxage.jobapp.company.dto.CompaniesListResponse;
import com.xaxage.jobapp.company.dto.CompanyResponse;
import com.xaxage.jobapp.company.dto.CreateCompanyRequest;
import com.xaxage.jobapp.company.dto.UpdateCompanyRequest;

import java.util.UUID;

public interface CompanyService {
    CompaniesListResponse findAll();

    CompanyResponse createCompany(CreateCompanyRequest request);

    CompanyResponse findCompanyById(UUID id);

    void deleteCompanyById(UUID id);

    CompanyResponse updateCompany(UUID companyId, UpdateCompanyRequest request);
}
