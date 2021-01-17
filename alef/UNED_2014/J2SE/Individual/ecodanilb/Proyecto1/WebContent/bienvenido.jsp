<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
     <%@ page import="org.Otros.*, java.util.Date, java.net.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Programacion Java</title>

<link href="newstile.css" rel="stylesheet" type="text/css" />

</head>

<body>

<p><h2>BIENVENIDO AL PORTAL<h2></p>
<%
	Date fecha=new Date();

	String asunto="Conexion a las " + fecha.toLocaleString();

	MiIp MiIp =new MiIp();
	String ipPublica = MiIp.obtenerIp();
	
	InetAddress IP=InetAddress.getLocalHost();

    String contenido="Se ha conectado: "+session.getAttribute("nombre") + "  con la IP Local:  "+IP+"  y la IPExterna: "+ipPublica;
	EmailUtility enviar=new EmailUtility();

	enviar.sendEmail(asunto, contenido);
	

%>


<div class="container">

  <div class="header"><!-- end .header --></div>

  <div class="content">
  

    <div class="menu">
    		<ul>
  				<li class="nivel1"><strong><a href="#" class="nivel1">Archivo</a></strong>
  				    <ul>
                		<li><a class="pdf" href="tiempos.jsp" target="_blank">Resumen de Tiempos</a></li>
                		<li><a class="pdf" href="recursos.jsp" target="_blank">Resumen de Recursos</a></li>
                		<li><a class="pdf" href="insertador.jsp" target="_blank">Insertar</a></li>
                		<li><a class="pdf" href="actualizador.jsp" target="_blank">Actualizar</a></li>
                		<li><a class="pdf" href="eliminador.jsp" target="_blank">Eliminar</a></li>

            	    </ul>


        		</li>

		   		<li class="nivel1"><strong><a href="#" class="nivel1">Busquedas</a></strong>
  				    <ul>
                		<li><a class="pdf" href="buscador.jsp" target="_blank">Buscar</a></li>

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
      				<li class="nivel1"><strong><a href="./CerrarSesion" class="nivel1">Salir</a></strong>
		  		</li>
    		</ul>

   
    </div>
    		
    		   <center><h1 style="color:black" >Bienvenidos a la página Web de</h1></center>

   <center><h1 style="text-decoration:underline; margin-top:0em; margin-bottom:1em;color:black">JAVA S.A.</h1></center><br />

   <center><h2 style="color:black">Empresa especializada en servicios de Programacion<br /> </h2></center>
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