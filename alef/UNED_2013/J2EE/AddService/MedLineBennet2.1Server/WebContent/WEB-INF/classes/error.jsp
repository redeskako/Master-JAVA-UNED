<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1 align="center">Se ha producido un error</h1><br/>
		<p>El mensaje del error es:<%=exception.toString() %></p>
		<p>Por favor pongase en contacto con el administrador.</p> 
    </jsp:body>
</t:genericpage>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Se ha producido un error</h1><br/>
<p>El mensaje del error es:<%=exception.toString() %></p></br>
<p>Por favor pongase en contacto con el administrador.</p>
</body>
</html>-->