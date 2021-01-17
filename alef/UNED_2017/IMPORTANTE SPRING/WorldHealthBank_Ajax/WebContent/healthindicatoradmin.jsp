<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.lang.String, dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator,controller.HealthIndicatorCont, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles/mainestilo.css" />
<link href="Styles/estilotablas.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<title>Health Indicator Page</title>

</head>
<body>

<%@ include file="header.jsp" %>

<div id="paginador"></div>

<div class="header">
	<h1>Gestión Tabla HealthIndicator</h1>
</div>


<form  name="newindicator" class="new-indicator" action="./HealthIndicatorCont" method="get">
<div class="content">
<fieldset>
<legend>Add HealthIndicator</legend>
<input name="indicatorcode" type="text" class="addfield" placeholder="Indicator Code" />
<input name="indicatorname" type="text" class="addfield" placeholder="Indicator Name" />
<input name="sourcenote" type="text" class="addfield" placeholder="source Note" />
<input name="sourceorganization" type="text" class="addfield"" placeholder="Source Organization" />
<input type= "submit" class="button" name="Add_Indicator" value= "Add"> 
</fieldset>
</div>
</form>

<br/>

<form   id="edit"  name="editindicator" class="edit-indicator" action="./HealthIndicatorCont" method="get">
<div class="content">
<fieldset>
<legend>Edit HealthIndicator</legend>
<input  name="editindicatorcode" type="text" class="addfield" placeholder="Indicator Code" />
<input  name="editindicatorname" type="text" class="addfield" placeholder="Indicator Name" />
<input  name="editsourcenote" type="text" class="addfield" placeholder="source Note" />
<input  name="editsourceorganization" type="text" class="addfield"" placeholder="Source Organization" />
<input id="editbutton" type="submit" class="button" name="Update_Indicator" value= "Update"> 
</fieldset>
</div>
</form>

<br/><br/>

<table border="1" align="center" id="tblDatos">

<table class="login-form" border=1>
 <thead>
  <tr>
     <th>INDICATOR_CODE</th>
     <th>INDICATOR_NAME</th>
     <th>SOURCE_NOTE</th>
     <th>SOURCE_ORGANIZATION</th>
     <th>ACCION</th>
  </tr>
 </thead>

<tbody>
<c:forEach items="${listaIndicador}" var="indicadores">
	<tr>
 		<td id="indicatorid"><c:out value="${indicadores.indicator_code}" /></td> 
 		<td><c:out value="${indicadores.indicator_name}" /></td>
 		<td><c:out value="${indicadores.source_note}" /></td>
 		<td><c:out value="${indicadores.source_organization}" /></td>
 		<td>
    		<form action="./HealthIndicatorCont"method="get">
        		<input type="submit" class="button" name="Delete_Indicator" value="Delete" />
        		<input type="hidden" name="deleteindicator" value="${indicadores.indicator_code}" />
    		</form>    
		</td>
	</tr> 
</c:forEach>
</tbody>
</table>

</body>
</html>