package org.aprende.java.bbdd;

import javax.servlet.jsp.tagext.TagSupport;

public class InicializaDisconformidades extends TagSupport {

	public int doStartTag(){
		this.pageContext.setAttribute("usuario", "nombre");

		return TagSupport.SKIP_BODY;
	}
}
