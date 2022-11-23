--liquibase formatted sql

--changeset nch:1613507794839-1
CREATE TABLE split_exercise_set (split_id VARCHAR(255) NOT NULL, name VARCHAR(255) NULL, repetition_maximum INT NULL);

--changeset nch:1613507794839-2
ALTER TABLE split ADD name VARCHAR(255) NULL;

--changeset nch:1613507794839-3
ALTER TABLE split_exercise_set ADD CONSTRAINT FK_split_exercise_set_split_id FOREIGN KEY (split_id) REFERENCES split (id);

--changeset nch:1613507794839-4
ALTER TABLE programme_exercise DROP FOREIGN KEY FK_programme_exercise_id;

--changeset nch:1613507794839-5
ALTER TABLE programme_exercise DROP FOREIGN KEY FK_programme_split_id;

--changeset nch:1613507794839-6
DROP TABLE programme_exercise;

