CREATE DATABASE  IF NOT EXISTS `gestres` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gestres`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: gestres
-- ------------------------------------------------------
-- Server version	5.5.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurso` (
  `recurso_id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `estado` enum('DESCONOCIDO','MALO','ACEPTABLE','BUENO','EXCELENTE') NOT NULL DEFAULT 'DESCONOCIDO',
  `autorizador` smallint(5) unsigned DEFAULT NULL,
  `color` enum('ROJO','NARANJA','VERDE','AZUL','VIOLETA','DEFECTO') DEFAULT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recurso_id`),
  KEY `idx_desc_corta` (`nombre`),
  KEY `idx_fk_autorizador` (`autorizador`),
  KEY `FKA3C2722918C46D54` (`autorizador`),
  CONSTRAINT `FKA3C2722918C46D54` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `fk_recurso_autorizador` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'P1NO1','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(2,'P1NO2','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(3,'P1NE1','Sala de reuniones','BUENO',1,'ROJO','2014-07-12 23:26:30'),(4,'P1NE2','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(5,'P1SO1','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(6,'P1SO2','Sala de reuniones','ACEPTABLE',1,'VERDE','2014-07-13 15:47:01'),(7,'P1SE1','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(8,'P1SE2','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(9,'P2NO1','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(10,'P2NO2','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(11,'P2NE1','Sala videoconferencia','BUENO',1,'AZUL','2014-07-12 23:26:30'),(12,'P2NE2','Sala videoconferencia','BUENO',1,'AZUL','2014-07-12 23:26:30'),(13,'P2SO1','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(14,'P2SO2','Sala de reuniones','BUENO',1,'AZUL','2014-07-12 23:26:30'),(15,'P2SE1','Pruebas EPD','BUENO',1,'NARANJA','2014-07-12 23:18:24'),(16,'P2SE2','Pruebas EPI','BUENO',1,'NARANJA','2014-07-12 23:18:24'),(17,'PSC21865','Equipo portátil','MALO',1,'ROJO','2014-07-15 12:46:18'),(18,'PSC21866','Equipo portátil','ACEPTABLE',1,'ROJO','2014-07-12 23:27:48');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `reserva_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `solicitante` smallint(5) unsigned NOT NULL,
  `recurso_id` mediumint(8) unsigned NOT NULL,
  `autorizador` smallint(5) unsigned DEFAULT NULL,
  `creacion` datetime NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `autorizacion` enum('PENDIENTE','APROBADA','DENEGADA') NOT NULL,
  `estado_inicial` enum('DESCONOCIDO','MALO','ACEPTABLE','BUENO','EXCELENTE') NOT NULL,
  `estado_final` enum('DESCONOCIDO','MALO','ACEPTABLE','BUENO','EXCELENTE') DEFAULT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`reserva_id`),
  UNIQUE KEY `fecha_inicio` (`fecha_inicio`,`solicitante`,`recurso_id`),
  KEY `idx_fk_solicitante` (`solicitante`),
  KEY `idx_fk_autorizador` (`autorizador`),
  KEY `idx_fk_recurso` (`recurso_id`),
  KEY `FKA49CA498ABE3F32D` (`solicitante`),
  KEY `FKA49CA49818C46D54` (`autorizador`),
  KEY `FKA49CA49838D69EE6` (`recurso_id`),
  CONSTRAINT `FKA49CA49838D69EE6` FOREIGN KEY (`recurso_id`) REFERENCES `recurso` (`recurso_id`),
  CONSTRAINT `FKA49CA49818C46D54` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `FKA49CA498ABE3F32D` FOREIGN KEY (`solicitante`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `fk_reserva_autorizador` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_reserva_recurso` FOREIGN KEY (`recurso_id`) REFERENCES `recurso` (`recurso_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_reserva_solicitante` FOREIGN KEY (`solicitante`) REFERENCES `usuario` (`usuario_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,1,1,1,'2014-07-01 00:00:00','2014-07-01 00:00:00','2014-08-01 00:00:00','PENDIENTE','ACEPTABLE',NULL,'2014-07-14 13:55:40'),(2,3,6,1,'2014-07-13 17:45:07','2014-07-08 18:00:00','2014-07-25 18:00:00','PENDIENTE','DESCONOCIDO','ACEPTABLE','2014-07-14 13:55:40'),(3,5,17,1,'2014-07-14 12:35:59','2014-07-15 13:00:00','2014-07-16 13:00:00','APROBADA','DESCONOCIDO','MALO','2014-07-15 12:46:18');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario_id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `comentarios` text,
  `es_admin` tinyint(1) NOT NULL DEFAULT '0',
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `autorizador` smallint(5) unsigned DEFAULT NULL,
  `color` enum('ROJO','NARANJA','VERDE','AZUL','VIOLETA','DEFECTO') DEFAULT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`usuario_id`),
  KEY `idx_apellidos` (`apellidos`),
  KEY `idx_fk_autorizador` (`usuario_id`),
  KEY `FKF814F32E18C46D54` (`autorizador`),
  CONSTRAINT `FKF814F32E18C46D54` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `fk_usuario_usuario` FOREIGN KEY (`autorizador`) REFERENCES `usuario` (`usuario_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','Hh1W+PXEAhjxM3TEOT1EqePd5TfRffJ1','Usuario Administrador por Defecto',NULL,'aarquillos@bankia.com','no disponible','Usuario Administrador por Defecto',1,1,NULL,'VIOLETA','2014-07-12 23:24:45'),(2,'A126074','ryl5TVCfNQSwpdTcWpoe3KSK6ThwpPM/','Antonio Jesús','Arquillos Álvarez','aarquillos@orange.es','87024','Departamento operaciones',0,1,1,'NARANJA','2014-07-14 17:17:39'),(3,'A125397','4OdBX7GReNMD5/2nzICVocvCQ1RASPbT','María Jesús','Sánchez Sanz','mbustamb@orange.es','88812','Departamento operaciones',0,1,1,'VERDE','2014-07-12 23:34:32'),(4,'A125344','x81TAW6dCtdTjbS6sMiMx7AQ7Y/URbU/','Pedro','Solis Solis','psanz@orange.es','87654','Departamento operaciones',0,1,1,'ROJO','2014-07-14 16:57:03'),(5,'A120402','31x2pqWfWgK6qsO8lbYutJn4dY1aUr9P','Carlos','Pérez Gonzalo','carlwe@orange.es','89534','Departamento operaciones',0,1,1,'NARANJA','2014-07-14 15:27:06'),(6,'A126932','','Victoria','Pinardo Anguita','vpinrd@orange.es','89876','Departamento arquitectura',0,1,1,'NARANJA','2014-07-14 14:03:58'),(7,'A129765',NULL,'Susana','Bustamante Bermejo','sbustem@orange.es','89234','Departamento arquitectura',0,1,1,'NARANJA','2014-07-14 14:03:58');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-15 20:20:45
