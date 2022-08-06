-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2014 a las 23:48:11
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `recurso`
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `recursos`
--

INSERT INTO `recursos` (`IdRecurso`, `Descripcion`) VALUES
(1, 'Coche tipo 1'),
(2, 'Sala tipo 1');

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
  `Estado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IdReserva`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

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
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `IdUsuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Contrasena` varchar(250) COLLATE utf8_spanish_ci NOT NULL,
  `Rol` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `NombreUsuario`, `Nombre`, `Apellidos`, `Contrasena`, `Rol`) VALUES
(1, 'marta', 'Marta', 'Estalayo', 'marta', 1),
(2, 'marta2', 'Marta', 'Estalayo', 'marta2', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
