<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="es">

<head>
	<title>Lista de videos del canal ${id}.</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="Pragma" content="no-cache">
	
	<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet">
	
	<!-- Javascript -->
	<script language="javascript">
	
		function volver()
		{
    		window.history.back();
		}
	
	</script>


</head>
	
<body>

    <!-- Contenedor -->
    <div id="contenedor">
    
		<h1>Reproducción de videos.</h1>
		
 		<div class="video"><iframe width="700" height="525" src="http://www.youtube.com/embed/${id}?autoplay=1"></iframe></div> 

		<div class="indice"><a href="javascript:volver();">Volver a la lista de videos</a></div>

	</div>
	
</body>
	
</html>