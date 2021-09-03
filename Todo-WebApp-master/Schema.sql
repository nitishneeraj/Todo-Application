CREATE database todo;

CREATE TABLE `user_details` (
	`user_id` INT(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL UNIQUE,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `todo_details` (
	`user_id` INT(20) NOT NULL,
	`subject` varchar(500) NOT NULL,
	`description` varchar(500),
	`date` DATE NOT NULL,
	`todo_id` INT(20) NOT NULL AUTO_INCREMENT UNIQUE,
	PRIMARY KEY (`todo_id`)
);

ALTER TABLE `todo_details` ADD CONSTRAINT `todo_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`);

