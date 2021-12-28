--liquibase formatted sql

--changeset nch:1611691616562-1
ALTER TABLE programme ADD creation_date date NULL;

--changeset nch:1611691616562-2
ALTER TABLE programme DROP COLUMN creation_date_time;

