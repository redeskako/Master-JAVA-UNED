<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet">
<title>Lista videos de Canal</title>

	
</head>
<body>

<h2>Pág ${pag} del canal ${id_canal}.</h2>	
	<br/>
	<br/>
	<div class="indice1">
	<a href="<c:url value='/pagant?id_canal=${id_canal}&pag=${pag}' />">Pág. Anterior</a>
	</div>
	<div class="indice2">
	<a href="<c:url value='/canales' />">Volver a canales</a>
	</div>
	<div class="indice3">
	<a href="<c:url value='/pagsig?id_canal=${id_canal}&pag=${pag}' />">Pág. Siguiente</a>
	</div>
	
	<table border="0">
		<tr bgcolor="#819FF7">
		<td>IMÁGEN</td><td>TITULO</td><td>DESCRIPCIÓN</td>
		</tr>
		
		<!-- Elabora tabla con los videos del canal. -->
		<c:forEach items="${videos}" varStatus="st" var="Videos">
			<tr<c:choose>
                    <c:when test='${st.count%2 ==0}'>
                            bgcolor="#819FF7"
                       </c:when>
                       <c:otherwise>
                            bgcolor="#CED8F6"
                       </c:otherwise>
                   </c:choose>>
            <td><a href="<c:url value='/muestra_video?id=${Videos.id}'/>"><img src='${Videos.thumbnail}'/></a></td>
			<td>${Videos.titulo}</td>
			<td>${Videos.descripcion}</td>
			</tr>
		</c:forEach>
	</table>

</body>

</html>
