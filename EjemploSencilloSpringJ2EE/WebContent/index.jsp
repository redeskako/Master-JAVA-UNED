<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="WEB-INF/indexTag.tld" prefix="probando" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prueba Spring Framework</title>
</head>
<body>
	<h4>Prueba Básica Spring Framework</h4>

	<c:out value="${'Iniciando Prueba'}"/>
	<br/>
	
	<probando:texto_prueba/>
	<br/>
	
	<form action="indexView" method="post">
		<input type="submit" name="boton" id="boton" value="Mostrar"/>                
	</form>
	
	<p>
		<c:out value="${mensajePantalonesColorHTML}" />
	</p>
	<p>
		<c:out value="${mensajePantalonesTallaHTML}" />
	</p>
	<p>
		<c:out value="${mensajePantalonesPrecioHTML}" />
	</p>
	<br/>
	<p>
		<c:out value="${mensajeCamisaColorHTML}" />
	</p>
	<p>
		<c:out value="${mensajeCamisaTallaHTML}" />
	</p>
	<p>
		<c:out value="${mensajeCamisaPrecioHTML}" />
	</p>
	
</body>
</html>