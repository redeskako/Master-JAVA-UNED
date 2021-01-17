<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page
	import="es.uned2014.recursosAlpha.reserva.*,es.uned2014.recursosAlpha.usuario.*,es.uned2014.recursosAlpha.recurso.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
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
   $("#inicioD").datepicker();
   $("#inicioH").datepicker();
   $("#finalD").datepicker();
   $("#finalH").datepicker();
});

function controlForm(){

	if($('#inicioD').val() !="" && $('#inicioH').val() =="" ){
		alert("Error: Por favor selecciona todo el intervalo para la fecha de inicio.");
		return false;	
	}

	if($('#inicioH').val() !="" && $('#inicioD').val() =="" ){
		alert("Error: Por favor selecciona todo el intervalo para la fecha de inicio.");
		return false;	
	}

	if($('#finalD').val() !="" && $('#finalH').val() =="" ){
		alert("Error: Por favor selecciona todo el intervalo para la fecha final.");
		return false;	
	}

	if($('#finalH').val() !="" && $('#finalD').val() =="" ){
		alert("Error: Por favor selecciona todo el intervalo para la fecha final.");
		return false;	
	}

	var id = obtenerFecha($('#inicioD').val()); 
	var ih = obtenerFecha($('#inicioH').val());

	if(ih<id){
		alert("Error: El intervalo para la fecha de inicio no es correcto. Revise las fechas.");
		return false;
	}

	var fd = obtenerFecha($('#finalD').val()); 
	var fh = obtenerFecha($('#finalH').val());

	if(fh<fd){
		alert("Error: El intervalo para la fecha final no es correcto. Revise las fechas.");
		return false;
	}

	return true;
}

