<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.usuario.*, java.util.*"%>
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
	<!-- Si intenta acceder un responsable con vista empleado, se le cambia de vista -->
	<c:redirect url="./CambioPerfil"/>
</lib:cambioVista>

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<h4>Listado de usuarios:</h4>

			<%
				// Dibuja la tabla de los empleados
					Usuario u = new Usuario();
					ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("usuariosListado");
					Iterator<Usuario> it = tsU.iterator();

			%>
			<!-- Si hay empleados se dibuja la tabla, si no aparece un mensajea -->
			<c:if test="${tsU.size() != 0}">
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
			
			<!-- Se comprueba si se necesita paginación -->
			<c:set var="paginas" value="<%=(int) Math.ceil(((Integer) request.getAttribute(\"numFilas\")) * 1.0 / 20)%>"></c:set>

			<!-- Muestra la paginacion si es necesario -->
			<c:if test="${ paginas > 1 }">
			<br />
			<div class="paginacion">
				P&aacute;gina:
				<ul>
					<c:forEach var="p" begin="1" end="${ paginas }">
						<li><a href="./AccionesUsuarios?tipo=listado&p=<c:out value="${ p }" />"><c:out	value="${ p }" /></a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>

			</c:if>
			
			
			<c:if test="${tsU.size() == 0}">
			<p>No hay ningun empleado</p>
			</c:if>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>