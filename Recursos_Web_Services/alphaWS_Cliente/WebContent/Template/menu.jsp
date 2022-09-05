<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- start menu -->
<div id="menu">
	<ul>

		<libTemplate:mostrar rolMostrar="1">
		<li><a href="#">Mis peticiones / reservas</a>

			<ul>
				<li><a href="./MisPeticiones">Mis peticiones pendientes</a></li>
				<li><a href="./MisReservas">Mis reservas</a></li>
			</ul>
		</li>
		</libTemplate:mostrar>
	
		<li><a href="#">Recursos</a>
			<ul>
				<li><a href="./FormularioBuscarRecurso">Buscador</a></li>
			</ul>
		</li>
				
		<li><a href="#">Reservas</a>
			<ul>
				<libTemplate:ocultar rolOcultar="0">
				<li><a href="./FormularioCrearReserva">Crear reserva</a></li>
				</libTemplate:ocultar>
				
				<li><a href="./FormularioBuscarReserva">Buscador</a></li>
				
			</ul>
		</li>
	</ul>
</div>
<!-- end menu -->