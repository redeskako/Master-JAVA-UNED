<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page import = "org.aprende.java.bbdd.*, java.util.Date, java.text.*"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%    
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
	 	
	 	try {
			if (request.getParameter("chkDevolucion").equalsIgnoreCase("on")){
	 			devolucion=true;
		 	}
	 	}catch (Exception e){
	 			 
	 	} finally{ 	}	
	 	
//	 	Disconformidad d =new Disconformidad(0,new Date(),request.getParameter("txtDocumentos"),1,2,devolucion,request.getParameter("txtMotivo"),request.getParameter("txtComentario"));
	 	Disconformidad d =new Disconformidad();
	 	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	 	
	 	d.numero(0);
	 	d.fecha(new Date());
	 	//d.fecha(sdf.parse(request.getParameter("txtFecha")));
	 	d.docs(request.getParameter("txtDocumentos"));
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
	 	 	
	 }else if (modo.equalsIgnoreCase("editar")) {
		boolean devolucion=false;
	 	try {
			if (request.getParameter("chkDevolucion").equalsIgnoreCase("on")){
	 			devolucion=true;
		 	}
	 	}catch (Exception e){
	 			 
	 	} finally{ 	}	
		Disconformidad d = (Disconformidad)session.getAttribute("dis");

	 //	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	 
	 //   d.fecha(new Date());
	 //   d.fecha(sdf.parse(request.getParameter("txtFecha")));
	 	d.docs(request.getParameter("txtDocumentos"));
	 	d.servicio(Integer.parseInt(request.getParameter("Servicio")));
	 	d.usuario(((Usuario)session.getAttribute("usuario")).Id());
	 	d.devolucion(devolucion);
	 	d.motivo(request.getParameter("txtMotivo"));
	 	d.comentario(request.getParameter("txtComentario"));
	 	
	 	if (!d.updateDisconformidad(d)) {
//		 	response.sendRedirect("error.jsp");
		 	out.println ("No actualiza");
		} 
//	 		out.println ("Insertar realizado con exito");
//	 	}else {
//	 		out.println ("Error al insertar");
//	 		response.sendRedirect("error.jsp");
//	 	}
// 		out.println("Editar");
 		
	 }else if (modo.equalsIgnoreCase("eliminar")) {
%>	  
	  <jsp:useBean id="elcontrolador"  class="org.aprende.java.Controlador"></jsp:useBean>
<%	  
		if (!elcontrolador.delete((Disconformidad)session.getAttribute("dis"))){
			response.sendRedirect("error.jsp");
		}
//	 	Disconformidad d = (Disconformidad)session.getAttribute("dis");
//		if (d.deleteDisconformidad(d)) {
//	 		out.println ("Eliminar realizado con exito");
//	 	}else {
//	 		out.println ("Error al Eliminar");
//	 	}
		  		
	 }
}

response.sendRedirect("disconformidades.jsp");

%>
</body>
</html>
<% } %>