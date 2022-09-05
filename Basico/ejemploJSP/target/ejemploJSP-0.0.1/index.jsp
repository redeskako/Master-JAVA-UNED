<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.prueba.jsp.Libro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Una simple clase</title>
</head>
<body>
	<jsp:useBean id="miLibro" class="com.prueba.jsp.Libro"/>
	<jsp:setProperty name="miLibro" property="titulo" value="Iniciandonse en JSP"/>
	T&iacute;tulo del libro: <jsp:getProperty name="miLibro" property="titulo"/>
</body>
</html>