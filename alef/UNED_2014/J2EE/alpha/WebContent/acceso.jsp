<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>

<%
	Integer rolSesion = (Integer) session.getAttribute("rolSesion");
	if (rolSesion != null){
%>
	<div id="container">

		<%@include file='Templates/encabezado.jsp'%>
		<%@include file='Templates/menu.jsp'%>

		<div id="content">
			<h2>¡¡Bienvenido a la aplicaci&oacute;n Gesti&oacute;n de
				Recursos!!</h2>
		</div>
		<!-- end content -->

		<%
		String estado = request.getParameter("estado");
		if (estado != null) {
				if (estado.equals("accesoEmpleado")) {
		%>
		<script type="application/javascript">
			alert('Ha accedido un empleado');
		</script>
		<%
				} else if (estado.equals("accesoResponsable")) {
		%>
		<script type="application/javascript">
			alert('Ha accedido un responsable');
		</script>
		<%
				} else if (estado.equals("ilegal")){
		%>	
		<script type="application/javascript">
			alert('No tiene acceso a esa página, inicie sesión.');
			document.formEntrar.usuario.focus();
		</script>		
		<%
				}
			}
		%>

	</div>
	<!-- end container -->
<%
	} else {
		response.sendRedirect("index.jsp?estado=ilegal");
	}
%>
</body>
</html>