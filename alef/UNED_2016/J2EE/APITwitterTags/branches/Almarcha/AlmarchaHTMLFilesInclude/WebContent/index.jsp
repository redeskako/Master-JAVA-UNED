<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/indexTag.tld" prefix="it" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>	
	<%@ include file="Resources/html/metadatos.html" %>
	<title>Practica Twitter con Clinical Trials</title>
</head>
<body>
<%@ include file="Resources/html/encabezado.html" %>
 <!-- Contenedor -->
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
</body>
</html>