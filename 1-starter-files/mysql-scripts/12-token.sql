USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `full-stack-myreddit`.`token`;

CREATE TABLE IF NOT EXISTS `full-stack-myreddit`.`token`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `expiry_date` DATETIME DEFAULT NULL ,
    `token` VARCHAR(255) DEFAULT NULL,
    `user_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    #CONSTRAINT `fk_subreddit_posts_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
)ENGINE= InnoDB
 AUTO_INCREMENT = 1;

SET foreign_key_checks = 1;