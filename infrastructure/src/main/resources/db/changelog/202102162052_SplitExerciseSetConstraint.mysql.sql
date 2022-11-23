--liquibase formatted sql

--changeset nch:1613508761606-1
ALTER TABLE split_exercise_set ADD exercise_entity_id VARCHAR(255) NULL;

--changeset nch:1613508761606-2
ALTER TABLE split_exercise_set ADD CONSTRAINT fk_exercise FOREIGN KEY (exercise_entity_id) REFERENCES exercise (id);

--changeset nch:1613508761606-3
ALTER TABLE split_exercise_set DROP COLUMN name;

