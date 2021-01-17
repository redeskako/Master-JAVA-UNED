<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">

<head>
	<title>Gestion de Canales Crawler</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet">
	
	
</head>

<body>
        
    <!-- Contenedor -->
    <div id="contenedor">

		<h1>Gestion de Canales Crawler</h1>
		
		${mensaje}	 
 		<br/>
		<br/>
		<form:form method="POST" modelAttribute="canal" action="addCanal">

			<div>

			   <label for="id">ID Canal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			   <form:input path="id" id="id"/>
			   <form:errors path="id" cssClass="error"/>
			   <br/><br/>

			   <label for="nombre">Nombre Canal&nbsp;</label>
			   <form:input path="nombre" id="nombre"/>
			   <form:errors path="nombre" cssClass="error"/>
			   <br/><br/>
			   
			    <label for="descripcion">Descripcion&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			   <form:input path="descripcion" id="descripcion"/>
			   <form:errors path="descripcion" cssClass="error"/>
			   <br/><br/>

				<input type="submit" value="Aceptar"/>				 
				
			</div>
			
		</form:form>
      <a href="<c:url value='/' />">logout</a>
       
</div>
</body>
</html>