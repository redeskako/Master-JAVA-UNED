<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>
	<div id="container">

		<%@include file='Templates/encabezado.jsp'%>
		<%@include file='Templates/menu.jsp'%>

		<div id="content">
			<h4>Listado de recursos:</h4>

			<%
				// Dibuja la tabla de las reservas confirmadas
				Recurso r = new Recurso();
				ArrayList<Recurso> tsR = (ArrayList<Recurso>) request.getAttribute("recursosListado");

				Iterator<Recurso> it = tsR.iterator();

				// Si hay recuros se dibuja la tabla, si no aparece un mensaje
				if (tsR.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Descripci&oacute;n</th>
				</tr>
				<%
					while (it.hasNext()) {
							r = (Recurso) it.next();
				%>
				<tr>
					<td><%=r.getDescripcion()%></td>
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
					<li><a href="./AccionesRecursos?tipo=listado&p=<%=p%>"><%=p%></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
					} // fin if

				} else {
			%>
			<p>No hay recursos disponibles</p>
			<%
				} // fin if
			%>


		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>