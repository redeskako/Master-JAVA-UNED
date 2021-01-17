<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.clasesCliente.*, java.util.*"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 
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

<libTemplate:ilegal rolIlegal="0">
	<!-- Si intenta acceder un anónimo: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</libTemplate:ilegal>

	<div id="container">

		<jsp:include page="Template/encabezado.jsp"></jsp:include>
		<jsp:include page="Template/menu.jsp"></jsp:include>

		<div id="content">
		
			<h4>Mis reservas vigentes:</h4>

			<!-- Se recupera el array de las reservas confirmadas -->
			<c:set var="misReservas" value="<%= (ArrayList<Reserva>) request.getAttribute(\"arrayReservas\") %>"></c:set>
			<c:set var="nUsuario" value="<%= (String)session.getAttribute(\"idSesion\") %>"></c:set>
			
			<!-- Se dibuja la tabla -->
			<c:if test="${ reservas.size() != 0 }">
				<table>
					<tr>		
						<th scope="col">Recurso</th>
						<th scope="col">Inicio</th>
						<th scope="col">Final</th>
						<th scope="col">Estado</th>
						<th scope="col">Sucursal</th>
						<th class="boton" scope="col"></th>
					</tr>
					<c:forEach var="res" items="${ misReservas }">
						<tr>
							<td>${res.getDescripcionRecurso()}</td>
							<td>${res.inicioToString()}</td>
							<td>${res.finToString()}</td>
							<td>${res.getEstado()}</td>
							<td>${res.getSucursal()}</td>
							<c:if test="${ res.getIdEstado() == 4 || res.getIdEstado() == 6 }">
								<td class="boton"></td>
							</c:if>
							<c:if test="${ res.getIdEstado() == 2 }">
								<td class="boton">						
									<form action="http://localhost:8080/alphaWS_Servidor/rest/solicitarAnulacion" method="post">
										<input type="hidden" name="volver" value="http://localhost:8080/alphaWS_Cliente/MisReservas">
										<input type="hidden" name="usuario" value="${ nUsuario }">
										<input type="hidden" name="idReserva" value="${ res.getIdReserva() }">
										<input class="btn" type="submit" name="solicitarAnulacion" value="Solicitar Anulación">
									</form>								
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>				
			</c:if>
			<c:if test="${ reservas.size() == 0 }">
				<p>No tiene ninguna reserva vigente.</p>
			</c:if>	
			
			

			<!-- Se comprueba si se necesita paginación -->
			<c:set var="paginas" value="<%=(int) Math.ceil(((Integer) request.getAttribute(\"numFilas\")) * 1.0 / 20)%>"></c:set>

			<!-- Paginación -->
			<c:set var="paginas" value="<%= (int) Math.ceil((Integer) request.getAttribute(\"numFilas\") * 1.0 / 20) %>"></c:set>

			<c:if test="${paginas > 1}"> 
				<br />
				<div class="paginacion">
					P&aacute;gina:
					<ul>
						<c:forEach var="p" begin="1" end="${paginas}">
							<li><a href="./misReservas?	pagina=${p}&usuario=${usuarioB}" method="get">${p}</a></li>
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