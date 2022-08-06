<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <!-- DW6 -->
    <head>
        <!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
        <title>Restaurante - Pagina principal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link REL="Shortcut Icon" HREF="template/logoIco2.ico" /> 
        <link rel="stylesheet" href="css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="css/estiloMenu.css" type="text/css" />
        
    </head>

    
    <!-- ------------------------------------------------------------------------ -->
    <!-- Idioma -->
        <c:set var="idioma" value="${sessionScope.idiomaPagina}"></c:set>
        <c:if test="${idioma == null}">
            <c:set var="idioma" value="en_EN"></c:set>
        </c:if>
        
        <fmt:setLocale value="${idioma}"/>
        ${idioma}
        <fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>
        <!-- Manejo de Excepciones -->
        <c:set var="exception" value="${requestScope.valido}"/>   
           
        <c:choose>
            <c:when test="${exception == -1}"><c:set var="str" value="Usuario o Password incorrecto"/></c:when>
            <c:when test="${exception == -2}"><c:set var="str" value="Debes iniciar sesion"/></c:when>							   
        </c:choose>
<!-- ---------------------------------------------------------------------------- -->
    <body>
        <div id="cabeceraFondo" >            
            <div id="cabecera_titulo">                
                <span class="titulo"><fmt:message key="membrete" bundle="${base}"/></span>
                <span class="slogan">| <fmt:message key="membrete_leyenda" bundle="${base}"/></span>
            </div>
        </div>
        <div id="menu">
            <span class="opcionesMenu">               
                <a href="cocina.jsp"><fmt:message key="menu_cocina" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="localizacion.jsp"><fmt:message key="menu_localizacion" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                <a href="carta.jsp"><fmt:message key="menu_carta" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="eventos.jsp"><fmt:message key="menu_eventos_especiales" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="contacto.jsp"><fmt:message key="menu_contacto" bundle="${base}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>                
            </span>  
            
            <span class="usuarioIdioma">
                <a href="<%=request.getContextPath()%>/Idioma?action=esp" ><img src="template/esp.jpg" alt="español" name="español" width="33" height="21" longdesc="Español" /></a>		
                <a href="<%=request.getContextPath()%>/Idioma?action=eng" ><img src="template/gb.jpg" alt="ingles" name="ingles" width="33" height="21" longdesc="Ingles" /></a>          
                
            </span> 
            
        </div>     
        <div id="contenido">
            <img src="css/mm_restaurant_image.jpg" alt="Home Page Image"/>
            <div id="envioLogin">
                <form id="formLogin" name="form1" method="post" action="ProcesaLogado">
                    
                    
                    <span class="fuente"><fmt:message key="usuario" bundle="${base}"/>:</span><br />
                    <input type="text" name="usuario" id="usuario" />
                    <br />
                    <span class="fuente"><fmt:message key="password" bundle="${base}"/>:</span><br />
                    <input type="password" name="password" id="password" />
                    <input style="width:150px" type="submit" name="submit" id="submit" value="<fmt:message key="btnEnviar" bundle="${base}"/>" />
                </form>
                <c:if test="${exception < 0 }"><span class="error"></span><span class="exception">${str}</span></c:if>
          </div> 
            <span class="rightText">Gracias por utilizar RBA 2.0</span>     
        </div>
        <span class="logotipo"></span>
        
        <div class="lineaAzul"></div>
		
        
    </body>
    
</html>











