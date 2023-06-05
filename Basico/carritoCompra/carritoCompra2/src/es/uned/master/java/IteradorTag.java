package es.uned.master.java;

import jakarta.servlet.jsp.tagext.TagSupport;
// import java.sql.*;
import java.util.*;
import jakarta.servlet.jsp.JspException;

public class IteradorTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private Vector<Libro> libros;
	private int puntero;
	public final int NULO = 0;
	public IteradorTag(){
		this.libros = new Vector<Libro>();
		this.puntero = this.NULO;
	}

	public int doStartTag(){
		String sql = "SELECT * FROM Libro ORDER BY nombre";
		this.libros = Libro.consulta(sql);
		this.puntero = this.NULO;
		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() throws JspException{
		try{
// System.out.println("Libro:" + this.libros.get(this.puntero));
			pageContext.getOut().print(this.libros.get(this.puntero).getLibro());
			//pageContext.getOut().print(this.libros.get(this.puntero) + "<BR/>");
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:" + err);
			throw new LibreriaException("Error Tag.");
		}
		this.puntero++;
		if (this.puntero >= this.libros.size()){
			return SKIP_BODY;
		}
		return EVAL_BODY_AGAIN;
	}

	public int doEndTag(){
		try{
			for(int i=0 ; i<this.libros.size() ; i++){
				pageContext.getOut().print("<option value=" + this.libros.get(i).getId() + ">" + 
											this.libros.get(i).getLibro() + "</option>");
			}
		}catch(Exception err){
			//throw new LibreriaException("Error Tag:" + err);
			throw new LibreriaException("Error Tag:");
		}
		return EVAL_PAGE;
	}
}