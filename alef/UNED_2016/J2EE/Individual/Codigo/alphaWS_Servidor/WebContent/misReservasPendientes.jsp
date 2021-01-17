<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.reserva.*, java.util.*"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>

<lib:ilegal rolIlegal="0">
	<!-- Si intenta acceder un anónimo: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

<lib:cambioVista visible="1">
	<!-- Si intenta acceder un responsable, se le cambia de vista -->
	<c:redirect url="./CambioPerfil"/>
</lib:cambioVista>

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
		
			<% // Si se ha modificado un elemento o ha habido error: MENSAJE INFORMATIVO:
			String idEditado = request.getParameter("idEditado");
			if (idEditado != null && !idEditado.equals("0")) {
				String nombre = request.getParameter("nombre");
			%>
			<div class="info">
				Se ha solicitado la anulación de la reserva con id <%=idEditado%> correctamente.
			</div> <!-- fin info -->
			<%
			}
			if (idEditado != null && idEditado.equals("0")) {
			%>
			<div class="info_error">
				La reserva no se ha modificado correctamente.
			</div> <!-- fin info_error -->
			<%
			}
			%>
		
			<h4>Mis reservas</h4>

			<%
				// Dibuja la tabla de las reservas confirmadas
					Reserva r = new Reserva();
					ArrayList<Reserva> tsR = (ArrayList<Reserva>) request.getAttribute("misReservas");
					Iterator<Reserva> it = tsR.iterator();

					// Si hay reservas se dibuja la tabla, si no aparece un mensaje
					if (tsR.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Recurso</th>
					<th scope="col">Inicio</th>
					<th scope="col">Final</th>
					<th scope="col">Estado</th>
					<th scope="col">Sucursal</th>
					<th class="boton" scope="col"></th>
				</tr>
				<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (it.hasNext()) {
								r = (Reserva) it.next();
				%>
				<tr>
					<td><%=r.getDescripcionRecurso()%></td>
					<td><%=r.dateCompleteToString(r.getInicio())%></td>
					<td><%=r.dateCompleteToString(r.getFin())%></td>
					<td><%=r.getEstado()%></td>
					<td><%=r.getSucursal()%></td>
					<%
					// Si el estado de la reserva esta pendiente de anulacion no se muestra el boton "Solicitar anulacion"
					if (r.getIdEstado() == 4 || r.getIdEstado() == 6) {
					%>
					<td class="boton"></td>
					<%
						} else {
					%>
					<td class="boton">						
						<a href="./MisPeticionesReservas?tipo=solicitarAnulacion&id=<%=r.getIdReserva()%>" class="btn">Solicitar anulaci&oacute;n</a> 										
					</td>

					<%
						}
					%>

				</tr>
				<%
					} // fin while
				%>
			</table>

			<!-- Se comprueba si se necesita paginación -->
			<c:set var="paginas" value="<%=(int) Math.ceil(((Integer) request.getAttribute(\"numFilas\")) * 1.0 / 20)%>"></c:set>

			<!-- Muestra la paginacion si es necesario -->
			<c:if test="${ paginas > 1 }">
			<br />
			<div class="paginacion">
				P&aacute;gina:
				<ul>
					<c:forEach var="p" begin="1" end="${ paginas }">
						<li><a href="./MisPeticionesReservas?tipo=reservas&p=<c:out value="${ p }" />"><c:out value="${ p }" /></a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
			<%
				} else {
			%>
			<p>No tiene ninguna reserva</p>
			<%
				} // fin if
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->


</body>
</html>