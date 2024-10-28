--liquibase formatted sql

--changeset VictorGalkevich:1
ALTER TABLE users
ADD COLUMN role VARCHAR(8);