--liquibase formatted sql

--changeset nch:1611692024246-1
ALTER TABLE programme ADD `description` VARCHAR(255) NULL;

