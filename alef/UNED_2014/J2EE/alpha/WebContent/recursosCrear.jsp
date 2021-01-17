<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
</head>
<body>
<%
	// Si ha accedido un responsable en vista de responsable: se muestra la pagina
	Integer rolSesion = (Integer) session.getAttribute("rolSesion");
	Integer vistaSesion = (Integer) session.getAttribute("vistaSesion");
	if (rolSesion != null && (rolSesion == 2 && vistaSesion == 2)){
%>
	<div id="container">

		<%@include file='Templates/encabezado.jsp'%>
		<%@include file='Templates/menu.jsp'%>

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

<%
	} else if (rolSesion != null && rolSesion == 2 && vistaSesion == 1){
		// Si intenta acceder un responsable en vista empleado, se le cambia de vista
		response.sendRedirect("./CambioPerfil"); 
	} else {
		// Si intenta acceder un empleado o anónimo, se manda a index
		response.sendRedirect("index.jsp?estado=ilegal"); 
	} // fin if
%>
</body>
</html>