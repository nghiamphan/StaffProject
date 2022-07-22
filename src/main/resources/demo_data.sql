-- CREATE TABLE IF NOT EXISTS public.staff
-- (
--     staff_id character varying(15) COLLATE pg_catalog."default" NOT NULL,
--     account character varying(15) COLLATE pg_catalog."default",
--     full_name character varying(255) COLLATE pg_catalog."default",
--     first_name character varying(15) COLLATE pg_catalog."default",
--     email character varying(63) COLLATE pg_catalog."default",
--     gender character varying(1) COLLATE pg_catalog."default",
--     status character varying(15) COLLATE pg_catalog."default",
--     birth_date LocalDate,
--     join_date LocalDate,
--     full_time_start LocalDate,
--     leave_date LocalDate,
--     unit character varying(255) COLLATE pg_catalog."default",
--     current_job_title character varying(255) COLLATE pg_catalog."default",
--     professional_start LocalDate,
--     CONSTRAINT staff_pkey PRIMARY KEY (staff_id)
-- )

-- TABLESPACE pg_default;

-- ALTER TABLE IF EXISTS public.staff
--     OWNER to postgres;

-- CREATE TABLE IF NOT EXISTS public.project
-- (
--     project_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     project_name character varying(255) COLLATE pg_catalog."default",
--     description text COLLATE pg_catalog."default",
--     client character varying(255) COLLATE pg_catalog."default",
--     start_date LocalDate,
--     end_date LocalDate,
--     status character varying(15) COLLATE pg_catalog."default",
--     note text COLLATE pg_catalog."default",
--     project_leader character varying(15) COLLATE pg_catalog."default",
--     CONSTRAINT project_pkey PRIMARY KEY (project_id),
--     CONSTRAINT project_project_leader_fkey FOREIGN KEY (project_leader)
--         REFERENCES public.staff (staff_id) MATCH SIMPLE
--         ON UPDATE RESTRICT
--         ON DELETE RESTRICT
--         NOT VALID
-- )

-- TABLESPACE pg_default;

-- ALTER TABLE IF EXISTS public.project
--     OWNER to postgres;

-- CREATE TABLE IF NOT EXISTS public.staff_project
-- (
--     staff_project_id integer NOT NULL,
--     staff_id character varying(15) COLLATE pg_catalog."default",
--     project_id character varying(255) COLLATE pg_catalog."default",
--     start_date LocalDate,
--     end_date LocalDate,
--     status character varying(15) COLLATE pg_catalog."default",
--     note text COLLATE pg_catalog."default",
--     CONSTRAINT staff_project_pkey PRIMARY KEY (staff_project_id),
--     CONSTRAINT staff_project_project_id_fkey FOREIGN KEY (project_id)
--         REFERENCES public.project (project_id) MATCH SIMPLE
--         ON UPDATE RESTRICT
--         ON DELETE RESTRICT,
--     CONSTRAINT staff_project_staff_id_fkey FOREIGN KEY (staff_id)
--         REFERENCES public.staff (staff_id) MATCH SIMPLE
--         ON UPDATE RESTRICT
--         ON DELETE RESTRICT
-- )

-- TABLESPACE pg_default;

-- ALTER TABLE IF EXISTS public.staff_project
--     OWNER to postgres;

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

SELECT  *
FROM public.staff
ORDER BY staff_id ASC;

INSERT INTO project
VALUES
    ('P1001', 'Project 1', 'Project 1', 'Client 1', CURRENT_DATE - 10, CURRENT_DATE - 1, 'Status 1', 'Note 1', 'S3'),
    ('P1002', 'Project 2', 'Project 2', 'Client 2', CURRENT_DATE - 20, CURRENT_DATE - 2, 'Status 2', 'Note 2', 'S5'),
    ('P1003', 'Project 3', 'Project 3', 'Client 3', CURRENT_DATE - 30, CURRENT_DATE - 3, 'Status 3', 'Note 3', 'S5'),
    ('P1004', 'Project 4', 'Project 4', 'Client 4', CURRENT_DATE - 40, CURRENT_DATE - 4, 'Status 4', 'Note 4', 'S6'),
    ('P1005', 'Project 5', 'Project 5', 'Client 5', CURRENT_DATE - 50, CURRENT_DATE - 5, 'Status 5', 'Note 5', 'S6'),
    ('P1006', 'Project 6', 'Project 6', 'Client 6', CURRENT_DATE - 60, CURRENT_DATE - 6, 'Status 6', 'Note 6', 'S6');

SELECT *
FROM project
ORDER by project_id ASC;

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

SELECT *
FROM staff_project
ORDER by staff_project_id ASC;   