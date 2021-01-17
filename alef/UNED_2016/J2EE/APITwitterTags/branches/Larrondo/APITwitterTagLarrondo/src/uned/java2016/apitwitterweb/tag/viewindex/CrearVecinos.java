package uned.java2016.apitwitterweb.tag.viewindex;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CrearVecinos extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<String> misNeighbors;

	public CrearVecinos(){
		super();
	}
	
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		
		ArrayList<String> misNeighbors=(ArrayList<String>)request.getAttribute("Neighbors");
		if(misNeighbors!=null){
			try {
				this.pageContext.getOut().print("<script type='text/javascript'>borrarVecinosAnteriores();</script>");
				this.pageContext.getOut().print("<div id='capa_principal' class='misZonas'>");
				this.pageContext.getOut().print("<div id='zonaResultadosTitulo'><h5>RESULTADOS</h5></div>");
				this.pageContext.getOut().print("<div id='zonaResultadosGeneral'>");
				for(int i=0;i<misNeighbors.size();++i) {
					this.pageContext.getOut().print("<div id='zonaResultadoParticular"+i+"' class='zonaResultadoParticularEnlaces'>");
					this.pageContext.getOut().print("<div id='zonaID"+i+" class='zonasID'><h5>Neighbor:&#160;&#160;</h5><a href='APITwitterView?enlace=Tweets&queHashtag="+misNeighbors.get(i)+"'>"+misNeighbors.get(i)+"</a></div>");
					this.pageContext.getOut().print("</div>");
				}
				this.pageContext.getOut().print("</div>");
				this.pageContext.getOut().print("</div>");
			}catch (IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return SKIP_BODY;
	}
}
