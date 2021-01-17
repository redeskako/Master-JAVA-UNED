SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `medlinebennett` ;
CREATE SCHEMA IF NOT EXISTS `medlinebennett` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `medlinebennett` ;

-- -----------------------------------------------------
-- Table `medlinebennett`.`HealthTopic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`HealthTopic` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`HealthTopic` (
  `Id` INT NOT NULL ,
  `HealthTopic` VARCHAR(100) NULL ,
  `Language` VARCHAR(45) NULL ,
  `DateCreated` DATETIME NULL ,
  `URL` VARCHAR(450) NULL ,
  `Summary` MEDIUMTEXT NULL ,
  `PrimaryInst` VARCHAR(150) NULL ,
  `PrimaryInstURL` VARCHAR(450) NULL ,
  PRIMARY KEY (`Id`) )
ENGINE = InnoDB;

CREATE INDEX `Id` ON `medlinebennett`.`HealthTopic` (`Id`) ;


-- -----------------------------------------------------
-- Table `medlinebennett`.`AlsoCalled`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`AlsoCalled` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`AlsoCalled` (
  `HealthTopic_Id` INT NOT NULL COMMENT 'Synonyms associated with the topic. It is not required. Some topics have no synonyms, while others may have multiple synonyms.' ,
  `AlsoCalled` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`HealthTopic_Id`, `AlsoCalled`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`Group` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`Group` (
  `HealthTopic_Id` INT NOT NULL ,
  `IdGroup` INT NOT NULL COMMENT 'Grupos a los que pertenece cada HT. Cada HT puede pertenecer a uno o más grupos. Habría que romper la tabla para que estuviese en 3NF, como la base de datos se carga desde un XML y no se actualizan los registros directamente a través de la base de datos,  /* comment truncated */ /*se deja en 1NF.*/' ,
  `Name` VARCHAR(100) NULL ,
  `URL` VARCHAR(450) NULL ,
  PRIMARY KEY (`HealthTopic_Id`, `IdGroup`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`SeeReference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`SeeReference` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`SeeReference` (
  `HealthTopic_Id` INT NOT NULL ,
  `Reference` VARCHAR(100) NOT NULL COMMENT 'Not every topic has an associated see reference. There may be more than one see reference.' ,
  PRIMARY KEY (`HealthTopic_Id`, `Reference`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`MeshHeading`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`MeshHeading` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`MeshHeading` (
  `HealthTopic_Id` INT NOT NULL ,
  `idMeshHeading` VARCHAR(45) NOT NULL ,
  `Descriptor` VARCHAR(100) NULL ,
  PRIMARY KEY (`HealthTopic_Id`, `idMeshHeading`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`RelatedTopic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`RelatedTopic` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`RelatedTopic` (
  `HealthTopic_Id` INT NOT NULL ,
  `idRelatedTopic` INT NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  `URL` VARCHAR(450) NULL ,
  PRIMARY KEY (`HealthTopic_Id`, `idRelatedTopic`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`LanguageMappedTopic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`LanguageMappedTopic` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`LanguageMappedTopic` (
  `HealthTopics_Id` INT NOT NULL ,
  `idLanguageMappedTopic` INT NOT NULL ,
  `URL` VARCHAR(450) NULL ,
  `Language` VARCHAR(100) NULL ,
  `Name` VARCHAR(100) NULL ,
  PRIMARY KEY (`HealthTopics_Id`, `idLanguageMappedTopic`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`OtherLanguage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`OtherLanguage` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`OtherLanguage` (
  `HealthTopic_Id` INT NOT NULL ,
  `Name` VARCHAR(100) NOT NULL ,
  `VernacularName` VARCHAR(100) NULL ,
  `URL` VARCHAR(450) NULL ,
  PRIMARY KEY (`HealthTopic_Id`, `Name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`MetaData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`MetaData` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`MetaData` (
  `Total` INT NOT NULL ,
  `Fecha` DATETIME NOT NULL )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `Total_UNIQUE` ON `medlinebennett`.`MetaData` (`Total` ASC) ;

CREATE UNIQUE INDEX `Fecha_UNIQUE` ON `medlinebennett`.`MetaData` (`Fecha` ASC) ;


-- -----------------------------------------------------
-- Table `medlinebennett`.`Site`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`Site` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`Site` (
  `URL_Id` INT NOT NULL AUTO_INCREMENT COMMENT 'Cada Health Topic puede tener uno o varios sites, por lo que el Id del Health Topic no puede ser la clave de esta tabla.\nSe añada la nueva columna URL_Id porque MySQL no admite como campo clave el campo URL, que es único para cada site, por su tamaño' ,
  `HealthTopic_Id` INT NOT NULL ,
  `URL` VARCHAR(1250) NULL ,
  `Name` VARCHAR(450) NULL ,
  `LanguageMappedURL` VARCHAR(450) NULL ,
  PRIMARY KEY (`URL_Id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`SiteDescription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`SiteDescription` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`SiteDescription` (
  `Site_URL_Id` INT NOT NULL ,
  `Description` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`Site_URL_Id`, `Description`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`SiteOrganization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`SiteOrganization` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`SiteOrganization` (
  `Site_URL_Id` INT NOT NULL ,
  `Organization` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`Site_URL_Id`, `Organization`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`SiteInfoCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`SiteInfoCategory` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`SiteInfoCategory` (
  `Site_URL_Id` INT NOT NULL ,
  `InfoCategory` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`Site_URL_Id`, `InfoCategory`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `medlinebennett`.`Bennett`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medlinebennett`.`Bennett` ;

CREATE  TABLE IF NOT EXISTS `medlinebennett`.`Bennett` (
  `OrgType` VARCHAR(100) NULL ,
  `Beds` VARCHAR(7) NULL ,
  `USNewsDirectory` VARCHAR(450) NULL ,
  `City` VARCHAR(100) NULL ,
  `State` VARCHAR(10) NULL ,
  `ParOrg` VARCHAR(100) NULL ,
  `Organization` VARCHAR(100) NOT NULL ,
  `PriDomain` VARCHAR(100) NULL ,
  `FullOrgURL` VARCHAR(450) NULL ,
  `YouTubeAccount` VARCHAR(100) NULL ,
  `YouTubeDate` DATETIME NULL ,
  `YouTubeURL` VARCHAR(450) NULL ,
  `TwitterFirstUp` DATETIME NULL ,
  `TwitterAccount` VARCHAR(100) NULL ,
  `TwitterURL` VARCHAR(450) NULL ,
  `FacebookURL` VARCHAR(450) NULL ,
  `Blog` VARCHAR(450) NULL ,
  `FourSquare` VARCHAR(450) NULL ,
  `LinkedIn` VARCHAR(450) NULL ,
  `Pinterest` VARCHAR(450) NULL ,
  `GooglePlus` VARCHAR(450) NULL ,
  `Delicious` VARCHAR(450) NULL ,
  `Flickr` VARCHAR(450) NULL ,
  `Quantcast` VARCHAR(450) NULL ,
  `Compete` VARCHAR(450) NULL ,
  `Id` INT NOT NULL AUTO_INCREMENT ,
PRIMARY KEY (`Id`) )
ENGINE = InnoDB;

USE `medlinebennett` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
