<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator,org.BBDD.*, org.Otros.*, javax.servlet.http.HttpSession;"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ELIMINADOR</title>
</head>
<body>
<h2  align="center"> Catálogo de Recursos</h2>
<form align="center" name="formAct" action="./Eliminador" method="get">
	<table  align="center" border="1">
		<tr  align="center">
			<td>CODIGO</td>
			<td>NOMBRE</td>
			<td>ELIMINAR</td>
		</tr>
<%
 Recursos xx = new Recursos(0,"");
 ArrayList<Recursos> ConjuntoRecursos = new ArrayList<Recursos>();
 ConjuntoRecursos = xx.listadoRecursos("Select * from recursos"); 
 Recursos unRecurso;
 int radio=1;
 int i=1;

 Iterator it= ConjuntoRecursos.iterator();

while (it.hasNext()){
	unRecurso =(Recursos)it.next();

%>
	
   <tr>
   
   	
		<td align="center"> <%out.println(unRecurso.getCodigo());%> </td>
		<td align="center"> <%out.println(unRecurso.getNombre());%> </td>

		<td align="center"> <input type="radio" name="radioact<%=radio%>">  </td>
		<% System.out.println("radioact"+radio); %>
		<td align="center"> <%=radio %>  </td>
		<%radio++; %>
	</tr>	
<%
}

session.setAttribute("radio", radio);


%>

		</table>
	
		<center>
		<input type="submit" name="enviar" value="Eliminar">
		<input type="reset" name="limpiar" value="Limpiar">
		</center>
		
</form>

	
</body>
</html>