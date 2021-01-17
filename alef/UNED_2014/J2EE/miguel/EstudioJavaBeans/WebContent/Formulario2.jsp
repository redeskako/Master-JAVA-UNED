<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2  align="center"> Catálogo de Recursos</h2>

<form name=Recursos1 action="Formulario.jsp" method="get">
 Código de Recurso : <input type="text" name="codigoRecurso"><br>
 Nombre de Recurso : <input type="text" name="NombreRecurso"><br>
 
 <table>
  <tr>
     <td>
      <input type="submit"  name="botonEnviar" value="Enviar">
     </td>
     <td>
      <a href="bienvenido.jsp">Menu</a>
     </td>
 </table>
 
</form>
<%
	// Se controla que el botón Enviar a sido pulsado 
	
	String enviar = request.getParameter("botonEnviar");

	if ((enviar !=null) && (enviar.equalsIgnoreCase("Enviar"))){
%>
 
<jsp:useBean id="Chequear"  class="org.aprende.java.Chequear2"></jsp:useBean>
<% 
    Chequear.comprobarCodigo(request);
    int correcto=1; 
    if ((Integer)session.getAttribute("codigoCorrecto") == correcto){
        System.out.println("El codigo es correcto");
    }else{
        System.out.println("El codigo no es correcto");
    }
    
%>  
<%} %>   
</body>
</html>