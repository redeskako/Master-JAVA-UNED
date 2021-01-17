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
    			String fec=(String)request.getParameter("fecha");
				java.sql.Date variabletemporal=new Date(108,7,21);
                Date dia=variabletemporal.valueOf(fec);
                Cita cita=new Cita();
                String diaString=dia.toString();
                HttpSession sesion= request.getSession();
                sesion.setAttribute("AtributoFecha", diaString);
                String dniSesion= (String) session.getAttribute("Atributodni");
                String tipoUsuario= (String) session.getAttribute("AtributoTipoUsuario");
                Vector citasdeldia=cita.citasDelDia(dia,dniSesion);
               


%>
<br>
<text3>Citas que tiene actualmente reservadas</text3>
<br>
<p>
<table class="mod1" align="center">
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
<td width=25% class="mod1">
    <%=c.getTurno()%>
</td>
<td width=25% class="mod1">
      <%=c.getCodigoCita()%>
</td>
<td width=25% class="mod1">
     <%=c.getTipoServicio()%>
</td>
<td width=25% class="mod1">
     <%=c.getDni()%>
</td>
<td width=25% class="mod1">
     <%=c.getFecha()%>
</td>
<%
   int codigo=c.getCodigoCita();
	
   %>
<td width=25% class="mod1">
<a href="ServletCita?accion=editarCita&codigocita=<%=codigo%>&fechadeldia=<%=diaString%>"><IMG SRC="template/shapes062.gif" ALT="AQUI VA UNA IMAGEN"></a>
</td>
<td width=25% class="mod1">
<a href="ServletCita?accion=borrarCita&codigocita=<%=codigo%>"><IMG SRC="template/shapes033.gif" ALT="AQUI VA UNA IMAGEN"></a>
</td>

</tr>
<%
	}
%>
</table>

<br/>
<br><font size=4 color="#FFFF00">Turnos libres para reservar:</font>
<br><br>


	<%	for (int i=1; i<=5;i++){
		if (Cita.NoEstaTurnoReservado(dia,"Turno"+i)){
	%><font size=4 color="#FFFF00">
		Turno <%=i%>:</font>
        <a href="ServletCita?accion=anadircita&fechadeldia=<%=diaString%>&turno=<%="Turno"+i%>"><IMG SRC="template/enviar.jpg" align="absmiddle" ALT="AQUI VA UNA IMAGEN"></a>
		<br>
	<% }
		} %>


</center>
</div>
<%@ include file="template/cerrarsesion.html" %>

</html>