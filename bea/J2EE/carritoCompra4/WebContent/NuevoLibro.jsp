<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" import="java.util.*" import="com.tomcat.prueba.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Añadir un nuevo libro</title>
</head>
<body>
<%
	String nuevo=request.getParameter("nuevo");
	if (nuevo==null){
%>
		<center><h1>A&ntilde;adir un nuevo libro</h1></center><br/>
		<center><form action="NuevoLibro.jsp" method=get>
			<input type=text name="NuevoLibro"/><br/>
			<input type="submit" name="nuevo" value="Añadir"/><br/>
		</form></center>
<%
	}else{
%>
	<jsp:useBean id="nuevoLibro" scope="request" class="com.tomcat.prueba.Libro"/>
<%
		String milibro= request.getParameter("NuevoLibro");
	 	if ( (milibro==null) || ((nuevoLibro.insertarLibro(milibro))==0)){
%>
			<center><h1>Imposible a&ntilde;adir libro.</h1></center>
<%
    	}else{
%>
			<center><h1>Libro a&ntilde;adido</h1></center><br/>
			<jsp:useBean id="carrito" scope="session"
				class="com.tomcat.prueba.CarritoCompra"></jsp:useBean>		
<%
//	out.print("Valor submit:"+submit);
			if (carrito != null){
%>
				<hr/>
					<h2 align="center">Tu pedido</h2>
					<center><h1>Pedido pendiente</h1></center>
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
		}
	}
%>
<center><a href="index.jsp">Volver</a></center>
</body>
</html>