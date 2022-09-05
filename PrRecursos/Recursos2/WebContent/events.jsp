<%@ page contentType="application/json" import="com.dhtmlx.planner.*,es.uned2014.recursosAlpha.reserva.*"%>
<%= getEvents(request, response) %>
<%!
String getEvents(HttpServletRequest request, HttpServletResponse response) throws Exception {
	EventsManager evs = new EventsManager(request, response);
	return evs.run();
	}
%>