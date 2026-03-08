CREATE TABLE review
(
    id          UUID PRIMARY KEY,
    company_id  UUID         NOT NULL REFERENCES company (id),
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    rating      REAL,
    created_at  TIMESTAMPTZ  NOT NULL,
    updated_at  TIMESTAMPTZ  NOT NULL
);
