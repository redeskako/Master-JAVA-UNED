<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">

<head>
	<title>Canal añadido correctamente</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/estilos.css" type="text/css" rel="stylesheet">	
</head>

<body>

    <!-- Contenedor -->
    <div id="contenedor">

		<!-- Salida sesión usuario -->
		<a class="cerrarSesion" href="Controlador?operacion=logout">Cerrar sesión</a>

		<div id="error">
	        <h1>Canal añadido correctamente</h1>
  			<a href="Controlador?operacion=anade_otro_canal">Añadir otro canal</a>
		</div>
    </div>

</body>
</html>