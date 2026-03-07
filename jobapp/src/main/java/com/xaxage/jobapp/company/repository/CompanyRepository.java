package com.xaxage.jobapp.company.repository;

import com.xaxage.jobapp.company.entity.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository {
    List<Company> findAll();

    Company findById(UUID id);

    Company save(Company company);

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
