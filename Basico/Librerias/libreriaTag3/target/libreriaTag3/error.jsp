<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="templates/cabecera.html" %>
<body>
<center><h1>Error!</h1></center>
<hr />
<p>El error se ha provocado porque programas fatal el error es <%= exception.toString() %></p>
<hr />
<%@ include file="templates/pie.html" %>
</body>
</html>