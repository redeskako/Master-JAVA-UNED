<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="rba.reservas.AccionReserva" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>ALTA RESERVA</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path%>/css/mm_restaurant1.css" type="text/css" />


</head>

<body bgcolor="#0066cc">

<table width="100%" height="101" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#99ccff">
		<td width="15" nowrap="nowrap" >
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="15" height="1" border="0" />
		</td>
		<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
			Restaurante Bicicleta Atomica<span class="tagline">| Donde los sentidos revelaran lo inimaginable</span>
		</td>
		<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

		<tr bgcolor="#003399">
		<td width="15" nowrap="nowrap">&nbsp;</td>
		<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText">
			<a href="<%=path%>/zonaPrivada/menu.jsp">Inicio</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path%>/zonaPrivada/clientes/cliente.jsp">Clientes</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/mesas/mesa.jsp">Mesas</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=path%>/zonaPrivada/reservas/reserva.jsp">Reservas</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path %>/index.jsp?valido=0">Cerrar Sesion</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	</td>
	  	<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
		<td colspan="6">
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="1" height="1" border="0" />
		</td>
	</tr>
	<form action="<%=path%>/AccionReserva?accion=4" method="post">
    	<table width="200" border="1">
          <caption>
            Formulario de Alta de Reserva
          </caption>
          <tr>
            <th scope="col">Campos</th>
            <th scope="col">Valores</th>
          </tr>
           <tr>
            <th scope="row">Fecha: </th>
            <%
              Reserva r = new Reserva();
              String txtReserva = r.recFecha();%>

            <td><input name="txtFecha" type="text" value=<%=txtReserva%>></td>
          </tr>
          <tr>
            <th scope="row">Hora: </th>
            <%

              String txtReserva2 = r.recHora();%>

            <td><input name="txtHora" type="text" value=<%=txtReserva2%>></td>
          </tr>
          <tr>
            <th scope="row">Turno: </th>
            <td><input name="txtTurno" type="text"></td>
          </tr>
          <tr>
            <th scope="row">Usuario:</th>
            <td><input name="txtUsuario" type="text"></td>
          </tr>
          <tr>
            <th scope="row">Cliente: </th>
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
            <th scope="row">Mesa: </th>
            <td><input name="txtMesa" type="text"></td>
          </tr>
        </table>
        <input name="submit" type="submit" value="Alta">

    </form>

</table>

</body>
</html>