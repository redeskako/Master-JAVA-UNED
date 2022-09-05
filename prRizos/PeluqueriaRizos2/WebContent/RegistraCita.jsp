<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

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
<c:set var="fecha" value="${sessionScope.AtributoFecha}" />
<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<div id="main">


<center>
<form onsubmit="return validacioncita(this)"
	action="ServletCita?accion=registraCita" method="post"><br>
<br>
<br>
<br>
<br>
<table class="tabla">
	<tr>
		<th class="mod1"><fmt:message key="registrarnuevacita" bundle="${lang}"/></th>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="dni" bundle="${lang}"/></td>
		<td class="mod1"><input type="text" name="dni" /></td>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="tiposervicio" bundle="${lang}"/></td>
		<td class="mod1">
		  <select name="tipoServicio">
			<option value="<fmt:message key="corte" bundle="${lang}"/>"><fmt:message key="corte" bundle="${lang}"/></option>
			<option value="<fmt:message key="tinte" bundle="${lang}"/>"><fmt:message key="tinte" bundle="${lang}"/></option>
			<option value="<fmt:message key="mechas" bundle="${lang}"/>"><fmt:message key="mechas" bundle="${lang}"/></option>
			<option value="<fmt:message key="alisadojapones" bundle="${lang}"/>"><fmt:message key="alisadojapones" bundle="${lang}"/></option>
			<option value="<fmt:message key="extensiones" bundle="${lang}"/>"><fmt:message key="extensiones" bundle="${lang}"/></option>
		  </select></td>
	</tr>

	<tr>
		<td class="mod1"><fmt:message key="turno" bundle="${lang}"/>:</td>
		<td class="mod1"><select name="turno">
			<option value="<fmt:message key="turno1" bundle="${lang}"/>"><fmt:message key="turno1" bundle="${lang}"/></option>
			<option value="<fmt:message key="turno2" bundle="${lang}"/>"><fmt:message key="turno2" bundle="${lang}"/></option>
			<option value="<fmt:message key="turno3" bundle="${lang}"/>"><fmt:message key="turno3" bundle="${lang}"/></option>
			<option value="<fmt:message key="turno4" bundle="${lang}"/>"><fmt:message key="turno4" bundle="${lang}"/></option>
			<option value="<fmt:message key="turno5" bundle="${lang}"/>"><fmt:message key="turno5" bundle="${lang}"/></option>
		</select></td>
	</tr>
</table>
<br>
<tr>
	<td>
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<input type="image" src="template/enviar.jpg" name="action" value="Registra la cita"> &nbsp
	<a href="MuestraCitasDelDia.jsp?fecha=<c:out value="${fecha}"/>"> <img src="template/volver.png" alt="volver" border="none"></a>
	</c:if>
	<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<input type="image" src="template/send.png" name="action" value="Registra la cita"> &nbsp
	<a href="MuestraCitasDelDia.jsp?fecha=<c:out value="${fecha}"/>"> <img src="template/Return.png" alt="Return" border="none"></a>
	</c:if>

	</td>
</tr>
</form>
</center>
<br>
<br>
<br>
<br>
</div>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

</html>