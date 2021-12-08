-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Distributor
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Distributor` ;

-- -----------------------------------------------------
-- Schema Distributor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Distributor` DEFAULT CHARACTER SET utf8 ;
USE `Distributor` ;

-- -----------------------------------------------------
-- Table `Distributor`.`OfficeCodes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`OfficeCodes` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`OfficeCodes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`SwitchBlocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`SwitchBlocks` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`SwitchBlocks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` CHAR(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`BlockPositions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`BlockPositions` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`BlockPositions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `switchBlockId` INT NOT NULL,
  `position` CHAR(4) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BlockPositions_SwitchBlocks_idx` (`switchBlockId` ASC),
  CONSTRAINT `fk_BlockPositions_SwitchBlocks`
    FOREIGN KEY (`switchBlockId`)
    REFERENCES `Distributor`.`SwitchBlocks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`SigmaLineEquipments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`SigmaLineEquipments` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`SigmaLineEquipments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `blockPositionId` INT NULL,
  `tsw` CHAR(2) NOT NULL,
  `khw` CHAR(2) NOT NULL,
  `phw` CHAR(2) NOT NULL,
  `row` CHAR(1) NOT NULL,
  `column` CHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_SigmaLineEquiplents_BlockPositions_idx` (`blockPositionId` ASC),
  CONSTRAINT `fk_SigmaLineEquiplents_BlockPositions`
    FOREIGN KEY (`blockPositionId`)
    REFERENCES `Distributor`.`BlockPositions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`ELineEquipments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`ELineEquipments` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`ELineEquipments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `blockPositionId` INT NULL,
  `spce` CHAR(2) NOT NULL,
  `hw` CHAR(1) NOT NULL,
  `shw` CHAR(1) NOT NULL,
  `gr` CHAR(2) NOT NULL,
  `sw` CHAR(1) NOT NULL,
  `lvl` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ELineEquipments_BlockPositions_idx` (`blockPositionId` ASC),
  CONSTRAINT `fk_ELineEquipments_BlockPositions`
    FOREIGN KEY (`blockPositionId`)
    REFERENCES `Distributor`.`BlockPositions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`Sites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`Sites` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`Sites` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` CHAR(3) NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`StreetFrames`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`StreetFrames` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`StreetFrames` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` CHAR(1) NOT NULL,
  `siteId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_StreetFrames_Sites_idx` (`siteId` ASC),
  CONSTRAINT `fk_StreetFrames_Sites`
    FOREIGN KEY (`siteId`)
    REFERENCES `Distributor`.`Sites` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`StreetCables`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`StreetCables` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`StreetCables` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` CHAR(1) NOT NULL,
  `streetFrameId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_StreetCables_StreetFrames_idx` (`streetFrameId` ASC),
  CONSTRAINT `fk_StreetCables_StreetFrames`
    FOREIGN KEY (`streetFrameId`)
    REFERENCES `Distributor`.`StreetFrames` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`StreetPairs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`StreetPairs` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`StreetPairs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pair` INT NOT NULL,
  `streetCableId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_StreetPairs_StreetCables_idx` (`streetCableId` ASC),
  CONSTRAINT `fk_StreetPair_StreetCables`
    FOREIGN KEY (`streetCableId`)
    REFERENCES `Distributor`.`StreetCables` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`SigmaELUs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`SigmaELUs` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`SigmaELUs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`SigmaELUEquipments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`SigmaELUEquipments` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`SigmaELUEquipments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `eluId` INT NOT NULL,
  `l3addr` VARCHAR(45) NOT NULL,
  `blockPositionId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_SigmaELUEquipments_SigmaELUs_idx` (`eluId` ASC),
  INDEX `fk_SigmaELUEquipments_BlockPositions_idx` (`blockPositionId` ASC),
  CONSTRAINT `fk_SigmaELUEquipments_SigmaELUs`
    FOREIGN KEY (`eluId`)
    REFERENCES `Distributor`.`SigmaELUs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SigmaELUEquipments_BlockPositions`
    FOREIGN KEY (`blockPositionId`)
    REFERENCES `Distributor`.`BlockPositions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`ZhoneEquipments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`ZhoneEquipments` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`ZhoneEquipments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cable` CHAR(2) NOT NULL,
  `port` CHAR(2) NOT NULL,
  `blockPositionId` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Zhone_BlockPositions_idx` (`blockPositionId` ASC),
  CONSTRAINT `fk_Zhone_BlockPositions`
    FOREIGN KEY (`blockPositionId`)
    REFERENCES `Distributor`.`BlockPositions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Distributor`.`Subscribers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Distributor`.`Subscribers` ;

CREATE TABLE IF NOT EXISTS `Distributor`.`Subscribers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `officeCodeId` INT NULL,
  `number` CHAR(4) NULL,
  `sigmaLineEquipmentId` INT NULL,
  `eLineEquipmentId` INT NULL,
  `sigmaELUEquipmentId` INT NULL,
  `zhoneEquipmentId` INT NULL,
  `streetPairId` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC, `officeCodeId` ASC),
  INDEX `fk_Subscribers_OfficeCodes_idx` (`officeCodeId` ASC),
  INDEX `fk_Subscribers_SigmaLineEquipments_idx` (`sigmaLineEquipmentId` ASC),
  INDEX `fk_Subscribers_ELineEquipments_idx` (`eLineEquipmentId` ASC),
  INDEX `fk_Subscribers_StreetPairs_idx` (`streetPairId` ASC),
  INDEX `fk_Subscribers_SigmaELUEquipments_idx` (`sigmaELUEquipmentId` ASC),
  INDEX `fk_Subscribers_ZhoneEquipments_idx` (`zhoneEquipmentId` ASC),
  CONSTRAINT `fk_Subscribers_OfficeCodes`
    FOREIGN KEY (`officeCodeId`)
    REFERENCES `Distributor`.`OfficeCodes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscribers_SigmaLineEquipments`
    FOREIGN KEY (`sigmaLineEquipmentId`)
    REFERENCES `Distributor`.`SigmaLineEquipments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscribers_ELineEquipments`
    FOREIGN KEY (`eLineEquipmentId`)
    REFERENCES `Distributor`.`ELineEquipments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscribers_StreetPairs`
    FOREIGN KEY (`streetPairId`)
    REFERENCES `Distributor`.`StreetPairs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscribers_SigmaELUEquipments`
    FOREIGN KEY (`sigmaELUEquipmentId`)
    REFERENCES `Distributor`.`SigmaELUEquipments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Subscribers_ZhoneEquipments`
    FOREIGN KEY (`zhoneEquipmentId`)
    REFERENCES `Distributor`.`ZhoneEquipments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
