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
<title>Restaurante - Calendario</title>
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


<body bgcolor="#0066cc">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr bgcolor="#99ccff">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td width="745" height="60" colspan="2" class="logo" nowrap="nowrap"><br />
	  <fmt:message key="membrete" bundle="${base}" /><span class="tagline">| <fmt:message key="membrete_leyenda" bundle="${base}" /></span></td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td height="36" colspan="2" id="navigation" nowrap="nowrap" class="navText"><a href="index.jsp?idioma2=<%=idioma %>"><fmt:message key="inicio" bundle="${base}" /></a></td>
	<td>&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td width="15" valign="top"><img src="css/mm_spacer.gif" alt="" width="15" height="1" border="0" /></td>
	<td width="35" valign="top"><img src="/css/mm_spacer.gif" alt="" width="35" height="1" border="0" /></td>
	<td width="710" valign="top"><br />
	<table border="0" cellspacing="0" cellpadding="2" width="610">
        <tr>
          <td class="pageName"><fmt:message key="especialeventosenero" bundle="${base}" /><br />
		  &nbsp;<br /></td>
        </tr>
	</table>
	<table width="710" cellpadding="2" cellspacing="01" border="0" id="calendar">
        <tr id="noborder">
          <td colspan="7" class="subHeader"><fmt:message key="Mes" bundle="${base}" /></td>
        </tr>
		<tr id="weekdays" bgcolor="#003399">
          <th align="center" width="15%" class="smallText"><fmt:message key="Domingo" bundle="${base}" /></th>
          <th align="center" width="14%" class="smallText"><fmt:message key="Lunes" bundle="${base}" /></th>
          <th align="center" width="14%" class="smallText"><fmt:message key="Martes" bundle="${base}" /></th>
          <th align="center" width="14%" class="smallText"><fmt:message key="Miercoles" bundle="${base}" /></th>
          <th align="center" width="14%" class="smallText"><fmt:message key="Jueves" bundle="${base}" /></th>
          <th align="center" width="14%" class="smallText"><fmt:message key="Viernes" bundle="${base}" /></th>
          <th align="center" width="15%" class="smallText"><fmt:message key="Sabado" bundle="${base}" /></th>
        </tr>
		<tr id="calheader" bgcolor="#ffffcc">
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
        </tr>
        <tr>
          <td valign="top" height="50" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
        </tr>
		<tr id="calheader" bgcolor="#ffffcc">
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
        </tr>
        <tr>
          <td valign="top" height="50" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
        </tr>
       <tr id="calheader" bgcolor="#ffffcc">
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
        </tr>
        <tr>
          <td valign="top" height="50" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
        </tr>
        <tr id="calheader" bgcolor="#ffffcc">
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
        </tr>
        <tr>
          <td valign="top" height="50" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
        </tr>
		<tr id="calheader" bgcolor="#ffffcc">
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
          <td valign="top" align="center" class="smallText">&nbsp;</td>
        </tr>
        <tr>
          <td valign="top" height="50" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
          <td valign="top" class="smallText">&nbsp;</td>
        </tr>
      </table>
	  <br />
	&nbsp;<br />	</td>
	<td>&nbsp;</td>
	</tr>

	<tr>
	<td width="15">&nbsp;</td>
    <td width="35">&nbsp;</td>
    <td width="710">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>
</table>
</body>
</html>
