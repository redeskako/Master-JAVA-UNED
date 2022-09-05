<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
    
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - Pagina principal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/mm_restaurant1.css" type="text/css" />
</head>

<%

HttpSession sesion= request.getSession();		

String idioma = request.getParameter("idioma2");



if (idioma  == null){	
	 idioma = "en_EN";
	 sesion.setAttribute("idioma2", idioma);
}else{
	  
	 sesion.setAttribute("idioma2", idioma);
}


%>

<fmt:setLocale value="<%=idioma%>"/>
<fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>

<body bgcolor="#0066cc">

<!--  include file="template/cabecera.html" -->


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<h1><center><fmt:message key="Error" bundle="${base}" /></center></h1>
<%
	String str =request.getParameter("error");
	if (str!=null){
%>
	<br/><h3><center><fmt:message key="DetalleError" bundle="${base}" /> <%=str%></center></h3>
<%
	}
%>

</html>



