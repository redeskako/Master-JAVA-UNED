<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Para logarse</title>
</head>
<body>
 <center> 
	<h1 > <font color="0000FF">DISCONFORMIDADES </font> </h1> </br>
	<h2> Introduzca los datos para logarse</h2> </br></br>
  	<form name="formLogin" action="index.jsp" method="post">
		<table  aling="center">
		
			<tr>
				<td >Usuario</td>
				<td><input type="TEXT" name="txtUsuario" size="15"> </td>
			</tr>			
			<tr>
				<td>Password</td>
				<td ><input type="PASSWORD" name="txtPwd" size="17"> </td>
			</tr>
			<tr>
				<td><br></td>
				<td ><br></td>
			</tr>
			
			<tr>
				<td><input type="submit" name="cmdAceptar" value="Aceptar"> </td>
				<td><input type="reset" name="cmdLimpiar" value="Limpiar"> </td>
			</tr>
		</table>		
	</form>
  	</center>
<%
	//Controlar que ha dado a aceptar
	String aceptar= request.getParameter("cmdAceptar");

	if ((aceptar !=null) && (aceptar.equalsIgnoreCase("Aceptar"))){
%>
		<jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<% 
		elcontrolador.logarse(request);

		if ((Integer) session.getAttribute("Estado")== elcontrolador.NOLOGADO  ){
			//Error usuario no autentificado
			System.out.println("Estado: " + session.getAttribute("Estado"));
%>
			<center><hr/><h2>Usuario o password inconrrectos</h2><br>
			<h2>Intentalo de nuevo.</h2></center>
<%
		}else  {
			System.out.println("Estado: " + session.getAttribute("Estado"));
			response.sendRedirect("disconformidades.jsp");
		}
%>	

<%		
	}
%>

		
</body>
</html>	
	