<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<%
	HttpSession sesion = request.getSession(true); //Obtengo la variable sesión.
%>

<div id=menu>
<ul>
  <li class="welcome" > Bienvenido <%=sesion.getAttribute("usuarioAcceso") %></li>
  <li><a href="http://localhost:8080/WorldHealthBank_Boza/index.jsp?estado=2">Cerrar Sesión</a></li>
  
  <li id = "adminList"><a href="#Administracion">Administración</a>
  	<ul>
  		<li><a href="#user">User</a></li>
  		<li><a href="http://localhost:8080/WorldHealthBank_Boza/data?action=get&pag=1">Data</a></li>
  		<li><a href="http://localhost:8080/WorldHealthBank_Boza/adminCountry.jsp">Country</a></li>
  		<li><a href="http://localhost:8080/WorldHealthBank_Boza/healthindicatoradmin.jsp">Health Indicator</a></li>
  	</ul>
  </li>
  <li><a href="http://localhost:8080/WorldHealthBank_Boza/main.jsp">Inicio</a></li>
</ul>
</div>
<script type="text/javascript">


var x = document.getElementById('adminList');
    x.style.display = 'none';
   
    <%
    if(sesion.getAttribute("usuarioAcceso").equals("admin")){%>
    	x.style.display = "block"; <%
    }
    %>

</script>

</body>
</html>