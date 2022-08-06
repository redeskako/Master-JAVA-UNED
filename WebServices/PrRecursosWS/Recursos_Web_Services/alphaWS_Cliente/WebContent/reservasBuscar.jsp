<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page
	import="es.uned2014.recursosAlpha.clasesCliente.*, java.util.*"%>
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
		
			<!-- Si se ha solicitado una búsqueda: se extraen los datos buscados -->
			<c:set var="usuarioB" value="<%= request.getParameter(\"usuario\") %>"></c:set>
			<c:set var="recursoB" value="<%= request.getParameter(\"recurso\") %>"></c:set>
			<c:set var="sucursalB" value="<%= request.getParameter(\"sucursal\") %>"></c:set>	

			<h4>Buscador de reservas:</h4>

			<form name="formBuscarReserva" action="./FormularioBuscarReserva" method="get">
				<div class="cntCampo">
					<label>Usuario</label>
					<div>
						<select name="usuario">
							<!-- Se crea una opción "cualquiera" -->
								<option value="">- - - cualquiera - - -</option>
							<!-- Se crea lista opciones con los distintos usuarios -->
							<c:set var="usuarios" value="<%= (ArrayList<Usuario>)request.getAttribute(\"arrayUsuarios\") %>"></c:set>
							<c:forEach var="u" items="${ usuarios }">
								<c:if test="${ u.getIdUsuario() == usuarioB }">
									<option value="${u.getIdUsuario()}" selected="selected">${u.getNombreUsuario()}</option>
								</c:if>
								<option value="${u.getIdUsuario()}"> ${ u.getNombreUsuario() }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Recurso</label>
					<div>
						<select name="recurso">
							<!-- Se crea una opción "cualquiera" -->
								<option value="">- - - cualquiera - - -</option>
							<!-- Se crea lista opciones con los distintos recursos -->
							<c:set var="recursos" value="<%= (ArrayList<Recurso>)request.getAttribute(\"arrayRecursos\") %>"></c:set>
							<c:forEach var="r" items="${ recursos }">
								<c:if test="${ r.getIdRecurso() == recursoB }">
									<option value="${r.getIdRecurso()}" selected="selected">${r.getDescripcion()}</option>
								</c:if>
								<option value="${r.getIdRecurso()}"> ${ r.getDescripcion() }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Sucursal</label>
					<div>
						<select name="sucursal">
							<!-- Se crea una opción "cualquiera" -->
								<option value="">- - - cualquiera - - -</option>
							<!-- Se crea lista opciones con las distintos sucursales -->
							<c:set var="sucursales" value="<%= (ArrayList<Reserva>)request.getAttribute(\"arraySucursales\") %>"></c:set>
							<c:forEach var="s" items="${ sucursales }">
								<c:if test="${ s.getIdSucursal() == sucursalB }">
									<option value="${s.getIdSucursal()}" selected="selected">${s.getSucursal()}</option>
								</c:if>
								<option value="${s.getIdSucursal()}"> ${ s.getSucursal() }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->
			
				
				<div class="cntCampo">
					<input class="btn" type="submit" value="Buscar">
				</div>
				<!-- fin cntCampo -->

			</form>

			<div style="clear: both"></div>


			<h4>Reservas vigentes encontradas:</h4>

			<!-- Se recupera el array resultado de la búsqueda -->
			<c:set var="reservas" value="<%= (ArrayList<Reserva>) request.getAttribute(\"arrayReservas\") %>"></c:set>

			<!-- Se dibuja la tabla de las reservas encontradas -->
			<c:if test="${ reservas.size() != 0 }">
				<table>
					<tr>
						<th scope="col">Usuario</th>
						<th scope="col">Recurso</th>
						<th scope="col">Inicio</th>
						<th scope="col">Final</th>
						<th scope="col">Sucursal</th>
					</tr>
					<c:forEach var="res" items="${ reservas }">
						<tr>
							<td>${res.getNombreUsuario()}</td>
							<td>${res.getDescripcionRecurso()}</td>
							<td>${res.inicioToString()}</td>
							<td>${res.finToString()}</td>
							<td>${res.getSucursal()}</td>
						</tr>
					</c:forEach>
				</table>				
			</c:if>
			<c:if test="${ reservas.size() == 0 }">
				<p>No existe ninguna reserva vigente para la búsqueda realizada.</p>
			</c:if>	



			<!-- Paginación -->
			<c:set var="paginas" value="<%= (int) Math.ceil((Integer) request.getAttribute(\"numFilas\") * 1.0 / 20) %>"></c:set>

			<c:if test="${paginas > 1}"> 
				<br />
				<div class="paginacion">
					P&aacute;gina:
					<ul>
						<c:forEach var="p" begin="1" end="${paginas}">
							<li><a href="./FormularioBuscarReserva?	pagina=${p}&usuario=${usuarioB}&recurso=${recursoB}&sucursal=${sucursalB}" method="get">${p}</a></li>
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