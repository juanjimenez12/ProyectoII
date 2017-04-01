-- MySQL Script generated by MySQL Workbench
-- Wed Mar 29 21:03:49 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema doctorado
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema doctorado
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doctorado` DEFAULT CHARACTER SET utf8 ;
USE `doctorado` ;

-- -----------------------------------------------------
-- Table `doctorado`.`estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`estudiante` (
  `est_identificador` INT NOT NULL AUTO_INCREMENT,
  `est_codigo` VARCHAR(20) NULL,
  `est_nombre` VARCHAR(20) NULL,
  `est_apellido` VARCHAR(20) NULL,
  `est_correo` VARCHAR(30) NULL,
  `est_cohorte` INT(4) NULL,
  `est_tutor` VARCHAR(45) NULL,
  `est_semestre` INT(2) NULL,
  `est_estado` VARCHAR(12) NULL,
  `est_usuario` VARCHAR(20) NULL,
  `est_contrasena` VARCHAR(40) NULL,
  PRIMARY KEY (`est_identificador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`tipo_publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`tipo_publicacion` (
  `tpub_identificador` INT NOT NULL,
  `tpub_descripcion` VARCHAR(30) NULL,
  PRIMARY KEY (`tpub_identificador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`publicacion` (
  `pub_identificador` INT NOT NULL AUTO_INCREMENT,
  `pub_titulo` VARCHAR(200) NULL,
  `pub_titulo_revista` VARCHAR(60) NULL,
  `pub_link_revista` VARCHAR(100) NULL,
  `pub_categoria` VARCHAR(5) NULL,
  `pub_creditos` INT NULL,
  `pub_fecha_visado` DATE NULL,
  `pub_fecha_registro` DATE NULL,
  `pub_estado` VARCHAR(15) NULL,
  `pub_est_identificador` INT NOT NULL,
  `pub_tpub_identificador` INT NOT NULL,
  INDEX `fk_publicacion_estudiante_idx` (`pub_est_identificador` ASC),
  PRIMARY KEY (`pub_identificador`),
  INDEX `fk_publicacion_tipo_publicacion1_idx` (`pub_tpub_identificador` ASC),
  CONSTRAINT `fk_publicacion_estudiante`
    FOREIGN KEY (`pub_est_identificador`)
    REFERENCES `doctorado`.`estudiante` (`est_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_publicacion_tipo_publicacion1`
    FOREIGN KEY (`pub_tpub_identificador`)
    REFERENCES `doctorado`.`tipo_publicacion` (`tpub_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`coordinador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`coordinador` (
  `coo_identificador` INT NOT NULL AUTO_INCREMENT,
  `coo_nombre` VARCHAR(45) NULL,
  `coo_contrasena` VARCHAR(40) NULL,
  `coo_correo` VARCHAR(30) NULL,
  `coo_usuario` VARCHAR(20) NULL,
  PRIMARY KEY (`coo_identificador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`doctorado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`doctorado` (
  `doc_identificador` INT NOT NULL AUTO_INCREMENT,
  `doc_coo_identificador` INT NOT NULL,
  `doc_est_identificador` INT NOT NULL,
  PRIMARY KEY (`doc_identificador`, `doc_coo_identificador`, `doc_est_identificador`),
  INDEX `fk_doctorado_coordinador1_idx` (`doc_coo_identificador` ASC),
  INDEX `fk_doctorado_estudiante1_idx` (`doc_est_identificador` ASC),
  CONSTRAINT `fk_doctorado_coordinador1`
    FOREIGN KEY (`doc_coo_identificador`)
    REFERENCES `doctorado`.`coordinador` (`coo_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctorado_estudiante1`
    FOREIGN KEY (`doc_est_identificador`)
    REFERENCES `doctorado`.`estudiante` (`est_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`archivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`archivo` (
  `arc_identificador` INT NOT NULL AUTO_INCREMENT,
  `arc_pub_identificador` INT NOT NULL,
  PRIMARY KEY (`arc_identificador`),
  INDEX `fk_archivo_publicacion1_idx` (`arc_pub_identificador` ASC),
  CONSTRAINT `fk_archivo_publicacion1`
    FOREIGN KEY (`arc_pub_identificador`)
    REFERENCES `doctorado`.`publicacion` (`pub_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doctorado`.`palabra_clave`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doctorado`.`palabra_clave` (
  `pcla_indentificador` INT NOT NULL,
  `pcla_palabra` VARCHAR(25) NULL,
  `pcla_pub_identificador` INT NOT NULL,
  PRIMARY KEY (`pcla_indentificador`),
  INDEX `fk_palabra_clave_publicacion1_idx` (`pcla_pub_identificador` ASC),
  CONSTRAINT `fk_palabra_clave_publicacion1`
    FOREIGN KEY (`pcla_pub_identificador`)
    REFERENCES `doctorado`.`publicacion` (`pub_identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
