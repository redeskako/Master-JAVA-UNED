<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosalpha.clasesCliente.*, java.util.*"%>
<%@ taglib uri="../WEB-INF/libreria.tld" prefix="lib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WS Gesti&oacute;n de Recursos - Sucursal SOAP</title>

<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

<script type="text/javascript">
jQuery(function($){
      $.datepicker.regional['es'] = {
            closeText: 'Cerrar',
            prevText: '&#x3c;Ant',
            nextText: 'Sig&#x3e;',
            currentText: 'Hoy',
            monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
            'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
            monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
            'Jul','Ago','Sep','Oct','Nov','Dic'],
            dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
            dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
            dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
            weekHeader: 'Sm',
            dateFormat: 'yy-mm-dd',
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix: ''};
      $.datepicker.setDefaults($.datepicker.regional['es']);
});    
 
$(document).ready(function() {
   $("#inicio").datepicker();
   $("#final").datepicker();
});

function controlForm(){
	
	if($('#inicio').val() ==""){
		alert("Error: Por favor selecciona una fecha de inicio");
		return false;	
	}
	
	if($('#final').val() ==""){
		alert("Error: Por favor selecciona una fecha de fin");
		return false;	
	}

	var fd = obtenerFecha($('#inicio').val()+"-"+$('#horaInicio').val()+"-"+$('#minutoInicio').val()); 
	var td = obtenerFecha($('#final').val()+"-"+$('#horaFinal').val()+"-"+$('#minutoFinal').val());

	if(td<fd){
		alert("Error: Fecha/horario fin es antes de fecha/horario inicio");
		return false;
	}
	
	return true;
}

function obtenerFecha(fe){
    var arr = fe.split("-");
    var returnDate = new Date(arr[0], arr[1] - 1, arr[2], arr[3], arr[4], 0, 0);
    return returnDate;	
}
</script>

</head>
<body>

	<!-- start container -->
	<div id="container">
		<jsp:include page="Template/menu.jsp"></jsp:include>

		<!-- start content -->
		<div id="content">

			<h4>Crear nueva reserva:</h4>

			<form name="formCrearReserva" action="./CrearReserva" method="post"
				onsubmit="return controlForm();">
				<div class="cntCampo">
					<label>Usuario</label>

					<div>
						<select name="usuario" required>

							<!-- Se crea lista opciones con los distintos usuarios -->
							<c:set var="usuarios"
								value="<%= (ArrayList<Usuario>)request.getAttribute(\"arrayUsuarios\") %>"></c:set>
							<c:forEach var="u" items="${ usuarios }">
								<option value="${u.getIdUsuario()}">${ u.getNombreUsuario() }</option>
							</c:forEach>

						</select>
					</div>

				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Recurso</label>
					<div>
						<select name="recurso" required>

							<!-- Se crea lista opciones con los distintos recursos -->
							<c:set var="recursos"
								value="<%= (ArrayList<Recurso>)request.getAttribute(\"arrayRecursos\") %>"></c:set>

							<c:forEach var="rec" items="${ recursos }">
								<option value="${rec.getIdRecurso()}">${ rec.getDescripcion() }</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Fecha inicio</label>
					<div>
						<input type="text" name="inicio" id="inicio" readonly size="12"
							placeholder="Indique fecha de inicio" required />

					</div>
				</div>
				<div class="cntCampo">
					<label>Hora inicio</label>
					<div class="hora">
						<select name="horaInicio" id="horaInicio">
							<option selected="selected">00</option>
							<% for(int hi=1;hi<=23;hi++){ 
	                    	if(hi<10){
	                    	%>
							<option>0<%= hi %></option>
							<% }else{ %>
							<option><%= hi %></option>
							<% }
	                    } %>
						</select> horas <select name="minutoInicio" id="minutoInicio">
							<option selected="selected">00</option>
							<% for(int mi=1;mi<=59;mi++){ 
	                    	if(mi<10){
	                    	%>
							<option>0<%= mi %></option>
							<% }else{ %>
							<option><%= mi %></option>
							<% }
	                    } %>
						</select> minutos
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<label>Fecha final</label>
					<div>
						<input type="text" name="final" id="final" readonly size="12"
							placeholder="Indique fecha fin" required />
					</div>
				</div>
				<!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Hora final</label>
					<div class="hora">
						<select name="horaFinal" id="horaFinal">
							<% for(int hi=0;hi<=22;hi++){ 
	                    	if(hi<10){
	                    	%>
							<option>0<%= hi %></option>
							<% }else{ %>
							<option><%= hi %></option>
							<% }
	                    } %>
							<option selected="selected">23</option>
						</select> horas <select name="minutoFinal" id="minutoFinal">
							<% for(int mi=0;mi<=58;mi++){ 
	                    	if(mi<10){
	                    	%>
							<option>0<%= mi %></option>
							<% }else{ %>
							<option><%= mi %></option>
							<% }
	                    } %>
							<option selected="selected">59</option>
						</select> minutos
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<label>Estado</label>

					<div>
						<select name="estado" required>

							<!-- Se crea lista opciones con los distintos estados -->
							<c:set var="estados"
								value="<%= (ArrayList<Reserva>)request.getAttribute(\"arrayEstados\") %>"></c:set>
							<c:forEach var="e" items="${ estados }">
								<option value="${e.getIdEstado()}">${ e.getEstado() }</option>
							</c:forEach>

						</select>
					</div>

				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Sucursal</label>

					<div>
						<select name="sucursal">

							<!-- Se crea lista opciones con las sucursales -->
							<c:set var="sucursales"
								value="<%= (ArrayList<Reserva>)request.getAttribute(\"arraySucursales\") %>"></c:set>

							<c:forEach var="s" items="${ sucursales }">
								<option value="${s.getIdSucursal()}">${ s.getSucursal() }</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<input type="hidden" name="volverIndex"
						value="http://localhost:8080/alphaWS_Cliente">
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<input class="btn" type="submit" name="crear" value="Crear">
				</div>
				<!-- fin cntCampo -->
			</form>
		</div>
		<!-- end content -->
	</div>
	<!-- end container -->

</body>
</html>