--liquibase formatted sql

--changeset nch:1611694595193-1
ALTER TABLE phase ADD name VARCHAR(255) NULL;

