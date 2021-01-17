<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - Pagina principal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/mm_restaurant1.css" type="text/css" />
</head>

<fmt:setLocale value="en_EN"/>
<fmt:setBundle basename="RbaEtiquetas" />


<body bgcolor="#0066cc">

<!--  include file="template/cabecera.html" -->


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<h1><center> ERROR EN RBA ... estamos resolviendo el problema</center></h1>
<%
	String str =request.getParameter("error");
	if (str!=null){
%>
	<br/><h3><center>Detalles del error: <%=str%></center></h3>
<%
	}
%>

</html>



