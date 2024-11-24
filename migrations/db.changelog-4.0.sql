--liquibase formatted sql

--changeset VictorGalkevich:1
ALTER TABLE users
ADD code INT;