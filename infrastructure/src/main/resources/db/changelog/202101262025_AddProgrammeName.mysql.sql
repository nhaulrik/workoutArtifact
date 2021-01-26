--liquibase formatted sql

--changeset nch:1611692760139-1
ALTER TABLE programme ADD name VARCHAR(255) NULL;

