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

	<div id="container">

		<%@include file='Templates/encabezado.jsp'%>
		<%@include file='Templates/menu.jsp'%>

		<div id="content">	
		
			<% // Si se ha eliminado un usuario y por eso se ha salido de sesión: MENSAJE INFORMATIVO:
			String eliminado = request.getParameter("eliminado");
	
			if (eliminado != null && eliminado.equals("true")) {
				String nombreEliminado = request.getParameter("nombre");
			%>
			<div class="info_error">
				Su usuario ha sido eliminado. <br/>
				Se ha salido de sesión porque este usuario ya no existe.
			</div> <!-- fin info -->
			<%
			}
			%>
		
		
			<h2>¡¡Bienvenido a la aplicaci&oacute;n Gesti&oacute;n de
				Recursos 2.0!!</h2>
		</div>
		<!-- end content -->

		<%
			String estado = request.getParameter("estado");
			if (estado != null) {
				if (estado.equals("noValido")) {
		%>
		<script type="application/javascript">
			alert('Usuario o contraseña no validos.');
			document.formEntrar.usuario.focus();
		</script>
		<%
				} else if (estado.equals("ilegal")){
		%>	
		<script type="application/javascript">
			alert('ERROR: No tiene acceso a esa página.');
			document.formEntrar.usuario.focus();
		</script>		
			
		<%		
				} // fin ilegal
			} // fin estado != null
		%>

	</div>
	<!-- end container -->

</body>
</html>