-- liquibase formatted sql

-- changeset weare4saken:1
CREATE TABLE users(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(60)
);