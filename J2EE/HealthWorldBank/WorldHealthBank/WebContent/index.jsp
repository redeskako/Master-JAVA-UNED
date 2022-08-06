<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="Styles/mainestilo.css" />
	<title> Login Page</title>
</head>

<body>

	<%
		String estado = request.getParameter("estado");
		if (estado != null){
			if(estado.equals("1")){
	%>

	<center><h2>Usuario inválido</h2></center>

	<%
			}else if (estado.equals("2")){
	%>
	
	<center><h2>Has salido de la sesión</h2></center>
	
	<%
			}else{
	%>
	<center><h2>Situacion desconocida</h2></center>
	<%
			}
		} else {
	%>
	<center><h2>Bienvenido</h2></center>
	<%
		}
	%>

	<form name="login-form" class="login-form" action="./AccesoWeb" method="get">
		<fieldset>
    		<legend>World Health Bank</legend>
			<div class="content">
				<input name="username" type="text" class="input username" placeholder="Usuario" />
				<div class="user-icon"></div>
				<input name="password" type="password" class="input password" placeholder="Password" />
				<div class="pass-icon"></div>		
			</div>
			</br>
			<div class="footer">
        		<input type= "submit" class="botonlogin" value= "Login"> 
			</div>
		</fieldset>
	</form>

</body>

</html>