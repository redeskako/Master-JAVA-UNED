<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="myTLD" prefix="libTemplate"%>

<div id="menu">
	<ul>

		<libTemplate:mostrar rolMostrar="1">
			<li><a href="#">Mis peticiones / reservas</a>

				<ul>
					<li><a href="./MisPeticionesReservas?tipo=peticiones">Mis
							peticiones pendientes</a></li>
					<li><a href="./MisPeticionesReservas?tipo=reservas">Mis
							reservas</a></li>
				</ul></li>
		</libTemplate:mostrar>

		<libTemplate:mostrar rolMostrar="2">
			<li><a href="#">Tareas</a>
				<ul>
					<li><a href="./Tareas?tipo=peticiones">Peticiones
							pendientes</a></li>
					<li><a href="./Tareas?tipo=reservas">Reservas a anular</a></li>
				</ul></li>
			<li><a href="#">Usuarios</a>
				<ul>
					<li><a href="./AccionesUsuarios?tipo=menuCrear">Crear</a></li>
					<li><a href="./AccionesUsuarios?tipo=menuEditar">Editar /
							Eliminar</a></li>
					<li><a href="./AccionesUsuarios?tipo=buscador">Buscador</a></li>
					<li><a href="./AccionesUsuarios?tipo=listado">Listado</a></li>
				</ul></li>
		</libTemplate:mostrar>

		<li>
            <a href="#">Recursos</a>
			<ul>
				<libTemplate:mostrar rolMostrar="2">
					<li><a href="./AccionesRecursos?tipo=menuCrear">Crear</a></li>
					<li><a href="./AccionesRecursos?tipo=menuEditar">Editar /
							Eliminar</a></li>
					<li><a href="./AccionesRecursos?tipo=buscador">Buscador</a></li>
				</libTemplate:mostrar>

				<li><a href="./AccionesRecursos?tipo=listado">Listado</a></li>
			</ul>
		</li>
		
		<li>
            <a href="#">Reservas</a>
			<ul>

				<libTemplate:ocultar rolOcultar="0">
					<li><a href="./AccionesReservas?tipo=menuCrear">Crear</a></li>
				</libTemplate:ocultar>

				<libTemplate:mostrar rolMostrar="2">
					<li><a href="./AccionesReservas?tipo=menuEditar">Editar /
							Eliminar</a></li>
				</libTemplate:mostrar>

				<li><a href="./AccionesReservas?tipo=buscador">Buscador</a></li>

				<libTemplate:mostrar rolMostrar="2">
					<li><a href="./AccionesReservas?tipo=listado">Listado</a></li>
				</libTemplate:mostrar>

			</ul>
        </li>
        
        <li>
            <a href="./CalendarioReservas">Calendario</a>
        </li>
	</ul>
</div>
<!-- end menu -->