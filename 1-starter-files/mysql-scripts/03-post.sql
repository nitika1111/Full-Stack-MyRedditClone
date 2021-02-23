USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `full-stack-myreddit`.`post`
--

DROP TABLE IF EXISTS `full-stack-myreddit`.`post`;

CREATE TABLE IF NOT EXISTS `full-stack-myreddit`.`post`(
	`id` BIGINT AUTO_INCREMENT ,
    `creation_date` DATETIME NOT NULL,
    `description` LONGTEXT NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `url` VARCHAR(255) NOT NULL,
    `vote_count` INT NOT NULL,
    `subreddit_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_post_user` FOREIGN KEY (`user_id`) REFERENCES `full-stack-myreddit`.`user` (`id`),
    CONSTRAINT `fk_post_subreddit` FOREIGN KEY (`subreddit_id`) REFERENCES `full-stack-myreddit`.`subreddit` (`id`)
)ENGINE= InnoDB
 AUTO_INCREMENT = 1;

SET foreign_key_checks = 1;
