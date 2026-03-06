CREATE TABLE job
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    min_salary  NUMERIC(19, 2),
    max_salary  NUMERIC(19, 2),
    location    VARCHAR(255),
    created_at  TIMESTAMPTZ  NOT NULL,
    updated_at  TIMESTAMPTZ  NOT NULL
);