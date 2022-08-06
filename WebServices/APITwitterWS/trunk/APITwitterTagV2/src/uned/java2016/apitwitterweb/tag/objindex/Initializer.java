package uned.java2016.apitwitterweb.tag.objindex;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import uned.java2016.apitwitter.dao.*;

public class Initializer extends TagSupport {
	
	public int doStartTag(){
		Tweet tweetInfo = (Tweet) this.pageContext.getRequest().getAttribute("tweetInfo");
		this.pageContext.setAttribute("tweetInfo", tweetInfo);
		ClinicalStudy studioInfo = (ClinicalStudy) this.pageContext.getRequest().getAttribute("studioInfo");
		this.pageContext.setAttribute("studioInfo", studioInfo);
		// evaluamos el resto de la pagina
		return EVAL_PAGE;
	}
}
