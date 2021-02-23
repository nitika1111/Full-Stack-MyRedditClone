USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;

CREATE TABLE `refresh_token`(
	`id` BIGINT NOT NULL,
    `creation_date` DATETIME,
    `token` VARCHAR(255),
    PRIMARY KEY (`id`)#,
    #CONSTRAINT `fk_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    #CONSTRAINT `fk_subreddit_posts_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
)ENGINE= InnoDB;

SET foreign_key_checks = 1;