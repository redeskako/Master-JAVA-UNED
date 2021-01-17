<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.uned2014.recursosAlpha.clasesCliente.*, java.util.*"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>

<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
</head>
<body>

	<div id="container">

		<jsp:include page="Template/encabezado.jsp"></jsp:include>
		<jsp:include page="Template/menu.jsp"></jsp:include>

		<div id="content">

			<!-- Se recupera el texto buscado -->
			<c:set var="busqueda" value="<%= request.getParameter(\"busqueda\") %>"></c:set>
											

			<h4>Buscador de recursos:</h4>

			<!-- Se muestra el formulario del buscador, con el texto introducido previamente (si lo hay) -->
			<form name="formBuscarRecurso" action="./FormularioBuscarRecurso" method="get">
				<div class="cntCampo">
					<div>
						<input type="text" name="busqueda" value="${busqueda}">
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<input class="btn" type="submit" value="Buscar">
				</div>
			</form>

			<div style="clear: both"></div>


			<h4>Recursos encontrados:</h4>

			<!-- Se recupera el array resultado de la búsqueda -->
			<c:set var="recursos" value="<%= (ArrayList<Recurso>) request.getAttribute(\"arrayRecursos\") %>"></c:set>

			<!-- Se dibuja la tabla de las recursos encontrados -->
			<c:if test="${ recursos.size() != 0 }">
				<table>
					<tr>
						<th scope="col">Descripci&oacute;n</th>
					</tr>
					<c:forEach var="r" items="${ recursos }">
						<tr>
							<td>${r.getDescripcion()}</td>
						</tr>
					</c:forEach>
				</table>				
			</c:if>
			<c:if test="${ recursos.size() == 0 }">
				<p>No existe ning&uacute;n recurso para la búsqueda realizada.</p>
			</c:if>			

			<!-- Paginación -->
			<c:set var="paginas" value="<%= (int) Math.ceil((Integer) request.getAttribute(\"numFilas\") * 1.0 / 20) %>"></c:set>

			<c:if test="${paginas > 1}"> 
				<br />
				<div class="paginacion">
					P&aacute;gina:
					<ul>
						<c:forEach var="p" begin="1" end="${paginas}">
							<li><a href="./FormularioBuscarRecurso?pagina=${p}&busqueda=${busqueda}"  method="get">${p}</a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			
		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>