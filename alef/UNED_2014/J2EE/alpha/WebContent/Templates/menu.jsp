<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<div id="menu">
	<ul>
		<%
			// Muestra Mis peticiones / reservas si es un empleado
			Integer vista = (Integer)session.getAttribute("vistaSesion");
			if (vista != null && vista == 1) {
		%>
		<li><a href="#">Mis peticiones / reservas</a>

			<ul>
				<li><a href="./MisPeticionesReservas?tipo=peticiones">Mis peticiones pendientes</a></li>
				<li><a href="./MisPeticionesReservas?tipo=reservas">Mis reservas</a></li>
			</ul></li>
		<%
			} // fin if
		%>
		<%
			// Muestra Tareas y Empleados si es un responsable
			if (vista != null && vista == 2) {
		%>
		<li><a href="#">Tareas</a>
			<ul>
				<li><a href="./Tareas?tipo=peticiones">Peticiones pendientes</a></li>
				<li><a href="./Tareas?tipo=reservas">Reservas a anular</a></li>
			</ul></li>
		<li><a href="#">Usuarios</a>
			<ul>
				<li><a href="./AccionesUsuarios?tipo=menuCrear">Crear</a></li>
				<li><a href="./AccionesUsuarios?tipo=menuEditar">Editar / Eliminar</a></li>
				<li><a href="./AccionesUsuarios?tipo=buscador">Buscador</a></li>
				<li><a href="./AccionesUsuarios?tipo=listado">Listado</a></li>
			</ul></li>
		<%
			} // fin if
		%>

		<li><a href="#">Recursos</a>
			<ul>
				<%
					/**
					*	Muestra las opciones del submenu solo al empleado
					*	Listado es comun para todos
					**/
					if (vista != null && vista == 2) {
				%>
				<li><a href="./AccionesRecursos?tipo=menuCrear">Crear</a></li>
				<li><a href="./AccionesRecursos?tipo=menuEditar">Editar / Eliminar</a></li>
				<li><a href="./AccionesRecursos?tipo=buscador">Buscador</a></li>
				<%
					} // fin if
				%>

				<li><a href="./AccionesRecursos?tipo=listado">Listado</a></li>
			</ul></li>
		<li><a href="#">Reservas</a>
			<ul>
				<%
					/**
					*	Si es un empleado un responsable, se muestra el submenu Crear
					**/
					if (vista != null && (vista == 1 || vista == 2)) {
				%>
				<li><a href="./AccionesReservas?tipo=menuCrear">Crear</a></li>
				<%
					} // fin if


					// Las opcines Editar / Eliminar solo las ve el responsable
					// Ver todo - Buscador es comun para todos

					if (vista != null && vista == 2) {
				%>
				<li><a href="./AccionesReservas?tipo=menuEditar">Editar / Eliminar</a></li>
				<%
					} // fin if
				%>
				<li><a href="./AccionesReservas?tipo=buscador">Buscador</a></li>
				<%
					// Listado solo lo ve el responsable


					if (vista != null && vista == 2) {
				%>
				<li><a href="./AccionesReservas?tipo=listado">Listado</a></li>
				<%
					} // fin if
				%>

			</ul></li>
	</ul>
</div>
<!-- end menu -->