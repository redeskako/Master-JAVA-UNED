<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>

<br/>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<form action="ProcesaLogado" method="post">
<center>
<table>
<tr>
<td>
Usuario:
</td>
<td>
<input type="text" name="usuario"/>
</td>
</tr>
<tr>
<td>
Password:
</td>
<td>
<input type="password" name="password"/>
</td>
</tr>
<tr>
<td>
<input type="submit" name="submit" value="Logarse"/>
</td>
</tr>
</table>
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
		<center><h1>Error:<%=valido.intValue()%></h1><br/>
		<h2><%=str %></h2></center>
<%
	}
%>
<%@ include file="template/pie.html" %>
