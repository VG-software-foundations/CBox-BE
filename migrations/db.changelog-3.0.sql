--liquibase formatted sql

--changeset Qooriq:1
ALTER TABLE files
    ADD created_at   TIMESTAMP           NOT NULL,
    ADD modified_at  TIMESTAMP           NOT NULL,
    ADD created_by   VARCHAR(128)        NOT NULL,
    ADD modified_by  VARCHAR(128)        NOT NULL;


--changeset Qooriq:2
INSERT INTO files (link, acces_type, created_at, modified_at, created_by, modified_by)
VALUES
    ('./files/8ca8d838-9072-4721-8fcc-1d58c9aa5ce7/aboba', 'PUBLIC', NOW(), NOW(), 'admin', 'admin'),
    ('./files/d686f214-007e-46ce-9557-ee2710b1eec9/aboba', 'PUBLIC', NOW(), NOW(), 'admin', 'admin'),
    ('./files/8ca8d838-9072-4721-8fcc-1d58c9aa5ce7/privet.txt', 'RESTRICTED', NOW(), NOW(), 'admin', 'admin');

--changeset Qooriq:3
INSERT INTO link_restrictions_bypass (file_id, user_id)
VALUES
    (1, '8ca8d838-9072-4721-8fcc-1d58c9aa5ce7'),
    (1, 'd686f214-007e-46ce-9557-ee2710b1eec9')