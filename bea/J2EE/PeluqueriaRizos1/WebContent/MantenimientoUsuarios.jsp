<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<%@ page import="es.rizos.beansClientes.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<center>
<%
if(request.getAttribute("cliente")!=null){
    //Cogemos el atributo que nos manda el controlador
    ArrayList clientes = (ArrayList) request.getAttribute("cliente");
    String accion=(String)request.getParameter("action");
    String campo=(String)request.getAttribute("campo");
    String campobusqueda=(String)request.getAttribute("campobusqueda");
    String textobusqueda=(String)request.getAttribute("textobusqueda");

%>

<table class="mod1">
<form action="ProcesoClientes" method="post"><P>
<center>
<text2>Busqueda </text2><INPUT type="text" name="textobusqueda">
<text2>Campo</text2>
<SELECT NAME="campobusqueda" size="1">
	<option value="nombre" >Nombre </option>
	<option value="apellido1">Apellido1 </option>
	<option value="apellido2">Apellido2 </option>
	<option value="dni">DNI </option>

</SELECT>
<INPUT TYPE="submit" name="action" value="buscar">
</form>
</table>
<br>
<table class="mod1" >
<caption class="mod1">
 Listado de usuarios</caption>
<tr>
<th class="mod1">
IDcliente
</th>
<th class="mod1">
<a href="ProcesoClientes?action=ordenar&campo=nombre">
Nombre </a>
</th>
<th class="mod1">
<a href="ProcesoClientes?action=ordenar&campo=apellido1">
Apellido1
</a>
</th>
<th class="mod1">
<a href="ProcesoClientes?action=ordenar&campo=apellido2">
Apellido2
</a>
</th>
<th class="mod1">
<a href="ProcesoClientes?action=ordenar&campo=dni">
DNI
</a>
</th>
<th class="mod1">
EDITAR
</th>
<th class="mod1">

ELIMINAR

</th>
</tr>
<%
	Iterator<Clientes> it = clientes.iterator();
        while (it.hasNext()){
		Clientes cl = it.next();

%>
<tr>
<td class="mod1">
		<%=cl.getId()%>
</td>
<td class="mod1">
		<%=cl.getNombre()%>
</td>
<td class="mod1">
		<%=cl.getApellido1() %>
</td>
<td class="mod1">
		<%=cl.getApellido2() %>
</td>
<td class="mod1">
		<%=cl.getDni() %>
</td>
<td class="mod1">
 <a href="ProcesoDatosClientes?action=obtenerCliente&campo=<%=cl.getId()%>">
 <img src="template/shapes062.gif" alt="editar">
 </a>
</td>
<td class="mod1">
 <a href="ProcesoClientes?action=eliminar&campo=<%=cl.getId()%>">
  <img src="template/shapes033.gif" alt="eliminar">
 </a>
</td>
<%}
	%>
</tr>
</table>
</center>
<br>
<center><table>
<tr>
<%
	//Mostrar la paginación correspondiente
	int inicio=0;
    int totalpaginas=Clientes.calculaTotalPaginas();
	for (int i=0;i<=totalpaginas;i++){
		if (accion.equals("ejecuta")){
%>
            <td>
               <a href="ProcesoClientes?action=<%=accion%>&paginado=<%=i %>" target="_self"><%=i %></a>-
            </td>

<%

		}else if (accion.equals("ordenar")){
%>
            <td>
                <a href="ProcesoClientes?action=<%=accion%>&campo=<%=campo%>&paginado=<%=i %>" target="_self"><%=i %></a>-
            </td>
<%

		}else if (accion.equals("buscaProcesoDatosClientesr")){
%>
            <td>
                <a href="ProcesoClientes?action=<%=accion%>&campobusqueda=<%=campobusqueda%>&textobusqueda=<%=textobusqueda%>&paginado=<%=i %>" target="_self"><%=i %></a>-
            </td>

<%

		}else{
%>
<td>
		<%=i %>
</td>
<%
		}
	}
  }
%>
</tr>
</table></center>
<center>
<tr>
<td>

<a href="AltaCliente.jsp"> <img src="template/anadircliente.jpg" align="absmiddle" alt="insertar" border="none"></a>
</td>
</tr>
</center>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>