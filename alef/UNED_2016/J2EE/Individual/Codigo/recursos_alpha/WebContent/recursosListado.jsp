<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
</head>
<body>
	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<h4>Listado de recursos:</h4>

			<!-- Si hay recuros se dibuja la tabla, si no aparece un mensaje -->
			<c:set var="recursos" value="<%=(ArrayList<Recurso>) request.getAttribute(\"recursosListado\")%>"></c:set>

			<c:choose>
				<c:when test="${ recursos.size() != 0 }">

					<table>
						<tr>
							<th scope="col">Descripci&oacute;n</th>
						</tr>

						<c:forEach var="r" items="${ recursos }">
							<tr>
								<td><c:out value="${ r.getDescripcion() }"></c:out></td>
							</tr>
						</c:forEach>

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
									<li><a href="./AccionesRecursos?tipo=listado&p=<c:out value="${ p }" />"><c:out	value="${ p }" /></a></li>
								</c:forEach>
							</ul>
						</div>
					</c:if>

				</c:when>
				<c:otherwise>
					<p>No hay recursos disponibles</p>
				</c:otherwise>
			</c:choose>


		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>