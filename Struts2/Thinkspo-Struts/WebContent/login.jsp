<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Thinspo</title>
	<link rel="stylesheet" href="/J2EEThinspo/css/estilos.css" type="text/css" />
</head>
<body class="thinspo">
    <s:if test="hasActionErrors()">
   		<div class="errors">
      		<s:actionerror/>
   		</div>
	</s:if>
	<div class="login">
    <s:form id="formulariologin" action="login" method="post">
      <legend>Introduzca usuario y password</legend>
         <s:textfield name="login" label="Login" value="" vmaxlength="15"/>
         <s:password name="password" label="Password" value="" vmaxlength="15"/>
         <s:submit value="Enviar"/>
     </s:form>
     </div>
</body>
</html>