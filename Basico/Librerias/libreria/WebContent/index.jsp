<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.Vector" import="es.uned.master.java.Libro" import="es.uned.master.java.Libreria"
errorPage="error.jsp"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="es.uned.master.java.Libro"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Librería</title>
</head>
<body>
<center>
	<form method="get" action="index.jsp">
		<h1> Librería </h1>
		<br/>
			Introduzca un libro para añadir a su librería
		<br/>
		<input type="text" name="libro"/>
		<input type="submit" name="submit" value="submit"/>
	</form>
<!--  Ahora incluiremos la presentación de la librería  -->
	<jsp:useBean id="itemLibreria" scope="session" class="es.uned.master.java.Libreria"/>
	<jsp:setProperty name="itemLibreria" property="*"/>
<%
	String submit = request.getParameter("submit");
	// out.print("Valor submit:" + submit);
	if (submit != null){
%>
		<hr/>
			<h2 align="center">añadido a libreria</h2>
		<p>
<%
	}
	itemLibreria.processRequest(request);
%>
	<table width="75%" align="center" border="1">
	<th><center>Título</center></th>
<%
		Vector<Libro> estante = itemLibreria.consultaLibreria();
		//out.print("estante:" + estante.toString());
		for (int i=0 ; i<estante.size() ; i++){
%>
			<tr>
				<td><center><%= estante.get(i).getLibro() %></center></td>
			</tr>
<%
		}
		if (estante.size() == 0){
%>
			<tr><td>La Librería está vacía!</td></tr>
<%
		}
%>
</table>
</center>
</body>
</html>