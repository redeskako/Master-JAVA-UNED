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
		
			<% //Si se ha solicitado la edición de un elemento: FORMULARIO EDITAR	
			String idEditarSt = request.getParameter("idEditar");  
			// Si existe un elemento a editar, de extrae del array
			if(idEditarSt != null){
				Usuario uEditar = new Usuario();
				int idEditar = Integer.parseInt(idEditarSt);
				
				ArrayList<Usuario> arrayU = (ArrayList<Usuario>) request.getAttribute("usuariosListadoCompleto");
				Iterator<Usuario> it = arrayU.iterator();
				while (it.hasNext()){
					Usuario u = (Usuario) it.next();
					if (u.getIdUsuario() == idEditar){
						uEditar = u;
					}
				}
			%>
			<form name="formEditarUsuario" action="./AccionesUsuarios" method="get">
				<div class="cntCampo">
					<label>Nombre de usuario: </label>
					<div>
						<input type="text" name="nombreUsuario" size="50" required
							aria-required="true" value="<%=uEditar.getNombreUsuario()%>">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Nombre: </label>
					<div>
						<input type="text" name="nombre" size="50" required
							aria-required="true" value="<%=uEditar.getNombre()%>">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Apellido(s): </label>
					<div>
						<input type="text" name="apellidos" size="50" required
							aria-required="true" value="<%=uEditar.getApellidos()%>">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Contrase&ntilde;a: </label>
					<div>
						<input type="text" name="contrasena" size="50" required
							aria-required="true">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Rol de usuario: </label>
					<div>
						<select name="rol">
							
							<%
								// Dibuja la tabla de los empleados
									Usuario u = new Usuario();
									ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("rolesUsuario");
									Iterator it2 = tsU.iterator();

									while (it2.hasNext()) {
										u = (Usuario) it2.next();
									if(uEditar.getIdRol() == u.getIdRol()){
							%>
							<option value="<%=u.getIdRol()%>" selected="selected"><%=u.getRol()%></option>
							<%
									}else{ %>
							<option value="<%=u.getIdRol()%>"><%=u.getRol()%></option>										
								 <% }
									} // fin while
							%>
						</select>
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="editar"> 
					<input type="hidden" name="idUsuario" value="<%=idEditar%>">
					<input class="btn" type="submit" name="editar" value="Guardar cambios">
				</div> <!-- fin cntCampo -->
			</form>
			
			<div style="clear: both"></div>
			<a class="info_cancelar" href="./AccionesUsuarios?tipo=menuEditar" > Cancelar la edición del usuario </a>
			
			<% } %>	


			
			<% //Si se ha solicitado la eliminación de un elemento: MENSAJE INFORMATIVO:
			String idEliminar = request.getParameter("idEliminar");
			if (idEliminar != null){
				String nombre = request.getParameter("nombre");
			%>
				<div class="info">
					¿Está seguro de que desea eliminar el usuario "<%=nombre%>"
					así como todas las reservas asociadas a este usuario?
					
					<div>
						<a class="info_btn" href="./AccionesUsuarios?tipo=eliminar&id=<%=idEliminar%>&nombre=<%=nombre%>" > Eliminar </a>
						<a class="info_btn" href="./AccionesUsuarios?tipo=menuEditar" > No eliminar </a>
					</div>
					<div style="clear: both"></div>
				</div> <!-- fin info -->
			<%			
			}			
			%>
			
			
			
			<% // Si se ha modificado un elemento o ha habido error: MENSAJE INFORMATIVO:
			String idEditado = request.getParameter("idEditado");
			if (!idEditado.equals("null") && !idEditado.equals("0")) {
				String nombre = request.getParameter("nombre");
			%>
			<div class="info">
				El usuario "<%=nombre%>" con id <%=idEditado%> se ha modificado correctamente.
			</div> <!-- fin info -->

			<%
			}
			if (!idEditado.equals("null") && idEditado.equals("0")) {
			%>
			<div class="info_error">
				El usuario no se ha modificado correctamente. <br/>
				Compruebe que no exista un usuario con el mismo nombre de usuario.
			</div> <!-- fin info_error -->

			<%
			}
			%>
			
			
			
			<% // Si se ha eliminado un elemento o ha habido error: MENSAJE INFORMATIVO:
			String eliminado = request.getParameter("eliminado");
			if (!eliminado.equals("null") && eliminado.equals("true")) {
				String nombreEliminado = request.getParameter("nombre");
			%>
			<div class="info">
				El usuario  <%=nombreEliminado%> se ha eliminado correctamente.
			</div> <!-- fin info -->

			<%
			}
			if (!eliminado.equals("null") && eliminado.equals("false")) {
			%>
			<div class="info_error">
				El usuario no se ha eliminado correctamente.
			</div> <!-- fin info_error -->

			<%
			}
			%>
			
			
			<% 
			if(idEditarSt == null && idEliminar == null){
			%>	
			
			<h4>Editar/eliminar usuarios:</h4>

			<%
				// Dibuja la tabla de los usuarios
					Usuario u = new Usuario();
					ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("usuariosListado");
					Iterator<Usuario> it = tsU.iterator();

					// Si hay empleados se dibuja la tabla, si no aparece un mensaje
					if (tsU.size() != 0) {
			%>

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
					<% 
					if(idEditarSt == null && idEliminar == null){
					%>
					<td class="boton">
						<a href="./AccionesUsuarios?tipo=botonEditar&id=<%=u.getIdUsuario()%>" class="btn" > Editar </a> 						
					</td>
					<td class="boton">
						<a href="./AccionesUsuarios?tipo=botonEliminar&id=<%=u.getIdUsuario()%>&nombre=<%=u.getNombreUsuario()%>" class="btn" > Eliminar </a> 						
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
						<li><a href="./AccionesUsuarios?tipo=menuEditar&p=<c:out value="${ p }" />"><c:out	value="${ p }" /></a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>

			<%
					} else {
			%>
			<p>No hay ningun empleado</p>
			<%
				} // fin if
			}
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>