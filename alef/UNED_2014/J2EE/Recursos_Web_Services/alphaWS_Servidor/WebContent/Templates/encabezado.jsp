<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 

	<libTemplate:ocultar rolOcultar="0">
	<div class="log"> <!-- log sesión iniciada -->
		
		<%
		// Obtiene el nombre del usuario que inicio la sesion
		String nUsuario = (String)session.getAttribute("nombreSesion");
		
		%>
		<p>Bienvenid@, <%= nUsuario %></p>
	
		<%
		// Muestra al responsable el enlace para cambiar de rol	
		Integer rolUsuario = (Integer)session.getAttribute("rolSesion");
		Integer vistaUsuario = (Integer)session.getAttribute("vistaSesion");
		
		if (rolUsuario != null && (rolUsuario == 2 && vistaUsuario == 2)){
		%>
			<a href="./CambioPerfil">Rol empleado</a>	
		<%
		} 
		
		if (rolUsuario != null && (rolUsuario == 2 && vistaUsuario == 1)){
		%>
			<a href="./CambioPerfil">Rol responsable</a>
		<%
		}
		%>
		
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
