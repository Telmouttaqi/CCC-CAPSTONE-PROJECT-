DROP DATABASE IF EXISTS culture_connection_calendar;
CREATE DATABASE culture_connection_calendar;
USE culture_connection_calendar;


CREATE TABLE user (
user_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(35) NOT NULL,
middle_name VARCHAR(50) NULL,
last_name VARCHAR(50) NOT NULL,
user_address VARCHAR(125) NOT NULL,
user_city VARCHAR(50) NOT NULL,
user_state VARCHAR(2) NOT NULL,
user_zip VARCHAR(12) NOT NULL,
user_phone VARCHAR(12) NOT NULL,
user_email VARCHAR(100) NOT NULL
);




CREATE TABLE rsvp (
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    approved BOOLEAN,
    constraint fk_rsvp_user_id foreign key(user_id) references `user`(user_id)
);

CREATE TABLE app_user (
    app_user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(2048) NOT NULL,
	disabled BIT NOT NULL DEFAULT(0)
);

CREATE TABLE app_role (
    app_role_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE app_user_role (
    app_user_id INT NOT NULL,
    app_role_id INT NOT NULL,
    CONSTRAINT pk_app_user_role PRIMARY KEY (app_user_id , app_role_id),
    CONSTRAINT fk_app_user_role_user_id FOREIGN KEY (app_user_id)
        REFERENCES app_user (app_user_id),
    CONSTRAINT fk_app_user_role_role_id FOREIGN KEY (app_role_id)
        REFERENCES app_role (app_role_id)
);

create table location (
	location_id int primary key auto_increment,
	address varchar(100) not null,
	city varchar(25) not null,
	state varchar(25) not null,
	zip_code varchar (7) not null
);

CREATE TABLE event_country (
country_id INT PRIMARY KEY AUTO_INCREMENT,
country_name VARCHAR(50) NOT NULL,
country_flag BLOB NULL
);


create table event_category (
	category_id int primary key auto_increment,
    category_name varchar(50) not null,
    category_description text(300) null
);


create table event_table (
	event_id int primary key auto_increment,
    event_name varchar(100) not null,
    location_id int null,
    start_date date not null,
    end_date date null,
    capacity int null,
    category_id int null,
    country_id int null,
    user_id int not null,
    
    constraint fk_event_category_id
		foreign key (category_id)
        references event_category(category_id),
        
	constraint fk_event_country_id
		foreign key (country_id)
        references event_country(country_id),
        
	constraint fk_event_location_id
		foreign key (location_id)
        references location(location_id),
        
	constraint fk_event_user_id
		foreign key (user_id)
        references user(user_id)
);


create table performances (
performance_id int primary key auto_increment,
name varchar(100) not null,
description varchar(300) not null
);

create table event_performance(
	event_id int not null,
    performance_id int not null,
    
    constraint pk_event_performance_id
        unique (event_id, performance_id),
        
    constraint fk_event_performance_event_id
		foreign key (event_id)
        references event_table(event_id),
        
	constraint fk_event_performance_performance_id
		foreign key (performance_id)
        references performances(performance_id)
);
