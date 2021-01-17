package org.aprende.java.bbdd;

import org.aprende.java.*;
import org.aprende.java.bbdd.*;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspException;

public class IteradorTagDisconformidades extends TagSupport {
	private Iterator listaDisconformidades;
	private Usuario usuario;
	private int puntero;
	public final int NULO=0;
	
	public IteradorTagDisconformidades(Usuario usu){
		//this.libros= new Vector();
		this.usuario=usu;
		this.puntero= this.NULO;
	}

	public int doStartTag(){
		Controlador elcontrolador = new Controlador();
		Iterator listaDisconformidades = elcontrolador.listarDisconformidades(this.usuario);
		this.puntero= this.NULO;
		return this.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException{
		try{
			//System.out.println("Libro:"+this.libros.get(this.puntero));
			if (this.listaDisconformidades.hasNext()) {
				Disconformidad dis= (Disconformidad)this.listaDisconformidades.next(); 
			
				pageContext.getOut().print();
				return this.EVAL_BODY_AGAIN;
			}else{
				return this.SKIP_BODY;
			}
			//pageContext.getOut().print(this.libros.get(this.puntero)+"<BR/>");
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new DisconformidadException("Error Tag.");
			
		}
		
		
	}
	public int doEndTag(){
		try{
			for(int i=0;i<this.libros.size();i++){
				pageContext.getOut().print("<option value="+this.libros.get(i).getId()+">"+this.libros.get(i).getLibro()+"</option>");
			}
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:"+err);
			throw new LibreriaException("Error Tag:");
		}
		return this.EVAL_PAGE;
	}
}
