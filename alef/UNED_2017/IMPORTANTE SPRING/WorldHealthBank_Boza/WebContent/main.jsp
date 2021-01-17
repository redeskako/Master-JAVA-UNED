<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Styles/mainestilo.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<title>Main</title>
<%
	HttpSession sesion = request.getSession(true); //capturo la respuesta
%>
</head>
<body>

<div id=menu>
<ul>
  <li class="welcome" > Bienvenido <%=sesion.getAttribute("usuarioAcceso") %></li>
  <li><a href="#home">Logout</a></li>
  <li><a href="#contact">Register</a></li>
  <li id = "adminList"><a href="#Administracion">Administración</a>
  	<ul>
  		<li><a href="#user">User</a></li>
  		<li><a href="http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1">Data</a></li>
  		<li><a href="http://localhost:8080/WorldHealthBank_Boza/adminCountry.jsp">Country</a></li>
  		<li><a href="healthindicatoradmin.jsp">Health Indicator</a></li>
  	</ul>
  </li>
  <li><a href="#about">Home</a></li>
</ul>
</div>
<script type="text/javascript">


var x = document.getElementById('adminList');
    x.style.display = 'none';
   
    <%
    if(sesion.getAttribute("usuarioAcceso").equals("admin")){%>
    	x.style.display = "block"; <%
    }
    %>

</script>

<h1>Seleccion de informacion:</h1>
</br></br><%
	
	List<Country> listadoPaises = (List<Country>)sesion.getAttribute("listaPaises");
	List<HealthIndicator> listadoIndicador = (List<HealthIndicator>)sesion.getAttribute("listaIndicador");
	
    System.out.println("Using for loop listadoPaises");
    for (int i = 0; i < listadoPaises.size(); i++) {
        System.out.println( "OK!! " + listadoPaises.get(i).toString());
    }
    
    System.out.println("Using for loop listadoIndicador");
    for (int i = 0; i < listadoIndicador.size(); i++) {
        System.out.println( "OK!! " + listadoIndicador.get(i).toString());
    }
	
%>

<form align="center" name="formSelect" action="" method="get">
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
  		<option value="<% out.print(listadoIndicador.get(i).getIndicador_code());%>"><% out.print(listadoIndicador.get(i).getIndicador_name()); %></option>
		<%} %>
	</select>
	<br/>
	<input type="submit" name="ver" value="Ver">
</form>

    

<a href="http://localhost:8080/Prueba_web/">Página principal</a>
<a href=".">home</a>



</body>
</html>