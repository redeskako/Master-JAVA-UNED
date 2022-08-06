<!--<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MedlineBennet Web Service Search DWR</title>
<link type="text/css" rel="stylesheet" media="screen" href="css/estilos.css" />
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="./javascript/medben.js" charset="UTF-8"></script>
<script type='text/javascript' src='/MedBenAjaxDWR/dwr/engine.js'></script>
<script type='text/javascript' src='/MedBenAjaxDWR/dwr/interface/Dwrwsrest.js'></script>
<script type='text/javascript' src='/MedBenAjaxDWR/dwr/util.js'></script>

</head>
<body onload="inicializarJSP();">
	<div id="contenido">
        <div id="cabecera">
        	<div id="logo">
            </div>
            <div id="titulo">
            	<h1> MedlineBennett Web Service Search DWR</h1>
            </div>
        </div>
        <div id="cuerpo">
            <form name="formMenu" action="" method="get" onsubmit="return false;">
            <div id="topMenu">
                <div id="opciones">
                    <div id="divMenu">
                        <select id="selectMenu" onChange="actualizarCampos();">
                            <option value="MedLine">MedLine</option>
                            <option value="Bennett">Bennett</option>
                        </select>
                    </div>
                    <div id="divBusqueda">
                        <div id="divPalabra">
                            <input id="textPalabra" type="text" value="Palabra a buscar" onBlur="if(this.value == ''){ this.value = 'Palabra a buscar'; this.style.color = '#BBB';}" onFocus="if(this.value == 'Palabra a buscar'){ this.value = ''; this.style.color = '#000';}" onKeyUp="iniciarBusquedaPredictiva(event);" style="color:#BBB;"/>
                        </div>
                        <!-- Para controlar la visibilidad del menú emergente, utilizar la propiedad css display:none o display:block -->
                        <div id="divEmergente">
                            <table id="tEmergente">
                                <tr>
                                    <td id= "resultado1" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td id= "resultado2" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td id= "resultado3" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td id= "resultado4" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td id = "resultado5" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td id = "resultado6" onclick="rq34(this.innerText)">
                                        <span></span>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div id="divCampo">
                        <select id="selectCampo">
                            <option>Cualquier campo</option>
                            <option value="campo1">Campo 1</option>
                            <option value="campo2">Campo 2</option>
                        </select>
                    </div>
                </div>
                <div id="buscar">
                    <input id="btnBuscar" type="submit" value="Buscar" onClick="localizaPalabra(); return false;"/>
                </div>
            </div>
            <div id="divCentral">
                <div id="divTabla">
                    <table id="tMenu">
                    	<thead id="cabeceraTabla">
	                        <tr>
	                            <th>
	                                Healthtopic
	                            </th>
	                            <th>
	                            URL
	                            </th>
	                        </tr>
	                    </thead>
	                    <tbody id="cuerpoTabla">
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                               <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td>
	                                
	                            </td>
	                            <td>
	                                <a href="#" target="_blank"> </a>
	                            </td>
	                        </tr>
	                    </tbody>
                    </table>
                </div>	
               <div id="paginacion">
                    <div id="menosDiez">
                        <input id="btn10menos" type="submit" value="10-" onclick="menos10paginacion()" />
                    </div>
                    <div id="divBuscaPalabra">
                        <input disabled id="txtBuscaPalabra" type="text" value="Inserta arriba la palabra a buscar sin espacios" onBlur="if(this.value == ''){ this.value = 'Inserta la palabra a buscar sin espacios'; this.style.color = '#BBB';}" onFocus="if(this.value == 'Inserta la palabra a buscar sin espacios'){ this.value = ''; this.style.color = '#000';}" style="color:#BBB;" />
                    </div>
                    <div id="masDiez">
                        <input id="btn10mas" type="submit" value="10+" onclick="mas10paginacion()" />
                    </div>
                </div>
                <div id="pie">
                     &copy; Copyright Alef - Uned 2013 
                </div>
            </div>
            </form>
        </div>
    </div>
</body>
</html>