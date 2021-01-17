<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="rba.clientes.AccionCliente" %>
<%@ page import="rba.bbdd.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
     <%String path = request.getContextPath();%>
        <!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
        <title>Nuevo Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="../../css/estiloMenu.css" type="text/css" />
        <link rel="stylesheet" href="../../css/mm_restaurant1.css" type="text/css" />
        <link rel="stylesheet" href="../../css/estiloGeneral.css" type="text/css" />
    </head>
    <!-- ------------------------------------------------------------------------ -->
    <!-- Idioma -->
        <c:set var="idioma" value="${sessionScope.idiomaPagina}"></c:set>
        <c:if test="${idioma == null}">
            <c:set var="idioma" value="en_EN"></c:set>
        </c:if>
        <fmt:setLocale value="${idioma}"/>
        <fmt:setBundle basename="RbaEtiquetas" />
    <!-- Datos del Cliente a modificar -->
        <c:set var="cliente" value="${requestScope.cliente}"></c:set>
    
    
    <!-- ---------------------------------------------------------------------------- -->
    <body >
        <jsp:include page="${path}/plantillas/plantillaMenu.jsp" flush="true" />
        <div id="fondoGeneral"> 
		
                <div class="tituloSubmenu">
                    <div class="tituloPagina">
                        <fmt:message key="FormularioModiCliente" bundle="${base}"/>
                    </div>
                </div>  
		<div class="altaFormulario">
        <form action="<%=path %>/AccionCliente?accion=<%=6%>&codigo=${cliente.codigo}" method="post">
		    <!--<fmt:message key="Campos" bundle="${base}" />
                        <fmt:message key="Valores" bundle="${base}" />-->
		
		<span class="fila">
			<fmt:message key="Nombre" bundle="${base}" />:
            <input name="txtNombreCliente" type="text" value="${cliente.nombre}">
			&nbsp;&nbsp;&nbsp;&nbsp;
		    <fmt:message key="Apellido1" bundle="${base}" />: 
            <input name="txtApellido1Cliente" type="text" value="${cliente.apellido1}">
			&nbsp;&nbsp;&nbsp;&nbsp;
            <fmt:message key="Apellido2" bundle="${base}" />:
            <input name="txtApellido2Cliente" type="text" value="${cliente.apellido2}">	<br />	
		</span>
		<span class="fila">
            <fmt:message key="Telefono" bundle="${base}"  />: 
            <input name="txtTelefono" type="text" value="${cliente.telefono}">	
			&nbsp;&nbsp;&nbsp;&nbsp;
            <fmt:message key="Correo" bundle="${base}" />: 
            <input name="txtCorreo" type="text" value="${cliente.correo}"><br />		
		</span>
		<span class="fila">
            <fmt:message key="password" bundle="${base}" />: 
            <input name="txtPass" type="text" value="${cliente.pass}">
			&nbsp;&nbsp;&nbsp;&nbsp;	
            <fmt:message key="Cp" bundle="${base}" />:
            <input name="txtCp" type="text" value="${cliente.cp}"><br />			
		</span>
		
	    <input style="width:150px" name="txtAceptar" type="submit" value="<fmt:message key="btnEnviar" bundle="${base}" />">
   	</form>	
		</div>
        </div>

 
        
        
    </body>
</html>