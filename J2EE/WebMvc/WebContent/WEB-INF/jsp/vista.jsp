<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MVC Capa Vista</title>
</head>
<body>
 <% 
      HttpSession objSesion = request.getSession(false); 
      String usuario = (String)objSesion.getAttribute("usuario");
      out.println("Logged as: " + usuario); 
      
      %>
      <br>
       <a href="Controlador?operacion=logout">logout</a>
  <h2>Capa Vista</h2>
</body>
</html>