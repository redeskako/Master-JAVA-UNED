CREATE DATABASE IF NOT EXISTS Seguridad;

USE Seguridad;

CREATE TABLE users(
	user_name VARCHAR(15) NOT NULL PRIMARY KEY,
	user_pass VARCHAR(32) NOT NULL
);

CREATE TABLE user_roles(
	user_name VARCHAR(15) NOT NULL REFERENCES users(user_name),
	role_name VARCHAR(15) NOT NULL,
	PRIMARY KEY (user_name,role_name)
);