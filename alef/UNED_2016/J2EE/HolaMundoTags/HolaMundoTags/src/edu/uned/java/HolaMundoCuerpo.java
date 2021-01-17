package edu.uned.java;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundoCuerpo extends BodyTagSupport{
	private static final long serialVersionUID = 1L;

	public HolaMundoCuerpo(){
		super();
	}
	
    public int doAfterBody() throws JspException {
        try {
            BodyContent bc=getBodyContent();
            String cuerpo=bc.getString();
            JspWriter out=bc.getEnclosingWriter();
            if (cuerpo!=null){
                out.print("Lo que hay entre la etiqueta de abrir y de cerrar es " + cuerpo);
            }
        } catch (IOException e){
            throw new JspException("Error: EXception"+e.getMessage());
        }
        return SKIP_BODY;
    }
}