<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<%@ include file="template/cabecera.html"%>

<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/informacion.html"%>
	<fmt:setLocale value="es_ES" scope="session" />
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/informacionen.html"%>
	<fmt:setLocale value="en_EN" scope="session" />
</c:if>
<c:set var="mes" value="${sessionScope.MesActual}" />

<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<div id="main"><br>
<br>
<br>
<br>

	<center>
	<head>
	<text3>
			<fmt:message key="gestionadmin.gestionadministrador" bundle="${lang}" />
	</text3>
	</head>

	</center>
	<br>
	<br>
	<center>
     <table class="tabla">
		<tr>
		<th class="mod1"><a href="ProcesoClientes?action=ejecuta">
			<fmt:message key="gestionadmin.gestioncliente" bundle="${lang}"/></a></th>
		</tr>
		<br />
		<tr>
			<th class="mod1">
			<c:choose>
				<c:when test="${mes =='1'}">
					<a href="Calendario/January.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='2'}">
					<a href="Calendario/February.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='3'}">
					<a href="Calendario/March.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='4'}">
					<a href="Calendario/April.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='5'}">
					<a href="Calendario/May.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='6'}">
					<a href="Calendario/June.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='7'}">
					<a href="Calendario/July.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='8'}">
					<a href="Calendario/August.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='9'}">
					<a href="Calendario/September.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='10'}">
					<a href="Calendario/October.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='11'}">
					<a href="Calendario/November.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
				<c:when test="${mes =='12'}">
					<a href="Calendario/December.jsp"><fmt:message key="gestionadmin.gestioncita" bundle="${lang}"/></a>
				</c:when>
		</c:choose>

			</th>
		</tr>
        <tr>
        	<th class="mod1">
        	  <a href="ServletCita?accion=estadistica"><fmt:message key="estadisticas" bundle="${lang}"/></a>
        	</th>
        </tr>
	</table>
	</center>
</div>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>


</html>

