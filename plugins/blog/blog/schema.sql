-- command line:
-- start mySQL - mysql -u root -p 
-- run script - source setup.sql; 

set global character_set_server = utf8;
set session character_set_server = utf8;

set global character_set_database = utf8;
set session character_set_database = utf8;

set global collation_server = utf8_general_ci;
set session collation_server = utf8_general_ci;



CREATE DATABASE PhotoBlog CHARACTER SET utf8 COLLATE utf8_general_ci;

create user 'testUser'@'localhost' identified by 'testPassword';
grant all on PhotoBlog.* to 'testUser'@'localhost';

use PhotoBlog;

select 'Creating USER table' AS '';

CREATE TABLE IF NOT EXISTS USER (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	LOGGED_IN INT,
	NAME VARCHAR(100),
	EMAIL VARCHAR(100),
	PASSWORD VARCHAR(100),
	PRIMARY KEY (ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating ROLE table' AS '';

CREATE TABLE IF NOT EXISTS ROLE (
	ID INT NOT NULL AUTO_INCREMENT,
	ROLE TINYINT,
	PRIMARY KEY (ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating USER_ROLE table' AS '';

CREATE TABLE IF NOT EXISTS USER_ROLE (
	USER_ID INT,
	ROLE_ID INT,
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating BLOG table' AS '';

CREATE TABLE IF NOT EXISTS BLOG (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NAME VARCHAR(100),
	COMMENT_ALLOWANCE TINYINT,
	PRIMARY KEY (ID),
	INDEX(name(10))
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating USER_BLOG table' AS '';

CREATE TABLE IF NOT EXISTS USER_BLOG (
	USER_ID INT,
	BLOG_ID INT,
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating IMAGE table' AS '';

CREATE TABLE IF NOT EXISTS IMAGE (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	IMAGE LONGBLOB NOT NULL,
	PRIMARY KEY (ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating POST table' AS '';

CREATE TABLE IF NOT EXISTS POST (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	COMMENT_ALLOWANCE TINYINT,
	SUBJECT VARCHAR(100),
	DESCRIPTION VARCHAR(1000),
	BODY TEXT(10000),
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating COMMENTABLE table' AS '';

CREATE TABLE IF NOT EXISTS COMMENTABLE (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	COMMENT_ALLOWANCE TINYINT,
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating PHOTO table' AS '';

CREATE TABLE IF NOT EXISTS PHOTO (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	COMMENT_ALLOWANCE TINYINT,
	LOCATION VARCHAR(255),
	DESCRIPTION VARCHAR(255),
	IMAGE_ID INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGE(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating POST_IMAGE table' AS '';

CREATE TABLE IF NOT EXISTS POST_IMAGE (
	POST_ID INT,
	IMAGE_ID INT,
	FOREIGN KEY (POST_ID) REFERENCES POST(ID),
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGE(ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating COMMENT table' AS '';

CREATE TABLE IF NOT EXISTS COMMENT (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	LANGUAGE TINYINT,
	USER_ID INT,
	BLOG_ENTRY_ID INT,
	COMMENT_ALLOWANCE TINYINT,
	BODY TEXT(5000),
	PRIMARY KEY (ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	INDEX(BLOG_ENTRY_ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating BLOCKED_USERS table' AS '';

CREATE TABLE IF NOT EXISTS BLOCKED_USERS (
	BLOG_ID INT,
	USER_ID INT,
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating ADVERT table' AS '';

CREATE TABLE IF NOT EXISTS ADVERT (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	PRIMARY KEY (ID)
)CHARACTER SET utf8 COLLATE utf8_general_ci;

select 'Creating hibernate_sequences table' AS '';

CREATE TABLE IF NOT EXISTS hibernate_sequences (
	sequence_name VARCHAR(255) NOT NULL,
    next_val INT NOT NULL
);