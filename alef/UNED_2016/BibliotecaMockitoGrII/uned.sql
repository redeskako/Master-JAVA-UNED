/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema UNED
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ UNED;
USE UNED;
CREATE TABLE `Libros` (
  `IdLibro` int(11) NOT NULL auto_increment,
  `Nombre` varchar(100) NOT NULL default '',
  `Autor` varchar(100) NOT NULL default '0',
  `Tema` varchar(100) NOT NULL default '0',
  PRIMARY KEY  (`IdLibro`),
  UNIQUE KEY `U_Libro` (`Nombre`),
  KEY `FK_Libros_Autor` (`Autor`),
  KEY `FK_Libros_Tema` (`Tema`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Libros de Biblioteca';
INSERT INTO `Libros` VALUES  (1,'Informatica sdaf','Alfredo Ruiz Roldan','Ingenieria Informatica'),
 (2,'Oracle Forms','Samuel L. Jackson','Ingenieria Informatica'),
 (3,'Diseño de Bases de Datos','Ruben aasdf','Ingenieria Informatica'),
 (4,'Redes y Comunicaciones','Lucas Perilo','asd'),
 (5,'Platero y yo','Juan Ramon Jimenez ','Historia'),
 (6,'Cien años de soleda','Gabriel Garcia Marquez','Novela'),
 (9,'Prueba','Prueba1','*>'),
 (10,'Ciudad de las Bestias','Isabel Allende','Novela'),
 (11,'Los Pasos Perdidos','Alejo Carpentier','Narrativa'),
 (12,'Introduccion al Proceso Soft','Watts S. Humpphrey','Ingenieria Informatica'),
 (13,'AProbar','AProbar','AProbar');
CREATE TABLE `Prestamos` (
  `IdPrestamo` int(11) NOT NULL auto_increment,
  `IdSocio` int(11) NOT NULL default '0',
  `IdLibro` int(11) NOT NULL default '0',
  `FechaInicio` date NOT NULL default '0000-00-00',
  `FechaFin` date NOT NULL default '0000-00-00',
  PRIMARY KEY  (`IdPrestamo`),
  UNIQUE KEY `U_Prestamo` (`IdSocio`,`IdLibro`,`FechaInicio`),
  KEY `FK_Prestamo_Libro` (`IdLibro`),
  CONSTRAINT `FK_Prestamo_Libro` FOREIGN KEY (`IdLibro`) REFERENCES `Libros` (`IdLibro`),
  CONSTRAINT `FK_Prestamo_Socio` FOREIGN KEY (`IdSocio`) REFERENCES `Socios` (`IdSocio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Prestamos de los Socios';
INSERT INTO `Prestamos` VALUES  (1,7,4,'2006-02-03','2006-04-04'),
 (2,1,4,'2006-03-08','0000-00-00'),
 (4,3,2,'2006-04-23','0000-00-00'),
 (5,3,1,'2006-06-11','0000-00-00'),
 (8,3,1,'2006-08-04','2006-08-04'),
 (9,1,9,'2006-08-07','2005-05-05'),
 (10,4,5,'2006-08-07','0000-00-00'),
 (11,2,6,'2006-08-07','0000-00-00'),
 (12,9,6,'2006-08-07','0000-00-00'),
 (13,9,13,'2006-08-07','0000-00-00');
CREATE TABLE `Socios` (
  `IdSocio` int(11) NOT NULL auto_increment,
  `DNI` varchar(15) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Direccion` varchar(255) NOT NULL,
  `FechaAlta` date NOT NULL default '0000-00-00',
  PRIMARY KEY  (`IdSocio`),
  UNIQUE KEY `U_DNI` (`DNI`),
  KEY `I_Socios` (`Apellidos`,`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Socios de la biblioteca';
INSERT INTO `Socios` VALUES  (1,'11111111C','Luis adfd ','Fernandez Najera','Pico Urbion DFS','2004-05-05'),
 (2,'11111112A','Sabadell Lopez','Alberto','Ruiz de Canavas','2005-04-27'),
 (3,'11111113C','Martin Pretel','Roberto gas','XXXXX','2004-10-07'),
 (4,'11111114D','Ruiz Ruiz af','XXXXX','Cura Merino','2006-08-01'),
 (5,'11111115E','Rafael xxxxxx','Sanchez Lopez','Samaniego 30','2002-10-30'),
 (7,'X3333333Z','Carlos','Ruiz Hernandez','*>','2006-08-01'),
 (8,'00000000A','aa','bb','dd','2006-08-05'),
 (9,'000005423','Banderas','Toño','Marbella Beach Corruption Village','2006-08-07'),
 (10,'0000AAAAV','Rodriguez','Luis','Sabadell','2006-08-07');
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
