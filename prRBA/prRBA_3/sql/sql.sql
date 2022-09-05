-- phpMyAdmin SQL Dump
-- version 2.11.3deb1ubuntu1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-09-2008 a las 16:29:31
-- Versión del servidor: 5.0.51
-- Versión de PHP: 5.2.4-2ubuntu5.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `rba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CLIENTES`
--

CREATE TABLE IF NOT EXISTS `CLIENTES` (
  `codigo` int(11) NOT NULL auto_increment,
  `nombre` varchar(20) default NULL,
  `apellido1` varchar(20) default NULL,
  `apellido2` varchar(20) default NULL,
  `telefono` varchar(13) default NULL,
  `correo` varchar(20) default NULL,
  `pass` varchar(255) default NULL,
  `cp` int(11) default NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Volcar la base de datos para la tabla `CLIENTES`
--

INSERT INTO `CLIENTES` (`codigo`, `nombre`, `apellido1`, `apellido2`, `telefono`, `correo`, `pass`, `cp`) VALUES
(3, 'maricarmen', 'perez', 'fernandez', '952485474', 'mari@hotmail.com', '*3B101A36F73153B61AFE416512F1080C04A8564C', 29650),
(5, 'lolita', 'moreno', 'reyes', '987678876', 'lolita@hotmail.com', '*0B47A0761C6EC90C4979B145882C7B374B469248', 34567),
(7, 'Ivan', 'segundo 2', 'esteban', '978352611', 'ivan@hotmail.com', '*3E16155FA1F9A3A7FCAE345D3D6CB236DFD30187', 45333),
(10, 'pedro', 'quintero', 'rosales', '897678987', 'pedro2@hotmail.com', '*B60BAC7AFAC45E988FAF28CFF8767C3A7F3DE61D', 67543),
(12, 'luis', 'garcia', 'garcia', '786567654', 'luis@hotmail.com', '*15B2F7B36BE21E811F0641EA7A1FDE324C32E87F', 65432),
(13, 'marcos', 'navas', 'peÃ±a', '876543212', 'marcos@hotmail.com', '*A0FC39B3F3F0C5485229676E143204B9D7F52715', 67890),
(16, 'rosa', 'marin', 'marin', '789635467', 'rosa@hotmail.com', '*9711BF61EAC689845730E105056DC054C8EE349E', 45678),
(17, 'luis', 'perez', 'perez', '876789098', 'luis2@hotmail.com', '*B883E0E9D11E9ED46113C5664741C32D6BD12010', 21345),
(18, 'raul', 'vilavedra', 'muÃ±oz', '670252676', 'raulvm@telefonica.ne', '*6A3A2A29EB5F58F756E24BC396F255EBF8DCD4D9', 29012),
(21, '44', '44', '44', '44', '44', '*AB326C9F9673DFA3CBAD57A643177C1A36983C68', 44),
(22, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(23, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(24, ' ', ' bb', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(25, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(26, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(27, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(28, ' ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0),
(29, '   ', ' ', ' ', ' ', ' ', '*1A256E4E2FE95B8BF7349C168991EA8035D1359B', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MESAS`
--

CREATE TABLE IF NOT EXISTS `MESAS` (
  `codigo` int(11) NOT NULL auto_increment,
  `numSillas` int(11) default NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Volcar la base de datos para la tabla `MESAS`
--

INSERT INTO `MESAS` (`codigo`, `numSillas`) VALUES
(11, 9),
(12, 6),
(16, 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RESERVAS`
--

CREATE TABLE IF NOT EXISTS `RESERVAS` (
  `codigo` int(11) NOT NULL auto_increment,
  `fecha` varchar(10) default NULL,
  `hora` varchar(10) default NULL,
  `turno` char(1) default NULL,
  `usuario` int(11) default NULL,
  `cliente` int(11) NOT NULL,
  `mesa` int(11) NOT NULL,
  `usuarioInternet` int(11) default NULL,
  PRIMARY KEY  (`codigo`),
  KEY `cliente` (`cliente`),
  KEY `mesa` (`mesa`),
  KEY `usuario` (`usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Volcar la base de datos para la tabla `RESERVAS`
--

INSERT INTO `RESERVAS` (`codigo`, `fecha`, `hora`, `turno`, `usuario`, `cliente`, `mesa`, `usuarioInternet`) VALUES
(12, '6/8/2008', '15:19:38', 'A', 4, 7, 11, NULL),
(13, '6/8/2008', '15:20:2', 'A', 6, 5, 11, NULL),
(14, '6/8/2008', '15:37:11', 'B', 2, 5, 12, NULL),
(15, '6/8/2008', '15:38:32', 'A', 3, 17, 11, NULL),
(16, '6/8/2008', '15:40:40', 'A', 5, 7, 11, NULL),
(17, '41/12/2008', '17:0:52', '2', 1, 17, 11, NULL),
(18, '12/8/2008', '17:24:8', '2', NULL, 16, 11, NULL),
(19, '12/8/2008', '18:11:0', '1', NULL, 16, 12, NULL),
(20, '12/8/2008', '18:11:7', '1', NULL, 18, 12, NULL),
(21, '6/8/2008', '18:15:7', '1', NULL, 16, 11, NULL),
(22, '12/8/2008', '19:6:57', '1', NULL, 16, 12, NULL),
(23, '15/8/2008', '16:57:10', '1', NULL, 16, 12, NULL),
(24, '15/9/2008', '16:57:48', '1', NULL, 16, 12, NULL),
(25, '15/8/2008', '17:15:33', '2', NULL, 16, 12, NULL),
(26, '15/8/2008', '17:15:49', '2', NULL, 5, 11, NULL),
(27, '18/8/2008', '18:9:56', '1', NULL, 16, 12, NULL),
(28, '18/8/2008', '19:21:7', '2', NULL, 16, 12, NULL),
(29, '19/8/2008', '16:41:29', '1', NULL, 16, 12, NULL),
(30, '19/8/2008', '17:41:28', '1', NULL, 16, 16, NULL),
(31, '20/8/2008', '19:2:12', '1', NULL, 3, 16, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOS`
--

CREATE TABLE IF NOT EXISTS `USUARIOS` (
  `codigo` int(11) NOT NULL auto_increment,
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `nivel_acceso` int(11) NOT NULL,
  `tiempo_inicio` datetime NOT NULL,
  `tiempo_cierre` datetime NOT NULL,
  `idioma` varchar(5) NOT NULL,
  PRIMARY KEY  (`codigo`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcar la base de datos para la tabla `USUARIOS`
--

INSERT INTO `USUARIOS` (`codigo`, `dni`, `nombre`, `pass`, `nivel_acceso`, `tiempo_inicio`, `tiempo_cierre`, `idioma`) VALUES
(1, '12221212', 'Eduardo ', '*D00775E064B2B864A2D422475C60567D103336F9', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'es_ES'),
(2, '11111', 'Edmundo', '*323BCEAB9395172ED251D9878866E57C8A5A48EC', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'en_EN'),
(3, '22222', 'Rafael', '*757D6B56F44F54F005A69333C21FE397240DE7B4', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'es_ES'),
(4, '23', 'Carmen', '*0B696D49704B6192D6E5FBBA3A13646DDF29D481', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'es_ES'),
(5, '26833459H', 'carlos', '*56293C419E3D9AF7E274AACE5E33745E70F144C0', 2, '2008-08-14 08:30:37', '2008-08-14 08:30:50', 'es_ES'),
(6, '27393526C', 'isabel', '*2395C874FE5D7071577A3B0C4B202E8E98197BCF', 1, '2008-08-14 17:38:15', '2008-08-14 17:38:20', 'es_ES'),
(7, '44576202V', 'raul', '*3E16155FA1F9A3A7FCAE345D3D6CB236DFD30187', 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'en_EN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOS_INTERNET`
--

CREATE TABLE IF NOT EXISTS `USUARIOS_INTERNET` (
  `codigo` int(11) NOT NULL auto_increment,
  `correo` varchar(20) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `hora_acceso` datetime NOT NULL,
  `ultimo_acceso` datetime NOT NULL,
  PRIMARY KEY  (`codigo`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `USUARIOS_INTERNET`
--

INSERT INTO `USUARIOS_INTERNET` (`codigo`, `correo`, `pass`, `telefono`, `hora_acceso`, `ultimo_acceso`) VALUES
(1, 'alef@gmail.com', '*A9E01EBAE34B8EACEE29C155454F609BB704938B', '952456743', '2008-08-14 07:43:28', '2008-08-14 07:44:09');

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `RESERVAS`
--
ALTER TABLE `RESERVAS`
  ADD CONSTRAINT `RESERVAS_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `CLIENTES` (`codigo`),
  ADD CONSTRAINT `RESERVAS_ibfk_2` FOREIGN KEY (`mesa`) REFERENCES `MESAS` (`codigo`),
  ADD CONSTRAINT `RESERVAS_ibfk_3` FOREIGN KEY (`usuario`) REFERENCES `USUARIOS` (`codigo`);
