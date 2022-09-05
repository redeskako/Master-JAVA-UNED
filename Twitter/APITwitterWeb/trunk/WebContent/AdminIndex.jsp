<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.Iterator, java.util.ArrayList"%>
<%@ page import = "uned.java2016.apitwitter.dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<link href="Resources/css/estilos.css" rel="stylesheet" type="text/css" />
	<title>Area Administrador de Hastags</title>
</head>
<body>

	<!-- Salida sesión usuario -->
		<div><a class="cerrarSesion" href="APITwitterLogin?operacion=logout">Cerrar sesión</a></div>
	
	<!-- Titulo Area Administrador -->
	<h1>Area de administrador de APITwitter</h1> 
	
	<%
	String usuario = (String) session.getAttribute("idSesion");
	String rol = (String) session.getAttribute("rolSesion");
	//System.out.println("EL USUARIO ES: "+usuario);
	if (usuario != null){
		if (rol.equals("adm")){
	
		//Cargo la lista de hashtag de a tabla y ver cual elige el usuario para borrar
		ArrayList<String> listaHashtags=(ArrayList<String>)request.getAttribute("miListaHashtagsHTML");
		String miHashtag=(String)request.getAttribute("queHashtagElegido");
		
		//No hay hashtags cargados por entrar 1º vez, los cargamos de BBDD
		if(listaHashtags==null)
		{ 
		%>
			<script type='text/javascript'>window.location='APITwitterAdm';</script>
		<% 
		}
		
		//Cargamos la pagina.
		else{%>
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
											<% for(int i=0;i<listaHashtags.size();++i) {
													if(miHashtag!=null){
														if(miHashtag.equals(listaHashtags.get(i))){
															out.println("<option value="+listaHashtags.get(i)+" selected='selected'>"+listaHashtags.get(i)+"</option>");
														}
														else{
															out.println("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
														}
													}
													else{
														out.println("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
													}
												}
												out.println("</select>");
											%>
								
								
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
					
					<% //Sección para los errores %>
					<div id="capa_errores" class="misZonas">
						<br/>
						&#160;&#160;ESTA ES LA ZONA DE ERRORES SOLO ESTARÁ VISIBLE SI SE PRODUCE UN ERROR SINO NO SE VE
						<br/>
						&#160;
					</div>
					
					<% //Sección Principal cargamos tabla Administrativa %>
					<div id="capa_principal" class="misZonas">
						<div id="zonaResultadosTitulo">
										
							<h4>Tabla de Admministración de Hashtag</h4>

						<%
							// Dibuja los datos de la tabla de Gestion de Hashtags
							HashtagAdm p = new HashtagAdm();
							//ArrayList<HashtagAdm> listaHashtags2= new HashtagAdm().listadoHashtags();
                                     ArrayList<HashtagAdm> listaHashtags2=(ArrayList<HashtagAdm>)request.getAttribute("miTablaHashtagsHTML");
							Iterator<HashtagAdm> it = listaHashtags2.iterator();

							// Si hay peticiones pendientes se dibuja la tabla, si no aparece un mensaje
							if (listaHashtags2.size() != 0) {
						%>
							<table>
								<tr>
									<th scope="col">hashtag_id</th>
									<th scope="col">origen</th>
									<th scope="col">fecha_entrada</th>
									<th class="boton" scope="col" colspan="5"></th>
								</tr>
							<%
							// Se dibuja la tabla mientras exitan valores en la base de datos
							while (it.hasNext()) {
								p = (HashtagAdm) it.next();
							%>
							<tr>
								<td><%=p.getHashtag()%></td>
								<td><%=p.getOrigen()%></td>
								<td><%=p.getFechaEntrada().toString()%></td>
							</tr>
							<%
								} // fin while
							%>
						</table>
						</div>
					</div>
				</div>
				<footer>
				</footer>
			</div>
			<%
			} 	
			%>
			<%
			} 
		}	else{
			response.sendRedirect("./index.jsp?estado=responsabilidad");
		}

	}
	else{
		response.sendRedirect("./index.jsp?estado=ilegal");
	}
			%>
</body>
</html>