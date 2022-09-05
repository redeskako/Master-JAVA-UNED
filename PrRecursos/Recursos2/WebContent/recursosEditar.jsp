<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
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

<lib:cambioVista visible="2">
	<!-- Si intenta acceder un responsable con vista empleado, se le cambia de vista -->
	<c:redirect url="./CambioPerfil"/>
</lib:cambioVista>

<lib:ilegal rolIlegal="0">
	<!-- Si intenta acceder un anónimo: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

<lib:ilegal rolIlegal="1">
	<!-- Si intenta acceder un empleado: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>


	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
		
			<% //Si se ha solicitado la edición de un elemento: FORMULARIO EDITAR	
			String idEditarSt = request.getParameter("idEditar"); 
			// Si existe un elemento a editar, de extrae del array
			if(idEditarSt != null){
				Recurso rEditar = new Recurso();
				int idEditar = Integer.parseInt(idEditarSt);
				
				ArrayList<Recurso> arrayR = (ArrayList<Recurso>) request.getAttribute("recursosListadoCompleto");
				Iterator<Recurso> it = arrayR.iterator();
				while (it.hasNext()){
					Recurso r = (Recurso) it.next();
					if (r.getIdRecurso() == idEditar){
						rEditar = r;
					}
				}
			%>	
			<form name="formEditarRecurso" action="./AccionesRecursos" method="get">
				<div class="cntCampo">
					<label>Descripci&oacute;n: </label>
					<div>
						<input type="text" name="descripcion" size="50" required
							aria-required="true" value="<%=rEditar.getDescripcion()%>">
					</div>
				</div> <!-- fin cntCampo -->
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="editar"> 
					<input type="hidden" name="idRecurso" value="<%=idEditar%>">
					<input class="btn" type="submit" name="editar" value="Guardar cambios">
				</div> <!-- fin cntCampo -->
			</form>
			
			<div style="clear: both"></div>
			<a class="info_cancelar" href="./AccionesRecursos?tipo=menuEditar" > Cancelar la edición del recurso </a>

			<% } %>	
		
		
		
			<% //Si se ha solicitado la eliminación de un elemento: MENSAJE INFORMATIVO:
			String idEliminar = request.getParameter("idEliminar");
			if (idEliminar != null){
				String nombre = request.getParameter("nombre");
			%>
				<div class="info">
					¿Está seguro de que desea eliminar el recurso "<%=nombre%>", 
					así como todas las reservas asociadas a este recurso?
					
					<div>
						<a class="info_btn" href="./AccionesRecursos?tipo=eliminar&id=<%=idEliminar%>&nombre=<%=nombre%>" > Eliminar </a>
						<a class="info_btn" href="./AccionesRecursos?tipo=menuEditar" > No eliminar </a>
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
				El recurso "<%=nombre%>" con id <%=idEditado%> se ha modificado correctamente.
			</div> <!-- fin info -->

			<%
			}
			if (!idEditado.equals("null") && idEditado.equals("0")) {
			%>
			<div class="info_error">
				El recurso no se ha modificado correctamente.
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
				El recurso "<%=nombreEliminado%>" se ha eliminado correctamente.
			</div> <!-- fin info -->

			<%
			}
			if (!eliminado.equals("null") && eliminado.equals("false")) {
			%>
			<div class="info_error">
				El recurso no se ha eliminado correctamente.
			</div> <!-- fin info_error -->

			<%
			}
			%>			
					
		
		
		
			<% 
			if(idEditarSt == null && idEliminar == null){
			%>
		
			<h4>Editar/eliminar recursos:</h4>

			<%
				// Dibuja la tabla de las reservas confirmadas
				Recurso r = new Recurso();
				ArrayList<Recurso> tsR = (ArrayList<Recurso>) request.getAttribute("recursosListado");

				Iterator it = tsR.iterator();

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
					<% 
					if(idEditarSt == null && idEliminar == null){
					%>
					<td class="boton">
						<a href="./AccionesRecursos?tipo=botonEditar&id=<%=r.getIdRecurso()%>" class="btn" > Editar </a> 						
					</td>
					<td class="boton">
						<a href="./AccionesRecursos?tipo=botonEliminar&id=<%=r.getIdRecurso()%>&nombre=<%=r.getDescripcion()%>" class="btn" > Eliminar </a> 						
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
						<li><a href="./AccionesRecursos?tipo=menuEditar&p=<c:out value="${ p }" />"><c:out value="${ p }" /></a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
			
			<%				
				} else {
			%>
			<p>No hay recursos disponibles</p>
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