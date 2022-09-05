<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/error.jsp"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.*" %>
<%@ page import="rba.bbdd.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String path = request.getContextPath();%>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<title>Baja Reserva</title>
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
 <!-- Recopilamos datos para el alta de la reserva  -->
 <c:set var="reserva" value="${requestScope.reserva}"></c:set>
 <c:set var="mesas" value="${requestScope.mesas}"></c:set>
 <c:set var="clientes" value="${requestScope.clientes}"></c:set>

<!-- ---------------------------------------------------------------------------- -->

<body>  
<jsp:include page="${path}/plantillas/plantillaMenu.jsp" flush="true" />
    <div id="fondoGeneral"> 

        <div class="tituloSubmenu">
            <div class="tituloPagina">
                 <fmt:message key="FormularioBajaRsva" bundle="${base}"/>
            </div>
        </div>
                <div class="altaFormulario">
                    <form action="<%=path%>/AccionReserva?accion=5&codigo=${reserva.codigo}" method="post">
 		<span class="fila">
                    <fmt:message key="Fecha" bundle="${base}"/>:
                    <input name="txtFecha" type="text" value="${reserva.fecha}" disabled>		
		</span>&nbsp;&nbsp;
 		<span class="fila">
                    <fmt:message key="Hora" bundle="${base}"/>:
                    <input name="txtHora" type="text" value="${reserva.hora}" disabled>
		</span>    
                 <br />
 		<span class="fila">
                    <fmt:message key="Turno" bundle="${base}"/>:
                    <select name="CmbTurno" id="CmbTurno" onChange="MM_jumpMenu('parent',this,0)" disabled>
                        <option value="1" <c:if test="${reserva.turno == 1}"> selected</c:if> ><fmt:message key="PrimerTurno" bundle="${base}"/></option>
                        <option value="2" <c:if test="${reserva.turno == 2}"> selected</c:if> ><fmt:message key="SegundoTurno" bundle="${base}"/></option>
                    </select>
		</span>&nbsp; &nbsp;   
 		<span class="fila">
                    <fmt:message key="Cliente" bundle="${base}"/>:
                    <select name="CmbCliente" id="CmbCliente" onChange="MM_jumpMenu('parent',this,0)" disabled>
                            <c:forEach var="cliente" items="${clientes}">    
                                    <option value="${cliente.codigo}" <c:if test="${reserva.cliente == cliente.codigo}"> selected</c:if> >  ${cliente.apellido1} - ${cliente.apellido2} , ${cliente.nombre}</option>
                            </c:forEach>
                    </select>
		</span>      
                 <br />
 		<span class="fila">
                    <fmt:message key="Mesa" bundle="${base}"/>:
                    <select name="CmbMesa" id="CmbMesa" onChange="MM_jumpMenu('parent',this,0)" disabled>
                            <c:forEach var="mesa" items="${mesas}">
		            	<option value="${mesa.codigo}" <c:if test="${reserva.mesa == mesa.codigo}"> selected</c:if>> Mesa: ${mesa.codigo} - Sillas: ${mesa.sillas}</option>
                            </c:forEach>

                </select>
                    
		</span> &nbsp;&nbsp;       
                     <input style="width:150px" name="txtAceptar" type="submit" value="<fmt:message key="btnEnviar" bundle="${base}" />" >
                        </form>	

                 </div> 
                 <div class="agenda"></div>  
</div>
        <span class="rightGeneral">
            <fmt:message key="Graciasporutilizar" /></span>   
        <div class="lineaAzul"></div>       

</body>
</html>