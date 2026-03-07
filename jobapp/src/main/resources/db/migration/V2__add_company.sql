CREATE TABLE company
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  TIMESTAMPTZ  NOT NULL,
    updated_at  TIMESTAMPTZ  NOT NULL
);

ALTER TABLE job
    ADD COLUMN company_id UUID NOT NULL REFERENCES company (id);