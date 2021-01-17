<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.basedatos.*, org.sesion.* "%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
</head>
<body>

<div style="width: 100%; height: 60px; background-color: #db9542; padding: 30px;">
<h1>Gestión de Recursos</h1>
</div>
<div style= "width: 100%; height: 100%; background-color:#d3d1d0;">
<%
	String estado = request.getParameter("estado");
	if(estado != null){
		if(estado.equals("invalido")){
%>			
			<center> <h1> Usuario inválido </h1> </center>
<%
			}else if (estado.equals("salir")) {
%>	
			<center><h1> Has salido de la sesión</h1></center>
<%			
			}else if (estado.equals("ilegal")){
%>		
			<center><h1> Inserta un usuario y contraseña para acceder</h1> </center>
<%
	}
}
%>
<form  style="align:'center';" name="inicio" action="./Access" method="get">
	<table align="center">
	
		<tr>
			<td>Usuario</td>
			<td><input type="TEXT" name="usuario" size="10"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="PASSWORD" name="password" size="10"></td>
		</tr>
		<tr>
			<td> <input type="submit" name="cmdAceptar" value="Aceptar"></td>
			<td> <input type="reset" name="cmdLimpiar" value="Limpiar"></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>