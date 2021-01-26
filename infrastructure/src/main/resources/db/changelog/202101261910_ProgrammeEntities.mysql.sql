--liquibase formatted sql

--changeset nch:1611688221873-1
CREATE TABLE phase (id VARCHAR(255) NOT NULL, `description` VARCHAR(255) NULL, number INT NULL, programme_id VARCHAR(255) NULL, CONSTRAINT phasePK PRIMARY KEY (id));

--changeset nch:1611688221873-2
CREATE TABLE programme (id VARCHAR(255) NOT NULL, creation_date_time datetime(6) NULL, CONSTRAINT programmePK PRIMARY KEY (id));

--changeset nch:1611688221873-3
CREATE TABLE programme_exercise (id VARCHAR(255) NOT NULL, rpe INT NULL, number INT NULL, repetitions INT NULL, rest INT NULL, set_amount INT NULL, exercise_id VARCHAR(255) NULL, split_id VARCHAR(255) NULL, CONSTRAINT programme_exercisePK PRIMARY KEY (id));

--changeset nch:1611688221873-4
CREATE TABLE split (id VARCHAR(255) NOT NULL, creation_date_time datetime(6) NULL, day_of_week INT NULL, number INT NULL, week INT NULL, phase_id VARCHAR(255) NULL, CONSTRAINT splitPK PRIMARY KEY (id));

--changeset nch:1611688221873-5
ALTER TABLE phase ADD CONSTRAINT FK_phase_programme_id FOREIGN KEY (programme_id) REFERENCES programme (id);

--changeset nch:1611688221873-6
ALTER TABLE programme_exercise ADD CONSTRAINT FK_programme_exercise_id FOREIGN KEY (exercise_id) REFERENCES exercise (id);

--changeset nch:1611688221873-7
ALTER TABLE programme_exercise ADD CONSTRAINT FK_programme_split_id FOREIGN KEY (split_id) REFERENCES split (id);

--changeset nch:1611688221873-8
ALTER TABLE split ADD CONSTRAINT FK_split_phase_id FOREIGN KEY (phase_id) REFERENCES phase (id);

