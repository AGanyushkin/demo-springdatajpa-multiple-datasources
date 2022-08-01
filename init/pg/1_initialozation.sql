
-- CREATE DATABASE "plantdb"
--     WITH
--     OWNER = penguin
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'en_US.utf8'
--     LC_CTYPE = 'en_US.utf8'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;

CREATE SCHEMA IF NOT EXISTS images
    AUTHORIZATION penguin;

CREATE SCHEMA IF NOT EXISTS text
    AUTHORIZATION penguin;


CREATE TABLE IF NOT EXISTS public.plant
(
    id character varying(255) NOT NULL,
    available boolean,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT plant_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS public.plant
    OWNER to penguin;


CREATE TABLE IF NOT EXISTS text.plant_text_chunk
(
    id character varying(255) NOT NULL,
    text text COLLATE pg_catalog."default",
    plant_id character varying(255),
    CONSTRAINT plant_text_chunk_pkey PRIMARY KEY (id),
    CONSTRAINT fk546g8ds2nasekfojdq8m83sjp FOREIGN KEY (plant_id)
    REFERENCES public.plant (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
TABLESPACE pg_default;
ALTER TABLE IF EXISTS text.plant_text_chunk
    OWNER to penguin;

CREATE TABLE IF NOT EXISTS images.plant_image
(
    id character varying(255) NOT NULL,
    url character varying(255) COLLATE pg_catalog."default",
    plant_id character varying(255),
    CONSTRAINT plant_image_pkey PRIMARY KEY (id),
    CONSTRAINT fkna7jbtxqelkxpc3ifx6m3yrr3 FOREIGN KEY (plant_id)
    REFERENCES public.plant (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS images.plant_image
    OWNER to penguin;
