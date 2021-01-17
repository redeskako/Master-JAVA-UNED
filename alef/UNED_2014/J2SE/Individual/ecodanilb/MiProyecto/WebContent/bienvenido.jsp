<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Programacion Java</title>

<link href="newstile.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="icono.ico" />
</head>

<body>

<p><h2>BIENVENIDO AL PORTAL<h2></p>

<%
	String estado = request.getParameter("estado");
	if (estado != null){
		if(estado.equals("invalido")){
%>
			<center><h3>Introduce un usuario y contraseña válidos</h3></center>
<%
		}else if (estado.equals("salir")){
%>
			<center><h3>Has salido de la sesión</h3></center>
<%
		}else if (estado.equals("ilegal")){
%>
			<center><h3>Acceso incorrecto, debe introdurir usuario y contraseña</h3></center>
<%
		}
	}
%>



<div class="container">

  <div class="header"><!-- end .header --></div>

  <div class="content">

    <div class="menu">

    		<ul>

  				<li class="nivel1"><strong><a href="#" class="nivel1">Archivo</a></strong>
  				
  				
  				    <ul>

				

                		<li><a class="pdf" href="#" target="_blank">Empleados</a></li>

						<li><a class="pdf" href="#" target="_blank">Recursos</a></li>


				

            	    </ul>
  				

		



		  		</li>

		   		<li class="nivel1"><strong><a href="#" class="nivel1">Peticiones</a></strong>


  				    <ul>

				

                		<li><a class="pdf" href="#" target="_blank">Consultar Mis Peticiones</a></li>

						<li><a class="pdf" href="#" target="_blank">Modificar Mis Peticiones</a></li>

						<li><a class="pdf" href="#" target="_blank">Eliminar Mis Peticiones</a></li>

				

            	    </ul>
  				



		  		</li>

          		<li class="nivel1"><strong><a href="#" class="nivel1">Contacto</a></strong>

			

		

        		</li>


  

  				<li class="nivel1"><strong><a class="nivel1">Administrador</a></strong>

		

        			<ul>

				

                		<li><a class="pdf" href="#" target="_blank">Consultar Peticiones</a></li>

						<li><a class="pdf" href="#" target="_blank">Modificar Peticiones</a></li>

						<li><a class="pdf" href="#" target="_blank">Eliminar Peticiones</a></li>

				

            	    </ul>

		

        		</li>
        		
        		
        		  				<li class="nivel1"><strong><a href="index.jsp" class="nivel1">Salir</a></strong>
		



		  		</li>
        		
        		

	

    		</ul>

    </div>



   <center><h1>Bienvenidos a la página Web de</h1></center>

   <center><h1 style="text-decoration:underline; margin-top:0em; margin-bottom:1em;">JAVA S.A.</h1></center><br />



<center><h2>Empresa especializada en servicios de Programacion<br /> 

</h2></center>

    <!-- end .content --></div>

  <div class="footer">
				<br />
                <br />
                <br />
                <br />
                

    <!-- end .footer --></div>

  <!-- end .container --></div>


</body>
</html>