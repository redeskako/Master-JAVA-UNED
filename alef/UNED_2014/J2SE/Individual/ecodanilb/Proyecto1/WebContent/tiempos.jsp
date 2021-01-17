<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resumen de Tiempos</title>
</head>
<body>
<b><h2  align="center"  style="color:green"> INICIO DE SESIONES/TIEMPOS</br> <%=new java.util.Date()%></h2></b></br>
<b><h3  align="center"  style="color:blue"> Usuario Conectado:  <%= session.getAttribute("nombre") %></h3></b>

	

<%

Cookie[] todasCookies=request.getCookies();

if(todasCookies!=null)
{%>

	<table  align="center" border="1">
		<tr  align="center">
			<b></b><td style="color:red">Nombre</td></b>
			<b><td style="color:red">Duración Segundos</td></b>

		</tr>
		
		
	<% for(int i=1; i<todasCookies.length; i++)
	{
	
			
		Cookie unaCookie=todasCookies[i];
%>

   <tr>
		<td align="center"> <%out.println(unaCookie.getName());%> </td>
		<td align="center"> <%out.println(unaCookie.getValue());%> </td>

  </tr>	

<% }//fin for
		}// fin else
		else{out.println("<center><h2 style='color:red'> No Existen Datos a Mostrar </h2></center>");
			
		}

%>



</body>
</html>