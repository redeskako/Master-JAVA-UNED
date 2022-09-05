<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html"%>
<c:if test="${(sessionScope.idioma==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>
</c:if>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/informacion.html"%>
	<fmt:setLocale value="es_ES" scope="session" />
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/informacionen.html"%>
	<fmt:setLocale value="en_EN" scope="session" />
</c:if>

<fmt:setBundle basename="Etiquetas" var="lang" scope="session" />
 <c:set var="fecha" value="${param.fecha}" />
<div id="main">


<center>
<form onsubmit="return validacioncita(this)"
	action="ServletCita?accion=registraCitaCli" method="post"><br>
<br>
<br>
<br>
<br>
<table class="tabla" align="center">
	<tr>
		<th class="mod1"><fmt:message key="registrarnuevacita" bundle="${lang}"/></th>
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
</table>
<br>
<tr>
	<th>
		<c:if test="${(sessionScope.idioma== 'es_ES')}">
			<input type="image" src="template/enviar.jpg" name="action" value="Registra la cita">
			<a href="MuestraCitasDelDia.jsp?fecha=<c:out value="${fecha}" />"> <img src="template/volver.png" alt="volver" border="none"></a>
  		</c:if>
		<c:if test="${(sessionScope.idioma== 'en_EN')}">
			<input type="image" src="template/send.png" name="action" value="Registra la cita">
			<a href="MuestraCitasDelDia.jsp?fecha=<c:out value="${fecha}" />"> <img src="template/Return.png" alt="Return" border="none"></a>
		</c:if>

	</th>
</tr>
</form>
</center>
</div>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

</html>