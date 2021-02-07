DROP SCHEMA IF EXISTS todoList;
CREATE SCHEMA `todoList`;
USE `todoList`;

CREATE TABLE IF NOT EXISTS `todoList`.`category` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL DEFAULT 'Unknown' UNIQUE,
    PRIMARY KEY (`id`))
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `todoList`.`task`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL DEFAULT 'Unknown',
    `description` VARCHAR(500) NULL     DEFAULT NULL,
    `category_id` BIGINT(20)   NULL     DEFAULT NULL,
    `date`        DATE         NULL     DEFAULT NULL,
    `time`        TIME         NULL     DEFAULT NULL,
    `completed`   BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL)
    AUTO_INCREMENT = 1;