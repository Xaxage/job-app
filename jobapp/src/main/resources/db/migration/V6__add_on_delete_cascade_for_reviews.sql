ALTER TABLE review
    DROP CONSTRAINT review_company_id_fkey,
    ADD CONSTRAINT review_company_id_fkey
        FOREIGN KEY (company_id)
            REFERENCES company (id)
            ON DELETE CASCADE;