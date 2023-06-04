-- use `nhn_academy_49`;
DROP TABLE IF EXISTS `mile_stone`;
DROP TABLE IF EXISTS `get_project`;
DROP TABLE IF EXISTS `project_authority`;
DROP TABLE IF EXISTS `comment_mention`;
DROP TABLE IF EXISTS `get_tag`;
DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `task`;
DROP TABLE IF EXISTS `project`;
DROP TABLE IF EXISTS `project_status`;

CREATE TABLE `project_status`
(
    `project_status_id` INT AUTO_INCREMENT primary key,
    `name`              VARCHAR(20) NULL
);

CREATE TABLE `project`
(
    `project_id`        BIGINT AUTO_INCREMENT primary key,
    `project_status_id` INT			  default 2,
    `name`              VARCHAR(100)  NOT NULL,
    `description`       VARCHAR(1000) NULL,
    CONSTRAINT `FK_project_status_TO_project_1`
        FOREIGN KEY (`project_status_id`) REFERENCES `project_status` (`project_status_id`)
);

CREATE TABLE `task`
(
    `task_id`        BIGINT AUTO_INCREMENT primary key,
    `task_writer_id` VARCHAR(30)   NOT NULL,
    `project_id`     BIGINT        NOT NULL,
    `milestone_id`   BIGINT        NOT NULL,
    `title`          VARCHAR(100)  NOT NULL,
    `content`        VARCHAR(3000) NULL,
    CONSTRAINT `FK_project_TO_task_1`
        FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
);

CREATE TABLE `tag`
(
    `tag_id`     BIGINT AUTO_INCREMENT,
    `project_id` BIGINT,
    `name`       VARCHAR(100) NOT NULL,
    PRIMARY KEY (
                 `tag_id`,
                 `project_id`
        ),
    CONSTRAINT `FK_project_TO_tag_1`
        FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
);

CREATE TABLE `milestone`
(
    `milestone_id` BIGINT AUTO_INCREMENT,
    `project_id`    BIGINT,
    `name`          VARCHAR(20) NOT NULL,
    `start_period`  DATE        NULL,
    `end_period`    DATE        NULL,
    `status`        VARCHAR(20) NULL,
    primary key (
                 `milestone_id`,
                 `project_id`
        ),
    CONSTRAINT `FK_project_TO_mile_stone_1`
        FOREIGN KEY (`project_id`)REFERENCES `project` (`project_id`)
);

CREATE TABLE `comment`
(
    `comment_id`        BIGINT AUTO_INCREMENT,
    `task_id`           BIGINT,
    `comment_writer_id` VARCHAR(30)   NOT NULL,
    `comment`           VARCHAR(1000) NOT NULL,
    primary key (
                 `comment_id`,
                 `task_id`
        ),
    CONSTRAINT `FK_task_TO_comment_1`
        FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
);

CREATE TABLE `get_project`
(
    `target_member_id`     VARCHAR(30),
    `project_id`           BIGINT,
    `project_authority_id` INT NOT NULL,
    primary key (
                 `target_member_id`,
                 `project_id`
        ),
    CONSTRAINT `FK_project_TO_get_project_1`
        FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
);

CREATE TABLE `project_authority`
(
    `project_authority_id` INT AUTO_INCREMENT primary key,
    `name`                 VARCHAR(20) NOT NULL
);

CREATE TABLE `get_tag`
(
    `tag_id`  BIGINT NOT NULL,
    `task_id` BIGINT NOT NULL,
    primary key (
                 `task_id`,
                 `tag_id`
        ),
    CONSTRAINT `FK_tag_TO_get_tag_1`
        FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`),
    CONSTRAINT `FK_task_TO_get_tag_1`
        FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
);

CREATE TABLE `comment_mention`
(
    `target_member_id`  VARCHAR(30),
    `comment_id` BIGINT,
    `task_id`    BIGINT,
    primary key (
                 `target_member_id`,
                 `task_id`,
                 `comment_id`
        ),
    CONSTRAINT `FK_comment_TO_comment_mention_1`
        FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`),
    CONSTRAINT `FK_comment_TO_comment_mention_2`
        FOREIGN KEY (`task_id`) REFERENCES `comment` (`task_id`)
);


--project_status 테이블 데이터 추가

MERGE INTO `project_status` VALUES (1, '활성');
MERGE INTO `project_status` VALUES (2, '휴면');
MERGE INTO `project_status` VALUES (3, '종료');

--project_authority 테이블 데이터 추가

MERGE INTO `project_authority`
VALUES (1, 'ADMIN');
MERGE INTO `project_authority`
VALUES (2, 'MEMBER');
