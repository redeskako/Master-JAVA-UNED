<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<libTemplate:ocultar rolOcultar="0">
	<div class="log"> <!-- log sesión iniciada -->
		
		<c:set var="nUsuario" value="<%= (String)session.getAttribute(\"nombreSesion\") %>"></c:set>

		<p>Bienvenid@, <c:out value="${ nUsuario }"/></p>
		
		<a href="./SalidaUsuario" class="cerrar">Cerrar sesión</a>
		
	</div> <!-- fin log sesión iniciada -->
	</libTemplate:ocultar>

	<libTemplate:mostrar rolMostrar="0">
	<div class="log">  <!-- log sesión no iniciada-->
		<form name="formEntrar" action="./AccesoUsuario" method="get">
			<div class="logCampo">
				<label>Usuario</label>
				<input type="text" name="usuario" placeholder="usuario" size="10" required aria-required="true">
			</div>
			<div class="logCampo">
				<label>Contrase&ntilde;a</label>
				<input type="password" name="contrasena" placeholder="contrase&ntilde;a" size="10" required aria-required="true">
			</div>
			<input class="logBtn" type="submit" name="Entrar" value="Entrar">
		</form>
	</div> <!-- fin sesión no iniciada-->
	</libTemplate:mostrar>
	
	<div>
	<h3>APLICACIÓN SUCURSAL</h3>
	</div>
