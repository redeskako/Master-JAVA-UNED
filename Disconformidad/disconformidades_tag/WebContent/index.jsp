<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Disconformidades - Entrada de usuario</title>
</head>
<body>
  	<form align="center" name="formLogin" action="index.jsp" method="get">
		<table aling="center">
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
<%
	// Controlar que ha dado a aceptar
	String aceptar= request.getParameter("cmdAceptar");
	if (aceptar.equalsIgnoreCase("Aceptar")){
		//Validación de usuario
%>
	<jsp:useBean id="elcontrolador" class="org.aprende.java.Controlador"></jsp:useBean>
<%
	elcontrolador.logarse(request);
	//Recuperas si existe la sesion....
	
	if (session.getAttribute("idUsuario")==null) {
	//		Error usuario no autentificado
		response.sendRedirect("index.jsp");		
	}
%>	
	<center><hr/><h1>Usuario o password inconrrectos</h1><br>
	<h2>Intentalo de nuevo ceporro.</h2></center>
<%
	}else{
		response.sendRedirect("disconformidades.jsp");		
		
%>

<%
	}
%>
</body>
</html>