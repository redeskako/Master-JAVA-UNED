<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.clasesCliente.*, java.util.*"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WS Gesti&oacute;n de Recursos</title>

<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
</head>
<body>
<!-- start container -->
<div id="container">
	<jsp:include page="Template/encabezado.jsp"></jsp:include>
	<jsp:include page="Template/menu.jsp"></jsp:include>
	
	<!-- start content -->
	<div id="content">
		<h2>¡¡Bienvenido al Servicio Web Gesti&oacute;n de Recursos - versión Cliente!!</h2>
	</div>
	<!-- end content -->
	
	<!-- info -->
	<c:set var="estado" value="<%= request.getParameter(\"estado\") %>"></c:set>
	
	<c:if test="${ estado != null  && estado.equals(\"accesoEmpleado\") }">
		<script type="application/javascript">
			alert('Ha accedido un empleado');
		</script>
	</c:if>
	
	<c:if test="${ estado != null  && estado.equals(\"ilegal\") }">
		<script type="application/javascript">
			alert('No tiene acceso a esa página, inicie sesión.');
			document.formEntrar.usuario.focus();
		</script>
	</c:if>
	
	<!-- end info -->
	
	
</div>
<!-- end container -->

</body>
</html>