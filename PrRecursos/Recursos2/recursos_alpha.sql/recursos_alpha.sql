-- phpMyAdmin SQL Dump
-- version 4.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2014 at 10:33 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `recursos_alpha`
--
CREATE DATABASE IF NOT EXISTS `recursos_alpha` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `recursos_alpha`;

-- --------------------------------------------------------

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
CREATE TABLE IF NOT EXISTS `estados` (
`IdEstado` int(10) unsigned NOT NULL,
  `Estado` varchar(250) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `estados`
--

INSERT INTO `estados` (`IdEstado`, `Estado`) VALUES
(1, 'Pendiente'),
(2, 'Confirmada'),
(3, 'Denegada'),
(4, 'Pendiente de anulación'),
(5, 'Anulada'),
(6, 'No anulada');

-- --------------------------------------------------------

--
-- Table structure for table `recursos`
--

DROP TABLE IF EXISTS `recursos`;
CREATE TABLE IF NOT EXISTS `recursos` (
`IdRecurso` int(10) unsigned NOT NULL,
  `Descripcion` varchar(250) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=31 ;

--
-- Dumping data for table `recursos`
--

INSERT INTO `recursos` (`IdRecurso`, `Descripcion`) VALUES
(1, 'Vehículo diésel 5 puertas'),
(2, 'Vehículo gasolina 5 puertas'),
(3, 'Furgoneta diésel'),
(4, 'Portátil 15"'),
(5, 'Portátil 17"'),
(6, 'Tablet 7"'),
(7, 'Sala reunión 4-6 personas'),
(8, 'Sala reunión 6-10 personas'),
(9, 'Sala reunión 10-20 personas'),
(10, 'Samsung Galaxy S3'),
(11, 'Samsung Galaxy S4'),
(12, 'Samsung Galaxy S5'),
(13, 'iPhone 4'),
(14, 'iPhone 5'),
(15, 'iPhone 5s'),
(16, 'Moto de Agua 50CV'),
(17, 'Moto de Agua 30CV'),
(18, 'Moto de Agua 20CV'),
(19, 'Harley Davidson 250CC'),
(20, 'Harley Davidson 500CC'),
(21, 'Sala de boda 150 personas'),
(22, 'Sala de boda 200 personas'),
(23, 'Sala de boda 250 personas'),
(24, 'Sala de boda 300 personas'),
(25, 'Velero 6m'),
(26, 'Velero 10m'),
(27, 'Velero 14m'),
(28, 'Catering 100 personas'),
(29, 'Catering 200 personas'),
(30, 'Catering 300 personas');

-- --------------------------------------------------------

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
CREATE TABLE IF NOT EXISTS `reservas` (
`IdReserva` int(10) unsigned NOT NULL,
  `IdUsuario` int(10) unsigned NOT NULL,
  `IdRecurso` int(10) unsigned NOT NULL,
  `Inicio` datetime NOT NULL,
  `Final` datetime NOT NULL,
  `IdEstado` int(10) unsigned NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=37 ;

--
-- Dumping data for table `reservas`
--

INSERT INTO `reservas` (`IdReserva`, `IdUsuario`, `IdRecurso`, `Inicio`, `Final`, `IdEstado`) VALUES
(1, 1, 1, '2014-06-24 10:00:00', '2014-06-26 10:00:00', 1),
(2, 2, 3, '2014-06-30 13:00:00', '2014-07-03 15:10:00', 2),
(3, 3, 6, '2014-09-09 05:00:00', '2014-09-15 12:00:00', 4),
(4, 1, 5, '2014-05-04 04:00:00', '2014-05-10 11:00:00', 2),
(5, 2, 7, '2014-10-01 06:00:00', '2014-10-02 10:00:00', 3),
(6, 3, 4, '2014-09-01 05:00:00', '2014-09-10 10:00:00', 5),
(7, 1, 4, '2014-06-26 00:00:00', '2014-06-27 00:00:00', 6),
(8, 1, 30, '2014-07-01 10:00:00', '2014-07-02 15:00:00', 1),
(9, 3, 29, '2014-07-02 10:00:00', '2014-07-05 16:00:00', 6),
(10, 2, 28, '2014-07-03 10:00:00', '2014-07-06 17:00:00', 3),
(11, 2, 27, '2014-07-04 10:00:00', '2014-07-08 20:00:00', 4),
(12, 3, 26, '2014-07-05 10:00:00', '2014-07-10 15:00:00', 3),
(13, 3, 25, '2014-07-06 10:00:00', '2014-07-08 15:00:00', 5),
(14, 3, 24, '2014-08-10 10:00:00', '2014-08-11 11:00:00', 2),
(15, 2, 23, '2014-08-10 10:00:00', '2014-08-12 14:00:00', 2),
(16, 2, 22, '2014-08-02 10:00:00', '2014-08-10 15:00:00', 1),
(17, 1, 21, '2014-08-05 10:00:00', '2014-08-20 15:00:00', 1),
(18, 1, 20, '2014-08-06 10:00:00', '2014-08-08 15:00:00', 6),
(19, 1, 19, '2014-08-07 10:00:00', '2014-08-08 15:00:00', 5),
(20, 1, 18, '2014-08-08 10:00:00', '2014-08-10 13:00:00', 4),
(21, 1, 17, '2014-08-21 10:00:00', '2014-08-25 15:00:00', 3),
(22, 2, 16, '2014-09-01 10:00:00', '2014-09-03 17:00:00', 3),
(23, 3, 15, '2014-09-04 10:00:00', '2014-09-07 15:00:00', 4),
(24, 3, 14, '2014-09-04 10:00:00', '2014-09-12 18:00:00', 2),
(25, 2, 13, '2014-09-29 10:00:00', '2014-09-29 22:00:00', 2),
(26, 1, 12, '2014-09-29 10:00:00', '2014-09-30 15:00:00', 2),
(27, 1, 11, '2014-10-10 10:00:00', '2014-10-12 15:00:00', 3),
(28, 3, 10, '2014-10-11 10:00:00', '2014-10-19 15:00:00', 6),
(29, 1, 25, '2014-10-13 10:00:00', '2014-10-21 12:00:00', 3),
(30, 2, 26, '2014-10-15 10:00:00', '2014-10-20 14:00:00', 2),
(31, 2, 21, '2014-11-01 10:00:00', '2014-11-15 13:00:00', 1),
(32, 2, 20, '2014-12-01 10:00:00', '2014-12-02 15:00:00', 1),
(33, 3, 19, '2014-12-10 10:00:00', '2014-12-15 15:00:00', 1),
(34, 1, 30, '2014-12-12 10:00:00', '2014-12-18 15:00:00', 4),
(35, 3, 13, '2014-12-15 10:00:00', '2014-12-18 15:00:00', 1),
(36, 1, 14, '2014-12-20 10:00:00', '2014-12-30 15:00:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
`IdRol` int(10) unsigned NOT NULL,
  `Rol` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`IdRol`, `Rol`) VALUES
(1, 'Empleado'),
(2, 'Responsable');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
`IdUsuario` int(10) unsigned NOT NULL,
  `NombreUsuario` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Contrasena` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `IdRol` int(10) unsigned NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `NombreUsuario`, `Nombre`, `Apellidos`, `Contrasena`, `IdRol`) VALUES
(1, 'cmorales', 'Cristina', 'Morales Cas', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 1),
(2, 'loliva', 'Luis', 'Oliva', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 2),
(3, 'soevering', 'Symon', 'Oevering', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 1),
(4, 'csanchez', 'Carlos', 'Sánchez Bocanegra', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estados`
--
ALTER TABLE `estados`
 ADD PRIMARY KEY (`IdEstado`);

--
-- Indexes for table `recursos`
--
ALTER TABLE `recursos`
 ADD PRIMARY KEY (`IdRecurso`);

--
-- Indexes for table `reservas`
--
ALTER TABLE `reservas`
 ADD PRIMARY KEY (`IdReserva`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
 ADD PRIMARY KEY (`IdRol`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`IdUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `estados`
--
ALTER TABLE `estados`
MODIFY `IdEstado` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `recursos`
--
ALTER TABLE `recursos`
MODIFY `IdRecurso` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `reservas`
--
ALTER TABLE `reservas`
MODIFY `IdReserva` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
MODIFY `IdRol` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `IdUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
