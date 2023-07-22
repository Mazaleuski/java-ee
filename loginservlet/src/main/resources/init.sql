DROP SCHEMA IF EXISTS users_db;
CREATE SCHEMA IF NOT EXISTS users_db;
USE
    users_db;

DROP TABLE IF EXISTS users;

CREATE TABLE `users_db`.`users`
(
    `email`    VARCHAR(20) NOT NULL,
    `password` VARCHAR(20) NOT NULL,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE
);
