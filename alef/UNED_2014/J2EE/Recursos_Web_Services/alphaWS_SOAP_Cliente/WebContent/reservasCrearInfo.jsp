<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosalpha.clasesCliente.*, java.util.*"%>
<%@ taglib uri="../WEB-INF/libreria.tld" prefix="lib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WS Gesti&oacute;n de Recursos - Sucursal SOAP</title>

<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

</head>
<body>

	<!-- start container -->
	<div id="container">
		<jsp:include page="Template/menu.jsp"></jsp:include>

		<!-- start content -->
		<div id="content">

			<!-- Se crea lista opciones con los distintos usuarios -->
			<c:set var="id" value="<%= request.getParameter(\"id\") %>"></c:set>

			<c:if test="${!id.equals(\"0\") && !id.equals(\"null\")}">
				<div class="info">La reserva con id "${id}" se ha creado
					correctamente.</div>
				<!-- fin info -->
			</c:if>

			<c:if test="${id.equals(\"0\") || id.equals(\"null\")}">
				<div class="info_error">
					La reserva no se ha creado correctamente. <br /> Compruebe que es
					compatible con las reservas aprobadas para ese recurso.
				</div>
				<!-- fin info_error -->
			</c:if>

		</div>
		<!-- end content -->
	</div>
	<!-- end container -->

</body>
</html>