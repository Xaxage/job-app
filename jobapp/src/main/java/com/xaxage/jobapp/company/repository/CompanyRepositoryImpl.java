package com.xaxage.jobapp.company.repository;

import com.xaxage.jobapp.company.entity.Company;
import com.xaxage.jobapp.generated.tables.records.CompanyRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.xaxage.jobapp.generated.Tables.COMPANY;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    private final DSLContext context;

    public CompanyRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public List<Company> findAll() {
        return context.selectFrom(COMPANY)
                .fetch()
                .map(this::toCompany);
    }

    @Override
    public Company findById(UUID id) {
        CompanyRecord record = context.selectFrom(COMPANY)
                .where(COMPANY.ID.eq(id))
                .fetchOne();
        return record == null ? null : toCompany(record);
    }

    @Override
    public Company save(Company company) {
        context.insertInto(COMPANY)
                .set(COMPANY.ID, company.getId())
                .set(COMPANY.DESCRIPTION, company.getDescription())
                .set(COMPANY.NAME, company.getName())
                .set(COMPANY.CREATED_AT, company.getCreatedAt())
                .set(COMPANY.UPDATED_AT, company.getUpdatedAt())
                .onConflict(COMPANY.ID)
                .doUpdate()
                .set(COMPANY.DESCRIPTION, company.getDescription())
                .set(COMPANY.NAME, company.getName())
                .set(COMPANY.UPDATED_AT, company.getUpdatedAt())
                .execute();
        return company;
    }

    @Override
    public boolean existsById(UUID id) {
        return context.fetchExists(
                context.selectFrom(COMPANY)
                        .where(COMPANY.ID.eq(id))
        );
    }

    @Override
    public void deleteById(UUID id) {
        context.deleteFrom(COMPANY)
                .where(COMPANY.ID.eq(id))
                .execute();
    }

    private Company toCompany(CompanyRecord r) {
        return new Company(
                r.getId(),
                r.getName(),
                r.getDescription(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }
}
