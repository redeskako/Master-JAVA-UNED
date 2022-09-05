-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-07-2016 a las 17:25:25
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `miuned`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupojava2016`
--

CREATE TABLE `grupojava2016` (
  `ID` int(11) NOT NULL,
  `Nombre` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Rol` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Edad` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `grupojava2016`
--

INSERT INTO `grupojava2016` (`ID`, `Nombre`, `Rol`, `Edad`) VALUES
(1, 'Carlos Luis', 'Product Owner', 91),
(2, 'Nacho', 'Scrum Master', 92),
(3, 'Isma', 'Programmer', 93),
(4, 'Jose', 'Programmer', 94),
(5, 'Fede', 'Programmer', 95),
(6, 'Carlos A.', 'Programmer', 96),
(7, 'Carlos L.', 'Programmer', 97);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '0');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `grupojava2016`
--
ALTER TABLE `grupojava2016`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `grupojava2016`
--
ALTER TABLE `grupojava2016`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
