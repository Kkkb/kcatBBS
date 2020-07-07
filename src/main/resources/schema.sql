DROP DATABASE IF EXISTS ssm;
CREATE DATABASE ssm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ssm;
CREATE TABLE Todo
(
    `id`      INT          NOT NULL AUTO_INCREMENT,
    `content`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ssm`.`weibo` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `ssm`.`topic` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `content` VARCHAR(1000) NOT NULL,
    `userId` INT NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE `ssm`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `note` VARCHAR(45) NOT NULL,
    `role` ENUM('normal', 'admin', 'guest') NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE `ssm`.`topiccomment` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(200) NOT NULL,
    `userId` INT NOT NULL,
    `topicId` INT NOT NULL,
    PRIMARY KEY (`id`));

ALTER TABLE `ssm`.`topiccomment`
    ADD INDEX `fk_topicCommentTopicId_topicId_idx` (`topicId` ASC);


ALTER TABLE `ssm`.`topiccomment`
    ADD CONSTRAINT `fk_topicCommentTopicId_topicId`
        FOREIGN KEY (`topicId`)
            REFERENCES `ssm`.`topic` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `ssm`.`user`
    ADD COLUMN `avatar` VARCHAR(500) NOT NULL AFTER `role`;

ALTER TABLE `ssm`.`user`
    ADD COLUMN `email` VARCHAR(45) NOT NULL AFTER `avatar`;

ALTER TABLE `ssm`.`user`
    ADD COLUMN `createdTime` BIGINT(100) NOT NULL AFTER `email`;

ALTER TABLE `ssm`.`topic`
    ADD INDEX `fk_topicUserId_userId_idx` (`userId` ASC);
;
ALTER TABLE `ssm`.`topic`
    ADD CONSTRAINT `fk_topicUserId_userId`
        FOREIGN KEY (`userId`)
            REFERENCES `ssm`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `ssm`.`topic`
    ADD COLUMN `createdTime` INT NOT NULL AFTER `userId`,
    ADD COLUMN `updatedTime` INT NOT NULL AFTER `createdTime`;

ALTER TABLE `ssm`.`topiccomment`
    ADD COLUMN `createdTime` INT NOT NULL AFTER `topicId`,
    ADD COLUMN `updatedTime` INT NOT NULL AFTER `createdTime`;

ALTER TABLE `ssm`.`topic`
    ADD COLUMN `clickCount` INT NOT NULL AFTER `updatedTime`;

ALTER TABLE `ssm`.`topic`
    ADD COLUMN `type` VARCHAR(100) NOT NULL AFTER `clickCount`;

ALTER TABLE `ssm`.`topic`
    ADD COLUMN `rawContent` VARCHAR(1000) NOT NULL AFTER `type`;
