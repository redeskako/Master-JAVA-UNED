<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.bbdd.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>BAJA RESERVA</title>
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
	<%Integer codigo = Integer.parseInt(request.getParameter("codigo"))  ;
	   Set<Reserva> reservas=Reserva.ejecutaBusquedaReserva(codigo);

		Iterator<Reserva> it = reservas.iterator();
		while (it.hasNext()){
			Reserva u = it.next();
	%>

	<form action="<%=path%>/AccionReserva?accion=<%=5%>&codigo=<%=u.getCodigo()%>" method="post">
    	<table width="200" border="1" align="center">
          <caption>
            Formulario de Baja de Reserva
          </caption>
          <tr>
            <th scope="col">Campos</th>
            <th scope="col">Valores</th>
          </tr>
          <tr>
            <th scope="row">Fecha: </th>
            <td><%=u.getFecha()%></td>
          </tr>
          <tr>
            <th scope="row">Hora: </th>
            <td><%=u.getHora()%></td>
          </tr>
          <tr>
            <th scope="row">Turno: </th>
            <td><%=u.getTurno()%></td>
          </tr>
		  <tr>
            <th scope="row">Usuario: </th>
            <td><%=u.getUsuario()%></td>
          </tr>
          <tr>
          	<%
          	// proceso para introducir el codigo de cliente y nos sale el correo del cliente
	   		Set<Cliente> nombrecliente =Cliente.ejecutaBusquedaCliente(u.getCodigo());

			Iterator<Cliente> it3 = nombrecliente.iterator();

			while (it3.hasNext()){
				Cliente u3 = it3.next();
			%>
            <th scope="row">Cliente: </th>
           <td> <%=u3.getApellido1()%> - <%=u3.getApellido2() %> , <%=u3.getNombre()%></td>
             <%
			}
			%>
          </tr>
          <tr>
            <th scope="row">Mesa: </th>
            <td><%=u.getMesa()%></td>
          </tr>
        </table>
        <p align="center"><input name="submit" type="submit" value="Confirmar Eliminacion" ></p>

    </form>
	<%
	}
	%>

</table>

</body>
</html>