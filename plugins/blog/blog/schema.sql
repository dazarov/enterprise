/*
 * command line:
 * start mySQL - mysql -u root -p
 * run script - source setup.sql;
 * */

CREATE database PhotoBlog;
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
);

select 'Creating ROLE table' AS '';

CREATE TABLE IF NOT EXISTS ROLE (
	ID INT NOT NULL AUTO_INCREMENT,
	ROLE TINYINT,
	PRIMARY KEY (ID)
);

select 'Creating USER_ROLE table' AS '';

CREATE TABLE IF NOT EXISTS USER_ROLE (
	USER_ID INT,
	ROLE_ID INT,
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID)
);

select 'Creating BLOG table' AS '';

CREATE TABLE IF NOT EXISTS BLOG (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NAME VARCHAR(100),
	MODERATED BOOLEAN,
	PRIMARY KEY (ID),
	INDEX(name(10))
);

select 'Creating USER_BLOG table' AS '';

CREATE TABLE IF NOT EXISTS USER_BLOG (
	USER_ID INT,
	BLOG_ID INT,
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID)
);

select 'Creating IMAGE table' AS '';

CREATE TABLE IF NOT EXISTS IMAGE (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	IMAGE LONGBLOB NOT NULL,
	PRIMARY KEY (ID)
);

select 'Creating POST table' AS '';

CREATE TABLE IF NOT EXISTS POST (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	VISITED INT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	SUBJECT VARCHAR(100),
	DESCRIPTION VARCHAR(200),
	BODY TEXT,
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
);

select 'Creating COMMENTABLE table' AS '';

CREATE TABLE IF NOT EXISTS COMMENTABLE (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	VISITED INT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
);

select 'Creating PHOTO table' AS '';

CREATE TABLE IF NOT EXISTS PHOTO (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	VISITED INT,
	LANGUAGE TINYINT,
	BLOG_ID INT,
	LOCATION VARCHAR(255),
	DESCRIPTION VARCHAR(255),
	IMAGE_ID INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGE(ID),
	INDEX(BLOG_ID, CREATION_TIME, STATUS)
);

select 'Creating POST_IMAGE table' AS '';

CREATE TABLE IF NOT EXISTS POST_IMAGE (
	POST_ID INT,
	IMAGE_ID INT,
	FOREIGN KEY (POST_ID) REFERENCES POST(ID),
	FOREIGN KEY (IMAGE_ID) REFERENCES IMAGE(ID)
);

select 'Creating COMMENT table' AS '';

CREATE TABLE IF NOT EXISTS COMMENT (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	VISITED INT,
	LANGUAGE TINYINT,
	USER_ID INT,
	POST_ID INT,
	PHOTO_ID INT,
	COMMENT_ID INT,
	BODY TEXT,
	PRIMARY KEY (ID),
	FOREIGN KEY (POST_ID) REFERENCES POST(ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (PHOTO_ID) REFERENCES PHOTO(ID),
	FOREIGN KEY (COMMENT_ID) REFERENCES COMMENT(ID),
	INDEX(POST_ID),
	INDEX(PHOTO_ID),
	INDEX(COMMENT_ID)
);


select 'Creating BLOCKED_USERS table' AS '';

CREATE TABLE IF NOT EXISTS BLOCKED_USERS (
	BLOG_ID INT,
	USER_ID INT,
	FOREIGN KEY (BLOG_ID) REFERENCES BLOG(ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);

select 'Creating ADVERT table' AS '';

CREATE TABLE IF NOT EXISTS ADVERT (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	PRIMARY KEY (ID)
);

select 'Creating hibernate_sequences table' AS '';

CREATE TABLE IF NOT EXISTS hibernate_sequences (
	sequence_name VARCHAR(255) NOT NULL,
    next_val INT NOT NULL
);