USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `user_subreddits`
--

DROP TABLE IF EXISTS `user_subreddits`;

CREATE TABLE `user_subreddits`(
	`user_id` BIGINT NOT NULL,
    `subreddit_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`,`subreddit_id`)
    #,CONSTRAINT `fk_subreddit_posts_subreddit_id` FOREIGN KEY (`subreddit_id`) REFERENCES `subreddit` (`id`),
    #CONSTRAINT `fk_subreddit_posts_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
)ENGINE= InnoDB;

SET foreign_key_checks = 1;