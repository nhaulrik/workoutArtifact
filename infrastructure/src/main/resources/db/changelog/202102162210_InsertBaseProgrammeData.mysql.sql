--liquibase formatted sql


--changeset nch:1613513408183-1
INSERT INTO programme (id, creation_date, `description`, name) VALUES ('c1ac4d1f-b720-4c91-9a30-3f3334352006', '2021-02-16', 'By Nikolaj Haulrik', 'Custom');
INSERT INTO programme (id, creation_date, `description`, name) VALUES ('d998c3ee-9a27-46af-ae0b-36041ab62ec7', '2021-02-16', 'By Brad Schoenfeld', 'The Max Muscle Plan');

--changeset nch:1613513408183-2
INSERT INTO phase (id, `description`, number, programme_id, name) VALUES ('1b5f81a4-f47c-40f1-bd8d-7486e56376a6', 'Focus on endurance and high volume', 3, 'd998c3ee-9a27-46af-ae0b-36041ab62ec7', 'Metabolic Phase');
INSERT INTO phase (id, `description`, number, programme_id, name) VALUES ('25f9c883-c235-4525-8c04-5e3b5001514d', 'Lets get started', 1, 'd998c3ee-9a27-46af-ae0b-36041ab62ec7', 'Break-In');
INSERT INTO phase (id, `description`, number, programme_id, name) VALUES ('b5a8d95a-ff8d-463e-ac3f-1dbc13b59eb7', 'Focus on heavy sets with few repetitions', 2, 'd998c3ee-9a27-46af-ae0b-36041ab62ec7', 'Strength Phase');
INSERT INTO phase (id, `description`, number, programme_id, name) VALUES ('c23e4df9-1cb6-4ea8-8911-dbf77b705897', 'Focus on hypertrophy', 4, 'd998c3ee-9a27-46af-ae0b-36041ab62ec7', 'Muscle Phase');
