CREATE database UsersDB;
create user 'testuser'@'localhost' identified by 'password';
grant all on UsersDB.* to 'testuser'@'localhost';

use UsersDB;

CREATE TABLE IF NOT EXISTS SecurityAnswers (uid varchar(255), givenname varchar(255), email varchar(255),
	question1 varchar(255), question2 varchar(255), question3 varchar(255), answer1 varchar(255), answer2 varchar(255), answer3 varchar(255), PRIMARY KEY (uid));
	

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

GRANT EXECUTE ON FUNCTION UsersDB.SSHA to 'testuser'@'localhost';
GRANT EXECUTE ON FUNCTION UsersDB.VERIFY_SSHA to 'testuser'@'localhost';

INSERT INTO SecurityAnswers (uid, givenname, email, question1, question2, question3, answer1, answer2, answer3)
	VALUES ('user','Name' , 'wagtail@mail.ru',
	'What was your childhood nickname? ', 'In what city did you meet your spuse/significant other?', 'What is the name of your favorite childhood friend? ',
	SSHA('nickname'), SSHA('other'), SSHA('friend'));
	
SELECT * FROM SecurityAnswers;