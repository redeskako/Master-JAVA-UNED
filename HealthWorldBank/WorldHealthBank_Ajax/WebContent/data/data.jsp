<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<div>

<a href="data?action=edit&Indicador=&code=&year=">
   <button>A&ntilde;adir nuevo registro</button>
</a>
</br></br>
<table border=1>
        <thead>
            <tr>
                <th>Indicador Code</th>
                <th>Country Code</th>
                <th>Year</th>
                <th>Percentage</th>
                <th>Editar</th>
                <th>Borrar</th>
            </tr>
        </thead>
        <tbody>
             <c:forEach items="${datas}" var="data">
                <tr>
                   <td><c:out value="${data.indicador_code}" /></td>
                     <td><c:out value="${data.country_code}" /></td>
                    <td><c:out value="${data.year}" /></td>
                    
                    <td><c:out value="${data.percentage}" /></td>
                    <td><a href="data?action=edit&Indicador=${data.indicador_code}&code=${data.country_code}&year=${data.year}">
						<img src="img/edit.png" height="35" width="35">
						</a></td>
					<td><a href="data?action=delete&Indicador=${data.indicador_code}&code=${data.country_code}&year=${data.year}">
							<img src="img/Delete-icon.png" height="35" width="35">
						</a></td>
                
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    	
    	<div id="paginas">
    	<fmt:parseNumber var = "i" type = "number" value = "${paginaActual}" />
    	<a id="primera" href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1" name="pag" >
    	<img src="img/flecha_primera.jpg"  height="15" width="15"></a>
    	
    	<a id="anterior" href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=${paginaActual-1}" name="pag" >
    	<img src="img/fecha_anterior.jpg"  height="15" width="15"></a>
    	
    	${paginaActual}
    	
    	<a id="siguiente" href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=${paginaActual+1}" name="pag" >
    	<img src="img/fecha_siguiente.jpg"  height="15" width="15"></a>
    	
    	<a id="ultima" href = "http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=${numeroPaginas}" name="pag" >
    	<img src="img/flecha_ultima.jpg"  height="15" width="15"></a>
    	
    </div>
</div>
  
<script>
$(document).ready(function(){
	

	
	if(${paginaActual} == 1){
		$("#primera").removeAttr('href');
		$("#anterior").removeAttr('href');
	}else if(${paginaActual} == ${numeroPaginas}){
		$("#siguiente").removeAttr('href');
		$("#ultima").removeAttr('href');
	}
	
});
</script>
    
</body>    
</html>