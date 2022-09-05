<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">

<head>
	<title>Youtube Crawler</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet">
	
</head>

<body>
        
    <!-- Contenedor -->
    <div id="contenedor">

		<h1>YouTube Crawler</h1>
		
		<form:form method="POST" modelAttribute="usuario">

			<div>

			   <label for="user">Usuario&nbsp;</label>
			   <form:input path="user" id="user"/>
			   <form:errors path="user" cssClass="error"/>
			   <br/><br/>

			   <label for="password">Clave&nbsp;&nbsp;&nbsp;&nbsp;</label>
			   <form:input path="password" id="password"/>
			   <form:errors path="password" cssClass="error"/>
			   <br/><br/>

				<input type="submit" value="Aceptar"/>
			</div>
			
		</form:form>
		
		<p>Master Java UNED - 2015.</p>
	
	</div> <!-- Contenedor -->

</body>

</html>