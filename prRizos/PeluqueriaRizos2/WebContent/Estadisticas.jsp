<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
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
<center>
<div id="main" onLoad="crearGrafico">
<br>
<c:if test="${(sessionScope.tiposervicios==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>
</c:if>
<c:set var="tiposervicios" value="${sessionScope.tiposervicios}" />
   <table class="tabla">
   		   <th class="mod1">  <fmt:message key="tiposervicio" bundle="${lang}"/></th>
		   <th class="mod1">  <fmt:message key="porcentaje" bundle="${lang}"/></th>
		<c:forEach var="tiposervicio" items="${tiposervicios}">
		<tr class="mod1">
			<td class="mod1"><c:out value="${tiposervicio.tipoServicio }"/></td>
			<td class="mod1"><c:out value="${tiposervicio.porcentaje }"/> %</td>

	    	<td class="mod2">
	    		<table class="mod2" width="${tiposervicio.porcentaje}%" bgcolor=${tiposervicio.color} HEIGHT="50%">
	    		  <tr><td > </td></tr>
       			 </table>
       		</td>
         </tr>

	    </c:forEach>
</table>
<br><br>

<tr>
<td>
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
       <text3> <a href="GestionaAdmin.jsp"><img src="template/volver.png" alt="volver" border="none"></a></text3>
   </c:if>

   <c:if test="${(sessionScope.idioma== 'en_EN')}">

		<text3><a href="GestionaAdmin.jsp"> <img src="template/Return.png" alt="Return" border="none"></a></text3>
	</c:if>
</td>
</tr>



</div>
</center>

<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>


</body>
</html>