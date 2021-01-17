<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aritmetica con EL</title>
</head>
<body>
<h1>Aritmetica con EL</h1>
 <table border="1">
   <thead>
     <tr>
      <th><b>Expresion EL</b></th>
      <th><b>Resultado</b></th>
     </tr>
    <thead>
     <tr>
      <td>\${1 + 2}</td>
      <td>${1 + 2}</td>
     </tr>
     <tr>
     <td>\${21 * 2}</td>
      <td>${21 * 2}</td>
     </tr>
     <tr>
     <td>\${3 / 4}</td>
      <td>${3 /4}</td>
     </tr>
     <tr>
     <td>\${3 / 0}</td>
      <td>${3 /0}</td>
     </tr>
     <tr>
     <td>\${10%3}</td>
      <td>${10%3}</td>
     </tr>
     <tr>
     <td>\${(1==2) ? 3:4}</td>
      <td>${(1==2) ? 3:4}</td>
     </tr>
       
 </table>

</body>
</html>