-- phpMyAdmin SQL Dump
-- version 2.11.3deb1ubuntu1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 10-09-2008 a las 19:54:44
-- Versión del servidor: 5.0.51
-- Versión de PHP: 5.2.4-2ubuntu5.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `rizos_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `dni` varchar(10) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  `nombre` varchar(20) NOT NULL,
  `apellido1` varchar(20) NOT NULL,
  `apellido2` varchar(20) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `codigopostal` int(11) NOT NULL,
  `localidad` varchar(20) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `activo` tinyint(4) NOT NULL,
  `observaciones` varchar(1024) NOT NULL,
  `tipoUsuario` varchar(20) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `locale` varchar(5) NOT NULL default 'es',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcar la base de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dni`, `id`, `nombre`, `apellido1`, `apellido2`, `direccion`, `codigopostal`, `localidad`, `provincia`, `telefono`, `activo`, `observaciones`, `tipoUsuario`, `usuario`, `pass`, `locale`) VALUES
('54641345f ', 1, 'pepe', 'perez', 'delgado', 'su casa', 12231, 'malaga', 'malaga', '232323232', 1, 'lkdjfdlñsj', 'administrador', 'nick1', 'pass1', 'es'),
('13131313-H', 2, 'carmen', 'cazorla', 'carol', 'la casas verde', 78974, 'malaga', 'malaga', '63954444', 0, 'kdkdk', 'cliente', 'nick2', 'pas2', 'es'),
('45345j', 3, 'sdsf', 'dfsdfsdfsd', 'sdfs', 'casa roja 1', 3232, 'malaga', 'malaga', '5646', 1, 'ddf', 'administrador', 'nick3', 'pass3', 'es'),
('112233f', 4, 'pepito', 'grillo', 'grillo', 'sus casas', 4321, 'malaga', 'malaga', '43214', 1, 'nose', 'administrador', 'pepito', 'pepito', 'es'),
('22222222d', 5, 'dfsdf', 'sdf', 'sdf', 'fsdfsd', 43242, 'masdf', 'sdfs', '4324', 1, 'sdfs', 'administrador', 'prueba', 'prueba', 'es'),
('21323222-W', 6, 'dsdsd', 'fdsf', 'sdfs', 'dsfdf', 12432, 'dfdf', 'dfsdf', '343434', 1, 'sdfsdfsd', 'null', 'nick4', 'pass4', 'es');
