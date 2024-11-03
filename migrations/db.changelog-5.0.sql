--liquibase formatted sql

--changeset Qooriq:1
ALTER TABLE files
    ADD created_at   TIMESTAMP           NOT NULL,
    ADD modified_at  TIMESTAMP           NOT NULL,
    ADD created_by   VARCHAR(128)        NOT NULL,
    ADD modified_by  VARCHAR(128)        NOT NULL;


--changeset Qooriq:2
INSERT INTO files (id, link, acces_type, created_at, modified_at, created_by, modified_by)
VALUES
    (1, './files/aboba', 'PUBLIC', NOW(), NOW(), 'admin', 'admin'),
    (2, './files/privet', 'RESTRICTED', NOW(), NOW(), 'admin', 'admin');

INSERT INTO link_restrictions_bypass (file_id, user_id)
VALUES
    (1, 1)
    (1, 2)