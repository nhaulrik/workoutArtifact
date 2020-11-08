--liquibase formatted sql

--changeset nch:1604609777547-1
INSERT INTO user (id, birth_day, first_name, gender, last_name) VALUES ('51a649d4-d693-4b69-b039-b5ed0f971ac7', '1991-08-18', 'Nikolaj', 'MALE', 'Haulrik');
INSERT INTO user (id, birth_day, first_name, gender, last_name) VALUES ('1aad7954-9812-421e-9e0a-7437df8b5558', '1992-04-08', 'Rikke', 'FEMALE', 'Langkjær');

