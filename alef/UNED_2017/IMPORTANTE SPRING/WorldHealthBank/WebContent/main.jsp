<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator, model.Data, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Styles/mainestilo.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<title>Main</title>
</head>
<body>

<%@ include file="header.jsp" %>


<h1>Seleccion de informacion:</h1>
<%
	
	List<Country> listadoPaises = (List<Country>)sesion.getAttribute("listaPaises");
	List<HealthIndicator> listadoIndicador = (List<HealthIndicator>)sesion.getAttribute("listaIndicador");
	List<Data> listData = (List<Data>)sesion.getAttribute("datas");
	
   // System.out.println("Using for loop listadoPaises");
    for (int i = 0; i < listadoPaises.size(); i++) {
       // System.out.println( "OK!! " + listadoPaises.get(i).toString());
    }
    
    //System.out.println("Using for loop listadoIndicador");
    for (int i = 0; i < listadoIndicador.size(); i++) {
        //System.out.println( "OK!! " + listadoIndicador.get(i).toString());
    }
	
%>

<form align="center" name="formSelect" action="./GetEstadisticas" method="get">
	<label for="nombre">Seleccion del pais:</label> <br/>
	<select id="pais" name="pais">
  		<option value="" selected="selected">- selecciona pais-</option>
  		<% for (int i = 0; i < listadoPaises.size(); i++) { %>
  		<option value="<% out.print(listadoPaises.get(i).getCountry_code());%>"><% out.print(listadoPaises.get(i).getCountry_name()); %></option>
		<%} %>
	</select>
	<br/>
	<label for="nombre">Seleccion del indicador:</label> <br/>
	<select id="indicador" name="indicador">
  		<option value="" selected="selected">- selecciona indicador-</option>
  		<% for (int i = 0; i < listadoIndicador.size(); i++) { %>
  		<option value="<% out.print(listadoIndicador.get(i).getIndicator_code());%>"><% out.print(listadoIndicador.get(i).getIndicator_name()); %></option>
		<%} %>
	</select>
	<h4><input type="submit" name="ver" value="Ver">
	<%
		String estado = request.getParameter("estado");
		if (estado != null){
			if(estado.equals("2")){
	%>
	Seleccion incorrecta...</h4>
	<%
			}}
	%>
</form>




</body>
</html>