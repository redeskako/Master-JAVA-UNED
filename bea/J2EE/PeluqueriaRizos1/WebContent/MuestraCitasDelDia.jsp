<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="es.rizos.beansCitas.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/modelo1.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="template/cabecera.html" %>
<%@ include file="template/informacion.html" %> 
<div id="main">
<center>
<%
	String tipoUsuario= (String) session.getAttribute("AtributoTipoUsuario");
	String fec=(String)request.getParameter("fecha");
	System.out.println(tipoUsuario);
	if (tipoUsuario.equals("admin")){
%>

 <%
				java.sql.Date variabletemporal=new Date(108,7,21);
                Date dia=variabletemporal.valueOf(fec);
                Cita cita=new Cita();
                String diaString=dia.toString();
                HttpSession sesion= request.getSession();
                sesion.setAttribute("AtributoFecha", diaString);
                String dniSesion= (String) session.getAttribute("Atributodni");
                Vector citasdeldia=cita.citasDelDia(dia);


%>


<table class="mod1" >
<caption class="mod1">CITAS DEL DIA</caption>
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
	Iterator it = citasdeldia.iterator();
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
   int codigo=(Integer)c.getCodigoCita();
   if (dniSesion.equals(c.getDni())||(tipoUsuario.equals("admin"))){

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
<td class="mod1">
<IMG SRC="template/shapes0343.gif" ALT="AQUI VA UNA IMAGEN">
</td>
<%} %>

</tr>
<%
	}
%>
</table>
<br/>
<table class="mod1">
   <a href="ServletCita?accion=anadircita&fechadeldia=<%=diaString%>"><IMG SRC="template/anadir.jpg" align="absmiddle" ALT="AQUI VA UNA IMAGEN" border="none"></a>

</table>
</center>
<%
	}else{response.sendRedirect("CitasDelDiaCliente.jsp?fecha="+fec);
%>

<%}%>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>