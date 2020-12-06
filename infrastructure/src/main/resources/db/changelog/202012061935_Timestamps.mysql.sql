--liquibase formatted sql

--changeset nch:1607283328599-1
ALTER TABLE exercise ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-2
ALTER TABLE muscle ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-3
ALTER TABLE session ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-4
ALTER TABLE user ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-5
ALTER TABLE workout_exercise ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-6
ALTER TABLE workoutset ADD create_date datetime(6) NULL;

--changeset nch:1607283328599-7
ALTER TABLE exercise ADD modify_date datetime(6) NULL;

--changeset nch:1607283328599-8
ALTER TABLE muscle ADD modify_date datetime(6) NULL;

--changeset nch:1607283328599-9
ALTER TABLE session ADD modify_date datetime(6) NULL;

--changeset nch:1607283328599-10
ALTER TABLE user ADD modify_date datetime(6) NULL;

--changeset nch:1607283328599-11
ALTER TABLE workout_exercise ADD modify_date datetime(6) NULL;

--changeset nch:1607283328599-12
ALTER TABLE workoutset ADD modify_date datetime(6) NULL;

