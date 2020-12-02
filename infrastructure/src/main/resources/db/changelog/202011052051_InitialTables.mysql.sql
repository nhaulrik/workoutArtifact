--liquibase formatted sql

--changeset nch:1604609499565-1
CREATE TABLE exercise (id VARCHAR(255) NOT NULL, body_part VARCHAR(255) NULL, is_compound BIT NULL, name VARCHAR(255) NULL, CONSTRAINT exercisePK PRIMARY KEY (id));

--changeset nch:1604609499565-2
CREATE TABLE muscle (id VARCHAR(255) NOT NULL, body_part VARCHAR(255) NULL, name VARCHAR(255) NULL, CONSTRAINT musclePK PRIMARY KEY (id));

--changeset nch:1604609499565-3
CREATE TABLE session (id VARCHAR(255) NOT NULL, creation_date_time datetime(6) NULL, location VARCHAR(255) NULL, programme VARCHAR(255) NULL, split_name VARCHAR(255) NULL, user_id VARCHAR(255) NULL, CONSTRAINT sessionPK PRIMARY KEY (id));

--changeset nch:1604609499565-4
CREATE TABLE user (id VARCHAR(255) NOT NULL, birth_day date NULL, first_name VARCHAR(255) NULL, gender VARCHAR(255) NULL, last_name VARCHAR(255) NULL, CONSTRAINT userPK PRIMARY KEY (id));

--changeset nch:1604609499565-5
CREATE TABLE workout_exercise (id VARCHAR(255) NOT NULL, exercise_number INT NULL, session_id VARCHAR(255) NULL, CONSTRAINT workout_exercisePK PRIMARY KEY (id));

--changeset nch:1604609499565-6
CREATE TABLE workoutset (id VARCHAR(255) NOT NULL, repetition_maximum INT NULL, repetitions INT NULL, set_number INT NULL, single BIT NULL, weight DOUBLE NULL, exercise_id VARCHAR(255) NULL, session_id VARCHAR(255) NULL, workout_exercise_id VARCHAR(255) NULL, CONSTRAINT workoutsetPK PRIMARY KEY (id));

--changeset nch:1604609499565-7
ALTER TABLE session ADD CONSTRAINT FK_session_user_id FOREIGN KEY (user_id) REFERENCES user (id);

--changeset nch:1604609499565-8
ALTER TABLE workout_exercise ADD CONSTRAINT FK_workout_exercise_session_id FOREIGN KEY (session_id) REFERENCES session (id);

--changeset nch:1604609499565-9
ALTER TABLE workoutset ADD CONSTRAINT FK_workoutset_exercise_id FOREIGN KEY (exercise_id) REFERENCES exercise (id);

--changeset nch:1604609499565-10
ALTER TABLE workoutset ADD CONSTRAINT FK_workoutset_session_id FOREIGN KEY (session_id) REFERENCES session (id);

--changeset nch:1604609499565-11
ALTER TABLE workoutset ADD CONSTRAINT FK_workoutset_workout_exercise_id FOREIGN KEY (workout_exercise_id) REFERENCES workout_exercise (id);