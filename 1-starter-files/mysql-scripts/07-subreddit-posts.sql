USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `subreddit_posts`
--

DROP TABLE IF EXISTS `subreddit_posts`;

CREATE TABLE `subreddit_posts`(
	`subreddit_id` BIGINT AUTO_INCREMENT ,
    `post_id` BIGINT NOT NULL,
    PRIMARY KEY (`subreddit_id`,`post_id`),
    CONSTRAINT `fk_subreddit_posts_subreddit_id` FOREIGN KEY (`subreddit_id`) REFERENCES `subreddit` (`id`),
    CONSTRAINT `fk_subreddit_posts_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
)ENGINE= InnoDB, AUTO_INCREMENT = 1;

SET foreign_key_checks = 1;