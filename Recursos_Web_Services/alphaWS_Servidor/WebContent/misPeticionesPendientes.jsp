<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="es.uned2014.recursosAlpha.reserva.*, java.util.*"%>
<%@ page import="es.uned2014.recursosAlpha.usuario.*"%>
<%@ page import="es.uned2014.recursosAlpha.recurso.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gesti&oacute;n de Recursos</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

<script type="text/javascript">
	jQuery(function($) {
		$.datepicker.regional['es'] = {
			closeText : 'Cerrar',
			prevText : '&#x3c;Ant',
			nextText : 'Sig&#x3e;',
			currentText : 'Hoy',
			monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo',
					'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre',
					'Noviembre', 'Diciembre' ],
			monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
					'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
			dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Mi&eacute;rcoles',
					'Jueves', 'Viernes', 'S&aacute;bado' ],
			dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mi&eacute;', 'Juv', 'Vie',
					'S&aacute;b' ],
			dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S&aacute;' ],
			weekHeader : 'Sm',
			dateFormat : 'yy-mm-dd',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : false,
			yearSuffix : ''
		};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	});

	$(document).ready(function() {
		$("#inicio").datepicker();
		$("#final").datepicker();
	});

	function controlForm() {

		if ($('#inicio').val() == "") {
			alert("Error: Por favor selecciona una fecha de inicio");
			return false;
		}

		if ($('#final').val() == "") {
			alert("Error: Por favor selecciona una fecha de fin");
			return false;
		}

		var fd = obtenerFecha($('#inicio').val());
		var td = obtenerFecha($('#final').val());

		if (td < fd) {
			alert("Error: Fecha fin es antes de fecha inicio");
			return false;
		}

		return true;
	}

	function obtenerFecha(fe) {
		var arr = fe.split("-");
		var returnDate = new Date(arr[0], arr[1] - 1, arr[2], 0, 0, 0, 0);
		return returnDate;
	}
