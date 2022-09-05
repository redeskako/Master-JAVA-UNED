<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="rba.reservas.AccionReserva" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>ALTA RESERVA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path%>/css/mm_restaurant1.css" type="text/css" />


</head>

<%String idioma = (String) session.getAttribute("idioma");%>

<fmt:setLocale value="<%=idioma%>"/>
<fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>

<body bgcolor="#0066cc" align="center">

<table width="100%" height="101" border="0" cellpadding="0" cellspacing="0" align="center">
<tr bgcolor="#99ccff">
		<td width="15" nowrap="nowrap" >
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="15" height="1" border="0" />
		</td>
		<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
			<fmt:message key="membrete" bundle="${base}" /><span class="tagline">| <fmt:message key="membrete_leyenda" bundle="${base}" /></span>
		</td>
		<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

		<tr bgcolor="#003399">
		<td width="15" nowrap="nowrap">&nbsp;</td>
		<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText">
			<a href="<%=path%>/zonaPrivada/menu.jsp"><fmt:message key="menu_Inicio" bundle="${base}" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	<form action="<%=path%>/AccionReserva?accion=4" method="post" >
    	<table width="200" border="1" align="center">
          <caption>
            <fmt:message key="FormularioAltaRsva" bundle="${base}"/>
          </caption>
          <tr>
            <th scope="col"><fmt:message key="Campos" bundle="${base}"/></th>
            <th scope="col"><fmt:message key="Valores" bundle="${base}"/></th>
          </tr>
           <tr>
            <th scope="row"><fmt:message key="Fecha" bundle="${base}"/>: </th>
            <%
              Reserva r = new Reserva();
              String txtReserva = r.recFecha();%>

            <td><input name="txtFecha" type="text" value=<%=txtReserva%>></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Hora" bundle="${base}"/>: </th>
            <%

              String txtReserva2 = r.recHora();%>

            <td><input name="txtHora" type="text" value=<%=txtReserva2%>></td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Turno" bundle="${base}"/>: </th>
            <td>
            <select name="CmbTurno" id="CmbTurno" onchange="MM_jumpMenu('parent',this,0)">
            	<option value="1" selected><fmt:message key="PrimerTurno" bundle="${base}"/></option>
            	<option value="2"><fmt:message key="SegundoTurno" bundle="${base}"/></option>
            </select>
        
          </tr>          
          <tr>
            <th scope="row"><fmt:message key="Cliente" bundle="${base}"/>: </th>
            <td>
            	<select name="CmbCliente" id="CmbCliente" onchange="MM_jumpMenu('parent',this,0)">
	           <%
	          	// proceso para introducir el codigo de usuario y nos sale el nombre de usuario
		   		Set<Cliente> nombrecliente =Cliente.ejecutaListaCliente(0);

				Iterator<Cliente> it3 = nombrecliente.iterator();
				while (it3.hasNext()){
					Cliente u3 = it3.next();
					// u3.getCodigo (codigo cliente de tabla cliente
					%>
		            	<option value="<%=u3.getCodigo()%>" selected>  <%=u3.getApellido1()%> - <%=u3.getApellido2() %> , <%=u3.getNombre()%></option>
	       		     <%
					}
					%>

                </select>
	         </td>
          </tr>
          <tr>
            <th scope="row"><fmt:message key="Mesa" bundle="${base}"/>: </th>
            <td>
            <select name="CmbMesa" id="CmbMesa" onchange="MM_jumpMenu('parent',this,0)">
	           <%
	          	// proceso para introducir el codigo de usuario y nos sale el nombre de usuario
		   		Set<Mesa> numeromesa =Mesa.ejecutaListaMesa(0);

				Iterator<Mesa> it4 = numeromesa.iterator();
				while (it4.hasNext()){
					Mesa u4 = it4.next();
					// u3.getCodigo (codigo cliente de tabla cliente
					%>
		            	<option value="<%=u4.getCodigo()%>" selected> Mesa: <%=u4.getCodigo()%> - Sillas: <%=u4.getSillas() %></option>
	       		     <%
					}
					%>

                </select>
            
            
            
            </td>
          </tr>
        </table>
        <p align="center"><input name="submit" type="submit" value="<fmt:message key="AnadirReserva" bundle="${base}"/>"></p>
        <%
				Integer reservado;
				String cad;
				try{
					cad= request.getParameter("reservado");
				reservado= new Integer(Integer.parseInt(cad));
				}catch(Exception e){ reservado=new Integer(0);}

				//String str= "";
				//switch (reservado.intValue()){
				//	case 1: str="La Reserva no se puede realizar, ya esta ocupado.";break;
					
					
				//}
				if (reservado.intValue()!=0){
			%>

					<center>Error:<%=reservado.intValue()%><br/>
					<fmt:message key="ReservaOcupada" bundle="${base}"/>			
					</center>
				<%
				}
				%>
        

    </form>

</table>

</body>
</html>