--liquibase formatted sql

--changeset nch:1606928374626-1
CREATE TABLE workout_exercise (id VARCHAR(255) NOT NULL, created_time datetime(6) NULL, CONSTRAINT workout_exercisePK PRIMARY KEY (id));

--changeset nch:1606928374626-2
ALTER TABLE workoutset ADD created_time datetime(6) NULL;

--changeset nch:1606928374626-3
ALTER TABLE workoutset ADD workout_exercise_id VARCHAR(255) NULL;

--changeset nch:1606928374626-4
ALTER TABLE workoutset ADD CONSTRAINT FK_workoutset_workout_exercise_id FOREIGN KEY (workout_exercise_id) REFERENCES workout_exercise (id);

