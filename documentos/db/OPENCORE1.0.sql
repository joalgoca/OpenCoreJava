SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `opencore1`.`core_conexiones_dao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_conexiones_dao` (
  `CORE_CONEXIONES_DAO_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL COMMENT 'Tabla para conexión de base de datos para reportes de manera dinamica sin hardcodear el codigo',
  `DRIVER` VARCHAR(45) NOT NULL,
  `SERVIDOR` VARCHAR(45) NOT NULL,
  `USUARIO` VARCHAR(45) NOT NULL,
  `CONTRASENA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CORE_CONEXIONES_DAO_ID`),
  UNIQUE INDEX `NOMBRE_UNIQUE` (`NOMBRE` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4;


-- -----------------------------------------------------
-- Table `opencore1`.`core_derecho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_derecho` (
  `CORE_DERECHO_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DERECHO` VARCHAR(10) NOT NULL,
  `NOMBRE_MENU` VARCHAR(45) NOT NULL,
  `RUTA_MENU` VARCHAR(45) NULL DEFAULT NULL,
  `ES_ENLACE` VARCHAR(1) NOT NULL,
  `ES_VISIBLE` VARCHAR(1) NOT NULL,
  `ESTATUS` VARCHAR(2) NOT NULL,
  `ORDEN` INT(10) NOT NULL,
  `FK_PADRE` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`CORE_DERECHO_ID`),
  UNIQUE INDEX `DERECHO_UNIQUE` (`DERECHO` ASC),
  INDEX `fk_derecho_idx` (`FK_PADRE` ASC),
  CONSTRAINT `fk_derecho`
    FOREIGN KEY (`FK_PADRE`)
    REFERENCES `opencore1`.`core_derecho` (`CORE_DERECHO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 65;


-- -----------------------------------------------------
-- Table `opencore1`.`core_mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_mensaje` (
  `CORE_MENSAJE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TITULO` VARCHAR(45) NOT NULL,
  `TEXTO` VARCHAR(200) NOT NULL,
  `FECHA_CREACION` DATETIME NOT NULL,
  `TO_ID` INT(10) UNSIGNED NOT NULL,
  `FROM_ID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `TO_TYPE` VARCHAR(15) NOT NULL COMMENT 'core_usuario\\\\ntvm_usuario\\\\ntvm_cliente',
  `FROM_TYPE` VARCHAR(15) NOT NULL COMMENT 'core_usuario\\\\ntvm_usuario\\\\ntvm_cliente\\\\nanonimo',
  `ESTATUS` VARCHAR(2) NOT NULL COMMENT 'AC\\nIN\\nRE',
  PRIMARY KEY (`CORE_MENSAJE_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opencore1`.`core_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_perfil` (
  `CORE_PERFIL_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `PERFIL` VARCHAR(10) NOT NULL,
  `DESCRIPCION` VARCHAR(150) NOT NULL,
  `ESTATUS` VARCHAR(2) NOT NULL,
  `HOME_PAGE` VARCHAR(45) NOT NULL DEFAULT 'general/escritorio.jsp',
  PRIMARY KEY (`CORE_PERFIL_ID`),
  UNIQUE INDEX `PERFIL_UNIQUE` (`PERFIL` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3;


-- -----------------------------------------------------
-- Table `opencore1`.`core_perfil_derecho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_perfil_derecho` (
  `CORE_PERFIL_ID` INT(11) UNSIGNED NOT NULL,
  `CORE_DERECHO_ID` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`CORE_PERFIL_ID`, `CORE_DERECHO_ID`),
  INDEX `fk1_core_pefil_derecho_idx` (`CORE_DERECHO_ID` ASC),
  INDEX `fk2_core_perfil_derecho_idx` (`CORE_PERFIL_ID` ASC),
  CONSTRAINT `fk1_core_perfil_derecho`
    FOREIGN KEY (`CORE_DERECHO_ID`)
    REFERENCES `opencore1`.`core_derecho` (`CORE_DERECHO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk2_core_perfil_derecho`
    FOREIGN KEY (`CORE_PERFIL_ID`)
    REFERENCES `opencore1`.`core_perfil` (`CORE_PERFIL_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencore1`.`core_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_usuario` (
  `CORE_USUARIO_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USUARIO` VARCHAR(100) NOT NULL,
  `CONTRASENA` VARCHAR(100) NOT NULL,
  `NOMBRE` VARCHAR(45) NOT NULL,
  `APELLIDOS` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(100) NOT NULL,
  `TELEFONO` VARCHAR(22) NULL DEFAULT NULL,
  `ESTATUS` VARCHAR(2) NOT NULL,
  `ES_SUPER` VARCHAR(1) NOT NULL,
  `ESTILO` VARCHAR(20) NOT NULL DEFAULT 'default',
  `FOTO` BLOB NULL DEFAULT NULL,
  `VIGENCIA` DATETIME NOT NULL,
  `APLICA_BITACORA` BIT NOT NULL DEFAULT 0,
  `IS_LOGIN` BIT NOT NULL DEFAULT 0,
  `MULTI_SESION` BIT NOT NULL DEFAULT 1,
  `IP_RESTRICCION` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`CORE_USUARIO_ID`),
  UNIQUE INDEX `USUARIO_UNIQUE` (`USUARIO` ASC),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3;


-- -----------------------------------------------------
-- Table `opencore1`.`core_usuario_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_usuario_perfil` (
  `CORE_PERFIL_ID` INT(11) UNSIGNED NOT NULL,
  `CORE_USUARIO_ID` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`CORE_USUARIO_ID`, `CORE_PERFIL_ID`),
  INDEX `fk1_core_usuario_perfil_idx` (`CORE_USUARIO_ID` ASC),
  INDEX `fk2_core_usuario_perfil_idx` (`CORE_PERFIL_ID` ASC),
  CONSTRAINT `fk1_core_usuario_perfil`
    FOREIGN KEY (`CORE_USUARIO_ID`)
    REFERENCES `opencore1`.`core_usuario` (`CORE_USUARIO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk2_core_usuario_perfil`
    FOREIGN KEY (`CORE_PERFIL_ID`)
    REFERENCES `opencore1`.`core_perfil` (`CORE_PERFIL_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencore1`.`bitacora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`bitacora` (
  `BITACORA_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USUARIO_ID` INT(11) NOT NULL,
  `TIPO` VARCHAR(10) NOT NULL,
  `ORIGEN` VARCHAR(45) NOT NULL,
  `OPERACION` VARCHAR(300) NOT NULL,
  `FECHA_OPERACION` DATETIME NOT NULL,
  PRIMARY KEY (`BITACORA_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencore1`.`core_reporte_jasper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_reporte_jasper` (
  `CORE_REPORTE_JASPER_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(30) NOT NULL,
  `DESCRIPCION` VARCHAR(200) NULL,
  `DATA` MEDIUMBLOB NULL,
  `ESTATUS` VARCHAR(2) NOT NULL,
  `NOMBRE_ARCHIVO` VARCHAR(45) NOT NULL,
  `CATEGORIA` VARCHAR(2) NOT NULL,
  `PARAMETROS` VARCHAR(300) NULL,
  `CONEXION_DAO` INT(11) NOT NULL,
  PRIMARY KEY (`CORE_REPORTE_JASPER_ID`),
  UNIQUE INDEX `NOMBRE_UNIQUE` (`NOMBRE` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencore1`.`core_aviso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_aviso` (
  `CORE_AVISO_ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TITULO` VARCHAR(45) NOT NULL,
  `CONTENIDO` LONGTEXT NOT NULL,
  `ESTATUS` VARCHAR(2) NOT NULL,
  `PRIORIDAD` VARCHAR(5) NOT NULL,
  `VIGENCIA` VARCHAR(45) NOT NULL COMMENT 'DIAS DE VIGENCIA',
  PRIMARY KEY (`CORE_AVISO_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencore1`.`core_permisos_ip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencore1`.`core_permisos_ip` (
  `CORE_PERMISOS_IP_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CORE_USUARIO_ID` INT(11) UNSIGNED NOT NULL,
  `ACCION` VARCHAR(2) NOT NULL COMMENT 'PE - PERMITIR\nBL - Bloquear',
  `IP` VARCHAR(15) NOT NULL,
  `ESTATUS` VARCHAR(2) NOT NULL COMMENT 'AC - Activa\nIN - Inactiva',
  PRIMARY KEY (`CORE_PERMISOS_IP_ID`),
  INDEX `fk_CORE_IP_core_usuario1_idx` (`CORE_USUARIO_ID` ASC),
  CONSTRAINT `fk_CORE_IP_core_usuario1`
    FOREIGN KEY (`CORE_USUARIO_ID`)
    REFERENCES `opencore1`.`core_usuario` (`CORE_USUARIO_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
