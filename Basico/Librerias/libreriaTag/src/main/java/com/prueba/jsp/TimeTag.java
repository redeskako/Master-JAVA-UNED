package com.prueba.jsp;

import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import java.text.SimpleDateFormat;

public class TimeTag extends TagSupport{
	public int doEndTag() throws JspException{
		SimpleDateFormat sdf;
		sdf= new SimpleDateFormat("HH:mm:ss");
		String time= sdf.format(new java.util.Date());
		try{
			this.pageContext.getOut().print(time);
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		return this.EVAL_PAGE;
	}
}