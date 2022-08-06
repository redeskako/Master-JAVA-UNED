<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="es">

<head>
	<title>Cliente REST</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	<style>
		#datosVideo{margin:15px -5px; color:#013adf;}
		#datosVideo .lbl {color:black; font-weight: bold; margin-left: 5px;}
		#datosVideo div {margin-bottom:5px}
		.error {color: #ff0000; margin:15px 0px;}
		.firma{font-style:italic; margin-top:25px;}
	</style>

</head>

<body>
        
    <!-- Contenedor -->
    <div id="contenedor">

		<h1>Cliente REST</h1>
		
		<form:form method="POST" modelAttribute="video">

			<div id="frm">
				<label for="id">Id de video&nbsp;</label>
				<form:input path="id" id="id"/>
				<input type="submit" value="Aceptar"/>
				<form:errors path="id" cssClass="error" element="div"/>
			</div>

			<div class="error">${mensaje}</div>
			
   			<c:if test="${mostrarDatos eq 'true'}">
				<div id="datosVideo">
					<div><span class="lbl">Id: </span>${video.getId()}.</div>
					<div><span class="lbl">Id canal: </span>${video.getIdCanal()}.</div>
					<div><span class="lbl">Titulo: </span>${video.getTitulo()}.</div>
					<div><span class="lbl">Descripci&oacute;n: </span>${video.getDescripcion()}.</div>
					<div><span class="lbl">Fecha de publicaci&oacute;n: </span>${video.getFechaPublicacion()}.</div>
					<div><span class="lbl">Thumbnail: </span>${video.getThumbnail()}.</div>
				</div>
			</c:if>

		</form:form>
		
		<p class="firma">Master Java UNED - 2015.</p>
	
	</div> <!-- Contenedor -->

</body>

</html>