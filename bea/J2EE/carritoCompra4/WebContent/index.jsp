<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Vector" errorPage="error.jsp" 
    import="com.tomcat.prueba.*" %>
<%@ taglib uri="WEB-INF/librerias.tld" prefix="libros" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tienda en casa</title>
</head>
<body>
<center>
	<form method=get action="index.jsp">
		<h1>Carrito de la compra </h1> 
	<br/>
		Por favor seleccione un producto a a&ntilde;adir al carrito
	<br/>
	<select name="producto">
	<libros:itera/>
	</select>
	<input type="submit" name="submit" value="sumar"/>
	</form>

<!--  Ahora incluiremos la presentación del carrito de la compra  -->
	<jsp:useBean id="carrito" scope="session" 
		class="com.tomcat.prueba.CarritoCompra"/>
	<jsp:setProperty name="carrito" property="*"/>
<%
	String submit = request.getParameter("submit");
//	out.print("Valor submit:"+submit);
	if (submit != null){
%>
	<hr/>
		<h2 align="center">Tu pedido</h2>
	
<%
	carrito.processRequest(request);
%>
	<table width="75%" align="center" border="1">
<%
		Vector<Libro> productos= carrito.getProducto();
//out.print("Productos:"+productos.toString());
		for (int i=0;i<productos.size();i++){
%>
	<tr>
		<td><%= productos.get(i).getLibro() %></td>
		<td><a href="index.jsp?producto=<%= productos.get(i).getId() %>&submit=eliminar">Eliminar</a></td>
	</tr>
<%
		}
		if (productos.size() == 0){
%>
	<tr><td>No hay elementos en tu pedido.</td></tr>
<%
		}
	}
%>
</table>
</center>
<hr/>
<center><a href="NuevoLibro.jsp">A&ntilde;adir un nuevo libro.</a></center>
</body>
</html>