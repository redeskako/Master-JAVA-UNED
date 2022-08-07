<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Iterator, java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.*"%>
<%@ taglib uri="WEB-INF/AdminIndexTag.tld" prefix="admin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="Resources/html/metadatos.html" %>
	<title>Area Administrador de Hastags</title>
</head>
<body>
	<%@ include file="Resources/html/encabezado.html" %>
	<!-- Salida sesión usuario -->
		<div><a class="cerrarSesion" href="APITwitterLogin?operacion=logout">Cerrar sesión</a></div>
	
	
	<!-- Titulo Area Administrador -->
	<h1>Area de administrador de APITwitter</h1> 
	
	
		<admin:validarol>
			<div id="faja">
				<div id="centro">
					<div id="seccion_administrador" class="misZonas">
						
						<div id="zonaApiTwitter">
							<img src="imagenes/zona_buscar/ApiTwitter.png" alt="ApiTwitter" />
						</div>
						
						<div id="zonaHashtag">
														
								<form name="formAdmIndex" action="./APITwitterAdm" method="get" >
								
								<input type="submit" id="btn_borrar" name="boton" value="Eliminar" class="boton"/>
								
									<select name="buscadorHastags" id="buscadorHashtags" class="select_busqueda">
										<option value="" selected="selected" class="option_inicial_busqueda">
											Introduce aquí tu búsqueda
										</option>
											<admin:comboadm />
								
								
								<br/>
								<!-- Sección Administrador Añadir Hashtag-->
								<input type="submit" id="btn_sumar" name="boton" value="Sumar" class="boton"/>
								<input type="text" name="txt_sumar" id="txt_sumar" maxlength="45" size="30" />
								
								<!-- Sección Administrador Fichero de carga de Hashtag-->
								<input type="submit" id="btn_fichero" name="boton" value="Fichero" class="boton"/>
								<input type="text" name="txt_fichero" id="txt_fichero" maxlength="80" size="50" />
								
								</form>
						</div>
							
							
					<div id="zonaAdministrador">
							<h5>Administrador: &#160;</h5>
							<p>Nombre del Administrador</p>
						</div>
					</div>
					
					<!-- Sección para los errores  -->
					<div id="capa_errores" class="misZonas">
						<br/>
						&#160;&#160;ESTA ES LA ZONA DE ERRORES SOLO ESTARÁ VISIBLE SI SE PRODUCE UN ERROR SINO NO SE VE
						<br/>
						&#160;
					</div>
					
					<!-- Sección Principal cargamos tabla Administrativa  -->
					<div id="capa_principal" class="misZonas">
						<div id="zonaResultadosTitulo">
										
							<h4>Tabla de Admministración de Hashtag</h4>

						
							<table>
								<tr>
									<th scope="col">hashtag_id</th>
									<th scope="col">origen</th>
									<th scope="col">fecha_entrada</th>
									<th class="boton" scope="col" colspan="5"></th>
								</tr>
							
                            <admin:tablaadm />
						</table>
		
						</div>
					</div>
				</div>
			
			</div>
			
			</admin:validarol>
<%@ include file="Resources/html/footer.html" %>
</body>
</html>