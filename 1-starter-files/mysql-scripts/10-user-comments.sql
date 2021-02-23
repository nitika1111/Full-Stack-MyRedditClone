USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `user_comments`
--

DROP TABLE IF EXISTS `user_comments`;

CREATE TABLE `user_comments`(
	`user_id` BIGINT NOT NULL,
    `comment_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`,`comment_id`)
    #,CONSTRAINT `fk_subreddit_posts_subreddit_id` FOREIGN KEY (`subreddit_id`) REFERENCES `subreddit` (`id`),
    #CONSTRAINT `fk_subreddit_posts_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
)ENGINE= InnoDB;

SET foreign_key_checks = 1;