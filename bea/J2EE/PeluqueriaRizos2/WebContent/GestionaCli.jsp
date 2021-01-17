<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  errorPage="error.jsp"%>
<%@ page import="es.rizos.beansUsuario.*"%>
<%@ page import="java.util.*"%>
<%@ page import="es.rizos.beansCitas.*"%>
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

<%-- --%>
<c:set var="id" value="${sessionScope.id}" />
<c:set var="totalpaginas" value="${sessionScope.paginasCitasCliente}" />
<c:set var="citas" value="${sessionScope.AtributoCitas}" />
<c:set var="dniSesion" value="${sessionScope.Atributodni}" />
<c:set var="paginado" value="${param.paginado}" />
<c:set var="mes" value="${sessionScope.MesActual}" />

<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<head>
<div id="main">

<title>GestionaCliente</title>
</head>
<center><br>
<br>
	<text3><fmt:message key="citascliente" bundle="${lang}"/></text3> <br>
<br>
<br>
<br>
</center>
<center>

<table class="tabla">
	<tr>
		<th class="mod1"><fmt:message key="turno" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="codigocita" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="tiposervicio" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="dni" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="fecha" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="editar" bundle="${lang}"/></th>
		<th class="mod1"><fmt:message key="borrar" bundle="${lang}"/></th>
	</tr>

	<c:forEach var="cita" items="${citas}">
		<tr>
			<td class="mod1"><c:out value="${cita.turno}" /></td>
			<td class="mod1"><c:out value="${cita.codigoCita}" /></td>
			<td class="mod1"><c:out value="${cita.tipoServicio}" /></td>
			<td class="mod1"><c:out value="${cita.dni}" /></td>
			<td class="mod1"><c:out value="${cita.fecha}" /></td>
			<c:choose>
				<c:when test="${(cita.dni == dniSesion)&&(cita.activo =='1')}">
					<td class="mod1">
						<a href="ServletCita?accion=editarCita&codigocita=<c:out value="${cita.codigoCita}"/>&fechadeldia=<c:out value="${cita.fecha}"/>">
						<IMG SRC="template/shapes062.gif" ALT="AQUI VA UNA IMAGEN"></a>
					</td>
					<td class="mod1">
						<a href="ServletCita?accion=borrarCita&codigocita=<c:out value="${cita.codigoCita}"/>">
						<IMG SRC="template/shapes033.gif" ALT="AQUI VA UNA IMAGEN"></a>
					</td>
				</c:when>
				<c:when test="${(cita.dni == dniSesion)&&(cita.activo =='0')}">
					<td class="mod1"><IMG SRC="template/shapes063.GIF" ALT="AQUI VA UNA IMAGEN"></td>
					<td class="mod1 "><IMG SRC="template/shapes0343.gif" ALT="AQUI VA UNA IMAGEN"></td>
				</c:when>
			</c:choose>
		</tr>
	</c:forEach>
</table>
<c:if test="${totalpaginas>'0'}">
<c:forEach var="i" begin="0" end="${totalpaginas}">
	<td class="mod1">
		<a href="ProcesaLogado2?paginado=<c:out value="${i}"/>"><c:out value="${i}" /></a><text1>-</text1></td>

</c:forEach>
</c:if>
<br>
<br>
<br>
<br>
<tr>
	<td class="mod1">
		<a href="ProcesoDatosClientes?action=obtenerCliente&campo=<c:out value="${id}"/>">
		<text3><fmt:message key="modificarsusdatos" bundle="${lang}"/></text3></a></td>
</tr>
<br>
<tr>
	<td class="mod1">
		<c:choose>
				<c:when test="${mes =='1'}">
					<a href="Calendario/January.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='2'}">
					<a href="Calendario/February.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='3'}">
					<a href="Calendario/March.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='4'}">
					<a href="Calendario/April.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='5'}">
					<a href="Calendario/May.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='6'}">
					<a href="Calendario/June.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='7'}">
					<a href="Calendario/July.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='8'}">
					<a href="Calendario/August.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='9'}">
					<a href="Calendario/September.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='10'}">
					<a href="Calendario/October.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='11'}">
					<a href="Calendario/November.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
				<c:when test="${mes =='12'}">
					<a href="Calendario/December.jsp"><text3><fmt:message key="pedircitas" bundle="${lang}"/></text3></a>
				</c:when>
		</c:choose>
	</td>
</tr>

</div>
</center>
<br>
<br>
<br>
<br>
<br>
<br>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

<%@ include file="template/pie.html"%>
</html>