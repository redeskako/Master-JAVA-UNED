package org.aprende.java.bbdd;
import org.aprende.java.Controlador;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspException;

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
		listaDisconformidades= elcontrolador.listarDisconformidades(usu);
		this.pageContext.setAttribute("lista", listaDisconformidades);
		return this.SKIP_BODY;
	}

	}
