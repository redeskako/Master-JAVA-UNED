<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="vistaVideo" scope="request" type="beans.VistaVideo"/>

<html lang="es">

<head>
	<title>Lista de videos del canal<%=vistaVideo.getNombreVideo()%>.</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="Pragma" content="no-cache">
	<!-- <link href="/css/estilos.css" type="text/css" rel="stylesheet"> -->
	
	<!-- Javascript -->
	<script language="javascript">
	
		function volver()
		{
    		window.history.back();
		}
	
	</script>

	<!-- Estilos -->
	<style>
	
	body
	{
	    font-family: "Century Gothic", verdana, helvetica, arial, sans-serif;
	}

	/* Contenedor de la página */
	#contenedor
	{
    	width: 700px;
    	margin: 10px auto;
	}
	
	h1
	{
		margin-bottom: 10px;
		text-align: center;
	}
	
	.leyenda
	{
		color:red;
	}

	.indice
	{
		color:navy;
		text-align: center;
		margin-bottom: 30px;
		margin-top: 30px;
	}
	
	.datos
	{
		width: 100%;
	}

	.video
	{
		text-align: center;
	}
		
	</style>

</head>
	
<body>

    <!-- Contenedor -->
    <div id="contenedor">
    
		<h1>Reproducción de videos.</h1>

		<div class="datos"><%=vistaVideo.getDatosVideo()%></div>
		
 		<div class="video"><iframe width="700" height="525" src="http://www.youtube.com/embed/<%=vistaVideo.getIdVideo()%>?autoplay=1"></iframe></div> 

		<div class="indice"><a href="javascript:volver();">Volver a la lista de videos</a></div>

	</div>
	
</body>
	
</html>