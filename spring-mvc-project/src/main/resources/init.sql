DROP SCHEMA IF EXISTS project_shop;
CREATE SCHEMA IF NOT EXISTS project_shop;
USE
project_shop;

DROP TABLE IF EXISTS `project_shop`.`users`;
CREATE TABLE `project_shop`.`users`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NOT NULL,
    `surname`     VARCHAR(45) NOT NULL,
    `birthday`    DATE NULL,
    `email`       VARCHAR(45) NOT NULL,
    `password`    VARCHAR(45) NOT NULL,
    `balance`     INT NULL,
    `address`     VARCHAR(45) NOT NULL,
    `phoneNumber` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    UNIQUE INDEX `surname_UNIQUE` (`surname` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

DROP TABLE IF EXISTS `project_shop`.`categories`;
CREATE TABLE `project_shop`.`categories`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(45) NOT NULL,
    `imagePath` VARCHAR(75) NOT NULL,
    `rating`    INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

DROP TABLE IF EXISTS `project_shop`.`orders`;
CREATE TABLE `project_shop`.`orders`
(
    `id`      INT      NOT NULL AUTO_INCREMENT,
    `date`    DATETIME NOT NULL,
    `userId` INT      NOT NULL,
    `price`   INT      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id` (`id` ASC),
    CONSTRAINT fk_orders_user_id_users_id
        FOREIGN KEY (`userId`)
            REFERENCES `project_shop`.`users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `project_shop`.`products`;
CREATE TABLE `project_shop`.`products`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `price`       INT          NOT NULL,
    `categoryId` INT          NOT NULL,
    `imagePath`   VARCHAR(75)  NOT NULL,
    UNIQUE INDEX `id` (`id` ASC),
    PRIMARY KEY (`id`),
    CONSTRAINT fk_products_category_id_categories_id
        FOREIGN KEY (`categoryId`)
            REFERENCES `project_shop`.`categories` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `project_shop`.`images`;
CREATE TABLE `project_shop`.`images`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `imagePath`   VARCHAR(45) NOT NULL,
    `categoryId` INT NULL,
    `productId`  INT NULL,
    `primary`     INT         NOT NULL,
    UNIQUE INDEX `id` (`id` ASC),
    PRIMARY KEY (`id`),
    CONSTRAINT fk_images_category_id_categories_id
        FOREIGN KEY (`categoryId`)
            REFERENCES `project_shop`.`categories` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_images_product_id_products_id
        FOREIGN KEY (`productId`)
            REFERENCES `project_shop`.`products` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `project_shop`.`orders_products`;
CREATE TABLE `project_shop`.`orders_products`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `orderId`   INT NULL,
    `productId` INT NULL,
    `quantity`   INT NOT NULL DEFAULT 0,
    UNIQUE INDEX `id` (`id` ASC),
    PRIMARY KEY (`id`),
    CONSTRAINT fk_orders_products_order_id_orders_id
        FOREIGN KEY (`orderId`)
            REFERENCES `project_shop`.`orders` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_orders_products_product_id_products_id
        FOREIGN KEY (`productId`)
            REFERENCES `project_shop`.`products` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Мобильные телефоны', 'iphone.jpg', '1');
INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Наушники', 'airpods.jpg', '2');
INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Планшеты', 'ipad.jpg', '1');
INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Моноблоки', 'mac.jpg', '3');
INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Ноутбуки', 'macbook.jpg', '1');
INSERT INTO `project_shop`.`categories` (`name`, `imagePath`, `rating`)
VALUES ('Часы', 'watch.jpg', '4');
INSERT INTO `project_shop`.`products` (`name`, `description`, `price`, `categoryId`, `imagePath`)
VALUES ('Iphone 13', 'Хороший телефон', '800', '1', '13.jpg');
INSERT INTO `project_shop`.`products` (`name`, `description`, `price`, `categoryId`, `imagePath`)
VALUES ('Iphone 14', 'Отличный телефон', '900', '1', '14.jpg');
INSERT INTO `project_shop`.`products` (`name`, `description`, `price`, `categoryId`, `imagePath`)
VALUES ('Iphone 4', 'Создовал Джобс', '500', '1', '4.jpg');
INSERT INTO `project_shop`.`users` (`name`, `surname`, `birthday`, `email`, `password`, `balance`, `address`,
                                    `phoneNumber`)
VALUES ('A', 'B', '2024-10-10', 'aa@aa.aa', 'aa', '100', 'Minsk', '+37529');