</script>
</head>
<body>

	<lib:ilegal rolIlegal="0">
		<!-- Si intenta acceder un anónimo: acceso ilegal -->
		<c:redirect url="index.jsp?estado=ilegal" />
	</lib:ilegal>

	<lib:cambioVista visible="1">
		<!-- Si intenta acceder un responsable, se le cambia de vista -->
		<c:redirect url="./CambioPerfil" />
	</lib:cambioVista>

	<div id="container">

		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<%
				//Si se ha solicitado la edición de un elemento: FORMULARIO EDITAR	
				String idEditarSt = request.getParameter("idEditar");
				// Si existe un elemento a editar, de extrae del array
				if (idEditarSt != null) {
					Reserva rEditar = new Reserva();
					int idEditar = Integer.parseInt(idEditarSt);

					ArrayList<Reserva> arrayR = (ArrayList<Reserva>) request.getAttribute("misPeticiones");
					Iterator<Reserva> it = arrayR.iterator();
					while (it.hasNext()) {
						Reserva r = (Reserva) it.next();
						if (r.getIdReserva() == idEditar) {
							rEditar = r;
						}
					}
			%>

			<form name="formEditarReserva" action="./MisPeticionesReservas"
				method="get" onsubmit="return controlForm();">
				<div class="cntCampo">
					<input type="hidden" name="usuario"
						value="<%=new Usuario().getIdUsuario((String) session.getAttribute("idSesion"))%>">
				</div>
				<!-- fin cntCampo -->

				<div class="cntCampo">
					<label>Recurso:</label>
					<div>
						<select name="recurso">
							<%
								Recurso rec = new Recurso();
									ArrayList<Recurso> tsR = (ArrayList<Recurso>) request.getAttribute("recursosListadoCompleto");

									Iterator<Recurso> itR = tsR.iterator();

									while (itR.hasNext()) {
										rec = (Recurso) itR.next();

										// Crea lista de recursos	
										if (rEditar.getIdRecurso() == rec.getIdRecurso()) {
							%>
							<option value="<%=rec.getIdRecurso()%>" selected="selected"><%=rec.getDescripcion()%></option>
							<%
								} else {
							%>
							<option value="<%=rec.getIdRecurso()%>"><%=rec.getDescripcion()%></option>
							<%
								}
									}
							%>
						</select>
					</div>
				</div>
				<!-- fin cntCampo -->

				<%
					Date inicio = rEditar.getInicio();
						Date fin = rEditar.getFin();

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
					<input type="hidden" name="estado" value="1">
				</div>
				<!-- fin cntCampo -->
				
				<div class="cntCampo">
					<input type="hidden" name="sucursal" value="1">
				</div>
				<!-- fin cntCampo -->
				
				<div class="cntCampo">
					<input type="hidden" name="tipo" value="editar"> <input
						type="hidden" name="idReserva" value="<%=idEditar%>"> <input
						class="btn" type="submit" name="editar" value="Guardar cambios">
				</div>
				<!-- fin cntCampo -->
			</form>

			<div style="clear: both"></div>
			<a class="info_cancelar" href="./MisPeticionesReservas?tipo=peticiones"> Cancelar la edición de la reserva </a>

			<%
				}
			%>



			<%
				// Si se ha modificado un elemento o ha habido error: MENSAJE INFORMATIVO:
				String idEditado = request.getParameter("idEditado");
				if (idEditado != null && !idEditado.equals("0")) {
			%>
			<div class="info">
				La reserva con id <%=idEditado%> se ha modificado correctamente.
			</div>
			<!-- fin info -->
			<%
				}
				if (idEditado != null && idEditado.equals("0")) {
			%>
			<div class="info_error">La reserva no se ha modificado
				correctamente.</div>
			<!-- fin info_error -->
			<%
				}
			%>

			<%
				//Si se ha solicitado la eliminación de un elemento: MENSAJE INFORMATIVO:
				String idEliminar = request.getParameter("idEliminar");
				if (idEliminar != null) {
					String nombre = request.getParameter("nombre");
			%>
			<div class="info">
				¿Está seguro de que desea eliminar la reserva del recurso "<%=nombre%>"?
				<div>
					<a class="info_btn"
						href="./MisPeticionesReservas?tipo=eliminar&id=<%=idEliminar%>&nombre=<%=nombre%>"> Eliminar </a> <a class="info_btn"
						href="./MisPeticionesReservas?tipo=peticiones"> No eliminar </a>
				</div>
				<div style="clear: both"></div>
			</div>
			<!-- fin info -->
			<%
				}
			%>

			<%
				// Si se ha eliminado un elemento o ha habido error: MENSAJE INFORMATIVO:
				String eliminado = request.getParameter("eliminado");
				if (eliminado != null && eliminado.equals("true")) {
			%>
			<div class="info">La solicitud de reserva se ha eliminado correctamente.</div>
			<!-- fin info -->

			<%
				}
				if (eliminado != null && eliminado.equals("false")) {
			%>
			<div class="info_error">La solicitud de reserva no se ha eliminado correctamente.</div>
			<!-- fin info_error -->

			<%
				}
			%>

			<%
				// La tabla se oculta mientras se edita una reserva
				if (idEditarSt == null && idEliminar == null) {
			%>

			<h4>Mis peticiones pendientes</h4>

			<%
				// Dibuja la tabla de las peticiones pendientes
					Reserva p = new Reserva();
					ArrayList<Reserva> tsP = (ArrayList<Reserva>) request.getAttribute("misPeticiones");
					Iterator<Reserva> it = tsP.iterator();

					// Si hay peticiones pendientes se dibuja la tabla, si no aparece un mensaje
					if (tsP.size() != 0) {
			%>

			<table>
				<tr>
					<th scope="col">Recurso</th>
					<th scope="col">Inicio</th>
					<th scope="col">Final</th>
					<th scope="col">Sucursal</th>
					<th class="boton" scope="col" colspan="2"></th>
				</tr>

				<%
					// Se dibuja la tabla mientras exitan valores en la base de datos
							while (it.hasNext()) {
								p = (Reserva) it.next();
				%>

				<tr>
					<td><%=p.getDescripcionRecurso()%></td>
					<td><%=p.dateCompleteToString(p.getInicio())%></td>
					<td><%=p.dateCompleteToString(p.getFin())%></td>
					<td><%=p.getSucursal()%></td>
					<td class="boton"><a href="./MisPeticionesReservas?tipo=botonEditar&id=<%=p.getIdReserva()%>" 
						class="btn"> Editar </a></td>
					<td class="boton"><a href="./MisPeticionesReservas?tipo=botonEliminar&id=<%=p.getIdReserva()%>&nombre=<%=p.getDescripcionRecurso()%>"
						class="btn">Eliminar</a></td>
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
							<li><a href="./MisPeticionesReservas?tipo=peticiones&p=<c:out value="${ p }" />"><c:out value="${ p }" /></a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<%
				} else {
			%>
			<p>No tiene ninguna petici&oacute;n pendiente</p>
			<%
				} // fin if
				} // fin if
			%>

		</div>
		<!-- end content -->

	</div>
	<!-- end container -->


</body>
</html>