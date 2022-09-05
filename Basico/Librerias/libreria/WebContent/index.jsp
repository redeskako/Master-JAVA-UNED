<?xml version="1.0"
	  encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.Vector" import="Paq_libreria.Libro" import="Paq_libreria.Libreria"
errorPage="error.jsp"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="Paq_libreria.Libro"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
		 content="text/html; charset=UTF-8" />
<title>Librería</title>
</head>
<body>
<center>
<!--
	<form method=get
		  action="index.jsp">
		<h1> Librería </h1>
	<br/>
		Introduzca un libro para añadir a su librería
	<br/>
	<input type="text" name="libro"></input>
	<input type="submit" value="enviar">
	</form>
-->

<!--  Ahora incluiremos la presentación de la librería  -->
	<jsp:useBean id="itemLibreria"
			  scope="session"
			  class="Paq_libreria.Libreria"/>
	<jsp:setProperty name="itemLibreria"
				 property="*"/>
<%
	String submit = request.getParameter("submit");
	out.print("Valor submit:"+submit);
	if (submit != null){
%>
	<hr/>
		<h2 align="center">añadido a libreria</h2>
	<p>

<%
	}
	itemLibreria.processRequest(request);
%>
	<table width="75%"+
		   align="center"
		  border="1">
<%

Vector<Libro> estante= itemLibreria.consultaLibreria();
//out.print("estante:"+estante.toString());
		for (int i=0;i<estante.size();i++){
%>
	<tr>
		<td>Titulo:<%= estante.get(i).getLibro() %></td>

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