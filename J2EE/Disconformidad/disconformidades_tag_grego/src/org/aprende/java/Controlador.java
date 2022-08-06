package org.aprende.java;

import org.aprende.java.bbdd.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Controlador {
	Usuario user;
	Disconformidad disconf;
	Servicio servicio;
	ArrayList <Disconformidad> listadisconf;
	
	public static final Integer NOLOGADO=new Integer(0);
	public static final Integer LOGADO=new Integer(1);
	//public static final int CONSULTA=2;
	//public static final int MODIFICAR=3;
	//public static final int ELIMINAR=4;
	
	
	public Controlador(){
		this.user= new Usuario();
		this.servicio=new Servicio();
		this.disconf=new Disconformidad();
		listadisconf=null;
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
		}else{
			ses.setAttribute("Estado",Controlador.NOLOGADO); //
		}
		}catch (Exception e){
			HttpSession ses=req.getSession();
			ses.setAttribute("Estado",Controlador.NOLOGADO);
		}
	}
	
	public ArrayList<Disconformidad> listarDisconformidades(Usuario usu){
		//System.out.println(usu.getGestion());
		//System.out.println(usu.Id());
		this.listadisconf=new Disconformidad().listadoDisconformidades(usu.getGestion(),usu.Id());
	
		return this.listadisconf;
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
	
}
