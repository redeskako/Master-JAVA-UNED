<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"
	import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/libreria.tld" prefix="lib"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calendario de Reservas</title>
<link type="text/css" rel="stylesheet"
	href="Resources/css/grEstilos.css">
</head>
<body>
	<div id="container">
		<jsp:include page="Templates/encabezado.jsp"></jsp:include>
		<jsp:include page="Templates/menu.jsp"></jsp:include>

		<div id="content">
			<div class="planner" id="planner"><%=(String) request.getAttribute("planner")%></div>
		</div>
	</div>

	<script>
		window.userRol = <%=(Integer)request.getSession().getAttribute("rolSesion")%>
		scheduler.templates.event_class = function(start, end, event) {
			return event.estado;
		};
		scheduler.templates.event_text = function(start, end, event) {
			var s = "";
			if(userRol == 2){
				s = "<b>"+event.usuario+"</b><br>";
			}
			return s + event.estado + ': ' + event.text;
		};
		scheduler.templates.event_bar_text = function(start, end, event) {
			var s = "";
            if(userRol == 2){
                s = "<b>"+event.usuario+"</b> ";
            }
            return s + event.estado + ': ' + event.text;
		};
		dp.attachEvent("onAfterUpdate", function(sid, action, tid, node) {
			var host = window.location.protocol + "//" + window.location.host, r = "";
			switch (action) {
			case "inserted":
				r = "/recursos_alpha/AccionesReservas?tipo=menuCrear"
				break;
			case "update":
			case "delete":
				if(window.userRol && window.userRol==2){
					r = "/recursos_alpha/AccionesReservas?tipo=menuEditar"
				}else{
					r = "/recursos_alpha/MisPeticionesReservas?tipo=peticiones"
				}
				break;
			default:
				break;
			}
			window.location.replace(host + r);
		});
	</script>
</body>
</html>