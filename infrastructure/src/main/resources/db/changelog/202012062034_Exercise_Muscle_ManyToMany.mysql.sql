--liquibase formatted sql

--changeset nch:1607286881787-1
CREATE TABLE exercise_muscle (fk_exercise VARCHAR(255) NOT NULL, fk_muscle VARCHAR(255) NOT NULL);

--changeset nch:1607286881787-2
ALTER TABLE exercise_muscle ADD CONSTRAINT FK_muscle_exercise_fk_exercise FOREIGN KEY (fk_exercise) REFERENCES exercise (id);

--changeset nch:1607286881787-3
ALTER TABLE exercise_muscle ADD CONSTRAINT FK_muscle_exercise_fk_muscle FOREIGN KEY (fk_muscle) REFERENCES muscle (id);

