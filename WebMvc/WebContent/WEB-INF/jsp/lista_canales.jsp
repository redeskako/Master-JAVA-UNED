<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="vistaCanales" scope="request" type="beans.VistaCanales"/>

<html lang="es">

<head>
	<title>Lista de canales disponibles.</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/estilos.css" type="text/css" rel="stylesheet">
</head>
	
<body>

    <!-- Contenedor -->
    <div id="contenedor">
		
		<!-- Salida sesión usuario -->
		<a class="cerrarSesion" href="Controlador?operacion=logout">Cerrar sesión</a>
    
		<h1>Lista de canales disponibles.</h1>

		<p class="leyenda">Pulse sobre los enlaces para ver las listas de videos.</p>

		<div class="lista"><%=vistaCanales.getLista()%></div>		

	</div>

</body>

</html>