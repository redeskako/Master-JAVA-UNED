package org.aprende.java.bbdd;

import org.aprende.java.Controlador;
import javax.servlet.jsp.tagext.TagSupport;



public class CrearTagServicios  extends TagSupport{

	private Servicios listaServicios;

	public CrearTagServicios(){

	}

	public int doStartTag(){
		Controlador elcontrolador= new Controlador();
		listaServicios= elcontrolador.listarServicios();
		this.pageContext.setAttribute("listaServi", listaServicios);
		return this.SKIP_BODY;
	}
}
