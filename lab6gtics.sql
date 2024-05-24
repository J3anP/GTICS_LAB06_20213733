-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gestion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestion` DEFAULT CHARACTER SET utf8mb3 ;
USE `gestion` ;

-- -----------------------------------------------------
-- Table `gestion`.`mesas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`mesas` (
  `idmesa` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `capacity` INT NULL DEFAULT NULL,
  `ubicacion` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idmesa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gestion`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`roles` (
  `idrol` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrol`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gestion`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`users` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `roles_idrol` INT NOT NULL,
  PRIMARY KEY (`iduser`, `roles_idrol`),
  INDEX `fk_roles_roles1_idx` (`roles_idrol` ASC) VISIBLE,
  CONSTRAINT `fk_roles_roles1`
    FOREIGN KEY (`roles_idrol`)
    REFERENCES `gestion`.`roles` (`idrol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gestion`.`reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`reservas` (
  `idprestamos` INT NOT NULL,
  `reservation_date` DATE NULL DEFAULT NULL,
  `return_date` DATE NULL DEFAULT NULL,
  `idclient` INT NOT NULL,
  `idmesa` INT NOT NULL,
  PRIMARY KEY (`idprestamos`, `idclient`, `idmesa`),
  INDEX `fk_reservas_roles1_idx` (`idclient` ASC) VISIBLE,
  INDEX `fk_reservas_mesas1_idx` (`idmesa` ASC) VISIBLE,
  CONSTRAINT `fk_reservas_mesas1`
    FOREIGN KEY (`idmesa`)
    REFERENCES `gestion`.`mesas` (`idmesa`),
  CONSTRAINT `fk_reservas_roles1`
    FOREIGN KEY (`idclient`)
    REFERENCES `gestion`.`users` (`iduser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gestion`.`spring_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`spring_session` (
  `PRIMARY_ID` INT NOT NULL,
  `SESSION_ID` CHAR(36) NOT NULL,
  `CREATION_TIME` CHAR(36) NOT NULL,
  `LAST_ACCESS_TIME` BIGINT NOT NULL,
  `MAX_INACTIVE_INTERVAL` INT NOT NULL,
  `EXPIRY_TIME` BIGINT NOT NULL,
  `PRINCIPAL_NAME` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE INDEX `SESSION_ID_UNIQUE` (`SESSION_ID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `gestion`.`spring_session_attributes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestion`.`spring_session_attributes` (
  `ATRIBUTE_NAME` VARCHAR(200) NOT NULL,
  `ATRIBUTE_BYTES` VARCHAR(45) NOT NULL,
  `spring_session_PRIMARY_ID` INT NOT NULL,
  PRIMARY KEY (`ATRIBUTE_NAME`, `spring_session_PRIMARY_ID`),
  INDEX `fk_spring_session_attributes_spring_session1_idx` (`spring_session_PRIMARY_ID` ASC) VISIBLE,
  CONSTRAINT `fk_spring_session_attributes_spring_session1`
    FOREIGN KEY (`spring_session_PRIMARY_ID`)
    REFERENCES `gestion`.`spring_session` (`PRIMARY_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
