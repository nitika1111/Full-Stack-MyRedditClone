USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `full-stack-myreddit`.`subreddit`
--

DROP TABLE IF EXISTS `full-stack-myreddit`.`subreddit`;

CREATE TABLE `full-stack-myreddit`.`subreddit`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME NOT NULL,
    `description` LONGTEXT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `user_id` BIGINT,
     KEY `fk_userid` (`user_id`),
     CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `full-stack-myreddit`.`user` (`id`),
     PRIMARY KEY (`id`)
)ENGINE= InnoDB
 AUTO_INCREMENT=1;

SET foreign_key_checks = 1;
