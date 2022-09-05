<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  errorPage="error.jsp"%>

<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<%@ page import="es.rizos.librerias.*"%>
<%@ page import="es.rizos.beansClientes.*" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>

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

<c:if test="${(sessionScope.clienteeditar==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
		<jsp:param name="fecha" value="${fecha}" />
	</jsp:forward>

</c:if>
<c:if test="${(sessionScope.clienteeditar!=null)}">
<c:set var="cliente" value="${sessionScope.clienteeditar}" />

<form  onsubmit="return validacion(this)" action="ProcesoDatosClientes?action=editar" method="post" >
<center>
<table class="tabla" align="left">
 <tr>
  <th class="mod1"><fmt:message key="datospersonales" bundle="${lang}"/></th>
 </tr>
 <tr>
  <td class="mod1">
   <fmt:message key="nombre" bundle="${lang}"/>:
    <input type="text" name="nombre" value="<c:out value="${cliente.nombre}"/>"/>
  </td>
  <td class="mod1">
    <fmt:message key="apellido1" bundle="${lang}"/>:
    <input type="text" name="apellido1" value="<c:out value="${cliente.apellido1}"/>"/>
  </td>
  <td class="mod1">
  <fmt:message key="apellido2" bundle="${lang}"/>:
  <input type="text" name="apellido2" value="<c:out value="${cliente.apellido2}"/>"/>
  </td>
 </tr>
 <tr>
   <td class="mod1">
   <fmt:message key="dni" bundle="${lang}"/>:
   <input type="text" name="dni" value="<c:out value="${cliente.dni}"/>"/>
  </td>
  <td class="mod1">
   <fmt:message key="telefono" bundle="${lang}"/>:
   <input type="text" name="telefono" value="<c:out value="${cliente.telefono}"/>"/>
  </td>
 </tr>
</table>
</center>

<center>
<table class="tabla" align="left">
<tr>
<th class="mod1" ><fmt:message key="datosdireccion" bundle="${lang}"/></th>
</tr>
<tr>
<td class="mod1" >
<fmt:message key="direccion" bundle="${lang}"/>:
<input type="text" name="direccion" value="<c:out value="${cliente.direccion}"/>"/>
</td>
<td class="mod1" >
<fmt:message key="codigopostal" bundle="${lang}"/>:
<input type="text" name="codigopostal" onkeypress="return validar(event)" value="<c:out value="${cliente.codigopostal}"/>"/>
</td>
</tr>
<tr>
<td class="mod1" >
<fmt:message key="localidad" bundle="${lang}"/>:
<input type="text" name="localidad" value="<c:out value="${cliente.localidad}"/>"/>
</td>
<td class="mod1">
<fmt:message key="provincia" bundle="${lang}"/>:
<input type="text" name="provincia" value="<c:out value="${cliente.provincia}"/>"/>
</td>
</tr>
</table>
</center>

<center>
<table class="tabla" align="left">
<th class="mod1" ><fmt:message key="otros" bundle="${lang}"/></th>
<tr>
<td class="mod1" >
<fmt:message key="observaciones" bundle="${lang}"/>:
<textarea name="observaciones" rows=2 cols=30 ><c:out value="${cliente.observaciones}"/></textarea>
</td>
<td class="mod1" class="mod1">
<fmt:message key="id" bundle="${lang}"/>:
<input type="text" name="id" onkeypress="return validar(event)" value="<c:out value="${cliente.id}"/>"/>
</td>
</tr>
<tr>
<td class="mod1" >
<fmt:message key="usuario" bundle="${lang}"/>:
<input type="text" name="usuario" value="<c:out value="${cliente.usuario}"/>"/>
</td>
<td class="mod1" >
<fmt:message key="contraseña" bundle="${lang}"/>:
<input type="text" name="pass" value="<c:out value="${cliente.pass}"/>"/>
</td>
</tr>
<tr>
<td class="mod1">
<fmt:message key="idioma" bundle="${lang}"/>:
<SELECT NAME="locale" size="1">
	<option value="es_ES" ><fmt:message key="español" bundle="${lang}"/> </option>
	<option value="en_EN" ><fmt:message key="ingles" bundle="${lang}"/></option>
</SELECT>
</td>
</tr>
</center>
</table>
</div>
<div id="botonvolver">

<c:if test="${(sessionScope.idioma== 'es_ES')&&(sessionScope.AtributoTipoUsuario== 'admin')}">
	<a href="MantenimientoUsuarios2.jsp"> <img src="template/volver.png" alt="volver" border="none"></a>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')&&(sessionScope.AtributoTipoUsuario== 'admin')}">
	<a href="MantenimientoUsuarios2.jsp"><img src="template/Return.png" alt="Return" border="none"></a>
</c:if>
<c:if test="${(sessionScope.idioma== 'es_ES')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<a href="GestionaCli.jsp"><img src="template/volver.png" alt="volver" border="none"> </a>

</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')&&(sessionScope.AtributoTipoUsuario== 'cliente')}">
	<a href="GestionaCli.jsp"> <img src="template/Return.png" alt="Return" border="none"></a>

</c:if>



</div>
<br>
<div id="botoninsertar">
<tr>
<tr>
<td>
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
		<input  type="image" src="template/enviar.jpg" name="submit" value="editar"/>
   </c:if>
	<c:if test="${(sessionScope.idioma== 'en_EN')}">
		<input  type="image" src="template/send.png" name="submit" value="editar"/>
	</c:if>
</td>
</tr>

</form>
</c:if>
</div>
&nbsp &nbsp &nbsp

<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>


</html>