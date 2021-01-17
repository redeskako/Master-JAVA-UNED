<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Funciones JSTL para EL</title>
</head>
<body>
  <h1>Funciones JSTL para EL</h1>
  <table border ="1">
    <thead>
      <tr>
         <th><b>Expresion EL</b></th>
         <th><b>Resultado</b></th>
         </tr>
         </thead>
         <tr>
         <td>\${fn:length('hola')}</td>
         <td>${fn:length('hola')}</td>
         </tr>
         <tr>
           <td>\${fn:replace('hola',1,3)}</td>
           <td>${fn:replace('hola',h,c)}</td>
         </tr>
         <tr>
           <td>\${fn:substring('hola',1,2)}</td>
           <td>${fn:substring("hola",1,2)}</td>
         </tr>
          <tr>
           <td>\${fn:toUpperCase('hola')}</td>
           <td>${fn:toUpperCase('hola')}</td>
         </tr>
 </table>
  
  
</body>
</html>