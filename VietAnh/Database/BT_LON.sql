SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `BT_LON` DEFAULT CHARACTER SET utf8 ;
USE `BT_LON` ;

-- -----------------------------------------------------
-- Table `BT_LON`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Account` (
  `AID` VARCHAR(8) NOT NULL,
  `Username` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `FullName` VARCHAR(45) NULL DEFAULT NULL,
  `Birthday` DATE NULL DEFAULT NULL,
  `PhoneNumber` CHAR(13) NULL,
  PRIMARY KEY (`AID`),
  INDEX `AID` (`AID` ASC),
  UNIQUE INDEX `PhoneNumber_UNIQUE` (`PhoneNumber` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Roles` (
  `RLID` VARCHAR(8) NOT NULL,
  `Rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RLID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`AccountAssignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`AccountAssignment` (
  `AID` VARCHAR(8) NOT NULL,
  `RLID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`AID`, `RLID`),
  INDEX `fk_AccountAssignment_2` (`RLID` ASC),
  CONSTRAINT `fk_AccountAssignment_1`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AccountAssignment_2`
    FOREIGN KEY (`RLID`)
    REFERENCES `BT_LON`.`Roles` (`RLID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Restaurant` (
  `ResID` VARCHAR(8) NOT NULL,
  `Resname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ResID`),
  INDEX `ResID` (`ResID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Manage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Manage` (
  `AID` VARCHAR(8) NOT NULL,
  `ResID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`AID`, `ResID`),
  INDEX `fk_Manage_2_idx` (`ResID` ASC),
  CONSTRAINT `fk_Manage_1`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Manage_2`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`Restaurant` (`ResID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Objects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Objects` (
  `OBJID` VARCHAR(8) NOT NULL,
  `Objectname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`OBJID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Operations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Operations` (
  `OPID` VARCHAR(8) NOT NULL,
  `OperationName` VARCHAR(45) NOT NULL,
  `Read` TINYINT(1) NOT NULL,
  `Insert` TINYINT(1) NOT NULL,
  `Update` TINYINT(1) NOT NULL,
  `Delete` TINYINT(1) NOT NULL,
  PRIMARY KEY (`OPID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Provide`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Provide` (
  `ResID` VARCHAR(8) NOT NULL,
  `Foodname` VARCHAR(45) NOT NULL,
  `Cost` DOUBLE NULL DEFAULT NULL,
  `Describe` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ResID`, `Foodname`),
  INDEX `Foodname` (`Foodname` ASC),
  CONSTRAINT `fk_Provide_1`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`Restaurant` (`ResID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`SequenceRestaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`SequenceRestaurant` (
  `Address` VARCHAR(45) NOT NULL,
  `AID` VARCHAR(8) NOT NULL,
  `ResID` VARCHAR(8) NOT NULL,
  `Like` INT NULL DEFAULT 0,
  PRIMARY KEY (`Address`, `AID`, `ResID`),
  INDEX `fk_SequenceRestaurant_1_idx` (`AID` ASC),
  INDEX `fk_SequenceRestaurant_2_idx` (`ResID` ASC),
  CONSTRAINT `fk_SequenceRestaurant_1`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Manage` (`AID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SequenceRestaurant_2`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`Manage` (`ResID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Order` (
  `AID` VARCHAR(8) NOT NULL,
  `ResID` VARCHAR(8) NOT NULL,
  `OrderID` VARCHAR(8) NOT NULL,
  `Foodname` VARCHAR(45) NOT NULL,
  `Time` DATETIME NULL DEFAULT NULL,
  `Quantity` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`AID`, `ResID`, `OrderID`, `Foodname`),
  INDEX `fk_Order_2_idx` (`Foodname` ASC),
  INDEX `fk_Order_3_idx` (`ResID` ASC),
  CONSTRAINT `fk_Order_1`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_2`
    FOREIGN KEY (`Foodname`)
    REFERENCES `BT_LON`.`Provide` (`Foodname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_3`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`SequenceRestaurant` (`ResID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Permissions` (
  `PRMID` VARCHAR(8) NOT NULL,
  `PermissionName` VARCHAR(45) NOT NULL,
  `OBJID` VARCHAR(8) NOT NULL,
  `OPID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`PRMID`, `OBJID`, `OPID`),
  INDEX `fk_Permissions_1` (`OBJID` ASC),
  INDEX `fk_Permissions_2` (`OPID` ASC),
  CONSTRAINT `fk_Permissions_1`
    FOREIGN KEY (`OBJID`)
    REFERENCES `BT_LON`.`Objects` (`OBJID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Permissions_2`
    FOREIGN KEY (`OPID`)
    REFERENCES `BT_LON`.`Operations` (`OPID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`PermissionsAssignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`PermissionsAssignment` (
  `RLID` VARCHAR(8) NOT NULL,
  `PRMID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`RLID`, `PRMID`),
  INDEX `fk_PermissionsAssignment_2` (`PRMID` ASC),
  CONSTRAINT `fk_PermissionsAssignment_1`
    FOREIGN KEY (`RLID`)
    REFERENCES `BT_LON`.`Roles` (`RLID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PermissionsAssignment_2`
    FOREIGN KEY (`PRMID`)
    REFERENCES `BT_LON`.`Permissions` (`PRMID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
