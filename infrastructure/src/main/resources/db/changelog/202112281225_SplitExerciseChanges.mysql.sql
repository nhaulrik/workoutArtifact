--liquibase formatted sql

--changeset nch:1640694349872-1
CREATE TABLE split_exercise (id VARCHAR(255) NOT NULL, split_exercise_number INT NULL, CONSTRAINT split_exercisePK PRIMARY KEY (id));

--changeset nch:1640694349872-2
ALTER TABLE split_exercise_set ADD id VARCHAR(255) NOT NULL;

--changeset nch:1640694349872-3
ALTER TABLE split_exercise_set ADD split_exercise_id VARCHAR(255) NULL;

--changeset nch:1640694349872-4
ALTER TABLE split_exercise_set ADD split_exercise_sets_id VARCHAR(255) NOT NULL;

--changeset nch:1640694349872-5
ALTER TABLE split_exercise_set ADD PRIMARY KEY (id);

--changeset nch:1640694349872-6
ALTER TABLE split_exercise_set ADD CONSTRAINT UK_tfhsg44ivst624380145y0erw UNIQUE (split_exercise_sets_id);

--changeset nch:1640694349872-7
ALTER TABLE split_exercise_set ADD CONSTRAINT FK_split_exercise_set_split_exercise_id FOREIGN KEY (split_exercise_id) REFERENCES split_exercise (id);

--changeset nch:1640694349872-8
ALTER TABLE split_exercise_set ADD CONSTRAINT FKfbwa8ied2gi4l6uvc7mb4eth0 FOREIGN KEY (split_exercise_sets_id) REFERENCES split_exercise_set (id);

