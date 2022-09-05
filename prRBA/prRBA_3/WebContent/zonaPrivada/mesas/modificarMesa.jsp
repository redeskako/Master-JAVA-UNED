<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.mesas.AccionMesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - Pagina principal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path%>/css/mm_restaurant1.css" type="text/css" />


</head>

<%String idioma = (String) session.getAttribute("idioma");%>

<fmt:setLocale value="<%=idioma%>"/>
<fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>

<body bgcolor="#0066cc">

<table width="100%" height="101" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#99ccff">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td width="745" height="60" colspan="3" class="logo" nowrap="nowrap"> <fmt:message key="membrete" bundle="${base}" /><span class="tagline">|  <fmt:message key="membrete_leyenda" bundle="${base}" /></span></td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
		<td width="15" nowrap="nowrap">&nbsp;</td>
		<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText">
			<a href="<%=path%>/zonaPrivada/menu.jsp"><fmt:message key="menu_Inicio" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path%>/zonaPrivada/clientes/cliente.jsp"><fmt:message key="menu_Clientes" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/mesas/mesa.jsp"><fmt:message key="menu_Mesas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/reservas/reserva.jsp"><fmt:message key="menu_Reservas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path %>/index.jsp?valido=0"><fmt:message key="menu_CerrarS" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	</td>
	  	
	</tr>

	<tr bgcolor="#ffffff">
		<td colspan="6">
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="1" height="1" border="0" />
		</td>
	</tr>
	<%Integer codigo = Integer.parseInt(request.getParameter("codigo"));
	   Set<Mesa> mesas=Mesa.ejecutaBusquedaMesa(codigo);

		Iterator<Mesa> it = mesas.iterator();
		while (it.hasNext()){
			Mesa u = it.next();
	%>
	<form action="<%=path%>/AccionMesa?accion=<%=6%>&codigo=<%=codigo%>" method="post">
    	<table width="200" border="1" align="center">
          <caption>
            <div align="center"><fmt:message key="FormularioModiMesa" bundle="${base}" />
          </caption>
          <tr>
            <th scope="col"><div align="center"><fmt:message key="Campos" bundle="${base}" /></th>
            <th scope="col"><div align="center"><fmt:message key="Valores" bundle="${base}" /></th>
          </tr>
          <tr>
            <th scope="row"><div align="center"><fmt:message key="NumSillas" bundle="${base}" />: </th>
            <td><input name="txtNumSillas" type="text" value="<%=u.getSillas()%>"></td>
          </tr>

        </table>
        <p align="center"><input name="txtAceptar" type="submit" value="<fmt:message key="btnEnviar" bundle="${base}" />"></p>

    </form>
	<%
	}
	%>

</table>

</body>
</html>