<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


<link rel="stylesheet" href="css/template.css" type="text/css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html"%>

<c:if test="${(sessionScope.idioma== null)}">
	<%@ include file="template/informacion.html"%>
</c:if>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/informacion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/informacionen.html"%>
</c:if>


<c:if test="${(sessionScope.idioma== null)}">
	<fmt:setLocale value="${'es_ES'}" scope="session" />
</c:if>
<c:if test="${(sessionScope.idioma!= null)}">
	<fmt:setLocale value="${sessionScope.idioma}" scope="session" />
</c:if>
<fmt:setBundle basename="Etiquetas" var="lang" scope="session"/>

<div id="main">
<form onsubmit="return validacion(this)" action="ProcesoDatosClientes?action=insertar" method="post">
<center>
<table class="tabla" align="left">
	<tr>
		<th class="mod1"><fmt:message key="datospersonales" bundle="${lang}"/></th>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="nombre" bundle="${lang}"/>: <input type="text"
			name="nombre" /></td>
		<td class="mod1"><fmt:message key="apellido1" bundle="${lang}"/>: <input type="text"
			name="apellido1" /></td>
		<td class="mod1"><fmt:message key="apellido2" bundle="${lang}"/>: <input type="text"
			name="apellido2" /></td>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="dni" bundle="${lang}"/>: <input type="text" name="dni" />
		</td>
		<td class="mod1"><fmt:message key="telefono" bundle="${lang}"/>: <input type="text" name="telefono" /></td>
	</tr>
</table>
</center>
<center>
<table class="tabla" align="left">
	<tr>
		<th class="mod1"><fmt:message key="datosdireccion" bundle="${lang}"/></th>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="direccion" bundle="${lang}"/>: <input type="text" name="direccion" /></td>
		<td class="mod1"><fmt:message key="codigopostal" bundle="${lang}"/>: <input type="text" name="codigopostal" onkeypress="return validar(event)" /></td>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="localidad" bundle="${lang}"/>: <input type="text"
			name="localidad" /></td>
		<td class="mod1"><fmt:message key="provincia" bundle="${lang}"/>: <input type="text"
			name="provincia" /></td>
	</tr>
</table>
</center>
<center>
<table class="tabla" align="left">
	<tr>
		<th class="mod1"><fmt:message key="otros" bundle="${lang}"/></th>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="observaciones" bundle="${lang}"/>: <textarea
			name="observaciones" rows=2 cols=30></textarea></td>
		<td class="mod1"><fmt:message key="tipousuario" bundle="${lang}"/>: <SELECT NAME="tipoUsuario" size="1">
			<option value="cliente"><fmt:message key="cliente" bundle="${lang}"/></option>
			<option value="admin" DISABLED><fmt:message key="administrador" bundle="${lang}"/></option>
		</SELECT></td>
	</tr>
	<tr>
		<td class="mod1"><fmt:message key="usuario" bundle="${lang}"/>: <input type="text"
			name="usuario" /></td>
		<td class="mod1"><fmt:message key="contraseña" bundle="${lang}"/>: <input type="text" name="pass" />
		</td>
	</tr>

	<tr>
		<td class="mod1"><fmt:message key="idioma" bundle="${lang}"/>: <SELECT NAME="locale" size="1">
			<option value="es_ES"><fmt:message key="español" bundle="${lang}"/></option>
			<option value="en_EN"><fmt:message key="ingles" bundle="${lang}"/></option>
		</SELECT></td>
	</tr>
</table>
</center>
</div>

<br>
<div id="botonvolver">
<c:if test="${(sessionScope.AtributoTipoUsuario== null)}">
	<a href="Bienvenida.jsp"> <img src="template/volver.png" alt="volver" border="none"></a>
</c:if>


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
  <td>
  
	<c:if test="${((sessionScope.idioma== 'es_ES') || (sessionScope.idioma==null))}">
		<input  type="image" src="template/enviar.jpg" name="submit" value="editar"/>
   </c:if>
	<c:if test="${(sessionScope.idioma== 'en_EN')}">
		<input  type="image" src="template/send.png" name="submit" value="editar"/>
	</c:if>
  </td>
</tr>

</form>

</div>
<c:if test="${(sessionScope.idioma== null)}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>
<c:if test="${(sessionScope.idioma== 'es_ES')}">
	<%@ include file="template/cerrarsesion.html"%>
</c:if>

<c:if test="${(sessionScope.idioma== 'en_EN')}">
	<%@ include file="template/cerrarsesionen.html"%>
</c:if>

</html>