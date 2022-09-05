package edu.uned.java;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundoParametro extends TagSupport{
	private static final long serialVersionUID = 1L;	
	private String cad;
    
	public HolaMundoParametro(){
		super();
	}
	
	public void setCad(String cad){
        this.cad=cad;
    }
	
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print("La cadena como parámetro es <I>'"+this.cad+"'</I>");
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return SKIP_BODY;
    }

}
