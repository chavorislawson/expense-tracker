DROP DATABASE IF EXISTS `expense_tracker`;
CREATE DATABASE `expense_tracker`;
USE `expense_tracker`;

CREATE TABLE `expenses`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(15) NOT NULL,
    `purchaseMethod` VARCHAR(15) NOT NULL,
    `totalPrice` DOUBLE NOT NULL,
    `date` DATE NOT NULL,
    `place` VARCHAR(20) NOT NULL,
    `location` VARCHAR(30) NOT NULL,
    `time` TIME NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `items`(
	`id` BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `expense_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `description` TEXT NOT NULL,
    `category` VARCHAR(15) NOT NULL,
    `price` DOUBLE NOT NULL,
    FOREIGN KEY (expense_id)
		REFERENCES expenses(id)
		ON UPDATE CASCADE ON DELETE CASCADE
);

