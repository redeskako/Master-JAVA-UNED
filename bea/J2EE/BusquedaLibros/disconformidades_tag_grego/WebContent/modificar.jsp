<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.* "%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar disconformidad</title>

</head>
<body>
<form align="center" name="formLogin" action="" method="get">
		<table aling="center">
		
			<tr>
				<td>ID</td>
				<td><input type="TEXT" name="txtIdDisconformidad" size="10"> </td>
			</tr>			
			<tr>
				<td>Fecha</td>
				<td><input type="TEXT" name="txtFecha" size="10"> </td>
			</tr>
			<tr>
				<td>Usuario</td>
				<td><input type="TEXT" name="txtUsuario" size="10"> </td>
			</tr>
			<tr>
				<td><input type="submit" name="cmdAceptar" value="Aceptar"> </td>
				<td><input type="submit" name="cmdCancelar" value="Cancelar"> </td>
			</tr>
		</table>		
	</form>
	
	
<% 


	String id=request.getParameter("id");
%>
		<jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<% 
	Disconformidad dis=elcontrolador.getDisconformidad(Integer.parseInt(id));
	out.println(dis.numero());
	out.println(dis.fecha());
	out.println(dis.devolucion());
	out.println("Estado: " + session.getAttribute("Estado"));
%>
</body>
</html>