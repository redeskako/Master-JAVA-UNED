<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<h1><center>Mi Sexhop Error, estamos resolviendo el problema</center></h1>
<%
	String str =request.getParameter("error");
	if (str!=null){
%>
	<br/><h3><center>Detalles del error: <%=str%></center></h3>
<%
	}
%>
<%@ include file="template/pie.html" %>
</html>