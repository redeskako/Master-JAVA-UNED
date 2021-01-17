<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator, model.Data, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Styles/mainestilo.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<title>Main</title>
</head>
<body>

<%@ include file="header.jsp" %>

<h1>Seleccion de informacion:</h1>

<form align="center" name="formSelect" action="./GetEstadisticas" method="get">

	<label for="nombre">Seleccion del pais:</label> <br/>
	<select id="pais" name="pais">
  		<option value="" selected="selected">- selecciona pais-</option>
  		<c:forEach items="${listaPaises}" var="paises">
  		<option value="${paises.country_code}"><c:out value="${paises.country_name}" /></option>
  		</c:forEach>
	</select>
	<br/>
	
	<label for="nombre">Seleccion del indicador:</label> <br/>
	<select id="indicador" name="indicador">
  		<option value="" selected="selected">- selecciona Indicador-</option>
  		<c:forEach items="${listaIndicador}" var="indicadores">
  		<option value="${indicadores.indicator_code}"><c:out value="${indicadores.indicator_name}" /></option>
  		</c:forEach>
	</select>
	<br/>
	
	<h4><input type="submit" name="ver" value="Ver">
	
	<c:if test="${!empty param.estado}">
    <c:choose>
  		<c:when test="${param.estado eq 2}">
    		Seleccion incorrecta...</h4>
  		</c:when>
  		<c:when test="${param.estado eq 3}">
    		Datos inexistentes en Data...</h4>
  		</c:when>
  	</c:choose>
	</c:if>
</form>

</body>
</html>