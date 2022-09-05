package uned.java2016.spring.tag.index;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TextoPrueba extends TagSupport{
	private static final long serialVersionUID = 1L;	
	
	public int doEndTag() throws JspException
	{
		try {
			this.pageContext.getOut().print(" Las etiquetas JSTL funcionando, estamos cerrando la etiqueta <br/>");
        } catch (Exception e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
		
	}
	
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print(" Las etiquetas JSTL funcionando, estamos abriendo la etiqueta  <br/>");
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
    }
}