function obtenerFecha(fe){
    var arr = fe.split("-");
    var returnDate = new Date(arr[0], arr[1] - 1, arr[2], 0, 0, 0, 0);
    return returnDate;	
}
</script>
</head>
<body>
	<%
		// Se recuperan los atributos de rol y vista para mostrar/ocultar partes del jsp
		Integer rolSesion = (Integer) session.getAttribute("rolSesion");
		Integer vistaSesion = (Integer) session.getAttribute("vistaSesion");
	%>
	<div id="container">

		<%@include file='Templates/encabezado.jsp'%>
		<%@include file='Templates/menu.jsp'%>

		<div id="content">

			<% //Si se ha solicitado una búsqueda: se extraen los datos buscados	
			String usuarioB = request.getParameter("usuario");  
			String recursoB = request.getParameter("recurso"); 
			String inicioD = request.getParameter("inicioD");
			String inicioH = request.getParameter("inicioH");
			String finalD = request.getParameter("finalD");
			String finalH = request.getParameter("finalH");
			String estadoB = request.getParameter("estado");	
			%>

			<h4>Buscador de reservas:</h4>


			<form name="formBuscarReserva" action="./AccionesReservas"
				method="get" onsubmit="return controlForm();">
				<div class="cntCampo">
					<label>Usuario</label>
					<div>
						<select name="usuario">
							<%
							// Crea lista opciones con los distintos usuarios
							Usuario u = new Usuario();
							ArrayList<Usuario> tsU = (ArrayList<Usuario>) request.getAttribute("usuariosListadoCompleto");
							Iterator<Usuario> it = tsU.iterator();
 
							// Opción "cualquiera":
							%>
							<option value="">- - - cualquiera - - -</option>
							<%

							// resto del listado:		
							while (it.hasNext()) {
								u = (Usuario)it.next();
								if(u.getIdUsuario() == Integer.parseInt(usuarioB)){
									%>
							<option value="<%=u.getIdUsuario()%>" selected="selected"><%= u.getNombreUsuario() %></option>
							<%
								} else {
							%>
							<option value="<%=u.getIdUsuario()%>">
								<%= u.getNombreUsuario() %></option>
							<%
								}
							} // fin while
						%>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Recurso</label>
					<div>
						<select name="recurso">
							<%
							// Crea lista opciones con los distintos recursos
							Recurso rec = new Recurso();
							ArrayList<Recurso> tsR = (ArrayList<Recurso>) request.getAttribute("recursosListadoCompleto");

							Iterator<Recurso> itR = tsR.iterator();

							// Opción "cualquiera":
							%>
							<option value="">- - - cualquiera - - -</option>
							<%

							// resto del listado:		
							while (itR.hasNext()) {
								rec = (Recurso)itR.next();
								if(rec.getIdRecurso() == Integer.parseInt(recursoB)){
									%>
							<option value="<%=rec.getIdRecurso()%>" selected="selected"><%= rec.getDescripcion() %></option>
							<%
								} else {
							%>
							<option value="<%=rec.getIdRecurso()%>">
								<%= rec.getDescripcion() %></option>
							<%
								}
							} // fin while
							%>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Fecha Inicio</label>
					<div>
						entre:
						<%
                    	if (inicioD == null || inicioD.equals("")){
                    	%>
						<input type="text" name="inicioD" id="inicioD" readonly="readonly"
							size="12" placeholder="- - - cualquiera - - -" required value="" />
						<%	
                    	} else {
                    	%>
						<input type="text" name="inicioD" id="inicioD" readonly="readonly"
							required size="12" value="<%=inicioD%>" />
						<%	
                    	} 
                    	%>
						<br />hasta:
						<%
                    	if (inicioH == null || inicioH.equals("")){
                    	%>
						<input type="text" name="inicioH" id="inicioH"
							placeholder="- - - cualquiera - - -" value="" />
						<%	
                    	} else {
                    	%>
						<input type="text" name="inicioH" id="inicioH" readonly="readonly"
							size="12" value="<%=inicioH%>" />
						<%	
                    	} 
                    	%>
					</div>
				</div>
				<!-- cntCampo -->

				<div class="cntCampo">
					<label>Fecha Final</label>
					<div>
						entre:
						<%
                    	if (finalD == null || finalD.equals("")){
                    	%>
						<input type="text" name="finalD" id="finalD" readonly="readonly"
							size="12" placeholder="- - - cualquiera - - -" required value="" />
						<%	
                    	} else {
                    	%>
						<input type="text" name="finalD" id="finalD" readonly="readonly"
							required size="12" value="<%=finalD%>" />
						<%	
                    	} 
                    	%>
						<br />hasta:
						<%
                    	if (finalH == null || finalH.equals("")){
                    	%>
						<input type="text" name="finalH" id="finalH"
							placeholder="- - - cualquiera - - -" value="" />
						<%	
                    	} else {
                    	%>
						<input type="text" name="finalH" id="finalH" readonly="readonly"
							size="12" value="<%=finalH%>" />
						<%	
                    	} 
                    	%>
					</div>
				</div>
				<!-- fin cntCampo -->

				<%
				//Combo estado sólo visible para responsables:
				if (rolSesion != null && vistaSesion == 2){
				%>
				<div class="cntCampo">
					<label>Estado</label>
					<div>
						<select name="estado">

							<%
							Reserva res = new Reserva();
							ArrayList<Reserva> tsE = (ArrayList<Reserva>) request.getAttribute("estadosListado");

							Iterator<Reserva> itE = tsE.iterator();

							// Opción "cualquiera":
							%>
							<option value="">- - - cualquiera - - -</option>
							<%

							// resto del listado:		
							while (itE.hasNext()) {
								res = (Reserva)itE.next();
								if(res.getIdEstado() == Integer.parseInt(estadoB)){
									%>
							<option value="<%=res.getIdEstado()%>" selected="selected"><%= res.getEstado() %></option>
							<%
								} else {
							%>
							<option value="<%=res.getIdEstado()%>">
								<%= res.getEstado() %></option>
							<%
								}
							} // fin while
						%>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->
				<%
				}
				%>
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="buscador"> <input
						class="btn" type="submit" value="Buscar">
				</div>
				<!-- fin cntCampo -->

			</form>

			<div style="clear: both"></div>


			<h4>Reservas encontradas:</h4>

			<%
				// Dibuja la tabla de las reservas confirmadas
				Reserva r = new Reserva();
			
				// ArrayList en el que almacenar las reservas para el buscador:
				ArrayList<Reserva> tsRB = (ArrayList<Reserva>) request.getAttribute("reservasBuscadas");

				Iterator<Reserva> itB = tsRB.iterator();
			
				// Si hay reservas se dibuja la tabla, si no aparece un mensaje
				if (tsRB.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Usuario</th>
					<th scope="col">Recurso</th>
					<th scope="col">Inicio</th>
					<th scope="col">Final</th>
					<%
					// Si el usuario es responsable, se muestra el estado. Si no, no se muestra.
					if (rolSesion != null && vistaSesion == 2){
					%>
					<th scope="col">Estado</th>
					<%
					}
					%>
				</tr>
					<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (itB.hasNext()) {
								r = (Reserva) itB.next();
					%>
				<tr
					<%if (r.getIdEstado() == 3 || r.getIdEstado() == 5) {
							out.print(" class='datosRojo' ");
						}%>>
					<td><%=r.getNombreUsuario()%></td>
					<td><%=r.getDescripcionRecurso()%></td>
					<td><%=r.inicioToString()%></td>
					<td><%=r.finToString()%></td>
					<%
					// Si el usuario es responsable, se muestra el estado. Si no, no se muestra.
					if (rolSesion != null && vistaSesion == 2){
					%>
					<td><%=r.getEstado()%></td>
					<%
					}
					%>
				</tr>
				<%
					} // fin while
				%>
			</table>
			<%
				//Paginación
						Integer numFilas = (Integer) request.getAttribute("numFilas");
						int paginas = (int) Math.ceil(numFilas * 1.0 / 20);

						// Muestra la paginacion si es necesario
						if (paginas > 1) {
			%>
			<br />
			<div class="paginacion">
				P&aacute;gina:
				<ul>
					<%
						for (int p = 1; p <= paginas; p++) {
							String url = "./AccionesReservas?tipo=buscador&p="+p+"&usuario="+usuarioB
									+"&recurso="+recursoB+"&estado="+estadoB+"&inicioD="+inicioD
									+"&inicioH="+inicioH+"&finalD="+finalD+"&finalH="+finalH;
					%>
					<li><a href="<%=url%>"><%=p%></a></li>
					<%
						}
					%>
				</ul>
			</div>
			<%
					} // fin if
				} else {
			%>
			<p>No existe ninguna reserva</p>
			<%
				} // fin if
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->

</body>
</html>