<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>

<lib:ilegal rolIlegal="0">
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

<lib:ocultar rolOcultar="0">

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

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

</lib:ocultar>

</body>
</html>