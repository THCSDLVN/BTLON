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
  `Sex` VARCHAR(6) NULL,
  `Status` INT NULL DEFAULT 0,
  PRIMARY KEY (`AID`),
  INDEX `AIDIndex` (`AID` ASC),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC),
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
  INDEX `AIDIndex` (`AID` ASC),
  INDEX `RLIDIndex` (`RLID` ASC),
  CONSTRAINT `fk_AID_AccountAssignment`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`),
  CONSTRAINT `fk_RLID_AccountAssignment`
    FOREIGN KEY (`RLID`)
    REFERENCES `BT_LON`.`Roles` (`RLID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Restaurant` (
  `ResID` VARCHAR(8) NOT NULL,
  `Resname` VARCHAR(45) NULL DEFAULT NULL,
  `AID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`ResID`, `AID`),
  INDEX `ResIDIndex` (`ResID` ASC),
  INDEX `AIDIndex` (`AID` ASC),
  CONSTRAINT `fk_AID_Restaurant`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------
-- Table `BT_LON`.FoodSet
-- ----------------------------------------------------

CREATE TABLE IF NOT EXISTS `BT_LON`.`FoodSet` (
  `Foodname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Foodname`),
  INDEX `FoodnameIndex`(`Foodname` ASC)
  )
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
  `ReadOp` TINYINT(1) NOT NULL,
  `InsertOp` TINYINT(1) NOT NULL,
  `UpdateOp` TINYINT(1) NOT NULL,
  `DeleteOp` TINYINT(1) NOT NULL,
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
  `DescribeFood` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ResID`, `Foodname`),
  INDEX `FoodnameIndex` (`Foodname` ASC),
  INDEX `ResIDIndex` (`ResID` ASC),
  CONSTRAINT `fk_Foodname_Provide`
    FOREIGN KEY (`Foodname`)
    REFERENCES `BT_LON`.`FoodSet` (`Foodname`),
  CONSTRAINT `fk_ResID_Provide`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`Restaurant` (`ResID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`SequenceRestaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`SequenceRestaurant` (
  `Address` VARCHAR(45) NOT NULL,
  `ResID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`Address`, `ResID`),
  INDEX `ResIDIndex` (`ResID` ASC),
  INDEX `AddressIndex` (`Address` ASC),
  CONSTRAINT `fk_ResID_SequenceRestaurant`
    FOREIGN KEY (`ResID`)
    REFERENCES `BT_LON`.`Restaurant` (`ResID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`Reservation` (
  `AID` VARCHAR(8) NOT NULL,
  `ResAddress` VARCHAR(45) NOT NULL,
  `Foodname` VARCHAR(45) NOT NULL,
  `Time` DATETIME NULL DEFAULT NULL,
  `Quantity` MEDIUMTEXT NULL DEFAULT NULL,
  `StatusReser` VARCHAR(10) NULL, -- LOCK,UPDATING,DELETED,ACCEPTED
  `Cost` DOUBLE NULL,
  KEY(`Time`),
  PRIMARY KEY (`AID`, `ResAddress`, `Foodname`, `Time`),
  INDEX `ResAddressIndex` (`ResAddress` ASC),
  INDEX `AIDIndex` (`AID` ASC),
  INDEX `FoodnameIndex` (`Foodname` ASC),
  INDEX `TimeIndex` (`Time` ASC),
  CONSTRAINT `fk_ResAddress_Reservation`
    FOREIGN KEY (`ResAddress`)
    REFERENCES `BT_LON`.`SequenceRestaurant` (`Address`),
  CONSTRAINT `fk_AID_Reservation`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Account` (`AID`),
  CONSTRAINT `fk_Foodname_Reservation`
    FOREIGN KEY (`Foodname`)
    REFERENCES `BT_LON`.`FoodSet` (`Foodname`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `BT_LON`.`ServedOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`ServedOrder` (
  `AID` VARCHAR(8) NOT NULL,
  `ResAddress` VARCHAR(45) NOT NULL,
  `Foodname` VARCHAR(45) NOT NULL,
  `Time` DATETIME NULL DEFAULT NULL,
  `Feedback` VARCHAR(45) NULL,
  PRIMARY KEY (`AID`, `ResAddress`, `Foodname`, `Time`),
  INDEX `AIDIndex` (`AID` ASC),
  INDEX `ResAddressIndex` (`ResAddress` ASC),
  INDEX `FoodnameIndex` (`Foodname` ASC),
  INDEX `TimeIndex` (`Time` ASC),
  CONSTRAINT `fk_AID_ServedOrder`
    FOREIGN KEY (`AID`)
    REFERENCES `BT_LON`.`Reservation` (`AID`),
  CONSTRAINT `fk_ResAddress_ServedOrder`
    FOREIGN KEY (`ResAddress`)
    REFERENCES `BT_LON`.`Reservation` (`ResAddress`),
  CONSTRAINT `fk_Foodname_ServedOrder`
    FOREIGN KEY (`Foodname`)
    REFERENCES `BT_LON`.`FoodSet` (`Foodname`),
  CONSTRAINT `fk_Time_ServedOrder`
    FOREIGN KEY (`Time`)
    REFERENCES `BT_LON`.`Reservation` (`Time`))
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
  INDEX `PRMIDIndex` (`PRMID` ASC),
  INDEX `OPIDIndex` (`OPID` ASC),
  INDEX `OBJIDIndex` (`OBJID` ASC),
  CONSTRAINT `fk_OBJID_Permissions`
    FOREIGN KEY (`OBJID`)
    REFERENCES `BT_LON`.`Objects` (`OBJID`),
  CONSTRAINT `fk_OPID_Permissions`
    FOREIGN KEY (`OPID`)
    REFERENCES `BT_LON`.`Operations` (`OPID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `BT_LON`.`PermissionsAssignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BT_LON`.`PermissionsAssignment` (
  `RLID` VARCHAR(8) NOT NULL,
  `PRMID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`RLID`, `PRMID`),
  INDEX `PRMIDIndex` (`PRMID` ASC),
  INDEX `RLIDIndex` (`RLID` ASC),
  CONSTRAINT `fk_RLID_PermissionsAssignment`
    FOREIGN KEY (`RLID`)
    REFERENCES `BT_LON`.`Roles` (`RLID`),
  CONSTRAINT `fk_PRMID_PermissionsAssignment`
    FOREIGN KEY (`PRMID`)
    REFERENCES `BT_LON`.`Permissions` (`PRMID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
