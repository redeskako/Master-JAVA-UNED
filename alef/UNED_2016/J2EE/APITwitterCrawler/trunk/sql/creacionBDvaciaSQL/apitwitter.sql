-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2016 a las 00:49:34
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- BORRADO TABLAS `apitwitter`
--

DROP TABLE IF EXISTS `apiclinicaltrials`;
DROP TABLE IF EXISTS `hashtags_adm`;
DROP TABLE IF EXISTS `hashtags_nct`;
DROP TABLE IF EXISTS `hashtags_tweet`;
DROP TABLE IF EXISTS `hashtag_neighbors`;
DROP TABLE IF EXISTS `nct_id_interventions`;
DROP TABLE IF EXISTS `nct_id_responsible_party`; 
DROP TABLE IF EXISTS `ntc_id_condition`;
DROP TABLE IF EXISTS `ntc_id_keyword`;
-- DROP TABLE IF EXISTS `poll`;
DROP TABLE IF EXISTS `secondary_outcome_nct`;
DROP TABLE IF EXISTS `tweets`;
DROP TABLE IF EXISTS `urls_tweet`;
-- DROP TABLE IF EXISTS `usuarios`;

--
-- Base de datos: `apitwitter`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apiclinicaltrials`
--

CREATE TABLE `apiclinicaltrials` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `brief_title` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `official_title` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `brief_summary` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `detailed_description` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `study_design` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `primary_outcome_measure` varchar(4096) COLLATE utf8_spanish_ci NOT NULL,
  `overall_status` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `verification_date` date NOT NULL,
  `lastchanged_date` date NOT NULL,
  `firstreceived_date` date NOT NULL,
  `location_facility_name` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `organization` varchar(512) COLLATE utf8_spanish_ci NOT NULL,
  `oversight_info_authority` varchar(512) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtags_adm`
--

CREATE TABLE `hashtags_adm` (
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `origen` set('adm','neighbor','','') COLLATE utf8_spanish_ci NOT NULL,
  `fecha_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtags_nct`
--

CREATE TABLE `hashtags_nct` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `hashtag` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtags_tweet`
--

CREATE TABLE `hashtags_tweet` (
  `tweet_id` bigint(32) NOT NULL,
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtag_neighbors`
--

CREATE TABLE `hashtag_neighbors` (
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `neighbor` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nct_id_interventions`
--

CREATE TABLE `nct_id_interventions` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `type_0` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `name_0` varchar(512) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nct_id_responsible_party`
--

CREATE TABLE `nct_id_responsible_party` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `responsible_party_type` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `investigator_affiliation` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `investigator_full_name` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `investigator_title` varchar(128) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ntc_id_condition`
--

CREATE TABLE `ntc_id_condition` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `condition_0` varchar(128) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ntc_id_keyword`
--

CREATE TABLE `ntc_id_keyword` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `keyword_0` varchar(128) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poll`
--

CREATE TABLE IF NOT EXISTS `poll` (
  `fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `max_id` bigint(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `poll`
--

REPLACE INTO `poll` (`fecha_hora`, `max_id`) VALUES
('2016-06-03 21:11:05', 738808202121359360);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secondary_outcome_nct`
--

CREATE TABLE `secondary_outcome_nct` (
  `nct_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `measure` varchar(1024) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tweets`
--

CREATE TABLE `tweets` (
  `tweet_id` bigint(32) NOT NULL,
  `text` text COLLATE utf8_spanish_ci NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `user_name` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `followers_count` bigint(32) NOT NULL,
  `friends_count` int(10) NOT NULL,
  `profile_image_url_https` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `urls_tweet`
--

CREATE TABLE `urls_tweet` (
  `tweet_id` bigint(32) NOT NULL,
  `url` varchar(64) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `user` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `passwd` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `rol` set('adm','cliente') COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

REPLACE INTO `usuarios` (`user`, `passwd`, `rol`) VALUES
('alu_1_2016', 'alu_1_2016', 'adm'),
('alu_2_2016', 'alu_2_2016', 'cliente'),
('carlos', 'carlos', 'adm'),
('juan', 'juan', 'cliente');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apiclinicaltrials`
--
ALTER TABLE `apiclinicaltrials`
  ADD PRIMARY KEY (`nct_id`);

--
-- Indices de la tabla `hashtags_adm`
--
ALTER TABLE `hashtags_adm`
  ADD PRIMARY KEY (`hashtag_id`);

--
-- Indices de la tabla `nct_id_responsible_party`
--
ALTER TABLE `nct_id_responsible_party`
  ADD PRIMARY KEY (`nct_id`);

--
-- Indices de la tabla `poll`
--
ALTER TABLE `poll`
  ADD PRIMARY KEY IF NOT EXISTS (`fecha_hora`);

--
-- Indices de la tabla `tweets`
--
ALTER TABLE `tweets`
  ADD PRIMARY KEY (`tweet_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY IF NOT EXISTS(`user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
