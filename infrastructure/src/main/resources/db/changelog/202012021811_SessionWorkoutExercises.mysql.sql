--liquibase formatted sql

--changeset nch:1606932706010-1
ALTER TABLE workout_exercise ADD session_id VARCHAR(255) NULL;

--changeset nch:1606932706010-2
ALTER TABLE workout_exercise ADD CONSTRAINT FK_workout_exercise_session_id FOREIGN KEY (session_id) REFERENCES session (id);

