<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*"%>
<%@ page import="rba.bbdd.*" %>
<% %>-


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<%
// en la variable path, cargamos el direccionamiento, para referenciar todos los enlaces
String path = request.getContextPath();
%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Restaurante - Texto</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=path %>/css/mm_restaurant1.css" type="text/css" />
<link href="<%=path %>/css/mm_restaurant1.css" rel="stylesheet" type="text/css" />
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
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%
	//
 	// variable inicializada a vacio, pero que nos va a dar la posicion de la pagina de la select
 	String cad="";
	// variable inicializada a cero, que nos indica la posicion en la select para su paginacion
	int inicio=0;
	// variable inicializada a CORREO que es el campo por el que se va a identificar al cliente
	// si no se toca el combo de eleccion de campo de busqueda siempre se ordenara por CORREO
	// si se tocara el desplegable este valor cambiaria al elegido
	String campo="fecha";
	// variable inicializada a vacio, y es el valor que va a toma de la caja de texto de busqueda
	// si no se hace nada aunque se cambie el como de campo busqueda, no buscaria nada
	// ya que no hay ningun valor
	String textoabuscar="";

	//
	try{
		cad= request.getParameter("paginado");
		campo = request.getParameter("campo");
		textabuscar= request.getParameter("texto");
		inicio= Integer.parseInt(cad);
	}catch(Exception e){ inicio=0;
		campo="fecha";
		texto="";}

		//esta funcion generaba la llamada al listado de clientes ordenado solo por
		//el campo correo
		//Set<Cliente> clientes=Cliente.ejecutaListaCliente(inicio);
		// esta funcion genera listado ordenado por campo de busqueda y muestra solo
		// el texto a buscar
		Set<Cliente> clientes=Cliente.ejecutaListaReservas(inicio, campo,texto);



%>
	<tr bgcolor="#99ccff">
	<td width="15" nowrap="nowrap">&nbsp;</td>
	<td width="745" height="60" colspan="3" class="logo" nowrap="nowrap">Restaurante Bicicleta Atomica<span class="tagline">| Donde los sentidos revaran lo inimaginable</span></td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr bgcolor="#003399">
	<td width="15" nowrap="nowrap">&nbsp;</td>

	<td height="36" colspan="3" id="navigation" nowrap="nowrap" class="navText"><a href="<%=path%>/zonaPrivada/menu.jsp">INICIO</a></td>
	<td>&nbsp;</td>
	</tr>

	<tr bgcolor="#ffffff">
	<td width="15" valign="top"><img src="<%=path %>/css/mm_spacer.gif" alt="" width="15" height="1" border="0" /></td>
	<td width="140" valign="top"><img src="<%=path %>/css/mm_spacer.gif" alt="" width="140" height="1" border="0" /></td>
	<td width="505" valign="top"><br />
	<table border="0" cellspacing="0" cellpadding="2" width="440">
		<tr>
		<td class="pageName"><div align="center">Gestion de Reservas</div></td>
		</tr>

		<tr>
		<td class="bodyText">
		<table width="438" border="1">

          <tr>
          	<th width="103" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='codReserva'">Codigo Reserva</a></th>
            <th width="103" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='fecha'">Fecha</a></th>
            <th width="96" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='hora'">Hora</a></th>
            <th width="56" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='turno'">Turno</th>
            <th width="56" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='codUsuario'">Codigo Usuario</a></th>
            <th width="56" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='codCliente'">Codigo Cliente</a></th>
            <th width="56" scope="col"><a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?campo='codMesa'">Codigo Mesa</a></th>

            <th colspan="2" scope="col"><div align="center"></div>              <div align="center"></div></th>
            </tr>
            	<%
					Iterator<Reserva> it = reservas.iterator();
					while (it.hasNext()){
						Reserva u = it.next();
				%>
          <tr>
            <td><%=u.getCodigo()%></td>
            <td><%=u.getFecha()%></td>
            <td><%=u.getHora() %></td>
            <td><%=u.getTurno() %></td>

            <%
          	// proceso para introducir el codigo de usuario y nos sale el nombre de usuario
	   		Set<Reserva> nombreusuario =Reserva.ejecutaBusquedaUsuario(u.getCodigo());

			Iterator<Reserva> it2 = nombreusuario.iterator();
			while (it2.hasNext()){
				Reserva u2 = it2.next();
			%>
            <td><%=u2.getNombre()%></td>
            <%
			}
			%>

            <%
          	// proceso para introducir el codigo de cliente y nos sale el nombre de cliente
	   		Set<Cliente> nombrecliente =Cliente.ejecutaBusquedaCliente(u.getCodCliente());

			Iterator<Reserva> it3 = nombrecliente.iterator();
			while (it3.hasNext()){
				Reserva u3 = it3.next();
			%>
            <td><%=u3.getNombreCliente()%></td>
             <%
			}
			%>
            <td><%=u.getMesa() %></td>

            <td width="63"><div align="center"><a href="<%=path %>/rba.reservas/AccionReserva.java?codigo=<%u.getCodReserva();%>,accion=1" title="Eliminar Reserva">Eliminar</a></div></td>
            <td width="84"><div align="center"><a href="<%=path %>/rba.reservas/AccionReserva.java?codigo=<%u.getCodReserva();%>,accion=2" title="Modificar Reserva">Modificar</a></div></td>
          </tr>
			   <tr>
			<%
				//Mostrar la paginación correspondiente
				int totalpaginas= Reserva.calculaTotalPaginas();
				for (int i=0;i<=totalpaginas;i++){
					if (inicio!=i){
			%>
			<td>
			<a href="<%=path %>/zonaPrivada/reservas/reserva.jsp?paginado=<%=i %>" target="_self"><%=i %></a>-
			</td>
			<%

					}else{
			%>
			<td>
					<%=i %>-
			</td>
			<%
					}
				}
			%>
			</tr>

        </table>

		  <p>
		  	<form id="form1" name="form1" method="post" action="/rba.reservas/AccionReserva.java?accion=7">
                	Campo de Busqueda:
                       <select name="CmbCampos" id="CmbCampos" onchange="MM_jumpMenu('parent',this,0)">

                         <option value="codigo">Codigo Reserva</option>
                         <option value="fecha" selected>Fecha</option>
                         <option value="hora">Hora</option>
                         <option value="turno">Turno</option>
                         <option value="codUsuario" >Usuario</option>
                         <option value="codCliente">Cliente</option>
                         <option value="codMesa">Mesa</option>
                       </select>
                       <input name="txtbuscar" type="text">
                 		<input type="button" name="BtnBuscar" id="BtnBuscar" value="Buscar" />

		    <p align="center">
		    <label>

							    <a href="<%=path %>/rba.reservas/AccionReserva.java?codigo=null,accion=3" title="Añadir Reserva">Añadir Reserva</a>
		    </label>
		    <br />

		 </form>

		<p>&nbsp;</p>


		<br /></td>
		</tr>
		<tr>
		  <td class="bodyText">&nbsp;</td>
	    </tr>
	</table>
	<br />
	&nbsp;<br />	</td>
	<td valign="top">&nbsp;</td>
	<td width="100%">&nbsp;</td>
	</tr>

	<tr>
	<center><a href="index.jsp?valido=0">Cerrar Sesion</a></center>
	<td width="15">&nbsp;</td>
    <td width="140">&nbsp;</td>
    <td width="505">&nbsp;</td>
    <td width="100">&nbsp;</td>
    <td width="100%">&nbsp;</td>
  </tr>
</table>
</body>
</html>
