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
<title>Restaurante - PÃ¡gina principal</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path%>/css/mm_restaurant1.css" type="text/css" />
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>

</head>

<body bgcolor="#0066cc">

<table width="100%" height="101" border="0" cellpadding="0" cellspacing="0">
<tr bgcolor="#99ccff">
		<td width="15" nowrap="nowrap" >
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="15" height="1" border="0" />
		</td>
		<td height="60" colspan="3" class="logo" nowrap="nowrap"><br />
			Restaurante Bicicleta Atomica<span class="tagline">| Donde los sentidos revaran lo inimaginable</span>
		</td>
		<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
		<td width="15" nowrap="nowrap">&nbsp;</td>
		<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText">
			<a href="clientes/cliente.jsp">Clientes</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="mesas/mesa.jsp">Mesas</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="reservas/reserva.jsp">Reservas</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="listados/listado.jsp">Listados</a>&nbsp;&nbsp;&nbsp;&nbsp;
      	</td>
	  	<td width="40">&nbsp;</td>
		<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
		<td colspan="6">
			<img src="<%=path%>/css/mm_spacer.gif" alt="" width="1" height="1" border="0" />
		</td>
	</tr>
	<%Integer codigo = Integer.parseInt(request.getParameter("codigo")) ;
	   Set<Reserva> reservas=Reserva.ejecutaBusquedaReserva(codigo);

		Iterator<Reserva> it = reservas.iterator();
		while (it.hasNext()){
			Reserva u = it.next();
	%>
	<form action="/rba.reservas/AccionReserva.java?codigo=6" method="post">
    	<table width="200" border="1">
          <caption>
            Formulario de Modificacion de Reserva
          </caption>
          <tr>
            <th scope="col">Campos</th>
            <th scope="col">Valores</th>
          </tr>
          <tr>
            <th scope="row">Reserva: </th>
            <td><%=u.getCodigo()%></td>
          </tr>
          <tr>
            <th scope="row">Fecha: </th>
            <td><input name="txtFecha" type="text" value="<%=u.getFecha()%>"></td>
          </tr>
          <tr>
            <th scope="row">Hora: </th>
            <td><input name="txtHora" type="text" value="<%=u.getHora()%>"></td>
          </tr>
          <tr>
            <th scope="row">Turno: </th>
            <td><input name="txtTurno" type="text" value="<%=u.getTurno()%>"></td>
          </tr>


          <tr>
          	<th scope="row">Cliente: </th>
         	  <td>
         	  	<select name="CmbCampos" id="CmbCampos" onchange="MM_jumpMenu('parent',this,0)">
	           <%
	          	// proceso para introducir el codigo de cliente y nos sale el nombre de cliente
		   		Set<Cliente> nombrecliente =Cliente.ejecutaListaCliente(0);

				Iterator<Cliente> it3 = nombrecliente.iterator();
				while (it3.hasNext()){
					Cliente u3 = it3.next();
					// u.getCodigo (codigo usuario de tabla reserva
					// u2.getCodigo (codigo usuario de tabla usuario
					// el proceso siguietne hace que cuando vaya a cargar el combo
					// y llegue al usuario que esta guardado en reserva
					// se seleccion
					if (u.getCliente() == u3.getCodigo()){
						%>
		            	<option value="<%=u3.getCodigo()%>" selected><%=u3.getNombre()%></option>
	       		     <%
					}
					else
					{
						%>
		            	<option value="<%=u3.getCodigo()%>"><%=u3.getNombre()%></option>
	       		     <%
					}
					%>

                </select>
	         </td>
          </tr>
          <tr>
            <th scope="row">Mesa: </th>
            <td><input name="txtMesa" type="text" value="<%=u.getCodigo()%>"></td>
          </tr>

        </table>
        <input name="txtAceptar" type="submit">

    </form>
	<%
	}
		}
		
	%>

</table>

</body>
</html>