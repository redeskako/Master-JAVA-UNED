package uned.java2016.apitwitterweb.tag.viewindex;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class BucleHashtags extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<String> listaHashtags;
	private static String miHashtag;
	
	public BucleHashtags(){
		super();
	}
	
	@Override
	public int doStartTag() throws JspException {
		listaHashtags=Inicializar.getListaHashtags();
		miHashtag=Inicializar.getMiHashtag();
		
		this.pageContext.setAttribute("listaH",listaHashtags);
		this.pageContext.setAttribute("elHashtag",miHashtag);
				
		return super.EVAL_BODY_INCLUDE;
	}
}
