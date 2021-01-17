<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>    
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - Texto</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/mm_restaurant1.css" type="text/css" />
</head>

<%

String idioma = "en_EN";
//String idiomasession = (String) session.getAttribute("idioma2");
String idiomaparametro = request.getParameter("idioma2");

if (idiomaparametro != null){
	idioma = idiomaparametro;
}

%>

<fmt:setLocale value="<%=idioma%>"/>
<fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>


<fmt:setLocale value="es_ES"/>
<fmt:setBundle basename="RbaEtiquetas" />

<body bgcolor="#0066cc">
SESION:<%= session.getId() %><br/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#99ccff">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td width="745" height="60" colspan="3" class="logo" nowrap="nowrap"><br />
	<fmt:message key="membrete" bundle="${base}" /><span class="tagline">| <fmt:message key="membrete_leyenda" bundle="${base}" /></span></td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText"><a href="index.jsp?idioma2=<%=idioma %>"><fmt:message key="inicio" bundle="${base}" /></a></td>
	<td>&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td width="15" valign="top"><img src="css/mm_spacer.gif" alt="" width="15" height="1" border="0" /></td>
	<td width="140" valign="top"><img src="css/mm_spacer.gif" alt="" width="140" height="1" border="0" /></td>
	<td width="505" valign="top"><br />
	<table border="0" cellspacing="0" cellpadding="2" width="440">
		<tr>
		<td class="pageName"><fmt:message key="menupublicolocalizacionmembrete" bundle="${base}" /></td>
		</tr>

		<tr>
		<td class="bodyText">
		<p>
        <iframe width="300" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.es/maps?f=q&amp;hl=es&amp;geocode=&amp;q=mijas+pueblo&amp;ie=UTF8&amp;s=AARTsJrXOSxkgnCB_9TY5NB479ivGzlL9g&amp;ll=36.595339,-4.638333&amp;spn=0.010337,0.012875&amp;z=15&amp;output=embed"></iframe><br /><small><a href="http://maps.google.es/maps?f=q&amp;hl=es&amp;geocode=&amp;q=mijas+pueblo&amp;ie=UTF8&amp;ll=36.595339,-4.638333&amp;spn=0.010337,0.012875&amp;z=15&amp;source=embed" style="color:#0000FF;text-align:left"><fmt:message key="menupublicolocalizacionleyenda" bundle="${base}" /></a></small>
        </p>

		<br /></td>
		</tr>
	</table>
	<br />
	&nbsp;<br />	</td>
	<td valign="top">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr>
	<td width="15">&nbsp;</td>
    <td width="140">&nbsp;</td>
    <td width="505">&nbsp;</td>
    <td width="100">&nbsp;</td>
    <td width="100%">&nbsp;</td>
  </tr>
</table>
</body>
</html>
