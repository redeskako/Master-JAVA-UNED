-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-05-2016 a las 09:30:06
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `apitwitter`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtags_adm_index`
--

CREATE TABLE `hashtags_adm_index` (
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `origen` set('adm','neighbor','','') COLLATE utf8_spanish_ci NOT NULL,
  `fecha_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hashtags_adm_index`
--

INSERT INTO `hashtags_adm_index` (`hashtag_id`, `origen`, `fecha_entrada`) VALUES
('colesterol', 'adm', '2016-04-20 09:24:36'),
('depresión', 'neighbor', '2016-04-11 07:18:26'),
('diabetes', 'adm', '2016-04-05 09:26:29'),
('medicamento', 'neighbor', '2016-05-01 16:34:40'),
('tratamiento', 'neighbor', '2016-04-12 05:19:29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtags_tweet_index`
--

CREATE TABLE `hashtags_tweet_index` (
  `ID` int(5) NOT NULL,
  `tweet_id` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hashtags_tweet_index`
--

INSERT INTO `hashtags_tweet_index` (`ID`, `tweet_id`, `hashtag_id`) VALUES
(1, '1111111111', 'diabetes'),
(2, '1111111111', 'tratamiento'),
(3, '2222222222', 'colesterol'),
(4, '2222222222', 'medicamento'),
(5, '2222222222', 'depresión');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashtag_neighbors_index`
--

CREATE TABLE `hashtag_neighbors_index` (
  `ID` int(5) NOT NULL,
  `hashtag_id` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `neighbor` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hashtag_neighbors_index`
--

INSERT INTO `hashtag_neighbors_index` (`ID`, `hashtag_id`, `neighbor`) VALUES
(1, 'diabetes', 'depresión'),
(2, 'diabetes', 'tratamiento'),
(3, 'diabetes', 'medicamento'),
(4, 'colesterol', 'tratamiento'),
(5, 'colesterol', 'medicamento');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poll_index`
--

CREATE TABLE `poll_index` (
  `fecha_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `max_id` varchar(10) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `poll_index`
--

INSERT INTO `poll_index` (`fecha_hora`, `max_id`) VALUES
('2016-04-05 05:17:23', '1111111111'),
('2016-04-07 08:24:33', '2222222222'),
('2016-04-11 07:23:23', '3333333333'),
('2016-04-14 10:26:27', '4444444444'),
('2016-05-01 16:00:24', '5555555555');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tweets_index`
--

CREATE TABLE `tweets_index` (
  `tweet_id` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `text` text COLLATE utf8_spanish_ci NOT NULL,
  `user_id` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `user_name` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `followers_count` int(7) NOT NULL,
  `friends_count` int(7) NOT NULL,
  `profile_image_url_https` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tweets_index`
--

INSERT INTO `tweets_index` (`tweet_id`, `text`, `user_id`, `user_name`, `followers_count`, `friends_count`, `profile_image_url_https`) VALUES
('1111111111', 'Hola amigos mios todos estamos en el tweet 1', '1111111111', 'soy el number one', 11000, 1100, 'www.soyelnumberone.com'),
('2222222222', 'Este es el texto del tweet 2', '2222222222', 'soy el number two', 22000, 2200, 'www.soyelnumbertwo.com'),
('3333333333', 'Que pasa que soy el tweet número 3', '3333333333', 'soy el numbre three', 33000, 3300, 'www.soyelnumbrethree.com'),
('4444444444', 'Ya vamos terminando con el tweet 4', '4444444444', 'soy el number four', 44000, 4400, 'www.soyelnombrefour.com'),
('5555555555', 'Vamos que nos vamos con el tweet 5', '555555555', 'soy el number five', 55000, 5500, 'www.soyelnumberfive.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `urls_tweet_index`
--

CREATE TABLE `urls_tweet_index` (
  `ID` int(5) NOT NULL,
  `tweet_id` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `url` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `urls_tweet_index`
--

INSERT INTO `urls_tweet_index` (`ID`, `tweet_id`, `url`) VALUES
(1, '1111111111', 'www.miprimeraurl.com'),
(2, '1111111111', 'www.misegundaurl.com'),
(3, '1111111111', 'www.miterceraurl.com'),
(4, '2222222222', 'www.miprimeraurl.com'),
(5, '2222222222', 'www.misegundaurl.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hashtags_adm_index`
--
ALTER TABLE `hashtags_adm_index`
  ADD PRIMARY KEY (`hashtag_id`);

--
-- Indices de la tabla `hashtags_tweet_index`
--
ALTER TABLE `hashtags_tweet_index`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `hashtag_neighbors_index`
--
ALTER TABLE `hashtag_neighbors_index`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `poll_index`
--
ALTER TABLE `poll_index`
  ADD PRIMARY KEY (`fecha_hora`);

--
-- Indices de la tabla `tweets_index`
--
ALTER TABLE `tweets_index`
  ADD PRIMARY KEY (`tweet_id`);

--
-- Indices de la tabla `urls_tweet_index`
--
ALTER TABLE `urls_tweet_index`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `hashtags_tweet_index`
--
ALTER TABLE `hashtags_tweet_index`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `hashtag_neighbors_index`
--
ALTER TABLE `hashtag_neighbors_index`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `urls_tweet_index`
--
ALTER TABLE `urls_tweet_index`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
