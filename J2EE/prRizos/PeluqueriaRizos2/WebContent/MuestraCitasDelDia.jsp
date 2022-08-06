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

<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>


<div id="main">



<center>
<c:if test="${(sessionScope.AtributoTipoUsuario==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>
</c:if>
<c:set var="TipoUsuario" value="${sessionScope.AtributoTipoUsuario}" />
<c:set var="fecha" value="${param.fecha}" />
	<c:if test="${(TipoUsuario =='admin')}">
	<table class="tabla">
		<caption class="mod1"><fmt:message key="citasdeldia" bundle="${lang}"/></caption>
		<tr>
			<th class="mod1"><fmt:message key="turno" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="codigocita" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="tiposervicio" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="dni" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="fecha" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="editar" bundle="${lang}"/></th>
			<th class="mod1"><fmt:message key="borrar" bundle="${lang}"/></th>
		</tr>
		<c:set var="citas" value="${sessionScope.citasdeldia}" />
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
	 </c:if>
	<br>
<center>
<table class="tabla">
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
  		 <a href="ServletCita?accion=anadircita&fechadeldia=<c:out value="${fecha}"/>">
	   <IMG SRC="template/anadir.jpg" align="absmiddle" ALT="AQUI VA UNA IMAGEN" border="none">

	   </a>
   </c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
		<a href="ServletCita?accion=anadircita&fechadeldia=<c:out value="${fecha}"/>">
	   <IMG SRC="template/addquoters.png" align="absmiddle" ALT="AQUI VA UNA IMAGEN" border="none">
	   </a>
</c:if>



 <c:if test="${(TipoUsuario =='admin')}">
		<c:if test="${(sessionScope.idioma== 'es_ES')}">
			<a href="GestionaAdmin.jsp"> <img src="template/volver.png" align="absmiddle" alt="volver" border="none"></a>
  		</c:if>
		<c:if test="${(sessionScope.idioma== 'en_EN')}">
			<a href="GestionaAdmin.jsp"> <img src="template/Return.png" align="absmiddle" alt="Return" border="none"></a>
		</c:if>
</c:if>
</table>
</center>
 <c:if test="${(TipoUsuario =='cliente')}">
	<jsp:forward page="CitasDelDiaCliente.jsp">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>

</c:if>

</div>


<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>


</html>