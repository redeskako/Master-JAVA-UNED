<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ page isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detectado un error</title>
</head>
<body>
<center>

    <h1>El servidor ha detectado un error</h1> <br/>
	<p><%out.print(exception.getMessage()); %> </p> <br/><br/>
	
</center>	
</body>
</html>