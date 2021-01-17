<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="es.rizos.librerias.*" %>
<%@ page import="es.rizos.beansCitas.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>


<form action="ServletCitas.java" method="post">
 <%
    		String fec=(String)request.getParameter("fecha");
		java.sql.Date variabletemporal=new Date(108,7,21);
                Date dia=variabletemporal.valueOf(fec);
                Cita cita=new Cita();
                Vector citasdeldia=cita.citasDelDia(dia);
                String diaString=dia.toString();
%>


<table cellpadding="0" cellspacing="0" class="tabla" border="2" >
<caption class="caption">CITAS DEL DIA</caption>
<tr>
<th class="link">
TURNO
</th>
<th class="link">
CODIGO CITA
</th>
<th class="link">
TIPOSERVICIO
</th>
<th class="link">
DNI
</th>
<th class="link">
FECHA
</th>
<th class="link">
EDITAR
</th>
<th class="link">
BORRAR

</th>
</tr>
<%

	Iterator it = citasdeldia.iterator();
         while (it.hasNext()){
		Cita c =(Cita)it.next();


%>

<tr>
<th class="link">
    <%=c.getTurno()%>
</th>
<th class="link">
      <%=c.getCodigoCita()%>
</th>
<th class="link">
     <%=c.getTipoServicio()%>
</th>
<th class="link">
     <%=c.getDni()%>
</th>
<th class="link">
     <%=c.getFecha()%>
</th>
<%
   int codigo=c.getCodigoCita();
%>
<th class="link">
<a href="ServletCita?accion=editarCita&codigocita=<%=codigo%>"><IMG SRC="template/shapes062.gif" ALT="AQUI VA UNA IMAGEN"></a>
</th>
<th class="link">
<a href="ServletCita?accion=borrarCita&codigocita=<%=codigo%>"><IMG SRC="template/shapes033.gif" ALT="AQUI VA UNA IMAGEN"></a>

</th>

</tr>
<%
	}
%>
</table>
<br/>
<table class="tabla">
        Añadir cita
        <a href="ServletCita?accion=añadircita&fechadeldia=<%=diaString%>"><IMG SRC="template/shapes060.gif" ALT="AQUI VA UNA IMAGEN"></a>

</table>
<%@ include file="template/pie.html" %>
</html>