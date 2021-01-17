<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Practica Twitter con Clinical Trials</title>
<link type="text/css" rel="stylesheet" href="Resources/css/estilos.css" > 
<script type="text/javascript" src="Resources/javascript/comprobarAcceso.js" charset="UTF-8"></script>
</head>
<body>

 <!-- Contenedor -->
    <div id="contenedor">
    
        <%
	        String estado = request.getParameter("estado");
	        if (estado != null){
		        if(estado.equals("invalido")){
        %>  <div class="info_error">
			<center><h1>Usuario invalido</h1></center>
        <%
		        }else if (estado.equals("logout")){
        %>
			<center><h1>Has salido de la sesión</h1></center>
        <%
		        }else if (estado.equals("ilegal")){
        %>
		   	<center><h1>Acceso ilegal, debe antes entrar en sesión</h1></center>
        <%  
                }else if (estado.equals("responsabilidad")){
        %>
    		   	<center><h1>Acceso no permitido, su rol no es administrador</h1></center>
        <%
		        }
		%>
		        </div> <!-- fin info -->
	    <%
	        }
        %>


			<h1 class="login">Gesti&oacute;n de la aplicaci&oacute;n Twitter y Clinical Trials </h1>
		
		
		<form style="align:'center';" name="formLogin" action="APITwitterLogin?operacion=login" method="post" id="Twitterform">
			<div id="Acceso">
			   <span>Usuario</span>
			   <input type="text" name="txtUsuario" id="user" maxlength="10" size="10" />
			   <span>Clave</span>
			   <input type="password" name="txtPassword" id="password" maxlength="10" size="10" />
			   <input type="submit" name="btn" id="btn" value="Aceptar"/> 
			</div>                 

		</form>
	
		<p class="pie">Master Java UNED - 2016.</p>
	
	</div> <!-- Contenedor -->

</body>
</html>