<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*" import="PaqueteLibros.*"
     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Librería</title>
</head>
<body>
   <a href ="/BusquedaLibros/session?cerrar=cerrar_session"><img src="/BusquedaLibros/iconos/cerrar_sesion.png" alt="Cerrar session" border=0/></a>
   <br />
  <form method="post" action="/BusquedaLibros/session">
         <input type="submit" name="cerrar" value="cerrar_session"/>
    </form>
    <br />

    <!--  Ahora incluiremos la presentación de la librería  -->
	<jsp:useBean id="itemLibreria"
			  scope="session"
			  class="PaqueteLibros.Libreria"/>
	<jsp:setProperty name="itemLibreria"
			 property="*"/>
<%
	String submit = request.getParameter("submit");
	//out.print("Valor submit:"+submit);
	if (submit != null){
%>
	<hr/>
		<h2 align="center">añadido a libreria</h2>
	<p>

<%
	}
	itemLibreria.processRequest(request);
%>
	<table width="50%"+
		   align="center"
		  border="0">
<%
Vector<Libro> estante= itemLibreria.consultaLibreria();
//out.print("estante:"+estante.toString());
		for (int i=0;i<estante.size();i++){
%>
	<tr>
		<td align="left">Titulo:<%= estante.get(i).getLibro() %></td>
		<td align="center"><a href ="/BusquedaLibros/session?cerrar=cerrar_session"><img src="/BusquedaLibros/iconos/anadir.png" alt="Cerrar session" border=0/></a></td>
		<td align="center"><a href ="/BusquedaLibros/session?cerrar=cerrar_session"><img src="/BusquedaLibros/iconos/borrar.png" alt="Cerrar session" border=0/></a></td>
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
</body>
</html>