<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acceso</title>
<link href="file:///C|/Users/Daniel/Desktop/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<c:choose> 

	<c:when
		test="${param.estado =='invalido' }">
			<center><h3>Introduce un usuario y contraseña válidos</h3></center>
	</c:when>
	
	<c:when
		test="${param.estado =='salir' }">
			<center><h3>Has salido de la sesión</h3></center>
	</c:when>
	
	<c:when
		test="${param.estado =='ilegal' }">
			<center><h3>Acceso incorrecto, debe introdurir usuario y contraseña</h3></center>
	</c:when>
</c:choose>
	
		




<div class="container">
  <div class="header"><img src="caballos.gif" alt="Caballos" name="Insert_logo" width="180" height="150" id="Insert_logo" style="background-color: #C6D580; vertical-align:baseline"   />
 <center><h3 style="color:red">Acceso Protegido</h3></center>
  <!-- end .header --></div>
  <div class="content">
  

    <h1>Gesti&oacute;n de Recursos</h1>

   <center> <form align="center" name="formLogin" action="./Controlador" method="get">
            <table align="center">
                <tr>
                    <td style="color:red;">Usuario:</td>
                    <td><input type="TEXT" name="usuario" size="10"> </td>
                </tr>			
                <tr>
                    <td style="color:red;">Clave:</td>
                    <td><input type="PASSWORD" name="clave" size="10"> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="enviar" value="Aceptar"> </td>
                    <td><input type="reset" name="limpiar" value="Limpiar"> </td>
                </tr>
            </table>		
    </form></center>

    <!-- end .content --></div>

  <!-- end .container --></div>
  


</body>
</html>