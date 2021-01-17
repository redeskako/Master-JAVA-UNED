<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*" import="PaqueteLibros.*"
     errorPage="error.jsp"
%>
<%@ taglib uri="/WEB-INF/libreria.tld" prefix="libreria" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>


   <a href ="/Proyecto_Libreria/session?cerrar=cerrar_session"><img src="/Proyecto_Libreria/iconos/cerrar_sesion.png" alt="Cerrar session" border=0/></a>
   <br />
    <br />

    <!--  Ahora incluiremos la presentación de la librería  -->
<jsp:useBean id="itemLibreria"
			  scope="session"
			  class="PaqueteLibros.Libreria"/>
	<jsp:setProperty name="itemLibreria"
				 property="*"/>


<%
	String submit = request.getParameter("submit");
	if (submit != null){
%>
	<hr/>
		<h2 align="center">añadido a libreria</h2>
	<p>

<%
	}

%>

<table width="50%"+
		   align="center"
		  border="0">
		  <tr>
		  <td><b>Titulo</b></td>
          <td ></td>
         </tr>
	<libreria:itera/>
	<tr>
<td></td>
<td align="center">   <a href ="/Proyecto_Libreria/Libreria2?accion=anadir&nombre="+p_nombre+"&autor="+p_id_autor+"><img src='/Proyecto_Libreria/iconos/anadir.png'  border=0/></a></td>")
<td></td>
<td>
	Nombre Libro:



<INPUT type="text" name="p_nombre" ><BR>
	Id Autor:
<INPUT type="text" name="p_id_autor"><BR>
</td>

</tr>
</table>

</body>
</html>