--liquibase formatted sql

--changeset nch:1609163086515-1
ALTER TABLE body_measurement ADD date date NULL;

