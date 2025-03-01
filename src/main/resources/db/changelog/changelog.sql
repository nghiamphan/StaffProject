-- liquibase formatted sql
-- changeset pmnghia:1
CREATE TABLE IF NOT EXISTS "public"."staff" (
    "staff_id" VARCHAR(15) NOT NULL,
    "account" VARCHAR(15),
    "full_name" VARCHAR(255),
    "first_name" VARCHAR(15),
    "email" VARCHAR(63),
    "gender" VARCHAR(1),
    "status" VARCHAR(15),
    "birth_date" date,
    "join_date" date,
    "full_time_start" date,
    "leave_date" date,
    "unit" VARCHAR(255),
    "current_job_title" VARCHAR(255),
    "professional_start" date,
    CONSTRAINT "staff_pkey" PRIMARY KEY ("staff_id")
);

-- changeset pmnghia:2
CREATE TABLE IF NOT EXISTS "public"."project" (
    "project_id" VARCHAR(255) NOT NULL,
    "project_name" VARCHAR(255),
    "description" TEXT,
    "client" VARCHAR(255),
    "start_date" date,
    "end_date" date,
    "status" VARCHAR(15),
    "note" TEXT,
    "project_leader" VARCHAR(15),
    CONSTRAINT "project_pkey" PRIMARY KEY ("project_id"),
    CONSTRAINT "project_project_leader_fkey" FOREIGN KEY ("project_leader") REFERENCES "public"."staff" ("staff_id") ON UPDATE RESTRICT ON DELETE RESTRICT
);

-- changeset pmnghia:3
CREATE TABLE IF NOT EXISTS "public"."staff_project" (
    "staff_project_id" INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    "staff_id" VARCHAR(15),
    "project_id" VARCHAR(255),
    "start_date" date,
    "end_date" date,
    "status" VARCHAR(15),
    "note" TEXT,
    CONSTRAINT "staff_project_pkey" PRIMARY KEY ("staff_project_id"),
    CONSTRAINT "staff_project_staff_id_fkey" FOREIGN KEY ("staff_id") REFERENCES "public"."staff" ("staff_id") ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT "staff_project_project_id_fkey" FOREIGN KEY ("project_id") REFERENCES "public"."project" ("project_id") ON UPDATE RESTRICT ON DELETE RESTRICT
);

-- changeset pmnghia:4
-- comment: add dummy data
DELETE FROM staff_project;
DELETE FROM project;
DELETE FROM staff;

INSERT INTO staff (staff_id, full_name, email, gender, birth_date, join_date) 
VALUES
    ('S1', '1 full name', '1@email.com', 'M', '1990-01-10', CURRENT_DATE - 50),
    ('S2', '2 full name', '2@email.com', 'F', '1990-02-20', CURRENT_DATE - 504),
    ('S3', '3 full name', '3@email.com', 'M', '1990-03-20', CURRENT_DATE - 250),
    ('S4', '4 full name', '4@email.com', 'F', '1990-04-20', CURRENT_DATE - 342),
    ('S5', '5 full name', '5@email.com', 'M', '1990-05-20', CURRENT_DATE - 976),
    ('S6', '6 full name', '6@email.com', 'F', '1990-06-20', CURRENT_DATE - 850);

INSERT INTO project
VALUES
    ('P1001', 'Project 1', 'Project 1', 'Client 1', CURRENT_DATE - 10, CURRENT_DATE - 1, 'Status 1', 'Note 1', 'S3'),
    ('P1002', 'Project 2', 'Project 2', 'Client 2', CURRENT_DATE - 20, CURRENT_DATE - 2, 'Status 2', 'Note 2', 'S5'),
    ('P1003', 'Project 3', 'Project 3', 'Client 3', CURRENT_DATE - 30, CURRENT_DATE - 3, 'Status 3', 'Note 3', 'S5'),
    ('P1004', 'Project 4', 'Project 4', 'Client 4', CURRENT_DATE - 40, CURRENT_DATE - 4, 'Status 4', 'Note 4', 'S6'),
    ('P1005', 'Project 5', 'Project 5', 'Client 5', CURRENT_DATE - 50, CURRENT_DATE - 5, 'Status 5', 'Note 5', 'S6'),
    ('P1006', 'Project 6', 'Project 6', 'Client 6', CURRENT_DATE - 60, CURRENT_DATE - 6, 'Status 6', 'Note 6', 'S6');

INSERT INTO staff_project
VALUES
    (101, 'S1', 'P1001', CURRENT_DATE - 20, CURRENT_DATE - 3, 'Status 101', 'Note 101'),
    (102, 'S1', 'P1002', CURRENT_DATE - 22, CURRENT_DATE - 3, 'Status 102', 'Note 102'),
    (103, 'S1', 'P1003', CURRENT_DATE - 40, CURRENT_DATE - 4, 'Status 103', 'Note 103'),
    (104, 'S2', 'P1001', CURRENT_DATE - 50, CURRENT_DATE - 3, 'Status 104', 'Note 104'),
    (105, 'S3', 'P1001', CURRENT_DATE - 20, CURRENT_DATE - 1, 'Status 105', 'Note 105'),
    (106, 'S3', 'P1002', CURRENT_DATE - 40, CURRENT_DATE - 3, 'Status 106', 'Note 106'),
    (107, 'S4', 'P1003', CURRENT_DATE - 20, CURRENT_DATE - 0, 'Status 107', 'Note 107'),
    (108, 'S5', 'P1002', CURRENT_DATE - 40, CURRENT_DATE - 3, 'Status 108', 'Note 108'),
    (109, 'S5', 'P1003', CURRENT_DATE - 30, CURRENT_DATE - 4, 'Status 109', 'Note 109'),
    (110, 'S6', 'P1004', CURRENT_DATE - 20, CURRENT_DATE - 8, 'Status 110', 'Note 110'),
    (111, 'S6', 'P1005', CURRENT_DATE - 60, CURRENT_DATE - 9, 'Status 111', 'Note 111'),
    (112, 'S6', 'P1006', CURRENT_DATE - 20, CURRENT_DATE - 7, 'Status 112', 'Note 112');