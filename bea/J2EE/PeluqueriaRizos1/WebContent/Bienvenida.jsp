<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<br><br><br><br><br><br><br><br>
<form action="ProcesaLogado" method="post">
<center>
<table class="mod1">
<tr>
<td class="mod1">

Usuario:

</td>
<td>
<input type="text" name="usuario"/>
</td>
</tr>
<tr>
<td class="mod1">

Password:

</td>
<td>
<input type="password" name="password"/>
</td>
</tr>
</table>
<br>
<tr>
<td>
<input type="image" src="template/enviar.jpg" name="submit" value="Logarse" alt="enviar"/>
</td>
</tr>
<br><br><br>
<tr>
<td>
<a href="AltaCliente.jsp"> <img src="template/nuevo1.jpg" alt="insertar" border="none"></a>
<br><br><br><br><br>
</td>
</tr>


</center>
</form>

<%
	Integer valido;
	String cad;
	try{
		cad= request.getParameter("valido");
		valido= new Integer(Integer.parseInt(cad));
	
	}catch(Exception e){ valido=new Integer(0);}

	String str= "";
	switch (valido.intValue()){
		case -1: str="Usuario o Password incorrecto";break;
		case -4: str="Debes iniciar sesi&oacute;n";break;
		case 0: session.invalidate();
	}
	if (valido.intValue()!=0){
%>

		<center><blink> <text3><%=str %></text3></blink></center>
<%
	}
%>
</div>
<%@ include file="template/cerrarsesion.html" %>

