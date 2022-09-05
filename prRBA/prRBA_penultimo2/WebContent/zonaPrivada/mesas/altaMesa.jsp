<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/../error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="rba.mesas.AccionMesa" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%String path = request.getContextPath();%>
<title>Alta Mesa</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="../../css/estiloMenu.css" type="text/css" />
        <link rel="stylesheet" href="../../css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="../../css/estiloGeneral.css" type="text/css" />



</head>
    <!-- ------------------------------------------------------------------------ -->
    <!-- Idima -->
        <c:set var="idioma" value="${sessionScope.idiomaPagina}"></c:set>
        <c:if test="${idioma == null}">
            <c:set var="idioma" value="en_EN"></c:set>
        </c:if>
        <fmt:setLocale value="${idioma}"/>
        <fmt:setBundle basename="RbaEtiquetas" />
    <!-- ---------------------------------------------------------------------------- -->
<body>
    <jsp:include page="${path}/plantillas/plantillaMenu.jsp" flush="true" />
<div id="fondoGeneral"> 

        <div class="tituloSubmenu">
            <div class="tituloPagina">
                <fmt:message key="FormularioAltaMesa" bundle="${base}" />
            </div>
        </div>
        <div class="altaFormulario">
            <form action="<%=path%>/AccionMesa?accion=4" method="post">
 		<span class="fila">
                    <fmt:message key="NumSillas" bundle="${base}" />:
                    <input name="txtNumSillas" type="text">		
		</span>           
            <input style="width:150px" name="txtAceptar" type="submit" value="<fmt:message key="AnadirMesa" bundle="${base}" />">             
    		</form>	
                 
         </div>       
         <span class="imagenFondo"></span> 
</div>
          
</body>
</html>