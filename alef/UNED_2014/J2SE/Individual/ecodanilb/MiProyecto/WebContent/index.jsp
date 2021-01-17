<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<style type="text/css">
<!--
body {
	font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
	background-color: #999999;
	margin: 0;
	padding: 0;
	color: #000;
	
}

h1, h2, h3, h4, h5, h6, p {
	margin-top: 0;
	padding-right: 15px;
	padding-left: 15px; 
	border: none;
}


.container {
	width: 960px;
	background-color: #FFF;
	margin: 0 auto;
}

.header {
	background-color: #999999;
	padding-top:5%;

}

.content {


	padding-top:20%;
	padding-bottom:20%;
}


-->
</style></head>

<body>

<p><h2>Gestión de Reservas<h2></p>

<%
	String estado = request.getParameter("estado");
	if (estado != null){
		if(estado.equals("invalido")){
%>
			<center><h3>Introduce un usuario y contraseña válidos</h3></center>
<%
		}else if (estado.equals("salir")){
%>
			<center><h3>Has salido de la sesión</h3></center>
<%
		}else if (estado.equals("ilegal")){
%>
			<center><h3>Acceso incorrecto, debe introdurir usuario y contraseña</h3></center>
<%
		}
	}
%>



<div class="container">
  <div class="header"></div>
  <div class="content">

<form align="center" name="formLogin" action="Accion" method="get">
		<table align="center">
			<tr>
				<td style="color:red;">Usuario:</td>
				<td><input type="TEXT" name="usuario" size="10"> </td>
			</tr>			
			<tr>
				<td style="color:red;">Clave:</td>
				<td><input type="PASSWORD" name="clave" size="10"> </td>
			</tr>
			<tr>
				<td><input type="submit" name="enviar" value="Aceptar"> </td>
				<td><input type="reset" name="limpiar" value="Limpiar"> </td>
			</tr>
		</table>		
	</form>
    
    <!-- end .content --></div>

  <!-- end .container --></div>
</body>
</html>