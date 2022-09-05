package org.aprende.java.bbdd;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.*;
import java.util.*;
import javax.servlet.jsp.JspException;
import org.aprende.java.bbdd.*;

public class IteradorTagDisconformidades extends TagSupport {
	
	private ArrayList<Disconformidad> listaDisconformidades;
	private int contador;
	
	
	public IteradorTagDisconformidades(){
		this.contador=0;
	}
	public int doStartTag(){
		this.listaDisconformidades= (ArrayList<Disconformidad>)this.pageContext.getAttribute("lista");
		this.contador=0;
			
		return SKIP_BODY;
	}
	
	//funcion privada que comprueba si el valor es un null y en ese caso lo transforma en cadena vacia
	private String sNull(String valor){
		return (valor!=null ? valor: "");				
	}
	
	//funcion privada que saca por pantalla los datos de una disconformidad en una tabla 
	private void muestraDisconformidad(Disconformidad dis){
		
		try{
			Usuario usu=new Usuario().getUsuario(dis.usuario());
			Servicio servi= new Servicio().getServicio(dis.servicio());
			
			pageContext.getOut().print("<tr>");
			pageContext.getOut().print("<td align='center'>" + dis.numero() + "</td>");
			pageContext.getOut().print("<td align='center'>" + dis.fecha() + "</td>");
			pageContext.getOut().print("<td align='center'>" + sNull(dis.docs()) + "</td>");
			pageContext.getOut().print("<td align='center'>" + sNull(dis.motivo()) + "</td>");
			pageContext.getOut().print("<td align='center'>" + sNull(dis.comentario()) + "</td>");
			pageContext.getOut().print("<td align='center'>" + sNull(usu.nombre()) + "</td>");
			pageContext.getOut().print("<td align='center'>" + sNull(servi.nombre())+ "</td>");
			HttpSession ses=pageContext.getSession();
			if (((Usuario)ses.getAttribute("usuario")).getGestion() ==1) { // si el usuario es administrador
				pageContext.getOut().print("<td><A HREF='modifica.jsp?id=" + dis.numero() + "'>Modificar</A></td>");
				pageContext.getOut().print("<td><A HREF='elimina.jsp?id=" + dis.numero() + "'>Eliminar</A></td>");
			}
			pageContext.getOut().print("<tr>");
		
			
		}catch (Exception err){
			throw new DisconformidadException("Error Tag.");
		}
	}
	
/*	
	public int doAfterBody(){
System.out.println("CONTADOR: "+contador);		
		try{	
			if (contador<listaDisconformidades.size()){
				
				Disconformidad unaDisconformidad;
				unaDisconformidad= (Disconformidad) listaDisconformidades.get(contador);
			
				muestraDisconformidad(unaDisconformidad);
				contador++;
				
				return this.EVAL_BODY_AGAIN;
			}else{
				return this.SKIP_BODY;
			}
		}catch(Exception err){
			throw new DisconformidadException("Error Tag.");
		}
	}
*/
	public int doEndTag(){
		try{
			for (int i=0; i< this.listaDisconformidades.size();i++){
				this.muestraDisconformidad(this.listaDisconformidades.get(i));
			}
		}catch(Exception e){
			throw new DisconformidadException("Error End Tag");
		}
		return EVAL_PAGE;
	}

}
