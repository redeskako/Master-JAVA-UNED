<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


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

<fmt:setBundle basename="Etiquetas"/>
<div id="main">



<center>
<br>
<br>
<br>
<br>
<form onsubmit="return validacioneditacita(this)" action="ServletCita?accion=modificaCita" method="post">
<center>
<table class="tabla">
	<tr>
		<th class="mod1"><fmt:message key="editarcita"/></th>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="tiposervicio"/></td>
		<td class="mod1">
		  <select name="tipoServicio">
			<option value="<fmt:message key="corte"/>"><fmt:message key="corte"/></option>
			<option value="<fmt:message key="tinte"/>"><fmt:message key="tinte"/></option>
			<option value="<fmt:message key="mechas"/>"><fmt:message key="mechas"/></option>
			<option value="<fmt:message key="alisadojapones"/>"><fmt:message key="alisadojapones"/></option>
			<option value="<fmt:message key="extensiones"/>"><fmt:message key="extensiones"/></option>
		  </select></td>
	</tr>

	<tr>
		<td class="mod1"><fmt:message key="turno"/>:</td>
		<td class="mod1"><select name="turno">
			<option value="<fmt:message key="turno1"/>"><fmt:message key="turno1"/></option>
			<option value="<fmt:message key="turno2"/>"><fmt:message key="turno2"/></option>
			<option value="<fmt:message key="turno3"/>"><fmt:message key="turno3"/></option>
			<option value="<fmt:message key="turno4"/>"><fmt:message key="turno4"/></option>
			<option value="<fmt:message key="turno5"/>"><fmt:message key="turno5"/></option>
		</select>
		</td>
	</tr>
</table>
<br>
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
		<input type="image" src="template/enviar.jpg">
		   <a href="MuestraCitasDelDia.jsp"> <img src="template/volver.png" alt="volver" border="none"></a>
   </c:if>
	<c:if test="${(sessionScope.idioma== 'en_EN')}">
		<input type="image" src="template/send.png">
	      <a href="MuestraCitasDelDia.jsp"> <img src="template/Return.png" alt="Return" border="none"></a>
	</c:if>


</center>

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