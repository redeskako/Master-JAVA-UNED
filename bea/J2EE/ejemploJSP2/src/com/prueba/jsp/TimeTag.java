package com.prueba.jsp;

import javax.servlet.jsp.tagext.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import java.text.*;

public class TimeTag extends TagSupport{
	private String format="HH:mm:ss";

	public void setFormat(String format){
		this.format= format;
	}
	public int doEndTag(){
		SimpleDateFormat sdf= new SimpleDateFormat(this.format);
		String time= sdf.format(new java.util.Date());
		try{
			this.pageContext.getOut().print(time);
		}catch (Exception e){
			throw new JspException (e.toString());
		}
		return this.EVAL_PAGE;
	}
}