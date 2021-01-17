<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles/mainestilo.css" />
<link href="Styles/estilotablas.css" rel="stylesheet" type="text/css" />
<title> Country Page</title>
</head>
<body>

<%@ include file="header.jsp" %>

<c:choose>
	<c:when test="${!empty param.estadobbdd}">
		<c:choose>
  			<c:when test="${param.estadobbdd == 'addok'}">
    			<c:set var="estadogestion" value="Insert realizado correctamente"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'addnook'}">
    			<c:set var="estadogestion" value="Insert no realizado: Formato incorrecto"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'addexiste'}">
    			<c:set var="estadogestion" value="Insert no realizado: País existente"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'upok'}">
    			<c:set var="estadogestion" value="Update realizado correctamente"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'upnook'}">
    			<c:set var="estadogestion" value="Update no realizado: Formato incorrecto"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'upnoexiste'}">
    			<c:set var="estadogestion" value="Update no realizado: País inexistente"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'delok'}">
    			<c:set var="estadogestion" value="Delete realizado correctamente"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'delnook'}">
    			<c:set var="estadogestion" value="Delete no realizado: Formato incorrecto"/>
  			</c:when>
  			<c:when test="${param.estadobbdd == 'delnoexiste'}">
    			<c:set var="estadogestion" value="Delete no realizado: País inexistente"/>
  			</c:when>
  			<c:otherwise>
    			<c:set var="estadogestion" value="Estado gestion erroneo"/>
  			</c:otherwise>
		</c:choose>
	</c:when>
  	<c:otherwise>
    	<c:set var="estadogestion" value="¿Que gestion de bbdd hacer?"/>
  	</c:otherwise>
</c:choose>
			
<form name="Country-form" class="login-form" action="./GestionBBDD" method="get">
	
		<div class="header">
		<h1>Gestión Tabla Country</h1>
		</div>
		
	
		<div class="content">
		<fieldset>
    	<legend>Add Country</legend>
		<input name="country_code_add" type="text" class="input username" placeholder="country_code" />
		<input name="country_name_add" type="text" class="input username" placeholder="country_name" />
        <input type= "submit" class="botonCountry" name="Add_Country" value= "Add">
		</fieldset>
		</div>

		<div class="content">
		<fieldset>
    	<legend>Update Country</legend>
		<input name="country_code_up" type="text" class="input username" placeholder="country_code" />
		<input name="country_name_up" type="text" class="input username" placeholder="country_name" />
        <input type= "submit" class="botonCountry" name="Update_Country" value= "Update">
		</fieldset>
		</div>
		
		<div class="content">
		<fieldset>
    	<legend>Delete Country</legend>
		<input name="country_code_del" type="text" class="input username" placeholder="country_code" />
        <input type= "submit" class="botonCountry" name="Delete_Country" value= "Delete">
		</fieldset>
		</div>
		<p><c:out value="${estadogestion}"/></br></p>	
	</form>
	    
    <script type="text/javascript">
		function mostrar(){
		document.getElementById('oculto').style.display = 'block';}
		
		function ocultar(){
		document.getElementById('oculto').style.display = 'none';}
	</script>
	
    <input type="button" value="Mostrar tabla" onclick="mostrar()">
    <input type="button" value="Ocultar tabla" onclick="ocultar()">
    </br></br>
    <div id='oculto' style='display:none;'> 
    <table class="login-form" border=1>
        <thead>
            <tr>
                <th>Country_code</th>
                <th>Country_name</th>
            </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${listaPaises}" var="paises">
  			<tr>
            	<td> <c:out value="${paises.country_code}" /> </td>
                <td> <c:out value="${paises.country_name}" /> </td>
            </tr>
  		</c:forEach>
        </tbody>
    </table>
	</div>
</body>
</html>