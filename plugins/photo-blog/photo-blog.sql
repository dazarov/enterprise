CREATE database PhotoBlogDB;
create user 'blog_admin'@'localhost' identified by '2b4uTml';
grant all on PhotoBlogDB.* to 'blog_admin'@'localhost';

use PhotoBlogDB;

CREATE TABLE IF NOT EXISTS PostTable (id MEDIUMINT NOT NULL AUTO_INCREMENT, createdDate datetime DEFAULT(getDate()),
	location varchar(255), visited bigint, subject_ru varchar(255), subject_en varchar(255),
	body_ru varchar(3000), body_en varchar(3000), PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS PhotoTable (id MEDIUMINT NOT NULL AUTO_INCREMENT, createdDate datetime DEFAULT(getDate()),
	location varchar(255), visited bigint, name_ru varchar(255), name_en varchar(255),
	description_ru varchar(255), description_en varchar(255),
	photo image, PRIMARY KEY (id));
	
CREATE TABLE IF NOT EXISTS AdvertTable (id MEDIUMINT NOT NULL AUTO_INCREMENT,
	place_id tinylint, name_ru varchar(255), name_eng varchar(255),
	body_ru varchar(255), body_eng varchar(255), PRIMARY KEY (id));

