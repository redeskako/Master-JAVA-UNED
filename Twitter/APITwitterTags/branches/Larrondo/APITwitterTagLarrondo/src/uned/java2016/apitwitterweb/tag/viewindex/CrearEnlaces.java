package uned.java2016.apitwitterweb.tag.viewindex;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CrearEnlaces extends TagSupport{
	private static String miHashtag;
	
	public CrearEnlaces(){
		super();
	}
	
	public int doStartTag() throws JspException{
		CrearEnlaces.miHashtag=Inicializar.getMiHashtag();
		
		if(miHashtag!=null){
			try {
				this.pageContext.getOut().print("<script type='text/javascript'>crearEnlaces();</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return SKIP_BODY;
    }
}
