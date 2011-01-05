SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `facturatron` DEFAULT CHARACTER SET latin1 ;

-- -----------------------------------------------------
-- Table `facturatron`.`persona`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `facturatron`.`persona` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NULL DEFAULT NULL ,
  `rfc` VARCHAR(13) NULL DEFAULT NULL ,
  `telefono` VARCHAR(14) NULL DEFAULT NULL ,
  `calle` VARCHAR(64) NULL DEFAULT NULL ,
  `codigoPostal` VARCHAR(7) NULL DEFAULT NULL ,
  `colonia` VARCHAR(45) NULL DEFAULT NULL ,
  `municipio` VARCHAR(45) NULL DEFAULT NULL ,
  `estado` VARCHAR(45) NULL DEFAULT NULL ,
  `noExterior` VARCHAR(45) NULL DEFAULT NULL ,
  `noInterior` VARCHAR(45) NULL DEFAULT NULL ,
  `pais` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `facturatron`.`comprobante`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `facturatron`.`comprobante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(8) NOT NULL ,
  `fecha` DATE NOT NULL ,
  `serie` VARCHAR(45) NULL DEFAULT NULL ,
  `folio` VARCHAR(45) NOT NULL ,
  `sello` TEXT NOT NULL ,
  `noCertificado` VARCHAR(45) NOT NULL ,
  `noAprobacion` MEDIUMTEXT NOT NULL ,
  `anoAprobacion` MEDIUMTEXT NOT NULL ,
  `formaDePago` VARCHAR(45) NOT NULL ,
  `subtotal` DOUBLE NOT NULL ,
  `total` DOUBLE NOT NULL ,
  `descuentoTasa0` DOUBLE NULL DEFAULT NULL ,
  `descuentoTasa16` DOUBLE NULL DEFAULT NULL ,
  `tipoDeComprobante` VARCHAR(45) NOT NULL ,
  `idemisor` INT(11) NULL DEFAULT NULL ,
  `idreceptor` INT(11) NULL DEFAULT NULL ,
  `ivaTrasladado` DOUBLE NULL DEFAULT NULL ,
  `certificado` VARCHAR(45) NULL DEFAULT NULL ,
  `motivoDescuento` VARCHAR(45) NULL DEFAULT NULL ,
  `xml` TEXT NULL DEFAULT NULL ,
  `estadoComprobante` ENUM('CANCELADO','VIGENTE') NOT NULL DEFAULT 'VIGENTE' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Comprobate_Persona1` (`idemisor` ASC) ,
  INDEX `fk_Comprobate_Persona2` (`idreceptor` ASC) ,
  CONSTRAINT `fk_Comprobate_Persona1`
    FOREIGN KEY (`idemisor` )
    REFERENCES `facturatron`.`persona` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comprobate_Persona2`
    FOREIGN KEY (`idreceptor` )
    REFERENCES `facturatron`.`persona` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 145
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `facturatron`.`concepto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `facturatron`.`concepto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `idComprobante` INT(11) NULL DEFAULT NULL ,
  `unidad` VARCHAR(45) NULL DEFAULT NULL ,
  `noIdentificacion` VARCHAR(45) NULL DEFAULT NULL ,
  `importe` DECIMAL(10,2) NULL DEFAULT NULL ,
  `cantidad` DECIMAL(10,2) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,
  `valorUnitario` DECIMAL(10,2) NULL DEFAULT NULL ,
  `tasa0` INT(11) NULL DEFAULT '0' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Concepto_Comprobate` (`idComprobante` ASC) ,
  CONSTRAINT `fk_Concepto_Comprobate`
    FOREIGN KEY (`idComprobante` )
    REFERENCES `facturatron`.`comprobante` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 373
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `facturatron`.`serie`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `facturatron`.`serie` (
  `anoAprobacion` INT(11) NOT NULL ,
  `noAprobacion` INT(11) NOT NULL ,
  `noCertificado` TEXT NOT NULL ,
  `serie` VARCHAR(10) NULL DEFAULT NULL ,
  `folioInicial` INT(11) NOT NULL ,
  `folioFinal` INT(11) NOT NULL ,
  `folioActual` INT(11) NOT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
