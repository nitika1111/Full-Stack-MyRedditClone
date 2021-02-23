USE `full-stack-myreddit`;

SET foreign_key_checks = 0;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence`(
	`next_val` BIGINT NOT NULL
)ENGINE= InnoDB;

SET foreign_key_checks = 1;