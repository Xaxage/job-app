ALTER TABLE job
    DROP CONSTRAINT job_company_id_fkey,
    ADD CONSTRAINT job_company_id_fkey
        FOREIGN KEY (company_id)
            REFERENCES company (id)
            ON DELETE CASCADE;