DROP DATABASE IF EXISTS culture_connection_calendar_test;
CREATE DATABASE culture_connection_calendar_test;
USE culture_connection_calendar_test;


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
    approved BOOLEAN DEFAULT(FALSE),
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
    category_describtion text(300) null
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

delimiter //
create procedure set_known_good_state()
begin

DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT = 1;

INSERT INTO user (first_name, last_name, user_address, user_city, user_state, user_zip, user_phone, user_email) VALUES
	( 'Valma', 'Dredge', '7 Redwing Road', 'Columbia', 'SC', '29215',	'803-872-5270', 'vdredge0@instagram.com' ),
    ( 'Wilt', 'Fuzzens', '68564 Express Pass', 'Dallas', 'TX', '75367',	'214-685-3418', 'wfuzzens1@reverbnation.com' ),
    ( 'Sibylla', 'McRobbie', '821 Crowley Lane', 'Madison', 'WI', '53705',	'608-945-7088', 'smcrobbie2@howstuffworks.com' ),
    ( 'Giusto', 'Paten', '73104 Homewood Park', 'Tulsa', 'OK', '74141',	'918-728-6006', 'gpaten3@google.com.au' ),
    ( 'Andria', 'Teaz', '45 Pepper Wood Hill', 'Mobile', 'AL', '36610',	'251-292-0749', 'ateaz4@sphinn.com' ),
    ( 'Roy', 'Glasser', '33800 Kropf Plaza', 'Oakland', 'CA', '94605',	'510-315-6155', 'rglasser5@ucoz.com' ),
    ( 'Bonni', 'Roose', '69171 Sage Alley', 'Gastonia', 'NC', '28055',	'704-849-2868', 'broose6@bluehost.com' ),
    ( 'Marnie', 'Lints', '56 Everett Alley', 'Oklahoma City', 'OK', '73114',	'405-677-4809', 'broose6@bluehost.com' ),
    ( 'Randee', 'Casterot', '39975 Waxwing Hill', 'Nashville', 'TN', '37220',	'615-262-4004', 'rcasterot8@discuz.net' ),
    ( 'Dorene', 'Iannazzi', '3 Waywood Crossing', 'Watertown', 'MA', '24721',	'978-331-5732', 'diannazzi9@ebay.com' );



end //
delimiter ;

insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');
    
-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, disabled)
    values
    ('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

insert into app_user_role
    values
    (1, 2),
    (2, 1);
    
    
INSERT INTO user (first_name, last_name, user_address, user_city, user_state, user_zip, user_phone, user_email) VALUES
	( 'Valma', 'Dredge', '7 Redwing Road', 'Columbia', 'SC', '29215',	'803-872-5270', 'vdredge0@instagram.com' ),
    ( 'Wilt', 'Fuzzens', '68564 Express Pass', 'Dallas', 'TX', '75367',	'214-685-3418', 'wfuzzens1@reverbnation.com' ),
    ( 'Sibylla', 'McRobbie', '821 Crowley Lane', 'Madison', 'WI', '53705',	'608-945-7088', 'smcrobbie2@howstuffworks.com' ),
    ( 'Giusto', 'Paten', '73104 Homewood Park', 'Tulsa', 'OK', '74141',	'918-728-6006', 'gpaten3@google.com.au' ),
    ( 'Andria', 'Teaz', '45 Pepper Wood Hill', 'Mobile', 'AL', '36610',	'251-292-0749', 'ateaz4@sphinn.com' ),
    ( 'Roy', 'Glasser', '33800 Kropf Plaza', 'Oakland', 'CA', '94605',	'510-315-6155', 'rglasser5@ucoz.com' ),
    ( 'Bonni', 'Roose', '69171 Sage Alley', 'Gastonia', 'NC', '28055',	'704-849-2868', 'broose6@bluehost.com' ),
    ( 'Marnie', 'Lints', '56 Everett Alley', 'Oklahoma City', 'OK', '73114',	'405-677-4809', 'broose6@bluehost.com' ),
    ( 'Randee', 'Casterot', '39975 Waxwing Hill', 'Nashville', 'TN', '37220',	'615-262-4004', 'rcasterot8@discuz.net' ),
    ( 'Dorene', 'Iannazzi', '3 Waywood Crossing', 'Watertown', 'MA', '24721',	'978-331-5732', 'diannazzi9@ebay.com' );
    
INSERT INTO location (address, city, state, zip_code) VALUES 
	('935 Autumn Leaf Plaza',	'Washington',	'DC',	'20299'),
	('07920 Fallview Circle',	'Gadsden',	'AL',	'35905'),
	('4971 Corscot Crossing', 	'Birmingham',	'AL',	'35285'),
	('2478 Redwing Pass',	'Garland', 	'TX',	'75049'),
	('50967 Dorton Junction',	'Los Angeles',	'CA',	'90030'),
	('0663 Buell Crossing',	'Huntington',	'WV',	'25726'),
	('7 Bultman Plaza', 'Oxnard',	'CA',	'93034'),
	('9365 Kim Hill',	'Trenton',	'NJ',	'8608'),
	('596 Jenifer Point',	'Lansing',	'MI',	'48930'),
	('20 Oak Valley Junction',	'Saint Louis',	'MO',	'63143');
