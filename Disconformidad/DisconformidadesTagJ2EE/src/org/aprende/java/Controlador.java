package org.aprende.java;

import org.aprende.java.bbdd.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Controlador {
	Usuario user;
	Disconformidad disconf;
	ArrayList <Disconformidad> listadisconf;
	Servicio servicio;
	Servicios listaServi;


	public static final Integer NOLOGADO=new Integer(0);
	public static final Integer LOGADO=new Integer(1);

	//int validado;

	//public static final int CONSULTA=2;
	//public static final int MODIFICAR=3;
	//public static final int ELIMINAR=4;


	public Controlador(){
		this.user= new Usuario();
		this.servicio=new Servicio();
		this.disconf=new Disconformidad();
		this.listadisconf=null;
		//this.estalogado=false;
	}

	public void setUser(Usuario user){
		this.user= user;
	}


	public void logarse(HttpServletRequest req)  {
		try{
		this.user= this.user.validarUsuario(req.getParameter("txtUsuario"),req.getParameter("txtPwd"));
		// Establecer la sesion si ha validado.
		System.out.println(user.Id());

		HttpSession ses=req.getSession();
		if (user!=null) {
			//ses.setAttribute("Gestion",this.user.getGestion()); //VARIABLE DE SESION con la clave el usuario
			ses.setAttribute("Estado",Controlador.LOGADO); //VARIABLE DE SESION con la clave el usuario
			ses.setAttribute("usuario",this.user);
		//	this.validado=1;
		}else{
			ses.setAttribute("Estado",Controlador.NOLOGADO); //
		//	this.validado=0;
		}
		}catch (Exception e){
			HttpSession ses=req.getSession();
			ses.setAttribute("Estado",Controlador.NOLOGADO);
		//	this.validado=0;
		}
	}

	public ArrayList<Disconformidad> listarDisconformidades(Usuario usu,int pag,int intervalo, String orden){
		//System.out.println(usu.getGestion());
		//System.out.println(usu.Id());
		//this.listadisconf=new Disconformidad().listadoDisconformidades(usu.getGestion(),usu.Id());
		this.listadisconf=new Disconformidad().listadoDisconformidadesPorPaginas(usu.getGestion(),usu.Id(),pag,intervalo,orden);
		return this.listadisconf;
	}

	public Servicios listarServicios(){
		this.listaServi=new Servicios().AllServicios();

		return this.listaServi;
	}
	public Usuario getUsuario(int idusu){
		Usuario usu=new Usuario().getUsuario(idusu);
		return usu;
	}

	public Servicio getServicio(int idservi){
		Servicio servi=new Servicio().getServicio(idservi);
		return servi;
	}

	public Disconformidad getDisconformidad(int idDisconf){
		Disconformidad disconf=new Disconformidad().getDisconformidad(idDisconf);
		return disconf;
	}

	public boolean delete (Disconformidad d) {
		//Disconformidad d = (Disconformidad)session.getAttribute("dis");

		if (d.deleteDisconformidad(d)) {
	 		return true;
	 	}else {
	 		return false;
	 	}
	}

 /*	public boolean puedoseguir (){

		//if (session.getAttribute("Estado")==null) {
		if (this.validado==null){
			   response.sendRedirect("index.jsp");
		}else if ( ((Integer) session.getAttribute("Estado")).intValue()==0){
			   session.invalidate();
			   response.sendRedirect("index.jsp");
		}else if (((Integer) session.getAttribute("Estado")).intValue()==1 ){
		}
	} */

}
