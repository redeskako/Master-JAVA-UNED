<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.reserva.*, java.util.*"%>
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

			<% // Si se ha modificado un elemento o ha habido error: MENSAJE INFORMATIVO:
			String idEditado = request.getParameter("idEditado");
			if (idEditado != null && !idEditado.equals("0")) {
				String nombre = request.getParameter("nombre");
				String accion = request.getParameter("accion");
			%>
			<div class="info">
				La reserva con id <%=idEditado%> del usuario "<%=nombre%>" ha sido <%=accion%> correctamente.
			</div> <!-- fin info -->
			<%
			}
			if (idEditado != null && idEditado.equals("0")) {
			%>
			<div class="info_error">
				La reserva no se ha modificado correctamente. <br/>
				Compruebe que es compatible con las reservas aprobadas para ese recurso.
			</div> <!-- fin info_error -->
			<%
			}
			%>	

			<h4>Peticiones pendientes:</h4>

			<%
				// Dibuja la tabla de las peticiones pendientes
					Reserva r = new Reserva();
					ArrayList<Reserva> tsR = (ArrayList<Reserva>) request.getAttribute("arrayPeticiones");
					Iterator<Reserva> it = tsR.iterator();

					// Si hay reservas se dibuja la tabla, si no aparece un mensaje
					if (tsR.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Usuario</th>
					<th scope="col">Recurso</th>
					<th scope="col">Inicio</th>
					<th scope="col">Final</th>
					<th scope="col">Estado</th>
				</tr>
				<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (it.hasNext()) {
								r = (Reserva) it.next();
				%>
				<tr
					<%if (r.getIdEstado() == 3 || r.getIdEstado() == 5) {
							out.print(" class='datosRojo' ");
						}%>>
					<td><%=r.getNombreUsuario()%></td>
					<td><%=r.getDescripcionRecurso()%></td>
					<td><%=r.getInicio()%></td>
					<td><%=r.getFin()%></td>
					<td><%=r.getEstado()%></td>
					<td class="boton">
						<a href="./Tareas?tipo=confirmar&id=<%=r.getIdReserva()%>&nombre=<%=r.getNombreUsuario()%>" class="btn" name="confirmarPeticion">Confirmar</a> 						
					</td>
					<td class="boton">
						<a href="./Tareas?tipo=denegar&id=<%=r.getIdReserva()%>&nombre=<%=r.getNombreUsuario()%>" class="btn" name="denegarPeticion">Denegar</a>
					</td>
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
					<li><a href="./Tareas?tipo=peticiones&p=<%=p%>"><%=p%></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
					} // fin if
					
				} else {
			%>
			<p>No tiene ninguna petición pendiente de confirmar/denegar.</p>
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