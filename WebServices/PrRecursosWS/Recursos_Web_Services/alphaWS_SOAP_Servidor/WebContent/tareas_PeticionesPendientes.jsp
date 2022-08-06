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

<lib:ilegal rolIlegal="1">
	<!-- Si intenta acceder un empleado: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

<lib:cambioVista visible="2">
	<!-- Si intenta acceder un responsable con vista de empleado, se le cambia de vista -->
	<c:redirect url="./CambioPerfil"/>
</lib:cambioVista>

	<div id="container">
	
		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<!-- Si se ha modificado un elemento o ha habido error: MENSAJE INFORMATIVO: -->
			
			<c:set var="idEditado" value="<%= request.getParameter(\"idEditado\") %>"></c:set>
			
			<c:if test="${ idEditado != null  && !idEditado.equals(\"0\") }">
				<c:set var="nombre" value="<%=request.getParameter(\"nombre\")%>"></c:set>
				<c:set var="accion" value="<%=request.getParameter(\"accion\")%>"></c:set>
				<div class="info">
					La reserva con id <c:out value="${ idEditado }"></c:out> 
					del usuario "<c:out value="${ nombre }"></c:out>" 
					ha sido <c:out value="${ accion }"></c:out> correctamente.
				</div> <!-- fin info -->
			</c:if>
			
			<c:if test="${ idEditado != null && idEditado.equals(\"0\") }">
				<div class="info_error">
					La reserva no se ha modificado correctamente. <br/>
					Compruebe que es compatible con las reservas aprobadas para ese recurso.
				</div> <!-- fin info_error -->
			</c:if>

			<h4>Peticiones pendientes:</h4>
			
			<!-- Si hay reservas se dibuja la tabla, si no aparece un mensaje -->
			<c:set var="reservas" value="<%= (ArrayList<Reserva>) request.getAttribute(\"arrayPeticiones\") %>"></c:set>
			<c:choose>
				<c:when test="${ reservas.size() != 0 }">
					<table>
						<tr>
							<th scope="col">Usuario</th>
							<th scope="col">Recurso</th>
							<th scope="col">Inicio</th>
							<th scope="col">Final</th>
							<th scope="col">Estado</th>
							<th scope="col">Sucursal</th>
						</tr>


						<c:forEach var="r" items="${ reservas }">
						<tr <c:if test="${ r.getIdEstado() == 3 || r.getIdEstado() == 5 }">
							out.print(" class='datosRojo' ")</c:if> >
							
							<td><c:out value="${ r.getNombreUsuario() }"></c:out></td>
							<td><c:out value="${ r.getDescripcionRecurso() }"></c:out></td>
							<td><c:out value="${ r.getInicio() }"></c:out></td>
							<td><c:out value="${ r.getFin() }"></c:out></td>
							<td><c:out value="${ r.getEstado() }"></c:out></td>
							<td><c:out value="${ r.getSucursal() }"></c:out></td>
							<td class="boton">
								<a href="./Tareas?tipo=confirmar&id=${r.getIdReserva()}&nombre=${r.getNombreUsuario()}" class="btn" name="confirmarPeticion">Confirmar</a> 						
							</td>
							<td class="boton">
								<a href="./Tareas?tipo=denegar&id=${r.getIdReserva()}&nombre=${r.getNombreUsuario()}" class="btn" name="denegarPeticion">Denegar</a>
							</td>
						</tr>
						</c:forEach>

					</table>
					


					<!-- Se comprueba si se necesita paginación -->
					<c:set var="paginas" value="<%= (int) Math.ceil(((Integer) request.getAttribute(\"numFilas\")) * 1.0 / 20) %>"></c:set>
					
					<!-- Muestra la paginacion si es necesario -->
					<c:if test="${ paginas > 1 }">
					<br />
					<div class="paginacion">
						P&aacute;gina:
						<ul>
							<c:forEach var="p" begin="1" end="${ paginas }">
								<li><a href="./Tareas?tipo=peticiones&p=<c:out value="${ p }" />"><c:out value="${ p }" /></a></li>						
							</c:forEach>
						</ul>
					</div>
					</c:if>
				</c:when>
				
				<c:otherwise>
					<p>No tiene ninguna petición pendiente de confirmar/denegar.</p>
				</c:otherwise>
			</c:choose>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>