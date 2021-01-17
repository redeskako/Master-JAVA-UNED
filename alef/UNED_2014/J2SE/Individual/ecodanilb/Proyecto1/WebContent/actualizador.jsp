<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator,org.BBDD.*, org.Otros.*, javax.servlet.http.HttpSession;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ACTUALIZADOR</title>
</head>
<body>
<h2  align="center"> Catálogo de Recursos</h2>
<form align="center" name="formAct" action="./Actualizador" method="get">
	<table  align="center" border="1">
		<tr  align="center">
			<td>CODIGO</td>
			<td>NOMBRE</td>
			<td>ACTUALIZAR</td>
		</tr>
<%

// GEREAMOS EL ARRAYLIST QUE RECOGERA LOS RESULTADOS DE LA BUSQUEDA
 Recursos xx = new Recursos();
 ArrayList<Recursos> ConjuntoRecursos = new ArrayList<Recursos>();
 ConjuntoRecursos = xx.listadoRecursos("Select * from recursos"); 

 int radio=1;


 
 //Iterator it= ConjuntoRecursos.iterator();
 session.setAttribute("ConjuntoRecursos",ConjuntoRecursos);


 %>
 <c:forEach var="recurs" 
 							items="${sessionScope.ConjuntoRecursos}">

   <tr>
   
		<td align="center">${recurs.codigo}</td>
		
		<td align="center">${recurs.nombre}</td>

		<td align="center"> <input type="radio" name="radioact<%=radio%>">  </td>
		<% System.out.println("radioact"+radio); %>
		
		<%radio++; %>
	</tr>
</c:forEach>
<%


//CREAMOS LA SESION RADIO QUE RECOGERA EL NUMERO DE RESGITROS TOTALES
session.setAttribute("radio", radio);


%>

		</table>
	
		<center>
		<input type="submit" name="enviar" value="Actualizar">
		<input type="reset" name="limpiar" value="Limpiar">
		</center>
		
</form>

	
</body>
</html>