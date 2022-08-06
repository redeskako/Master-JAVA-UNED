<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import ="org.basedatos.*, java.util.Iterator, java.util.ArrayList, org.sesion.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Recursos</title>
</head>
<body>
<div style="width: 100%; height: 60px; background-color: #db9542; padding: 30px;">
<h1>Gestión de Recursos</h1>
</div>

<div style="float:left;">
  <%
	String user = (String) session.getAttribute("iduser");
		if(user!=null){
		%>
		<h2 align="center"> LISTADO DE RECURSOS </h2>
		<table align="center" border="1">
			<tr align="center">
				<td>ID RECURSO</td>
				<td>DESCRIPCION</td>
				</tr>
	<%! String sNull (String valor) {
			return (valor!=null? valor:"");
			};
	%>

<%		
	Recurso recurso1 = new Recurso();
	//ArrayList<Recurso> treeSetRecursos = new Recurso().listadoRecursos();
	ListadoRecursos treeSetRecursos = new ListadoRecursos().listadoRecursos();
	
	Iterator<Recurso> it= treeSetRecursos.iterator();
		while(it.hasNext()){
			recurso1 = (Recurso)it.next();
%>
	<tr>
		<td align ="center">
		<%
		out.println((recurso1.getIdRecurso()));
		%>
		</td>
		<td align="center">
		<%
		out.println(sNull(recurso1.getDescripcion()));
		%>
		</td>
		<td><a
href="./GestionRecursos?action=insert=<%=recurso1.getIdRecurso()%>">Editar</a></td>
<td><a
href="./GestionRecursos?action=delete=<%=recurso1.getIdRecurso()%>">Borrar</a></td>
	</tr>

<%
}
%>
<!-- <div style="height:20px;"></div>  -->

</table>
</div>

<table align="center" border="1">
<!-- <div style="height:50px;"></div> -->
<tr>
	<td>
		<a href="./CerrarSesion" target="_blank">Cerrar Sesion</a>
	</td>
	<td>
		<a href="./GestionRecursos?action=insert" target="_blank">Añadir</a>
	</td>
</tr>


</table>

<%
	} else{
			response.sendRedirect("./index.jsp?estado=ilegal");
	}

%>

</body>
</html>

<!-- Código  


<div style="width: 720px; height: 60px; background-color: #db9542; padding: 30px;">
<P><h1>Gestión de Recursos</h1></p>
</div>
<table>
<thead>

	<tr>

		<th> ID Recurso </th>
		<th> Recurso </th>
		<th colspan=2>Action</th>
	</tr>
</thead>


<tbody>
	<c:forEach items="${recurso.recursos}" var="recurso">
		<tr>
			<td><c:out value="${recurso.id}"/></td>
			<td><c:out value="${recurso.Descripcion}"/></td>
			<td><a href="GestionRecursos?action=edit&IdRecurso=<c:out value="${recurso.IdRecurso}"/>">Actualizar</a></td>
            <td><a href="GestionRecursos?action=delete&IdRecurso=<c:out value="${recurso.IdRecurso}"/>">Borrar</a></td>
		</tr>
	</c:forEach>
</tbody>

</table>

<p> <a href="./GestionRecursos?action=insert">Añadir Recurso</a></p>

<p><a href="./CerrarSesion" target="_blank">Cerrar Sesión</a></p>
</body>


 -->
