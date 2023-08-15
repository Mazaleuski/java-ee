DROP SCHEMA IF EXISTS shop_db;
CREATE SCHEMA IF NOT EXISTS shop_db;
USE
shop_db;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;

CREATE TABLE `shop_db`.`users`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NOT NULL,
    `surname`  VARCHAR(45) NOT NULL,
    `birthday` DATE NULL,
    `email`    VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `balance`  INT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    UNIQUE INDEX `surname_UNIQUE` (`surname` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE `shop_db`.`categories`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `imagePath` VARCHAR(75) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `shop_db`.`products`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `price`       INT          NOT NULL,
    `category_id` INT          NOT NULL,
    `imagePath`   VARCHAR(75)  NOT NULL,
    PRIMARY KEY (`id`)
);


