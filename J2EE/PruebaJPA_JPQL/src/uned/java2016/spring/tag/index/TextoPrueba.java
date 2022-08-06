package uned.java2016.spring.tag.index;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TextoPrueba extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print(" "
					+ "Para probar este programa debes tener <br/>"
					+ "el usuario 'uned' con la contraseña 'uned'<br/>"
					+ "en tu phpmyadmin. También debes crear una<br/>"
					+ "BBDD llamada 'miuned'. Utiliza el archivo<br/>"
					+ "'miuned.sql' para generar la tabla necesaria<br/>"
					+ "de manera rápida y sencilla.<br/>");
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return EVAL_PAGE;
    }
	
	public int doEndTag() throws JspException
	{
        return EVAL_PAGE;		
	}
}

