package com.prueba.jsp;

import javax.servlet.*;
import javax.servlet.jsp.tagext.*;

public class IterateTag extends TagSupport{
	private int cuenta = 0;
	private String[] cadenas = null;

	public int doStartTag(){
		this.cadenas = (String []) this.pageContext.getAttribute("cadenas");
		return super.EVAL_BODY_INCLUDE;
	}

	public int doAfterBody(){
		try{
			this.pageContext.getOut().print(" "+ this.cadenas[this.cuenta]+ "<BR/>");
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		this.cuenta ++;
		if (this.cuenta >= this.cadenas.length){
			return this.SKIP_BODY;
		}
		return this.EVAL_BODY_AGAIN;
	}
}