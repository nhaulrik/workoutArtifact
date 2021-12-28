--liquibase formatted sql

--changeset nch:1640692262332-1
CREATE TABLE split_exercise (id VARCHAR(255) NOT NULL, split_exercise_number INT NULL, split_id VARCHAR(255) NULL, CONSTRAINT split_exercisePK PRIMARY KEY (id));

--changeset nch:1640692262332-2
ALTER TABLE split_exercise_set ADD id VARCHAR(255) NOT NULL;

--changeset nch:1640692262332-3
ALTER TABLE split_exercise_set ADD split_exercise_id VARCHAR(255) NULL;

--changeset nch:1640692262332-4
ALTER TABLE split_exercise_set ADD PRIMARY KEY (id);

--changeset nch:1640692262332-5
ALTER TABLE split_exercise_set ADD CONSTRAINT FK_split_exercise_set_split_exercise_id FOREIGN KEY (split_exercise_id) REFERENCES split_exercise (id);

--changeset nch:1640692262332-6
ALTER TABLE split_exercise ADD CONSTRAINT FK_split_exercise_split_id FOREIGN KEY (split_id) REFERENCES split (id);

--changeset nch:1640692262332-7
ALTER TABLE split_exercise_set DROP FOREIGN KEY FK_split_exercise_set_split_id;

--changeset nch:1640692262332-8
ALTER TABLE split_exercise_set DROP COLUMN split_id;

