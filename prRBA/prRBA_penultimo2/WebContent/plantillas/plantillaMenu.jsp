<%-- 
    Document   : index
    Created on : 23-sep-2008, 18:06:11
    Author     : Alef08
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <c:set var="path" value="${sessionScope.ruta}" />
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link REL="Shortcut Icon" HREF="${path}/template/logoIco2.ico" /> 
        <link rel="stylesheet" href="${path}/css/estiloMenu.css" type="text/css" />
        <link rel="stylesheet" href="${path}/css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="${path}/css/estiloGeneral.css" type="text/css" />
        <script type="text/javascript" src="${path}/js/idioma.js">  </script>
        
        
        <!-- ---------------------------------------------------------- -->
    <c:set var="idioma" value="${sessionScope.idiomaPagina}" />
    <c:if test="${idioma == null}">
        <c:set var="idioma" value="en_EN" />
    </c:if>
    <fmt:setLocale value="${idioma}"/>
    <fmt:setBundle basename="RbaEtiquetas" var="base" scope="session"/>
    
    <c:set var="usuario" value="${sessionScope.usuario}"/>
        <!-- ---------------------------------------------------------- -->        
    </head>
    <body>
        <div id="cabeceraFondo" >            
            <div id="cabecera_titulo">                
                <span class="titulo"><fmt:message key="membrete" bundle="${base}" /></span>
                <span class="slogan">| <fmt:message key="membrete_leyenda" bundle="${base}" /></span>
            </div>
        </div>
        <div id="menu">
            <span class="opcionesMenu">               
                <a href="${path}/zonaPrivada/menu.jsp"><fmt:message key="menu_Inicio" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${path}/AccionCliente?accion=7"><fmt:message key="menu_Clientes" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${path}/AccionMesa?accion=7"><fmt:message key="menu_Mesas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${path}/AccionReserva?accion=7"><fmt:message key="menu_Reservas" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${path}/index.jsp?valido=0"><fmt:message key="menu_CerrarS" bundle="${base}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                </span>
        <span class="usuarioLogin"><fmt:message key="usuario" bundle="${base}"/>: ${usuario}</span></div>
        <div class="usuarioIdioma">
            <!--
            <a href="${path}/Idioma" ><img src="template/esp.jpg" alt="esp" width="33" height="21" longdesc="Español" /></a>		
            <a onclick=idiomaIngles() ><img src="template/gb.jpg" alt="gb" width="33" height="21" longdesc="Ingles" /></a>                         
            -->
        </div>
        
    </body>
</html>
