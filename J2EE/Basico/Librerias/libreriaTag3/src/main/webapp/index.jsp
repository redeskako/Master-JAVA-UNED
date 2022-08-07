<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib uri="WEB-INF/ejemploTag.tld" prefix="ejemplo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="templates/cabecera.html" %>
<body>

<ejemplo:crear/>

Hay <%= cadenas.length %> cadenas disponibles.
<hr/>
<ejemplo:itera>
El valor ahora es: 
</ejemplo:itera>

<hr/>
<%@ include file="templates/pie.html" %>
</body>
</html>