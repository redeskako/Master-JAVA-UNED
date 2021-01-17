package edu.uned.java;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundo extends TagSupport{
	private static final long serialVersionUID = 1L;	
	
	public int doEndTag() throws JspException
	{
		try {
			this.pageContext.getOut().print(" Al cerrar la etiqueta <br/>");
        } catch (Exception e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
		
	}
	
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print(" Al abrir la etiqueta <br/>");
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
    }
}
