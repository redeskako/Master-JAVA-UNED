-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-09-2014 a las 12:23:04
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `recursos_alpha`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE IF NOT EXISTS `estados` (
  `IdEstado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Estado` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdEstado`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `estados`
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
-- Estructura de tabla para la tabla `recursos`
--

CREATE TABLE IF NOT EXISTS `recursos` (
  `IdRecurso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdRecurso`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=31 ;

--
-- Volcado de datos para la tabla `recursos`
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
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `IdReserva` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(10) unsigned NOT NULL,
  `IdRecurso` int(10) unsigned NOT NULL,
  `Inicio` datetime NOT NULL,
  `Final` datetime NOT NULL,
  `IdEstado` int(10) unsigned NOT NULL,
  `IdSucursal` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`IdReserva`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=37 ;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`IdReserva`, `IdUsuario`, `IdRecurso`, `Inicio`, `Final`, `IdEstado`, `IdSucursal`) VALUES
(1, 1, 1, '2014-06-24 10:00:00', '2014-06-26 10:00:00', 1, 1),
(2, 2, 3, '2014-06-30 13:00:00', '2014-07-03 15:10:00', 2, 1),
(3, 3, 6, '2014-09-09 05:00:00', '2014-09-15 12:00:00', 4, 1),
(4, 1, 5, '2014-05-04 04:00:00', '2014-05-10 11:00:00', 2, 1),
(5, 2, 7, '2014-10-01 06:00:00', '2014-10-02 10:00:00', 3, 1),
(6, 3, 4, '2014-09-01 05:00:00', '2014-09-10 10:00:00', 5, 1),
(7, 1, 4, '2014-06-26 00:00:00', '2014-06-27 00:00:00', 6, 1),
(8, 1, 30, '2014-07-01 10:00:00', '2014-07-02 15:00:00', 1, 1),
(9, 3, 29, '2014-07-02 10:00:00', '2014-07-05 16:00:00', 6, 1),
(10, 2, 28, '2014-07-03 10:00:00', '2014-07-06 17:00:00', 3, 1),
(11, 2, 27, '2014-07-04 10:00:00', '2014-07-08 20:00:00', 4, 1),
(12, 3, 26, '2014-07-05 10:00:00', '2014-07-10 15:00:00', 3, 1),
(13, 3, 25, '2014-07-06 10:00:00', '2014-07-08 15:00:00', 5, 1),
(14, 3, 24, '2014-08-10 10:00:00', '2014-08-11 11:00:00', 2, 1),
(15, 2, 23, '2014-08-10 10:00:00', '2014-08-12 14:00:00', 2, 1),
(16, 2, 22, '2014-08-02 10:00:00', '2014-08-10 15:00:00', 1, 1),
(17, 1, 21, '2014-08-05 10:00:00', '2014-08-20 15:00:00', 1, 1),
(18, 1, 20, '2014-08-06 10:00:00', '2014-08-08 15:00:00', 6, 1),
(19, 1, 19, '2014-08-07 10:00:00', '2014-08-08 15:00:00', 5, 1),
(20, 1, 18, '2014-08-08 10:00:00', '2014-08-10 13:00:00', 4, 1),
(21, 1, 17, '2014-08-21 10:00:00', '2014-08-25 15:00:00', 3, 1),
(22, 2, 16, '2014-09-01 10:00:00', '2014-09-03 17:00:00', 3, 1),
(23, 3, 15, '2014-09-04 10:00:00', '2014-09-07 15:00:00', 4, 1),
(24, 3, 14, '2014-09-04 10:00:00', '2014-09-12 18:00:00', 2, 1),
(25, 2, 13, '2014-09-29 10:00:00', '2014-09-29 22:00:00', 2, 1),
(26, 1, 12, '2014-09-29 10:00:00', '2014-09-30 15:00:00', 2, 1),
(27, 1, 11, '2014-10-10 10:00:00', '2014-10-12 15:00:00', 3, 1),
(28, 3, 10, '2014-10-11 10:00:00', '2014-10-19 15:00:00', 6, 1),
(29, 1, 25, '2014-10-13 10:00:00', '2014-10-21 12:00:00', 3, 1),
(30, 2, 26, '2014-10-15 10:00:00', '2014-10-20 14:00:00', 2, 1),
(31, 2, 21, '2014-11-01 10:00:00', '2014-11-15 13:00:00', 1, 1),
(32, 2, 20, '2014-12-01 10:00:00', '2014-12-02 15:00:00', 1, 1),
(33, 3, 19, '2014-12-10 10:00:00', '2014-12-15 15:00:00', 1, 1),
(34, 1, 30, '2014-12-12 10:00:00', '2014-12-18 15:00:00', 4, 1),
(35, 3, 13, '2014-12-15 10:00:00', '2014-12-18 15:00:00', 1, 1),
(36, 1, 14, '2014-12-20 10:00:00', '2014-12-30 15:00:00', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `IdRol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Rol` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`IdRol`, `Rol`) VALUES
(1, 'Empleado'),
(2, 'Responsable');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE IF NOT EXISTS `sucursales` (
  `IdSucursal` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `NombreSucursal` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`IdSucursal`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`IdSucursal`, `NombreSucursal`) VALUES
(1, 'Oficina Central'),
(2, 'Sucursal 2'),
(3, 'Sucursal 3'),
(4, 'Sucursal 4'),
(5, 'Sucursal 5'),
(6, 'Sucursal 6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Contrasena` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `IdRol` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `NombreUsuario`, `Nombre`, `Apellidos`, `Contrasena`, `IdRol`) VALUES
(1, 'cmorales', 'Cristina', 'Morales Cas', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 1),
(2, 'loliva', 'Luis', 'Oliva', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 2),
(3, 'soevering', 'Symon', 'Oevering', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 1),
(4, 'csanchez', 'Carlos', 'Sánchez Bocanegra', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
