<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.reserva.*,es.uned2014.recursosAlpha.usuario.*,es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
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

<lib:ilegal rolIlegal="0">
	<!-- Si intenta acceder un anónimo: acceso ilegal -->
	<c:redirect url="index.jsp?estado=ilegal"/>
</lib:ilegal>

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<%
			String idNuevo = request.getParameter("idNuevo");
	
			if (!idNuevo.equals("null") && !idNuevo.equals("0")) {
				String nombre = request.getParameter("nombre");
			%>

			<div class="info">
				La reserva con id <%=idNuevo%> se ha creado correctamente para el usuario "<%=nombre%>".
			</div> <!-- fin info -->
			<%
			}
			if (!idNuevo.equals("null") && idNuevo.equals("0"))  {
			%>

			<div class="info_error">
				La reserva no se ha creado correctamente. <br/>
				Compruebe que es compatible con las reservas aprobadas para ese recurso.
			</div> <!-- fin info_error -->
			<%
			}
			%>



			<h4>Crear nueva reserva:</h4>
			
			<form name="formCrearReserva" action="./AccionesReservas" method="get" onsubmit="return controlForm();">
				<div class="cntCampo">
					<label>Usuario</label>

					<!-- Combo estado sólo visible para responsables: -->
					<lib:mostrar rolMostrar="2">
					<div>
						<select name="usuario" required>
						<%
							// Crea lista opciones con los distintos usuarios
							Usuario u = new Usuario();
							ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("usuariosListado");
							Iterator<Usuario> it = tsU.iterator();
						%>
							<option></option>
						<%
							while (it.hasNext()) {
								u = (Usuario)it.next();
						%>
							<option value="<%=u.getIdUsuario()%>"> <%= u.getNombreUsuario() %></option>
						<%
							} // fin while
						%>
						</select>
					</div>
					</lib:mostrar>
					
					<!-- Combo estado sólo visible para empleados: -->
					<lib:mostrar rolMostrar="1">
					<div>
						<select name="usuario">
							<% 
							int idUsuario = (Integer)session.getAttribute("idSesion");
							Usuario u2 = Usuario.getUserById(idUsuario);
							%>
							<option value="<%=idUsuario%>"> <%= u2.getNombreUsuario() %></option>
						</select>
					</div>
					</lib:mostrar>
									
				</div> <!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Recurso</label>
					<div>
						<select name="recurso" required>
							
							<%
							Recurso rec = new Recurso();
							ArrayList<Recurso> tsR = (ArrayList<Recurso>) request.getAttribute("recursosListado");
							
							Iterator<Recurso> itR = tsR.iterator();
							%>
							<option></option>
							<%							
							while (itR.hasNext()) {
								rec = (Recurso)itR.next();
							
							// Crea lista de recursos
								
							%>
							<option value="<%=rec.getIdRecurso()%>"> <%= rec.getDescripcion() %></option>
							<%
							}
							%>
						</select>
					</div>
				</div> <!-- fin cntCampo -->
				
				<div class="cntCampo">
					<label>Fecha inicio</label>
                    <div>  
                      <input type="text" name="inicio" id="inicio" readonly size="12" placeholder="Indique fecha de inicio" required />
                
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
	                    </select> horas
	                    <select name="minutoInicio" id="minutoInicio">
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
				</div> <!-- cntCampo -->
				
				<div class="cntCampo">
					<label>Fecha final</label>
					<div>
						<input type="text" name="final" id="final" readonly size="12" placeholder="Indique fecha fin" required />	
					</div>
				</div> <!-- fin cntCampo -->
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
	                    </select> horas
	                    <select name="minutoFinal" id="minutoFinal">
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
				</div> <!-- cntCampo -->
				
				<div class="cntCampo">
					<label>Estado</label>
	
					<!-- Combo estado sólo visible para responsables: -->
					<lib:mostrar rolMostrar="2">					
					<div>
						<select name="estado" required>
							
							<%
							Reserva res = new Reserva();
							ArrayList<Reserva> tsE = (ArrayList<Reserva>) request.getAttribute("estadosListado");
							
							Iterator<Reserva> itE = tsE.iterator();

							while (itE.hasNext()) {
								res = (Reserva)itE.next();
							
							// Crea lista de recursos
								
							%>
							<option value="<%=res.getIdEstado()%>"> <%= res.getEstado() %></option>
							<%
							}
							%>
						</select>
					</div>
					</lib:mostrar>

					<!-- Combo estado sólo visible para empleados: -->
					<lib:mostrar rolMostrar="1">
					<div>
						<select name="estado">
							<option value="1"> Pendiente </option>
						</select>
					</div>
					</lib:mostrar>				
					
					
				</div> <!-- fin cntCampo -->
		
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="crear">
					<input class="btn" type="submit" name="crear" value="Crear">
				</div> <!-- fin cntCampo -->
			</form>


	</div> <!-- end content -->

	</div> <!-- end container -->

</body>
</html>