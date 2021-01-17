<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import="java.lang.*" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <c:set var="path" value="${sessionScope.ruta}" />
        <!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
        <title>Restaurante - Pagina principal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="${path}/css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="${path}/css/estiloMenu.css" type="text/css" />
        
    </head>
    
    <body>
        
        <jsp:include page="../plantillas/plantillaMenu.jsp" flush="true" />
        

        <div id="contenido">
            <img src="${path}/css/mm_restaurant_image.jpg" alt="Home Page Image"/>
            <center><fmt:message key="Graciasporutilizar" bundle="${base}" /></center>
        </div>
        <span class="logotipo"></span>
        <div class="lineaAzul"></div>
    </body>
</html>