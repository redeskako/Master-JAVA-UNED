CREATE TABLE IF NOT EXISTS `comercios` (
  `co_id` int(10) NOT NULL auto_increment,
  `co_nombre` varchar(30) NOT NULL default '',
  `co_pais` varchar(30) NOT NULL default '',
  PRIMARY KEY  (`co_id`),
  UNIQUE KEY `co_id` (`co_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23432433 ;

--
-- Volcar la base de datos para la tabla `comercios`
--

INSERT INTO `comercios` (`co_id`, `co_nombre`, `co_pais`) VALUES
(516, 'JUAN PEREZ', 'MEXICO'),
(984, 'ANTONIO RODRIGUEZ', 'MEXICO'),
(996, 'INDALECIO TRAVIANNI', 'ARGENTINA'),
(975, 'ABELARDO SAINZ', 'PERU'),
(111, 'JOSE TOLTACA', 'PERU'),
(332, 'RAIMUNDO ALONSO', 'ARGENTINA'),
(123, 'JUN JUANES', 'BRASIL'),
(585, 'JOAO PAMINHIO', 'BRASIL'),
(23432432, 'JOAQUIN DIAZ', 'ESPAÃ‘A');
