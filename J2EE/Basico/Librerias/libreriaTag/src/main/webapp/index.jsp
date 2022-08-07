<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="WEB-INF/ejemploTags.tld" prefix="ejemplo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ejemplo de Iteracion</title>
</head>
<body>
<%
	String[] cadenas= new String[]{"James Bond", "Loco de atar", "Omega"};
	pageContext.setAttribute ("cadenas",cadenas);
%>
<ejemplo:itera>
	La cadena es: 
</ejemplo:itera>
<hr/>
Son las: <ejemplo:tiempo/>
</body>
</html>