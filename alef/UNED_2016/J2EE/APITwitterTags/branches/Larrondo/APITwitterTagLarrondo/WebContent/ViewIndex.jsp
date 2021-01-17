<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.Tweet"%>
<%@ page import = "uned.java2016.apitwitter.dao.ClinicalStudy"%>
<%@ taglib uri="WEB-INF/ViewIndexTag.tld" prefix="usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="css/ViewIndex.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/ViewIndex.js"></script>
		<title>Vista Clientes</title>
	</head>
	<body>
		<usuario:inicializar_valores/>
		<div id="faja">
				<header>
				</header>
				<div id="centro">
					<div id="capa_buscar" class="misZonas">
						<div id="zonaApiTwitter">
							<img src="imagenes/zona_buscar/ApiTwitter.png" alt="ApiTwitter" />
						</div>
						<div id="zonaHashtag">
							<h5>Hashtag:</h5>
							<select name="buscadorHastags" id="buscadorHashtags" class="select_busqueda">
								<option value="" disabled="disabled" selected="selected" class="option_inicial_busqueda">
									Introduce aquí tu búsqueda
								</option>
								<usuario:bucle_hashtags/>							
							</select>
						</div>
						<div id="zonaUsuario">
							<h5>Usuario: &#160;</h5>
							&#160;&#160;&#160;&#160;
							<p>
								<usuario:imprimir_usuario/>
							</p>
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
							<a href="APITwitterLogin?operacion=logout" target="_self">Cerrar sesión</a>
						</div>
						<usuario:crear_enlaces/>
						</div>
						<usuario:crear_tweets/>
						<usuario:crear_vecinos/>
						<usuario:crear_estudios/>
					</div>
			<footer>
			</footer>		
		</div>
	</body>
</html>