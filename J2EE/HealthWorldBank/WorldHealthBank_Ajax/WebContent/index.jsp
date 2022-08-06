<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="WEB-INF/index.tld" prefix="tag" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="Styles/mainestilo.css" />
	<title> Login Page</title>
	<!-- You have to include these two JavaScript files from DWR -->  
<script type='text/javascript' src='dwr/engine.js'></script>  
<script type='text/javascript' src='dwr/util.js'></script>  
<!-- This JavaScript file is generated specifically for your application -->  
<!-- in dwr.xml we have converted MyJavaClass to MyJavaScriptClass.js-->  
<!-- so we can call java class methods using this javascript class-->  
<script type='text/javascript' src='dwr/interface/Prueba.js'>  
</script>  

<script>  

//Metodo que usa el javascript generado con DWR Prueba.js para obtener un string con los usuarios de bbdd
//y mostrarlos como texto en el jsp
//handleReceivedUsers es el callback method para mostrar los usuarios recibidos de bbdd 
function getUsersFromServer()  
{  
Prueba.getAllUser(handleReceivedUsers);  
}

//Metodo que usa el javascript generado con DWR Prueba.js para obtener una lista con los usuarios de bbdd
//y mostrarlo como datalist desplegable en el input text del nombre de usuario
//handleReceivedUsers es el callback method para mostrar los usuarios recibidos de bbdd 
function getUsersListFromServer()  
{  
Prueba.getUserList(handleReceivedUsersList);  
}

//Metodo que usa el javascript generado con DWR Prueba.js para borrar los usuarios del json
//handleReceivedUsers es el callback method para mostrar los usuarios recibidos de bbdd 
function deleteUsersFromServer()  
{  
Prueba.limpiar(handleReceivedDelete);  
}

//Muestra los usuarios de BBDD en el jsp
function handleReceivedUsers(str)  
{   
	document.getElementById("demo").innerHTML = str;
}

//Muestra el listado de usuarios de BBDD en un deplagable (datalist) del imput text de usuarios
function handleReceivedUsersList(users)  
{   
    var options = '';

    for(var i = 0; i < users.length; i++)
      options += '<option value="'+users[i]+'" />';

    document.getElementById("usuariosBBDD").innerHTML = options;
}

//Borra la información de usuarios
function handleReceivedDelete(str)  
{   
	document.getElementById("demo").innerHTML = str;
}

</script>  
</head>

<tag:estado/>

	<form name="login-form" class="login-form" action="./AccesoWeb" method="get">
		<fieldset>
    		<legend>World Health Bank</legend>
			<div class="content">
				<input list="usuariosBBDD" name="username" type="text" class="input username" placeholder="Usuario" onfocus="getUsersListFromServer()">
				<datalist id="usuariosBBDD">
    				<option value="Obteniendo users...">
  				</datalist>
				<div class="user-icon"></div>
				<input name="password" type="password" class="input password" placeholder="Password" onfocusin="getUsersFromServer()" onfocusout="deleteUsersFromServer()"/>
				<div class="pass-icon"></div>		
			</div>
			</br>
			<div class="footer">
        		<input type= "submit" class="botonlogin" value= "Login"> 
			</div>
		</fieldset>
	</form>
<p id="demo"></p>
</body>

</html>