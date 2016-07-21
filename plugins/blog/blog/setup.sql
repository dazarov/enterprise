/* command line - mysql -u root -p */

CREATE database PhotoBlog;
create user 'testuser'@'localhost' identified by 'password';
grant all on PhotoBlog.* to 'testuser'@'localhost';

use PhotoBlog;

select 'Creating USER table' AS '';

CREATE TABLE IF NOT EXISTS USER (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NAME VARCHAR(100),
	EMAIL VARCHAR(100),
	PASSWORD VARCHAR(100),
	PRIMARY KEY (ID)
);

select 'Creating POST table' AS '';

CREATE TABLE IF NOT EXISTS POST (
	ID INT NOT NULL AUTO_INCREMENT,
	USER_ID INT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NUMBER_OF_VISITS INT,
	SUBJECT_EN VARCHAR(100),
	SUBJECT_RU VARCHAR(100),
	SHORT_EN VARCHAR(200),
	SHORT_RU VARCHAR(200),
	BODY_EN TEXT,
	BODY_RU TEXT,
	PRIMARY KEY (ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);

select 'Creating PHOTO table' AS '';

CREATE TABLE IF NOT EXISTS PHOTO (
	ID INT NOT NULL AUTO_INCREMENT,
	USER_ID INT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NUMBER_OF_VISITS INT,
	DESCRIPTION_EN VARCHAR(255),
	DESCRIPTION_RU VARCHAR(255),
	IMAGE LONGBLOB NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);

select 'Creating POST_PHOTO table' AS '';

CREATE TABLE IF NOT EXISTS POST_PHOTO (
	POST_ID INT,
	PHOTO_ID INT,
	FOREIGN KEY (POST_ID) REFERENCES POST(ID),
	FOREIGN KEY (PHOTO_ID) REFERENCES PHOTO(ID)
);

select 'Creating COMMENT table' AS '';

CREATE TABLE IF NOT EXISTS COMMENT (
	ID INT NOT NULL AUTO_INCREMENT,
	POST_ID INT,
	PHOTO_ID INT,
	COMMENT_ID INT,
	USER_ID INT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	NUMBER_OF_VISITS INT,
	BODY TEXT,
	PRIMARY KEY (ID),
	FOREIGN KEY (POST_ID) REFERENCES POST(ID),
	FOREIGN KEY (USER_ID) REFERENCES USER(ID),
	FOREIGN KEY (PHOTO_ID) REFERENCES PHOTO(ID),
	FOREIGN KEY (COMMENT_ID) REFERENCES COMMENT(ID)
);

select 'Creating ADVERT table' AS '';

CREATE TABLE IF NOT EXISTS ADVERT (
	ID INT NOT NULL AUTO_INCREMENT,
	CREATION_TIME TIMESTAMP,
	STATUS TINYINT,
	PRIMARY KEY (id)
);

select 'Creating Functions...' AS '';

DROP FUNCTION IF EXISTS SSHA;
DROP FUNCTION IF EXISTS __COMPUTE_SSHA;
DROP FUNCTION IF EXISTS VERIFY_SSHA;

delimiter //
CREATE FUNCTION __COMPUTE_SSHA(secret VARCHAR(64), salt BINARY(8)) RETURNS VARCHAR(128) DETERMINISTIC READS SQL DATA
BEGIN
	SET @hashedValue := SHA1(concat(secret, salt));
	RETURN CONCAT('{SSHA}', TO_BASE64(CONCAT(UNHEX(@hashedValue), salt)));
END//

CREATE FUNCTION SSHA(value VARCHAR(64)) RETURNS VARCHAR(128) DETERMINISTIC READS SQL DATA
BEGIN
	SET @salt := UNHEX(LEFT(MD5(RAND()), 16));
	RETURN __COMPUTE_SSHA(value, @salt);
END//

CREATE FUNCTION VERIFY_SSHA(hash VARCHAR(128), value VARCHAR(64)) RETURNS BOOLEAN DETERMINISTIC READS SQL DATA
BEGIN
	SET @salt := RIGHT(FROM_BASE64(SUBSTRING(hash, 7)), 8);
	RETURN __COMPUTE_SSHA(value, @salt) = hash;
END//

delimiter ;

GRANT EXECUTE ON FUNCTION PhotoBlog.SSHA to 'testuser'@'localhost';
GRANT EXECUTE ON FUNCTION PhotoBlog.VERIFY_SSHA to 'testuser'@'localhost';

/*
INSERT INTO SecurityAnswers (uid, givenname, email, question1, question2, question3, answer1, answer2, answer3)
	VALUES ('user','Name' , 'wagtail@mail.ru',
	'What was your childhood nickname? ', 'In what city did you meet your spuse/significant other?', 'What is the name of your favorite childhood friend? ',
	SSHA('nickname'), SSHA('other'), SSHA('friend'));
	
SELECT * FROM SecurityAnswers;
*/