<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "dao.HealthIndicatorDao, model.HealthIndicator, dao.DataDao, dao.CountryDao, model.Country, model.Data, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<title>Data</title>
<script src="jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="data/estiloData.css" />

<%
	HttpSession sesion = request.getSession(true); 
%>
</head>
<body>

<%
        Data data = (Data)sesion.getAttribute("data");
		List<Country> listCountry = (List<Country>)sesion.getAttribute("countries");
		List<HealthIndicator> listHealthIndicator = (List<HealthIndicator>)sesion.getAttribute("healthIndicators");
        %>

	<form method="POST" action='data' name="frmAddData" id="contenido">
		
        <table class="tabla" border=1>
	        <tr><td><h1 id=#header">Editar Data</h1></td></tr>
	        <tr>
	        <%if(data.getIndicador_code() == null){%>
	        <td><label>Indicador</label><br/>
	        <select name="indicador" id="indicador" required>
	        <%for(HealthIndicator healthIndicator : listHealthIndicator){%>
	        	<option  value=<%out.print(healthIndicator.getIndicador_code()); %>><%out.print(healthIndicator.getIndicador_code());%></option>
	       	<%}%>
	        </select>
	       
	        </tr>
	        <tr>
	        <td><label>Country</label><br/>
	        <select name="country" id="country" required>
	        <% for(Country country : listCountry){%>
	        	<option value=<%out.print(country.getCountry_code()); %>><%out.print(country.getCountry_code()); %></option>
	       	<%} %>
	        </select>
	         
	        </tr>
	        <tr>
	        <td><label>Year</label><br/>
	        <input type="text" name="year" value="<% out.print(data.getYear()); %>" required/> </td>
	        </tr>
	        <%}else{ %>
	        <td><label>Indicador</label><br/>
	        <input type="text" name="indicador" readonly="readonly" value="<% out.print(data.getIndicador_code()); %>" /> </td>
	        </tr>
	        <tr>
	        <td><label>Country</label><br/>
	        <input type="text"  name="country" readonly="readonly" value="<% out.print(data.getCountry_code()); %>" /> </td>  
	        </tr>
	        <tr>
	        <td><label>Year</label><br/>
	        <input type="text" name="year" readonly="readonly" value="<% out.print(data.getYear()); %>" /> </td>
	        </tr>
	        
	        
	        
	        
	        <%} %>
	        
	        
	        <tr>
	        <td><label>Percentage</label><br/>
	        <input type="text" name="percentage" value="<% out.print(data.getPercentage()); %>" /> </td>  
	        </tr>
	        <tr><td>
	        <input id="boton"
            type="submit" value="Submit" />
            </td></tr>
        </table>      
    </form>
    
    <script type="text/javascript">
    

	var x = document.getElementById('contenido');
    x.style.display = 'none';
    <%if(data.getIndicador_code() == null){%>
	    document.getElementById("indicador").value = "<% out.print(data.getIndicador_code()); %>";
	    document.getElementById("country").value = "<% out.print(data.getCountry_code()); %>";
    <%}%>
    <%
    if(sesion.getAttribute("usuarioAcceso").equals("admin")){%>
    	x.style.display ="block";<%
    }else{ %>
    	x.style.display = "hidden";<%
    }
    %>

</script>

</body>
</html>