<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.clasesCliente.*, java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="myTLD" prefix="libTemplate" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WS Gesti&oacute;n de Recursos - Nueva Reserva</title>

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

	<libTemplate:ilegal rolIlegal="0">
		<!-- Si intenta acceder un anónimo: acceso ilegal -->
		<c:redirect url="index.jsp?estado=ilegal" />
	</libTemplate:ilegal>

	<!-- start container -->
	<div id="container">
		<jsp:include page="Template/encabezado.jsp"></jsp:include>
		<jsp:include page="Template/menu.jsp"></jsp:include>

		<!-- start content -->
		<div id="content">

			<h4>Editar reserva:</h4>

			<!-- Se recupera la reserva que se está editando-->
			<c:set var="reserva"
				value="<%= (Reserva)request.getAttribute(\"reserva\") %>"></c:set>

			<form name="formEditarReserva"
				action="http://localhost:8080/alphaWS_Servidor/rest/editarReserva"
				method="post" onsubmit="return controlForm();">
				<div class="cntCampo">
					<label>Usuario</label>

					<div>
						<select name="usuario" required>

							<!-- Se crea lista opciones con los distintos usuarios -->
							<c:set var="usuarios"
								value="<%= (ArrayList<Usuario>)request.getAttribute(\"arrayUsuarios\") %>"></c:set>
							<c:forEach var="u" items="${ usuarios }">
								<c:if test="${ u.getIdUsuario() == reserva.getIdUsuario() }">
									<option value="${u.getIdUsuario()}">${ u.getNombreUsuario() }</option>
								</c:if> >
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
								<c:if test="${ rec.getIdRecurso() == reserva.getIdRecurso() }">
									<option value="${rec.getIdRecurso()}" selected="selected">
										${ rec.getDescripcion() }</option>
								</c:if>
								<option value="${rec.getIdRecurso()}">${ rec.getDescripcion() }</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<!-- fin cntCampo -->


				<%
					Reserva re = (Reserva)request.getAttribute("reserva");
					Date inicio = re.getInicio();
					Date fin = re.getFin();

						String fei = new SimpleDateFormat("yyyy-MM-dd").format(inicio);
						int hoi = Integer.parseInt(new SimpleDateFormat("H").format(inicio));
						int mii = Integer.parseInt(new SimpleDateFormat("m").format(inicio));

						String fef = new SimpleDateFormat("yyyy-MM-dd").format(fin);
						int hof = Integer.parseInt(new SimpleDateFormat("H").format(fin));
						int mif = Integer.parseInt(new SimpleDateFormat("m").format(fin));

				%>

				<div class="cntCampo">
					<label>Fecha Inicio:</label>

					<div>
						<input type="text" name="inicio" id="inicio" readonly="readonly"
							size="12" placeholder="Indique fecha de inicio" required
							value="<%=fei%>" />
					</div>
				</div>
				<div class="cntCampo">
					<label>Hora Inicio</label>
					<div class="hora">
						<select name="horaInicio">
							<%
								for (int hi = 0; hi <= 23; hi++) {
										String selhoi = "";
										if (hi == hoi) {
											selhoi = "selected=\"selected\"";
										}
										if (hi < 10) {
							%>
							<option <%=selhoi%>>0<%=hi%></option>
							<%
								} else {
							%>
							<option <%=selhoi%>><%=hi%></option>
							<%
								}
									}
							%>
						</select> horas <select name="minutoInicio">
							<%
								for (int mi = 0; mi <= 59; mi++) {
										String selmii = "";
										if (mi == mii) {
											selmii = "selected=\"selected\"";
										}
										if (mi < 10) {
							%>
							<option <%=selmii%>>0<%=mi%></option>
							<%
								} else {
							%>
							<option <%=selmii%>><%=mi%></option>
							<%
								}
									}
							%>
						</select> minutos
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<label>Final</label>
					<div>
						<input type="text" name="final" id="final" readonly="readonly"
							size="12" placeholder="Indique fecha fin" required
							value="<%=fef%>" />
					</div>
				</div>
				<!-- fin cntCampo -->
				<div class="cntCampo">
					<label>Hora Final:</label>
					<div class="hora">
						<select name="horaFinal">
							<%
								for (int hi = 0; hi <= 23; hi++) {
										String selhof = "";
										if (hi == hof) {
											selhof = "selected=\"selected\"";
										}
										if (hi < 10) {
							%>
							<option <%=selhof%>>0<%=hi%></option>
							<%
								} else {
							%>
							<option <%=selhof%>><%=hi%></option>
							<%
								}
									}
							%>
						</select> horas <select name="minutoFinal">
							<%
								for (int mi = 0; mi <= 59; mi++) {
										String selmf = "";
										if (mi == mif) {
											selmf = "selected=\"selected\"";
										}
										if (mi < 10) {
							%>
							<option <%=selmf%>>0<%=mi%></option>
							<%
								} else {
							%>
							<option <%=selmf%>><%=mi%></option>
							<%
								}
									}
							%>
						</select> minutos
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<input type="hidden" name="estado"
						value="${ reserva.getIdEstado() }">
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
								<c:if test="${ s.getIdSucursal() == reserva.getIdSucursal() }">
									<option value="${s.getIdSucursal()}" selected="selected">
										${ s.getSucursal() }</option>
								</c:if>
								<option value="${s.getIdSucursal()}">${ s.getSucursal() }</option>
							</c:forEach>

						</select>
					</div>
				</div>
				<!-- fin cntCampo -->
				
				<div class="cntCampo">
					<input type="hidden" name="reserva"	value="${reserva.getIdReserva()}">
				</div>
				<!-- fin cntCampo -->
				
				<div class="cntCampo">
					<input type="hidden" name="volverIndex"	value="http://localhost:8080/alphaWS_Cliente/MisPeticiones">
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<input class="btn" type="submit" name="editar" value="Editar">
				</div>
				<!-- fin cntCampo -->
			</form>
		</div>
		<!-- end content -->
	</div>
	<!-- end container -->

</body>
</html>