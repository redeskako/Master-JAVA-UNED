<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="WEB-INF/indexTag.tld" prefix="probando" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prueba JPA_JPQL</title>
</head>
<body>
	<h4>Prueba Básica JPA JPQL</h4>

	<c:out value="${'Hola compañeros'}"/>
	<br/><br/>
	<probando:texto_prueba/>
	<br/>
	
	<form action="indexView?operacion=MostrarTodos" method="post">
		<input type="submit" name="boton" id="boton" value="MostrarTodos"/>                
	</form>
	<form action="indexView?operacion=MostrarMenores95" method="post">
		<input type="submit" name="boton" id="boton" value="MostrarMenores95"/>                
	</form>
	<br/>
	<c:if test="${miListaHTML!=null}">
		<table border="0">
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Rol</th>
				<th>Edad</th>
			</tr>
			<c:forEach var="i" begin="1" end="${miListaHTML.size()}">
				<tr>
					<td align="center"><c:out value="${miListaHTML.get(i-1).getId()}" /></td>
					<td align="center"><c:out value="${miListaHTML.get(i-1).getNombre()}" /></td>
					<td align="center"><c:out value="${miListaHTML.get(i-1).getRol()}" /></td>
					<td align="center"><c:out value="${miListaHTML.get(i-1).getEdad()}" /></td>
				<tr/>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>