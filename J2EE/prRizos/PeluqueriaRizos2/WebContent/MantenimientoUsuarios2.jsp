<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.Exception"%>


<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<%@ page import="es.rizos.beansClientes.*"%>

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

<c:set var="ListaClientes" value="${sessionScope.cliente}" />
<c:if test="${(sessionScope.cliente !=null)}">

<c:set var="ListaClientes" value="${sessionScope.cliente}" />
<c:if test="${(sessionScope.textobusqueda !=null)}">
<c:set var="textobusqueda" value="${sessionScope.textobusqueda}" />
</c:if>
<c:if test="${(sessionScope.campo !=null)}">
</c:if>
<c:if test="${(sessionScope.campobusqueda !=null)}">
<c:set var="campobusqueda" value="${sessionScope.campobusqueda}" />
</c:if>
<c:set var="accion" value="${param.action}" />
<c:if test="${(param.action !=null)}">
</c:if>
<br>
<table class="tabla">
<form action="ProcesoClientes" method="post">

<text2><fmt:message key="mantenimiento.busqueda" bundle="${lang}"/></text2><INPUT type="text" name="textobusqueda">
<text2><fmt:message key="mantenimiento.campo" bundle="${lang}"/></text2>
<SELECT NAME="campobusqueda" size="1">
	<option value="nombre" ><fmt:message key="nombre" bundle="${lang}"/></option>
	<option value="apellido1"><fmt:message key="apellido1" bundle="${lang}"/> </option>
	<option value="apellido2"><fmt:message key="apellido2" bundle="${lang}"/> </option>
	<option value="dni"><fmt:message key="dni" bundle="${lang}"/> </option>

</SELECT>
<INPUT TYPE="submit" name="action" value="<fmt:message key="buscar" bundle="${lang}"/>">
</form>
</table>

<table class="tabla">
		<caption class="mod1"><fmt:message key="mantenimiento.listado" bundle="${lang}"/></caption>
		<tr>
		<th class="mod1">
			<fmt:message key="mantenimiento.idcliente" bundle="${lang}"/>
		</th>
		<th class="mod1">
			<a href="ProcesoClientes?action=ordenar&campo=nombre" ><fmt:message key="nombre" bundle="${lang}"/> </a>
		</th>
		<th class="mod1">
			<a href="ProcesoClientes?action=ordenar&campo=apellido1"><fmt:message key="apellido1" bundle="${lang}"/> </a>
		</th>
		<th class="mod1">
			<a href="ProcesoClientes?action=ordenar&campo=apellido2"><fmt:message key="apellido2" bundle="${lang}"/></a>
		</th>
		<th class="mod1">
			<a href="ProcesoClientes?action=ordenar&campo=dni"><fmt:message key="dni" bundle="${lang}"/> </a>
		</th>
		<th class="mod1">
			<fmt:message key="editar" bundle="${lang}"/>
		</th>
		<th class="mod1">
 			<fmt:message key="eliminar" bundle="${lang}"/>
		</th>
	</tr>
<c:forEach var="Cliente" items="${ListaClientes}">
<tr>
<td class="mod1">
		<c:out value="${Cliente.id}"/>
</td>
<td class="mod1">
		<c:out value="${Cliente.nombre}"/>
</td>
<td class="mod1">
		<c:out value="${Cliente.apellido1}"/>
</td>
<td class="mod1">
		<c:out value="${Cliente.apellido2}"/>
</td>
<td class="mod1">
		<c:out value="${Cliente.dni}"/>
</td>
<td class="mod1">
 <a href="ProcesoDatosClientes?action=obtenerCliente&campo=<c:out value="${Cliente.id}"/>">
 <img src="template/shapes062.gif" alt="editar">
 </a>
</td>
<td class="mod1">
 <a href="ProcesoClientes?action=eliminar&campo=<c:out value="${Cliente.id}"/>">
  <img src="template/shapes033.gif" alt="eliminar">
 </a>
</td>
</tr>
</c:forEach>
</table>
</center>
<br>
<center><table>
<tr>
<c:set var="totalpaginas" value="${sessionScope.paginasClientes}" />
<c:forEach var="i" begin="0" end= "${totalpaginas}">
<c:choose>
		<c:when test="${(accion == 'ejecuta')}">
	        <td><c:out value="${fecha}"/>
               <a href="ProcesoClientes?action=<c:out value="${accion}"/>&paginado=<c:out value="${i}"/>" target="_self"><c:out value="${i}"/></a><text1>-</text1>
            </td>
		</c:when>
		<c:when test="${(accion == 'ordenar')}">
            <td>
                <a href="ProcesoClientes?action=<c:out value="${accion}"/>&paginado=<c:out value="${i}"/>" target="_self"><c:out value="${i}"/></a><text1>-</text1>
            </td>
        </c:when>
        <c:when test="${(accion == 'buscar')}">
            <td>
                <a href="ProcesoClientes?action=<c:out value="${accion}"/>&campobusqueda=action=<c:out value="${campobusqueda}"/>&textobusqueda=action=<c:out value="${textobusqueda}"/>&paginado=<c:out value="${i}"/>" target="_self"><c:out value="${i}"/></a><text1>-</text1>
            </td>
		</c:when>
		<c:when test="${(accion == 'ejecuta')}">
            <td>
                <a href="ProcesoClientes?action=<c:out value="${accion}"/>&paginado=<c:out value="${i}"/>" target="_self"><c:out value="${i}"/></a><text1>-</text1>
            </td>
        </c:when>
</c:choose>
</c:forEach>
</c:if >
</tr>
</table></center>

<center>
<tr>

<td><br>
	<c:if test="${(sessionScope.idioma== 'es_ES')}">
		<a href="AltaCliente.jsp"> <img src="template/anadircliente.jpg" alt="insertar" border="none"></a>
          &nbsp
       <a href="GestionaAdmin.jsp"><img src="template/volver.png" alt="volver" border="none" ></a>
   </c:if>

   <c:if test="${(sessionScope.idioma== 'en_EN')}">
		<a href="AltaCliente.jsp"> <img src="template/addcustomer.png" alt="insertar" border="none"></a>
			&nbsp
		<a href="GestionaAdmin.jsp"> <img src="template/Return.png" alt="Return" border="none" ></a>
	</c:if>



</td>
</tr>
</center>
<c:if test="${(sessionScope.cliente ==null)}">
	<jsp:forward page="Bienvenida.jsp?valido=-4">
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