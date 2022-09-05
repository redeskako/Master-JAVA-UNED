-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-09-2014 a las 14:42:47
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `recursos_alpha_cliente`
--

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
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IdUsuario`, `NombreUsuario`, `Nombre`, `Apellidos`, `Contrasena`) VALUES
(1, 'cmorales', 'Cristina', 'Morales Cas', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc'),
(2, 'loliva', 'Luis', 'Oliva', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc'),
(3, 'soevering', 'Symon', 'Oevering', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc'),
(4, 'csanchez', 'Carlos', 'Sánchez Bocanegra', '437355046e9489eb3f8598ee7c3d20c5f25e9dfc');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
