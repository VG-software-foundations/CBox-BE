--liquibase formatted sql

--changeset VictorGalkevich:1
ALTER TABLE users
ADD COLUMN status VARCHAR(8);