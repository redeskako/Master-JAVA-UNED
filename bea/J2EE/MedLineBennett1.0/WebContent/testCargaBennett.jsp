<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*" import="java.io.File" 
    import="java.sql.Connection" import="javax.servlet.http"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    
    File f = new File(getServletContext().getRealPath("HospListSept2012.csv"));
    BBDD bd = new BBDD();
    bd.abrirConexion();
    Bennett bennett = new Bennett();
    bennett.cargaCvs(f, bd);
    System.out.println("La ruta del servidor es " + getServletContext().getContextPath());
    
    
%>

Carga de csv en la tabla bennett.
<br/>

</body>
</html>