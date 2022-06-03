DROP DATABASE IF EXISTS cultural_connections;
CREATE DATABASE cultural_connections;
USE cultural_connections;

CREATE TABLE user (
user_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(35) NOT NULL,
middle_name VARCHAR(50) NULL,
last_name VARCHAR(50) NOT NULL,
user_address VARCHAR(125) NOT NULL,
user_city VARCHAR(50) NOT NULL,
user_zip VARCHAR(12) NOT NULL,
user_phone VARCHAR(12) NOT NULL
);
