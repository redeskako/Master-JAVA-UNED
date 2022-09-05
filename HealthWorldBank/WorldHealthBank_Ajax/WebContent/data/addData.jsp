<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<title>Data</title>
<link rel="stylesheet" type="text/css" href="data/mainestilo.css"/>
<link href="data/estilotablas.css" rel="stylesheet" type="text/css" />


</head>
<body>

<%@ include file="header.jsp" %>

<div class="header">
		<h1>Gesti&oacute;n Tabla Data</h1>
</div>

</body> 
<form method="POST" action='data' name="frmAddData" id="contenido">

	<td><label>Indicador</label><br/>
	
	
	<select name="indicador" id="indicador" var="indicador" required>
		<option value="0">Elige una opci&oacute;n</option>
	    <tbody>
            <h:forEach items="${healthIndicators}" var="healthIndicator">
	        	<option  value="${healthIndicator.indicador_code}">${healthIndicator.indicador_code}</option>
	        </h:forEach>
        </tbody>
	       	
    </select><br />
            
            
	<td><label>Countries</label><br/>
	<select name="country" id="country" required>
		<option value="0">Elige una opci&oacute;n</option>
	        <tbody>
             <h:forEach items="${countries}" var="country">
	        	<option  value="${country.country_code}">${country.country_code}</option>
	        	</h:forEach>
        </tbody>
	       	
    </select><br /> 
            
             

	<td><label>Year</label><br/>
	<input type="text" id="year" name = "year"
            value="${data.year}"  /> <br />
            
            
     
            
            
            
	<td><label>Percentage</label><br/>
	<input type="text" id="percentage" name = "percentage"
            value="<c:out value="${data.percentage}" />" /> <br />     



<input id="boton" type="submit" value="Submit" />           


</form>

<script>

//Cargar datos
$(document).ready(function(){
	if("${data.year}" != 0){
    	$("[name=indicador]").val("${data.indicador_code}");
    	//$('#indicador').prop('disabled', 'disabled');
    	$("#country").val("${data.country_code}");
    	//$('#country').prop('disabled', 'disabled');
    	$("#year").val("${data.year}");
    	//$('#year').prop('disabled', 'disabled');
    	$("#percentage").val("${data.percentage}");
	}else{
		$("#indicador").val("0");
    	$("#country").val("0");
    	$("#year").val(2017);
    	$("#percentage").val(0);
	}
	
//Almacenar data
	$("#indicador").blur(function(){

		var a = $("#indicador").val();
		var val1 = document.getElementById('indicador').value;
		//console.log("jQuery: "+$("#indicador").val());
		//console.log("a: "+a);
		
	
        console.log("hola, "+a+" , "+val1+" , "+"${data}");
    });
	
});
</script>


</html>