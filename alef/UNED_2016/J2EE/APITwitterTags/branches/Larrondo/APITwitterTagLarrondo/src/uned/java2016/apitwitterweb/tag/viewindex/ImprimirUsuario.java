package uned.java2016.apitwitterweb.tag.viewindex;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ImprimirUsuario extends TagSupport{
	private static final long serialVersionUID = 1L;	
	private static String usuario;
    
	public ImprimirUsuario(){
		super();
	}
	
	public int doStartTag() throws JspException {
		ImprimirUsuario.usuario=Inicializar.getUsuario();
		try {
			this.pageContext.getOut().print(ImprimirUsuario.usuario);
        } catch (IOException e){
            throw new JspException("Error al enviar al cliente" + e.getMessage());
        }
        return SKIP_BODY;
    }
}
