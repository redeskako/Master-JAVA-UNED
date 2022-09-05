<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/css/estilos.css" />" rel="stylesheet">
<title>Lista videos de Canal</title>


</head>
<body>
<h2>Lista de Canales Youtube Disponibles</h2>	
	<br/>	
	<table border="0">
		<tr bgcolor="#819FF7">
			<td>TITULO</td><td>DESCRIPCION</td><td>VIDEOS</td>
		</tr>		
		<!-- Elabora tabla con los videos del canal. -->
		<c:forEach items="${canales}" varStatus="st" var="canales">
		<tr
		        <c:choose>
                    <c:when test='${st.count%2 ==0}'>
                            bgcolor="#819FF7"
                       </c:when>
                       <c:otherwise>
                            bgcolor="#CED8F6"
                       </c:otherwise>
                   </c:choose> >		
			
			<td>${canales.nombre}</td>
			<td>${canales.descripcion}</td>
			<td><a href="<c:url value='/videos?id_canal=${canales.id}&pag=1' />">Ver Videos</a></td>
			</tr>
		</c:forEach>
	</table>
	
	 <a href="<c:url value='/' />">logout</a>


</body>

</html>
