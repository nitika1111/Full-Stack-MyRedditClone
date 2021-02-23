-- -----------------------------------------------------
-- Schema full-stack-myreddit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `full-stack-myreddit`;
CREATE SCHEMA `full-stack-myreddit`;
USE `full-stack-myreddit`;

-- Table `full-stack-myreddit`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `full-stack-myreddit`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `enabled` BIT(1) DEFAULT FALSE,
  `username` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;