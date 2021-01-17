package com.prueba.jsp;

import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import java.text.*;

public class TimeTag extends TagSupport{
	public int doEndTag() throws JspException{
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
		String time= sdf.format(new java.util.Date());
		try{
			this.pageContext.getOut().print(time.toString());
		}catch(Exception e){
			throw new JspException(e.toString());
		}
		return this.EVAL_PAGE;
	}
}