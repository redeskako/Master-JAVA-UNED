<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Iterator,org.BBDD.*, org.Otros.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LISTADO DE RECURSOS</title>
</head>
<body>
<%

	//GRABAREMOS EN CONSULTA LA SENTENCIA SQL BIEN LA CONSULTA TOTAL O PARCIAL QUE VENGA DE LA BUSQUEDA DESDE EL SERVLET BUSCADOR
	String consulta;
	HttpSession sesion=request.getSession();
	consulta=String.valueOf(sesion.getAttribute("consulta"));
	System.out.println("Hemos obtenido el getaatribute de  consulta y es: " + consulta );

	//VERIFICAMOS SI ESTAMOS HACIENDO BUSQUEDA TOTAL O PARCIAL EN FUNCION DE LA VARIABLE OBTENIDA DESDE EL SERVLET  BUSCADOR EN SU DEFECTO SERA TOTAL

	
	
	if (session.getAttribute("consulta")==null){
		consulta="Select * from recursos";
		System.out.println(consulta);
	}
		
	else {
		
		session.setAttribute("consulta", null);
	
	}

 Recursos xx = new Recursos();
 ArrayList<Recursos> ConjuntoRecursos = new ArrayList<Recursos>();
 System.out.println("estos es la consulta:    "+consulta);
 ConjuntoRecursos = xx.listadoRecursos(consulta); 

 session.setAttribute("ConjuntoRecursos",ConjuntoRecursos);
if (ConjuntoRecursos.isEmpty())
{
	out.print("<center><h2 style='color:red'> No Hay Registros A Mostrar<h2></center>");
}
else
{
%>
	<h2  align="center"> Catálogo de Recursos</h2>

	<table  align="center" border="1">
		<tr  align="center">
			<td>CODIGO</td>
			<td>NOMBRE</td>
		</tr>






 <c:forEach var="recurs" 
 							items="${sessionScope.ConjuntoRecursos}">

   <tr>
   
		<td align="center">${recurs.codigo}</td>
		
		<td align="center">${recurs.nombre}</td>

	

	</tr>
</c:forEach>
<%
}
%>

</body>
</html>