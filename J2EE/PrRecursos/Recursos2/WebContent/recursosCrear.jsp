<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*, java.util.*" %>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
</head>
<body>

<lib:cambioVista visible="2">
	<!-- Si intenta acceder un responsable con vista empleado, se le cambia de vista -->
	<c:redirect url="./CambioPerfil"/>
</lib:cambioVista>

<lib:ilegal rolIlegal="0">
	<!-- Si intenta acceder un anónimo: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

<lib:ilegal rolIlegal="1">
	<!-- Si intenta acceder un empleado: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>


	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
		
			<%
			String idNuevo = request.getParameter("idNuevo");
	
			if (!idNuevo.equals("null") && !idNuevo.equals("0")) {
				String nombre = request.getParameter("nombre");
			%>

			<div class="info">
				El recurso "<%=nombre%>" con id <%=idNuevo%> se ha creado correctamente.
			</div> <!-- fin info -->
			<%
			}
			if (!idNuevo.equals("null") && idNuevo.equals("0"))  {
			%>

			<div class="info_error">
				El recurso no se ha creado correctamente.
			</div> <!-- fin info_error -->
			<%
			}
			%>
		
			<h4>Crear nuevo recurso:</h4>
			
			<form name="formCrearRecurso" action="./AccionesRecursos" method="get">
				<label>Descripci&oacute;n: </label>
				<input type="text" name="descripcion" size="50" required aria-required="true">
				<input type="hidden" name="tipo" value="crear" >


				<input class="btn" type="submit" name="Crear" value="Crear">
			</form>
	
		</div>
		<!-- end content -->

	</div>
	<!-- end container -->
	
</body>
</html>