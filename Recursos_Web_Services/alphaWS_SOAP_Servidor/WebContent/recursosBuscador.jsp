<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page
	import="es.uned2014.recursosAlpha.reserva.*,es.uned2014.recursosAlpha.usuario.*,es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
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

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">

			<% //Si se ha solicitado una búsqueda: se extraen los datos buscados	
			String busqueda = request.getParameter("busqueda");  	
			%>


			<h4>Buscador de recursos:</h4>

			<form name="formBuscarRecurso" action="./AccionesRecursos"
				method="get">
				<div class="cntCampo">
					<div>
						<input type="text" name="busqueda" value="<%=busqueda%>">
					</div>
				</div>
				<!-- fin cntCampo -->
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="buscador"> <input
						class="btn" type="submit" value="Buscar">
				</div>
				<!-- fin cntCampo -->

			</form>

			<div style="clear: both"></div>


			<h4>Recursos encontrados:</h4>

			<%
				// Dibuja la tabla de las recursos encontrados
				Recurso r = new Recurso();
			
				// ArrayList en el que almacenar las reservas para el buscador:
				ArrayList<Recurso> tsRB = (ArrayList<Recurso>) request.getAttribute("recursosBuscados");

				Iterator<Recurso> itB = tsRB.iterator();
			
				// Si hay reservas se dibuja la tabla, si no aparece un mensaje
				if (tsRB.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Descripci&oacute;n</th>
				</tr>
				<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (itB.hasNext()) {
								r = (Recurso) itB.next();
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
							String url = "./AccionesRecursos?tipo=buscador&p="+p+"&busqueda="+busqueda;
					%>
					<li><a href="<%=url%>"><%=p%></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
					} // fin if
				} else {
			%>
			<p>No existe ning&uacute;n recurso para la búsqueda realizada.</p>
			<%
				} // fin if
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>