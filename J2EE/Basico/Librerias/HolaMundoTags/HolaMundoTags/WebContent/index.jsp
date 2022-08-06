<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/miTag.tld" prefix="mensaje" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prueba Tags</title>
	</head>
	<body>
		<br/>Texto HTML<br/><mensaje:hola_amigos/>
		<br/>Texto HTML2<br/><mensaje:cadena cad="Esta es mi cadena parámetro"/>
		<br/><br/>
		<mensaje:cuerpo>
			0123456789
		</mensaje:cuerpo>
		<br/><br/>
		<mensaje:bucle veces="5">
			Estoy bucleando<br/>
		</mensaje:bucle>
	</body>
</html>