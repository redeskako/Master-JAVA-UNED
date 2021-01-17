<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date"%>
<%@ page import="es.rizos.beansCitas.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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

<fmt:setBundle basename="Etiquetas" var="lang" scope="session" />

<div id="main">


<center>
 <c:set var="fecha" value="${param.fecha}" /><br>
<text3><fmt:message key="citasactual" bundle="${lang}"/></text3> <br>
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
	<c:set var="citas" value="${sessionScope.citasdeldiacliente}" />
	<c:forEach var="cita" items="${citas}">
		<tr>
			<td class="mod1"><c:out value="${cita.turno}" /></td>
			<td class="mod1"><c:out value="${cita.codigoCita}" /></td>
			<td class="mod1"><c:out value="${cita.tipoServicio}" /></td>
			<td class="mod1"><c:out value="${cita.dni}" /></td>
			<td class="mod1"><c:out value="${cita.fecha}" /></td>
			<td class="mod1">
				<a href="ServletCita?accion=editarCita&codigocita=<c:out value="${cita.codigoCita}"/>&fechadeldia=<c:out value="${cita.fecha}"/>">
				<IMG SRC="template/shapes062.gif" ALT="AQUI VA UNA IMAGEN"></a></td>
			<td class="mod1">
				<a href="ServletCita?accion=borrarCita&codigocita=<c:out value="${cita.codigoCita}"/>">
				<IMG SRC="template/shapes033.gif" ALT="AQUI VA UNA IMAGEN"></a>
			</td>
		</tr>
	</c:forEach>
	<br>
</table>
<br>
<br>
<font size="4" color="yellow"><fmt:message key="turnoslibres" bundle="${lang}"/>:</font> <br>
<br>

  <c:set var="turnos" value="${sessionScope.turnosNoReservados}" />
   <c:forEach var="turno" items="${turnos}">
		<font size="4" color="yellow"><c:out value="${turno}" /> </font>
		<a href="ServletCita?accion=anadircita&fechadeldia=<c:out value="${fecha}"/>&turno=<c:out value="${turno}"/>">
			<c:if test="${(sessionScope.idioma== 'es_ES')}">
				<IMG SRC="template/enviar.jpg" align="absmiddle" ALT="AQUI VA UNA IMAGEN">
   			</c:if>
			<c:if test="${(sessionScope.idioma== 'en_EN')}">
				<IMG SRC="template/send.png" align="absmiddle" ALT="AQUI VA UNA IMAGEN">
			</c:if>
			</a>
	<br>
   </c:forEach>
</center>
<div id="botonvolver">
<c:if test="${(sessionScope.idioma== 'es_ES')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<a href="GestionaCli.jsp?action=ejecuta"><img src="template/volver.png" alt="volver" border="none"></a>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<a href="GestionaCli.jsp?action=ejecuta"> <img src="template/Return.png" alt="Return" border="none"></a>
</c:if>
</div>

<br>
<div id="botoninsertar">

<c:if test="${(sessionScope.idioma== 'es_ES')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<input type="image" src="template/horario.png" alt="Horario" ONCLICK="window.open('Horario.jsp','Ejemplo','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=yes,width=400,height=300')">
</c:if>
<c:if test="${(sessionScope.idioma== 'en_EN')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<input type="image" src="template/hours.png" alt="Horario" ONCLICK="window.open('Horario.jsp','Ejemplo','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=yes,width=400,height=300')">
</c:if>

</div>
</div>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>
<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

</html>