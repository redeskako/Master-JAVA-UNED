<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Envio de Formulario con JSTL</title>
</head>
<body bgcolor="white">
<h1>Envio de formulario con JSTL</h1>
<form action="form.jsp" method="post">
 <table>
 <tbody>
  <tr>
    <td>Nombre :</td>
    <td><input type="text" name="nombre"></td>
  </tr>
   <tr>
    <td>Fecha de nacimiento :</td>
    <td><input type="text" name="fecha"></td>
  </tr>
  <tr>
    <td>Email</td>
    <td><input type="text" name="email"></td>
  </tr>
  <tr>
    <td>Clave</td>
    <td><input type="password" name="clave"></td>
  </tr>
  <tr>
    <td>Genero :</td>
    <td>
     <input type="radio" name="genero" value="h">Hombre<br>
     <input type="radio" name="genero" value="m">Mujer<br>
     </td>
  </tr>
  <tr>
   <td>Deportes favoritos</td>
   <td><input type="checkbox" name="deporte" value="f">Futbol<br>
       <input type="checkbox" name="deporte" value="t">Tenis<br>
       <input type="checkbox" name="deporte" value="g">Golf<br>
    </td>  
    </tr>
    <tr>
     <td colspan="2">
      <input type="submit" value="Enviar">
     </td> 
     </tr> 
 </tbody>
 </table>
 </form>
 <c:if test="${!empty param.nombre}">
 <p>
   Introdujiste <br>
   Nombre : ${param.nombre}<br>
   Fecha :${param.fecha }<br>
   Email :${param.email }<br>
   Genero :
   <c:choose>
     <c:when test="${param.genero =='h'}">Hombre</c:when>
     <c:when test="${param.genero =='m'}">Mujer</c:when>
    
     <c:otherwise>Indefinido</c:otherwise>
   </c:choose>
   <br>
   Deporte:<br/>
   <c:forEach items="${paramValues.deporte}" var="actual">
     <c:choose>
       <c:when test="${actual=='f'}">
           <c:set var="futbol" value='true' />
        </c:when>
        <c:when test="${actual=='t'}">
           <c:set var="tenis" value='true'/>
        </c:when>
       <c:when test="${actual=='g'}">
          <c:set var="golf" value='true'/>
        </c:when>
     </c:choose> 
     
     </c:forEach>
      <input type="checkbox" name="deporte" value="f" ${futbol? 'checked':''} 
       disabled="disabled">futbol<br>
      <input type="checkbox" name="deporte" value="t" ${tenis? 'checked':''} 
       disabled="disabled">tenis<br>
      <input type="checkbox" name="deporte" value="t" ${golf? 'checked':''} 
       disabled="disabled">golf<br>
   </p>
   </c:if>
   
</body>
</html>