<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Detectado un error.</title>
</head>
<body>
<center><h1>El servidor ha detectado un error</h1><br/>
<p><%= exception.getMessage() %></p><br>
<h2>Por favor contacte con su administrador</h2>
<a href="index.jsp">Home</a>
</center>
</body>
</html>