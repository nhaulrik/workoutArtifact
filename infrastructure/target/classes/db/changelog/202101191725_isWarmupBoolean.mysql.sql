--liquibase formatted sql

--changeset nch:1611077172847-1
ALTER TABLE workout_exercise ADD is_warmup BIT NULL;

