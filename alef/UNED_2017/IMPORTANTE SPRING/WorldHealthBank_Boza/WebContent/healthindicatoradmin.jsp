<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import = "java.lang.String, dao.CountryDao, dao.HealthIndicatorDao, model.Country, model.HealthIndicator,controller.HealthIndicatorCont, java.util.List, java.util.Iterator, java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/tablepagination.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Styles/tablepagination.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<script type="text/javascript">

function deleterecord(idindicator) {
	
	document.getElementById("idindicator").value = idindicator;


	
	}




</script>


</head>
<body>

<div id="paginador"></div>
<table border="1" align="center" id="tblDatos">

<% 
 HttpSession sesion = request.getSession(true); 

 List<HealthIndicator> listadoIndicador = (List<HealthIndicator>)sesion.getAttribute("listaIndicador");

for (int i = 0; i < listadoIndicador.size(); i++) {
       System.out.println( "OK!! " + listadoIndicador.get(i).toString());%>
 

<tr>
  
 <td id="idindicator"><% out.print(listadoIndicador.get(i).getIndicador_code());%> </td> 
 <td><% out.print(listadoIndicador.get(i).getIndicador_name());%> </td>
 <td><% out.print(listadoIndicador.get(i).getSource_note());%> </td>
 <td><% out.print(listadoIndicador.get(i).getSource_organization());%> </td>
 <td> <input type="submit"  value="Edit" name="edit" onclick=""> </td>
 <td> <input type="submit" id="delete" value="Delete" name="delete" onclick="deleterecord()"> </td> 
 <% }%>
 
 
 </tr>

</table>

<form name="newindicator" class="new-indicator" action="./HealthIndicatorCont" method="get">
<input name="indicatorcode" type="text" class="indicatorcode" placeholder="Indicator Code" />
<input name="indicatorname" type="text" class="indicatorname" placeholder="Indicator Name" />
<input name="sourcenote" type="text" class="sourcenote" placeholder="source Note" />
<input name="sourceorganization" type="text" class="sourceorganization" placeholder="Source Organization" />
<input type= "submit" class="addindicator" value= "ADD"> 

</form>


<script type="text/javascript">
var p = new Paginador(
    document.getElementById('paginador'),
    document.getElementById('tblDatos'),
    4
);
p.Mostrar();
</script>

</body>
</html>