DROP SCHEMA IF EXISTS payment_system_db;
CREATE SCHEMA IF NOT EXISTS payment_system_db;
USE
payment_system_db;

DROP TABLE IF EXISTS bank_accounts;
DROP TABLE IF EXISTS merchants;

CREATE TABLE `bank_accounts`
(
    `id`             varchar(60) NOT NULL,
    `merchant_id`    varchar(60) NOT NULL,
    `status`         varchar(10) NOT NULL,
    `account_number` varchar(60) NOT NULL,
    `created_at`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `merchants`
(
    `id`         varchar(60) NOT NULL,
    `name`       varchar(15) NOT NULL,
    `created_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);
