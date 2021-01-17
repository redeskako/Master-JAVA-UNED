<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles/estilologin.css" />
<title> Country Page</title>
</head>
<body>

<%
	String estadobbdd = request.getParameter("estadobbdd");
	String estadogestion;
	if (estadobbdd != null){
		switch (estadobbdd) {
            case "addok":  estadogestion = "Insert OK";
                     break;
            case "addnook":  estadogestion = "Insert NOT OK";
                     break;
            case "upok":  estadogestion = "Update OK";
                     break;
            case "upnook":  estadogestion = "Update NOT OK";
                     break;
            case "delok":  estadogestion = "Delete OK";
                     break;
            case "delnook":  estadogestion = "Delete NOT OK";
                     break;
            default: estadogestion = "Estado gestion erroneo";
                     break;
        }
	}else{
		estadogestion = "¿Que gestion de bbdd hacer?";
	}
%>
			
<form name="Country-form" class="login-form" action="./GestionBBDD" method="get">
	
		<div class="header">
		<h1>Tabla Country</h1>
		</div>
		
	
		<div class="content">
		<fieldset>
    	<legend>Add Country</legend>
    	<br>
		<input name="country_code_add" type="text" class="input username" placeholder="country_code" />
		<input name="country_name_add" type="text" class="input username" placeholder="country_name" />
        <br><br>
        <input type= "submit" class="botonCountry" name="Add_Country" value= "Add">
		</fieldset>
		</div>

		<div class="content">
		<fieldset>
    	<legend>Update Country</legend>
		<br>
		<input name="country_code_up" type="text" class="input username" placeholder="country_code" />
		<input name="country_name_up" type="text" class="input username" placeholder="country_name" />
        <br><br>
        <input type= "submit" class="botonCountry" name="Update_Country" value= "Update">
		</fieldset>
		</div>
		
		<div class="content">
		<fieldset>
    	<legend>Delete Country</legend>
		<br>
		<input name="country_code_del" type="text" class="input username" placeholder="country_code" />
        <br><br>
        <input type= "submit" class="botonCountry" name="Delete_Country" value= "Delete">
		</fieldset>
		</div>
		<p align="center"><% out.print(estadogestion);%></p>	
	</form>
	
	
	<%
	HttpSession sesion = request.getSession(true); 
	
	List<Country> listadoPaises = (List<Country>)sesion.getAttribute("listaPaises");
	
   // System.out.println("Using for loop listadoPaises");
    for (int i = 0; i < listadoPaises.size(); i++) {
       System.out.println( "OK!! " + listadoPaises.get(i).toString());
    }

    %>
    
    <table class="login-form" border=1>
        <thead>
            <tr>
                <th>Country_code</th>
                <th>Country_name</th>
            </tr>
        </thead>
        <tbody>
        	<% for (int i = 0; i < listadoPaises.size(); i++) { %>
    
                <tr>
                    <td> <% out.print(listadoPaises.get(i).getCountry_code());%> </td>
                    <td> <% out.print(listadoPaises.get(i).getCountry_name()); %> </td>
                </tr>
             <%} %>
        </tbody>
    </table>

</body>
</html>