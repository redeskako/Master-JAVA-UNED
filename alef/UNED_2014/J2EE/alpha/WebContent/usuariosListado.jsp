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
			<h4>Listado de usuarios:</h4>

			<%!/**
	 *	Se usa en disconformidades pero creo que no lo neceistamos, porque todos los
	 *	valores en principio va a tener un valor, no?
	String sNull(String valor){
		return (valor!=null ? valor: "");
	};
	 **/%>

			<%
				// Dibuja la tabla de los empleados
					Usuario u = new Usuario();
					ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("usuariosListado");
					Iterator<Usuario> it = tsU.iterator();

					// Si hay empleados se dibuja la tabla, si no aparece un mensaje
					if (tsU.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Nombre usuario</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellidos</th>
					<th scope="col">Rol</th>
				</tr>
				<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (it.hasNext()) {
								u = (Usuario) it.next();
				%>
				<tr>
					<td><%=u.getNombreUsuario()%></td>
					<td><%=u.getNombre()%></td>
					<td><%=u.getApellidos()%></td>
					<td><%=u.getRol()%></td>
				</tr>
				<%
					} // fin while
				%>
			</table>
			<%
				//Paginación
				Integer numFilas = (Integer) request.getAttribute("numFilas");
				int paginas = (int) Math.ceil(numFilas * 1.0 / 20); 

				// Muestra la paginacion si es necesario
				if (paginas > 1) {
			%>
			<br />
			<div class="paginacion">
				P&aacute;gina:
				<ul>
					<%
						for (int p = 1; p <= paginas; p++) {
					%>
					<li><a href="./AccionesUsuarios?tipo=listado&p=<%=p%>"><%=p%></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
				} // fin if

					} else {
			%>
			<p>No hay ningun empleado</p>
			<%
				} // fin if
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

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