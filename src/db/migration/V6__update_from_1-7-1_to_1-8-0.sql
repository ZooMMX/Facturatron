-- --------------------------------------------
-- Tabla `facturatron`.`medida`
CREATE TABLE IF NOT EXISTS `medida` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(32) DEFAULT NULL,
   PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

-- --------------------------------------------
-- Tabla Productos
CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(32) DEFAULT NULL,
  `clave` varchar(12) NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `activo` tinyint(4) NOT NULL,
  `notas` varchar(254) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

-- Insertar Unidades por Default
INSERT INTO medida (id, nombre) VALUES (null,'NoAplica');
INSERT INTO medida (id, nombre) VALUES (null,'Piezas');

-- Eliminar columnas obsoletas legadas de CFDv2
ALTER TABLE serie DROP COLUMN folioInicial;
ALTER TABLE serie DROP COLUMN folioFinal;
ALTER TABLE serie DROP COLUMN noAprobacion;
ALTER TABLE serie DROP COLUMN anoAprobacion;
ALTER TABLE serie DROP COLUMN noCertificado;
