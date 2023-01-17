--liquibase formatted sql

--changeset nch:1669814345893-1
ALTER TABLE session ADD calories INT NULL;

--changeset nch:1669814345893-2
ALTER TABLE session ADD duration BIGINT NULL;

--changeset nch:1669814345893-3
ALTER TABLE session ADD heart_rate_average INT NULL;

--changeset nch:1669814345893-4
ALTER TABLE session ADD heart_rate_maximum INT NULL;

--changeset nch:1669814345893-5
ALTER TABLE session ADD sport VARCHAR(255) NULL;

