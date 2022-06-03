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


CREATE TABLE app_user (
    app_user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(2048) NOT NULL,
	disabled BIT NOT NULL DEFAULT(0),
    user_id INT NOT NULL DEFAULT(0),
    
    constraint fk_app_user_id
		foreign key(user_id)
        references `user`(user_id)
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

CREATE TABLE event_culture (
culture_id INT PRIMARY KEY AUTO_INCREMENT,
culture_name VARCHAR(50) NOT NULL,
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
    culture_id int null,
    username VARCHAR(50) NOT NULL,
    admin_approve boolean null default(true),
    
    constraint fk_event_category_id
		foreign key (category_id)
        references event_category(category_id),
        
	constraint fk_event_culture_id
		foreign key (culture_id)
        references event_culture(culture_id),
        
	constraint fk_event_location_id
		foreign key (location_id)
        references location(location_id),
        
	constraint fk_event_user_name
		foreign key (username)
        references app_user(username)
);

CREATE TABLE rsvp (
    username  VARCHAR(50) NOT NULL,
    event_id INT NOT NULL,
    approved BOOLEAN NULL default(true),
    constraint fk_rsvp_user_name foreign key(username) references `app_user`(username),
    constraint fk_rsvp_event_id foreign key(event_id) references `event_table`(event_id)
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
INSERT INTO user (first_name, last_name, user_address, user_city, user_state, user_zip, user_phone, user_email) VALUES
	( 'John', 'Smith', '7 Redwing Road', 'Columbia', 'SC', '29215',	'803-872-5270', 'john@smith.com' ),
    ( 'Sally', 'Jones', '68564 Express Pass', 'Dallas', 'TX', '75367',	'214-685-3418', 'sally@jones.com' );
    
    
insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

insert into app_user (username, password_hash, disabled, user_id)
    values
    ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 1),
    ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0, 2);

insert into app_user_role
    values
    (1, 2),
    (2, 1);
    
-- passwords are set to "P@ssw0rd!"
    
    INSERT INTO event_category (category_name, category_description) VALUES
	('Music', "Music category"),
	('Food', "Yum Yum"),
	('Comedy', "lol"),
    ("Play", "A play");
    
INSERT INTO event_culture (culture_name) VALUES
	('Central American'),
	('Asian'),
	('Morroccan');
INSERT INTO event_table (username, event_name, start_date, end_date, capacity, culture_id, category_id) VALUES
	('john@smith.com','Acanthaster planci',	'2022-5-01',	'2022-05-03', '71', 1,3)
