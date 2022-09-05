<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Date"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% //int elestado=(int)session.getAttribute("Estado");
   //if ((int)session.getAttribute("Estado")==1) {	
  // if ( elestado==1 ) {
//  if (session.getAttribute("usuario")!=null){
  
   
   if (session.getAttribute("Estado")==null) {
	   response.sendRedirect("index.jsp");
 
   }else if ( ((Integer) session.getAttribute("Estado")).intValue()==0){
	   session.invalidate();
	   response.sendRedirect("index.jsp");
	  
  
   }else if (((Integer) session.getAttribute("Estado")).intValue()==1 ){	   
	 	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modosout</title>
</head>
<body>

<%
String opcion=request.getParameter("cmdOpcion");

out.println(opcion);

if (opcion.equalsIgnoreCase("Aceptar")){
	 
	 String modo=(String)session.getAttribute("modo");
	 
	 if (modo.equalsIgnoreCase("nuevo")) {
	 
	 	boolean devolucion=false;
//	 	 if (request.getParameter("chkDevolucion")=="on"){
System.out.println("Checked: "+request.getParameter("chkDevolucion"));
	 	 if (request.getParameter("chkDevolucion").equalsIgnoreCase("on")){
	 		devolucion=true;
	 	}
	 	//else{
	 	//	devolucion=false;
	 	//}
	 	
	 	//boolean devolucion=(request.getParameter("chkDevolucion").equalsIgnoreCase("on")) ? true:false;
	 	
//	 	Disconformidad d =new Disconformidad(0,new Date(),request.getParameter("txtDocumentos"),1,2,devolucion,request.getParameter("txtMotivo"),request.getParameter("txtComentario"));
	 	Disconformidad d =new Disconformidad();
	 	//Disconformidad d = (Disconformidad)session.getAttribute("dis");
	 	d.numero(0);
	 	d.fecha(new Date());
	 	d.docs(request.getParameter("txtDocumentos"));
	 	
	 	//d.servicio(1);
	 	//Servicio s = new Servicio();
	 	//out.println (request.getParameter("Servicio"));
	 	//s.getServicio(request.getParameter("Servicio"));
	 	//d.servicio(s.Id());
	 	d.servicio(Integer.parseInt(request.getParameter("Servicio")));
	 	
	 	
	 	d.usuario(((Usuario)session.getAttribute("usuario")).Id());
	 	d.devolucion(devolucion);
	 	d.motivo(request.getParameter("txtMotivo"));
	 	d.comentario(request.getParameter("txtComentario"));
	 	
	 	if (d.insertDisconformidad(d)) {
	 		out.println ("Insertar realizado con exito");
	 	}else {
	 		out.println ("Error al insertar");
	 	}
	 
	  	out.println("Nuevo");
	 	 	
	 }else if (modo.equalsIgnoreCase("editar")) {
 		out.println("Editar");
 		
	 }else if (modo.equalsIgnoreCase("eliminar")) {
	  
	 	Disconformidad d = (Disconformidad)session.getAttribute("dis");

		if (d.deleteDisconformidad(d)) {
	 		out.println ("Eliminar realizado con exito");
	 	}else {
	 		out.println ("Error al Eliminar");
	 	}
		  		
	 }
 	
}else{
//	response.sendRedirect("disconformidades.jsp");
//	out.println("Cancelar");
}

	response.sendRedirect("disconformidades.jsp");
%>
</body>
</html>
<% 	 } %>