--liquibase formatted sql

--changeset nch:1608989664456-1
CREATE TABLE body_measurement (id VARCHAR(255) NOT NULL, chest DOUBLE NULL, create_date datetime(6) NULL, hip DOUBLE NULL, left_biceps DOUBLE NULL, left_calve DOUBLE NULL, left_forearm DOUBLE NULL, left_thigh DOUBLE NULL, modify_date datetime(6) NULL, right_biceps DOUBLE NULL, right_calve DOUBLE NULL, right_forearm DOUBLE NULL, right_thigh DOUBLE NULL, stomach DOUBLE NULL, weight DOUBLE NULL, user_id VARCHAR(255) NULL, CONSTRAINT body_measurementPK PRIMARY KEY (id));

--changeset nch:1608989664456-2
ALTER TABLE body_measurement ADD CONSTRAINT FK_body_measurement_user_id FOREIGN KEY (user_id) REFERENCES user (id);

