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
		
		/*if(listaHashtags!=null){
			for(int i=0;i<listaHashtags.size();++i) {
				try {
					if(miHashtag!=null){
						if(miHashtag.equals(listaHashtags.get(i))){
							this.pageContext.getOut().print("<option value="+listaHashtags.get(i)+" selected='selected'>"+listaHashtags.get(i)+"</option>");
						}
						else{
							this.pageContext.getOut().print("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
						}
					}
					else{
						this.pageContext.getOut().print("<option value="+listaHashtags.get(i)+">"+listaHashtags.get(i)+"</option>");
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		return super.EVAL_BODY_INCLUDE;
	}
}
