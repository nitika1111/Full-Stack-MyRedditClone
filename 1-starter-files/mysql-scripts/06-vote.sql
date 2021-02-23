USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `full-stack-myreddit`.`vote`;

CREATE TABLE `full-stack-myreddit`.`vote`(
	`id` BIGINT AUTO_INCREMENT,
    `type` INT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `post_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_vote_user` FOREIGN KEY (`user_id`) REFERENCES `full-stack-myreddit`.`user` (`id`),
    CONSTRAINT `fk_vote_post` FOREIGN KEY (`post_id`) REFERENCES `full-stack-myreddit`.`post` (`id`)
)ENGINE= InnoDB
 AUTO_INCREMENT = 1;

SET foreign_key_checks = 1;