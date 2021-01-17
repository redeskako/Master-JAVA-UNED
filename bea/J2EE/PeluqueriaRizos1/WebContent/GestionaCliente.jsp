<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="es.rizos.beansUsuario.*" %>
<%@ page import="java.util.*" %>
<%@ page import="es.rizos.beansCitas.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<%
	String cad="";
	int inicio=0;
	try{
		cad= request.getParameter("paginado");
		inicio= Integer.parseInt(cad);
	}catch(Exception e){ inicio=0;}
		String dniSesion= (String) session.getAttribute("Atributodni");
		int id=Usuario.buscaid(dniSesion);%>
<head>
<div id="main">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GestionaCliente</title>
</head>
<center>
<br><br>
<text3>Citas del cliente</text3>
<br><br><br><br>
</center>
<center>

<table class="mod1">

<%  Cita cita=new Cita();
    Vector citasdelusuario=cita.citasUsuario(dniSesion,inicio);
    System.out.println(dniSesion);
%>
<tr>
<th class="mod1">
TURNO
</th>
<th class="mod1">
CODIGO CITA
</th>
<th class="mod1">
TIPOSERVICIO
</th>
<th class="mod1">
DNI
</th>
<th class="mod1">
FECHA
</th>
<th class="mod1">
EDITAR
</th>
<th class="mod1">
BORRAR

</th>
</tr>
<%
	Iterator it = citasdelusuario.iterator();
         while (it.hasNext()){%>
         <%
		Cita c =(Cita)it.next();
%>

<tr>
<td class="mod1">
    <%=c.getTurno()%>
</td>
<td class="mod1">
      <%=c.getCodigoCita()%>
</td>
<td class="mod1">
     <%=c.getTipoServicio()%>
</td>
<td class="mod1">
     <%=c.getDni()%>
</td>
<td class="mod1">
     <%=c.getFecha()%>
</td>
<%
  int codigo=c.getCodigoCita();
  String diaString=c.getFecha().toString();
  int activo=Integer.parseInt(c.getActivo());
  if (dniSesion.equals(c.getDni())&& (activo == 1)){

   %>
<td class="mod1">
<a href="ServletCita?accion=editarCita&codigocita=<%=codigo%>&fechadeldia=<%=diaString%>"><IMG SRC="template/shapes062.gif" ALT="AQUI VA UNA IMAGEN"></a>
</td>
<td class="mod1">
<a href="ServletCita?accion=borrarCita&codigocita=<%=codigo%>"><IMG SRC="template/shapes033.gif" ALT="AQUI VA UNA IMAGEN"></a>
</td>
<%}else{ %>
<td class="mod1">
<IMG SRC="template/shapes063.GIF" ALT="AQUI VA UNA IMAGEN">
</td>
<td class="mod1 ">
<IMG SRC="template/shapes0343.gif" ALT="AQUI VA UNA IMAGEN">
</td>
<%} %>

</tr>
<%
	}
%>
</table>
<br>

<%
	//Mostrar la paginación correspondiente
	int totalpaginas= Cita.calculaTotalPaginas(dniSesion);
	for (int i=0;i<=totalpaginas;i++){
		if (inicio!=i){
%>

<td class="mod1">
<a href="GestionaCliente.jsp?paginado=<%=i %>" target="_self"><%=i %></a>-
</td>
<%

		}else{
%>
<td>
	<text1>	<%=i %>-</text1>
</td>
<%
		}
	}
%>
<br><br><br><br>
<tr>
<td class="mod1">
<a href="ProcesoDatosClientes?action=obtenerCliente&campo=<%=id%>"><font size=5 color="yellow">Modificar sus datos</font></a>
</td>
</tr>
<br/>
<tr>
<td class="mod1">
<a href="Calendario/January.jsp"><font size=5 color="yellow">Pedir Citas</font></a>
</td>
</tr>
</p>
</div>
</center>
<br><br><br><br><br><br>
<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>