USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `full-stack-myreddit`.`comment`
--

DROP TABLE IF EXISTS `full-stack-myreddit`.`comment`;

CREATE TABLE `full-stack-myreddit`.`comment`(
	`id` BIGINT AUTO_INCREMENT,
    `creation_date` DATETIME NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `post_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `full-stack-myreddit`.`user` (`id`),
    CONSTRAINT `fk_comment_post` FOREIGN KEY (`post_id`) REFERENCES `full-stack-myreddit`.`post` (`id`)
)ENGINE= InnoDB
 AUTO_INCREMENT = 1;
SET foreign_key_checks = 1;
