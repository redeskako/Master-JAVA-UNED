<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*" import="usuario.*"
     errorPage="error.jsp"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
    <form method=get action="MirarUsuario">
        User: <input type="text" name="user" value="pepe" size="20"/><br/>
        Pass: <input type="text" name="pass" value="pepe" size="20"/>
        <br/><br/>
        <input type="submit" value="Submit" value="comprobar"/>
    </form>
    <br />
<br />
<%
	HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("EstadoSesion")!=null){out.print(sesion.getAttribute("EstadoSesion"));}

%>
</body>
</html>