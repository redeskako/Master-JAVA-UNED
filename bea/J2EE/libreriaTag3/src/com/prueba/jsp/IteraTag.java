package com.prueba.jsp;

import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import javax.servlet.jsp.*;

public class IteraTag extends TagSupport{
	private int cuenta = 0;
	private String[] cad = null;

	public int doStartTag(){
		this.cad= (String []) this.pageContext.getAttribute("cadenas");
		return this.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody(){
		try{
			this.pageContext.getOut().print(" "+ this.cad[this.cuenta] + "<BR/>");
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		this.cuenta ++;
		if (this.cuenta>= this.cad.length){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}
}