 /* package org.aprende.java.bbdd;

import org.aprende.java.Controlador;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;


public class CrearTagDisconformidades extends TagSupport {

	private ArrayList <Disconformidad> listaDisconformidades;
	private Usuario usu;

	public CrearTagDisconformidades(){
		this.usu=new Usuario();
	}

	public int doStartTag(){
		Controlador elcontrolador= new Controlador();
		HttpSession ses=pageContext.getSession();
		usu=(Usuario)ses.getAttribute("usuario");


		listaDisconformidades= elcontrolador.listarDisconformidades(usu,(Integer)ses.getAttribute("pag"),4,(String)ses.getAttribute("orden") );
		this.pageContext.setAttribute("lista", listaDisconformidades);
		return this.SKIP_BODY;
	}

	}
*/