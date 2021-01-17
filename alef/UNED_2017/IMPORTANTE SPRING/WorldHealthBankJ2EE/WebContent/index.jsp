<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Styles/estilologin.css" />
<title> Login Page</title>
</head>
<body>
<form name="login-form" class="login-form" action="/WorldHealthBankJ2EE/servlet" method="post">
	
		<div class="header">
		<h1>World Health Bank</h1>
		
		</div>
	
		<div class="content">
		<input name="username" type="text" class="input username" placeholder="Usuario" />
		<div class="user-icon"></div>
		<input name="password" type="password" class="input password" placeholder="Password" />
		<div class="pass-icon"></div>		
		</div>

		<div class="footer">
        <input type= "submit" class="botonlogin" value= "Login"> 
	
		</div>
	
	</form>


</body>
</html>