<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
<%
	String estado = request.getParameter("estado");
	if (estado != null){
		if(estado.equals("invalido")){
%>
			<center><h1>Usuario invalido</h1></center>
<%
		}else if (estado.equals("salir")){
%>
			<center><h1>Has salido de la sesión</h1></center>
<%
		}else if (estado.equals("ilegal")){
%>
			<center><h1>Acceso ilegal, debe antes entrar en sesión</h1></center>
<%
		}
	}
%>
  	<form align="center" name="formLogin" action="./login_action" method="get">
		<table align="center">
			<tr>
				<td>Usuario</td>
				<td><input type="TEXT" name="txtUsuario" size="10"> </td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="PASSWORD" name="txtPwd" size="10"> </td>
			</tr>
			<tr>
				<td><input type="submit" name="cmdAceptar" value="Aceptar"> </td>
				<td><input type="reset" name="cmdLimpiar" value="Limpiar"> </td>
			</tr>
		</table>
	</form>
</body>
</html>