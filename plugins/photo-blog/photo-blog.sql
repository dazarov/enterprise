CREATE database AngularTrainingDB;
create user 'angular_admin'@'localhost' identified by 'pass4test';
grant all on AngularTrainingDB.* to 'angular_admin'@'localhost';

use AngularTrainingDB;

CREATE TABLE IF NOT EXISTS GroceryListTable (id MEDIUMINT NOT NULL AUTO_INCREMENT,
	name varchar(255), description varchar(255), category varchar(255),
	store varchar(255), unit varchar(255), price DECIMAL(10,4), PRIMARY KEY (id));

INSERT INTO GroceryListTable (name, description, category, store, unit, price)
	VALUES ('Golden', '', 'Fruit', 'Safeway', 'lb', 2.20);
	
SELECT * FROM GroceryListTable;
