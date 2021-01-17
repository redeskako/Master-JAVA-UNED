<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.usuario.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>
	<%
		// Si ha accedido un responsable en vista de responsable: se muestra la pagina
		Integer rolSesion = (Integer) session.getAttribute("rolSesion");
		Integer vistaSesion = (Integer) session.getAttribute("vistaSesion");
		if (rolSesion != null && (rolSesion == 2 && vistaSesion == 2)) {
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
				El usuario "<%=nombre%>" con id <%=idNuevo%> se ha creado correctamente.
			</div> <!-- fin info -->
			<%
			}
			if (!idNuevo.equals("null") && idNuevo.equals("0"))  {
			%>

			<div class="info_error">
				El usuario no se ha creado correctamente. <br/>
				Compruebe que no exista un usuario con el mismo nombre de usuario.
			</div> <!-- fin info_error -->
			<%
			}
			%>


			<h4>Crear nuevo usuario:</h4>

			<form name="formCrearUsuario" action="./AccionesUsuarios" method="get">
				<div class="cntCampo">
					<label>Nombre de usuario: </label>
					<div>
						<input type="text" name="nombreUsuario" size="50" required
							aria-required="true">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Nombre: </label>
					<div>
						<input type="text" name="nombre" size="50" required
							aria-required="true">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Apellido(s): </label>
					<div>
						<input type="text" name="apellidos" size="50" required
							aria-required="true">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Contrase&ntilde;a: </label>
					<div>
						<input type="text" name="contrasena" size="50" required
							aria-required="true">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Rol de usuario: </label>
					<div>
						<select name="rol">

							<%
								// Dibuja la tabla de los empleados
									Usuario u = new Usuario();
									ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("rolesUsuario");
									Iterator<Usuario> it = tsU.iterator();

									while (it.hasNext()) {
										u = (Usuario) it.next();
							%>
							<option value="<%=u.getIdRol()%>"><%=u.getRol()%></option>
							<%
									} // fin while
							%>
						</select>
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
				<input type="hidden" name="tipo" value="crear"> 
				<input class="btn" type="submit" name="Crear" value="Crear">
				</div> <!-- fin cntCampo -->
		</form>

	</div> <!-- end content -->

	</div> <!-- end container -->

	<%
		} else if (rolSesion != null && rolSesion == 2 && vistaSesion == 1) {
			// Si intenta acceder un responsable en vista empleado, se le cambia de vista
			response.sendRedirect("./CambioPerfil");
		} else {
			// Si intenta acceder un empleado o anónimo, se manda a index
			response.sendRedirect("index.jsp?estado=ilegal");
		} // fin if
	%>
</body>
</html>