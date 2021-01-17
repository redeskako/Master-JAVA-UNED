<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page
	import="es.uned2014.recursosAlpha.reserva.*,es.uned2014.recursosAlpha.usuario.*,es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet" href="Resources/css/grEstilos.css">
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
			<%
				// Si se ha solicitado una búsqueda: se extraen los datos buscados
				String nombreUsuarioB = request.getParameter("nombreUsuarioB");
				String nombreB = request.getParameter("nombreB");
				String apellidosB = request.getParameter("apellidosB");
				String rolB = request.getParameter("rolB");
			%>

			<h4>Buscador de usuarios:</h4>

			<form name="formBuscarUsuario" action="./AccionesUsuarios" method="get">
				<div class="cntCampo">
					<label>Nombre usuario</label>
					<div>
						<input type="text" name="nombreUsuarioB" size="50" value="<%=nombreUsuarioB%>">
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Nombre</label>
					<div>
						<input type="text" name="nombreB" size="50" value="<%=nombreB%>">
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Apellidos</label>
					<div>
						<input type="text" name="apellidosB" size="50" value="<%=apellidosB%>">
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<label>Rol de usuario</label>
					<div>
						<select name="rolB">
							<%
								// Crea lista de opciones con los distintos roles
								Usuario u = new Usuario();
								ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("rolesUsuarios");
								Iterator<Usuario> it = tsU.iterator();								
							
								// Opción "cualquiera":
							%>
							<option value="0">- - - cualquiera - - -</option>
							
							<%
								while (it.hasNext()) {
									u = (Usuario)it.next();
									if ( u.getIdRol() == Integer.parseInt(rolB)) {
							%>

							<option value="<%=u.getIdRol()%>" selected="selected"><%=u.getRol()%></option>
							<%
									} else {
							%>
							<option value="<%=u.getIdRol()%>"><%=u.getRol()%></option>
							<%
									} // fin if else
								} // fin while
							%>

						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<input type="hidden" name="tipo" value="buscador"> 
					<input class="btn" type="submit" value="Buscar">
				</div>
			</form>

			<div style="clear: both"></div>





			<h4>Usuarios encontrados:</h4>
			
				<%
					// ArrayList en el que almacenar las reservas para el buscador:
					Usuario usu = new Usuario();
					ArrayList<Usuario> tsUB = (ArrayList<Usuario>) request.getAttribute("usuariosBuscados");
					Iterator<Usuario> itB = tsUB.iterator();
			
					// Si hay usuarios se dibuja la tabla, si no aparece un mensaje
					if (tsUB.size() != 0) {		
				%>
			
			<table>
				<tr>
					<th scope="col">Nombre usuario</th>
					<th scope="col">Nombre</th>
					<th scope="col">Apellido</th>
					<th scope="col">Rol</th>
				</tr>
					<%
						while ( itB.hasNext() ) {
						usu = (Usuario)itB.next();
					%>			
				<tr>
					<td><%= usu.getNombreUsuario() %></td>
					<td><%= usu.getNombre() %></td>
					<td><%= usu.getApellidos() %></td>
					<td><%= usu.getRol() %></td>
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
						<li><a href="./AccionesUsuarios?tipo=buscador&p=<c:out value="${ p }" />
							&nombreUsuarioB=<c:out value="${ nombreUsuarioB }" />&nombreB=<c:out value="${ nombreB }" />
							&apellidosB=<c:out value="${ apellidosB }" />&rolB=<c:out value="${ rolB }" />"><c:out value="${ p }" /></a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
			
			<%
				} else {
			%>
			<p>No hay ning&uacute;n usuario para la b&uacute;squeda realizada</p>	
			<%
				} // fin if else
			%>
			

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>