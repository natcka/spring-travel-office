CREATE DATABASE travelOffice;CREATE USER my_new_user WITH PASSWORD 'my_new_password';\c travelOffice;CREATE TABLE customer(id serial PRIMARY KEY,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL;

INSERT INTO book (id, name, surname) VALUES
(1,'Adam','Nowak');

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO my_new_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to my_new_user;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to my_new_user;
