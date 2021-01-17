<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="vistaVideos" scope="request" type="beans.VistaVideos"/>

<html lang="es">

<head>
	<title>Lista de videos del canal<%=vistaVideos.getNombreCanal()%>.</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/estilos.css" type="text/css" rel="stylesheet">	
</head>
	
<body>

    <!-- Contenedor -->
    <div id="contenedor">
    
		<!-- Salida sesión usuario -->
		<a class="cerrarSesion" href="Controlador?operacion=logout">Cerrar sesión</a>
    
		<h1>Lista de videos del canal <%=vistaVideos.getNombreCanal()%>.</h1>
		
		<p class="leyenda">Pulse sobre las imágenes para ver los videos.</p>

		<div><a href="Controlador?operacion=lista_canales">Volver a la lista de canales</a></div>
		
		<div class="indice"><%=vistaVideos.getIndice()%></div>

		<div class="lista"><%=vistaVideos.getLista()%></div>
		
		<div class="indice"><%=vistaVideos.getIndice()%></div>
		
		<a href="Controlador?operacion=lista_canales">Volver a la lista de canales</a>

	</div>
	
</body>
	
</html>