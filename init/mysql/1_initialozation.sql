
CREATE DATABASE plantdb;

USE plantdb;

CREATE TABLE plant (
    id	varchar(255),
    available	bit(1) NULL,
    name	varchar(255) NULL,
    PRIMARY KEY (id)
);
