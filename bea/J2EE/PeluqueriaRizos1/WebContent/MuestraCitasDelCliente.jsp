<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.Exception" %>
<link rel="stylesheet" href="css/template.css" type="text/css" />
<%@ page import="es.rizos.beansCitas.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="template/cabecera.html" %>

<style type="text/css">
a{text-decoration:none}
a:hover{text-decoration: underline ; color:black;}
</style>
<center><h1>Citas reservadas actualmente</h1></center>
<%
	if(request.getAttribute("Atributodni")!=null){
    //Por el atributo Atributodni identificamos al cliente
    String dniSesion = (String) request.getAttribute("Atributodni");
    int inicio=0; Vector citas=new Vector();
    try{
    	String cad= request.getParameter("paginado");
        inicio= Integer.parseInt(cad);
    }catch(Exception e){ inicio=0;}
    Vector citasDelcliente=new Vector();
    citasDelcliente=Cita.citasDelcliente(inicio,dniSesion);

%>
<center>
<table cellpadding="0" cellspacing="0" class="tabla">
<caption class="caption">
<strong><font size=4 color="green">  Listado de citas</font></strong></caption>
<tr>
<th >
Fecha
</th>
<th >
TipoServicio
</th>
<th >
Turno
</th>
<th >
EDITAR
</th>
<th >
ELIMINAR
</th>
</tr>
<%
	Iterator it = citasDelcliente.iterator();
         while (it.hasNext()){
			Cita c =(Cita)it.next();
%>
<tr>
<th class="link">
    <%=c.getFecha()%>
</th>
<th class="link">
      <%=c.getTipoServicio()%>
</th>
<th class="link">
     <%=c.getTurno()%>
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
</center></br>
<center><table>
<tr>
<%
	//Mostrar la paginación correspondiente
	int totalpaginas=3;
    	//Clientes.calculaTotalPaginas();
	for (int i=0;i<=totalpaginas;i++){
%>          <td>
               <a href="ServletCita?action=mostrarcitas&paginado=<%=i %>" target="_self"><%=i %></a>-
            </td>

<%
	}
  }
%>
</tr>
</table></center>

<%@ include file="template/cerrarsesion.html" %>
<%@ include file="template/pie.html" %>
</html>