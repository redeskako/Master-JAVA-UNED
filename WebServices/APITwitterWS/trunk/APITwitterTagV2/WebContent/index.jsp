<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/indexTag.tld" prefix="it" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Practica Twitter con Clinical Trials</title>
	<link type="text/css" rel="stylesheet" href="Resources/css/estilos.css" > 
	<link href="Resources/css/stylesHeaderFooter.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="Resources/javascript/comprobarAcceso.js" charset="UTF-8"></script>
</head>
<body>

 <!-- Contenedor -->
 	<div id="faja">
 	<%@ include file="Resources/html/encabezado.html" %>
    <div id="contenedor">
   		<!--  Mostramos al usuario el mensaje correspondiente -->
        <it:userStatusMessage/>
 

			<h1 class="login">Gesti&oacute;n de la aplicaci&oacute;n Twitter y Clinical Trials </h1>
		
		
		<form style="align:'center';" name="formLogin" action="APITwitterLogin?operacion=login" method="post" id="Twitterform">
			<div id="Acceso">
			   <span>Usuario</span>
			   <input type="text" name="txtUsuario" id="user" maxlength="10" size="10" />
			   <span>Clave</span>
			   <input type="password" name="txtPassword" id="password" maxlength="10" size="10" />
			   <input type="submit" name="btn" id="btn" value="Aceptar"/> 
			</div>                 

		</form>
	
		<p class="pie">Master Java UNED - 2016.</p>
	
	</div> <!-- Contenedor -->
	<%@ include file="Resources/html/footer.html" %>
	</div>
</body>
</html>