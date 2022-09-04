-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2015 a las 01:42:53
-- Versión del servidor: 10.1.8-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `uned`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `IdLibro` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL DEFAULT '',
  `Autor` varchar(100) NOT NULL DEFAULT '0',
  `Tema` varchar(100) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Libros de Biblioteca';

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`IdLibro`, `Nombre`, `Autor`, `Tema`) VALUES
(1, 'Informatica para torpes', 'Alfredo Ruíz Roldán', 'Ingeniería Informática'),
(2, 'Oracle Forms', 'Samuel L. Jackson', 'Ingeniería Informática'),
(3, 'Diseño de Bases de Datos', 'Rubén Sánchez Aguilar', 'Ingeniería Informática'),
(4, 'Redes y Comunicaciones', 'Lucas Perilo', 'Ingeniería Informática'),
(5, 'Platero y yo', 'Juan Ramón Jiménez ', 'Historia'),
(6, 'Cien años de soledad', 'Gabriel García Márquez', 'Novela'),
(9, 'Tormenta de espadas', 'George R. R. Martin', 'Fantasía'),
(10, 'Ciudad de las Bestias', 'Isabel Allende', 'Novela'),
(11, 'Los Pasos Perdidos', 'Alejo Carpentier', 'Narrativa'),
(12, 'Introduccion al Proceso Soft', 'Watts S. Humpphrey', 'Ingeniería Informática'),
(13, 'Festín de cuervos', 'George R. R. Martin', 'Fantasía'),
(14, 'Juego de tronos', 'George R. R. Martin', 'Fantasía'),
(15, 'Choque de reyes', 'George R. R. Martin', 'Fantasía'),
(16, 'Danza de dragones', 'George R. R. Martin', 'Fantasía');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `IdPrestamo` int(11) NOT NULL,
  `IdSocio` int(11) NOT NULL DEFAULT '0',
  `IdLibro` int(11) NOT NULL DEFAULT '0',
  `FechaInicio` date NOT NULL DEFAULT '0000-00-00',
  `FechaFin` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Prestamos de los Socios';

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`IdPrestamo`, `IdSocio`, `IdLibro`, `FechaInicio`, `FechaFin`) VALUES
(1, 7, 4, '2006-02-03', '2006-04-04'),
(2, 1, 4, '2006-03-08', '0000-00-00'),
(4, 3, 2, '2006-04-23', '0000-00-00'),
(5, 3, 1, '2006-06-11', '0000-00-00'),
(8, 3, 1, '2006-08-04', '2006-08-04'),
(9, 1, 9, '2006-08-07', '2005-05-05'),
(10, 4, 5, '2006-08-07', '0000-00-00'),
(11, 2, 6, '2006-08-07', '0000-00-00'),
(12, 9, 6, '2006-08-07', '0000-00-00'),
(13, 9, 13, '2006-08-07', '0000-00-00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socios`
--

CREATE TABLE `socios` (
  `IdSocio` int(11) NOT NULL,
  `DNI` varchar(15) NOT NULL,
  `Apellidos` varchar(100) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Direccion` varchar(255) NOT NULL,
  `FechaAlta` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Socios de la biblioteca';

--
-- Volcado de datos para la tabla `socios`
--

INSERT INTO `socios` (`IdSocio`, `DNI`, `Apellidos`, `Nombre`, `Direccion`, `FechaAlta`) VALUES
(1, '31092741Y', 'Fernandez Najera', 'Luis', 'Pico Urbión', '2004-05-05'),
(2, '41981112A', 'Sabadell Lopez', 'Alberto', 'Ruiz de Canavas', '2005-04-27'),
(3, '11762113C', 'Martín Pretel', 'Roberto', 'Alameda', '2004-10-07'),
(4, '12341114D', 'Ruíz Ruíz', 'Alfredo', 'Cura Merino', '2006-08-01'),
(5, '67109515E', 'Sanchez Lopez', 'Rafael', 'Samaniego, 30', '2002-10-30'),
(7, 'X3333333Z', 'Ruíz Hernández', 'Carlos', 'La Pera', '2006-08-01'),
(8, '12345678A', 'Torres Ramos', 'Antonio', 'Av. del Mediterráneo, 16', '2006-08-05'),
(9, '10190542J', 'Banderas', 'Toño', 'Marbella', '2006-08-08'),
(10, '74890184V', 'Rodríguez', 'Luís', 'Sabadell', '2006-08-07'),
(11, '12345679B', 'González Díaz', 'Javier', 'Av. Europa, 15', '2015-12-06');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`IdLibro`),
  ADD UNIQUE KEY `U_Libro` (`Nombre`),
  ADD KEY `FK_Libros_Autor` (`Autor`),
  ADD KEY `FK_Libros_Tema` (`Tema`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`IdPrestamo`),
  ADD UNIQUE KEY `U_Prestamo` (`IdSocio`,`IdLibro`,`FechaInicio`),
  ADD KEY `FK_Prestamo_Libro` (`IdLibro`);

--
-- Indices de la tabla `socios`
--
ALTER TABLE `socios`
  ADD PRIMARY KEY (`IdSocio`),
  ADD UNIQUE KEY `U_DNI` (`DNI`),
  ADD KEY `I_Socios` (`Apellidos`,`Nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `IdLibro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `IdPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `socios`
--
ALTER TABLE `socios`
  MODIFY `IdSocio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `FK_Prestamo_Libro` FOREIGN KEY (`IdLibro`) REFERENCES `libros` (`IdLibro`),
  ADD CONSTRAINT `FK_Prestamo_Socio` FOREIGN KEY (`IdSocio`) REFERENCES `socios` (`IdSocio`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
