--liquibase formatted sql

--changeset nch:1611311872171-1
ALTER TABLE body_measurement ADD waist DOUBLE NULL;

