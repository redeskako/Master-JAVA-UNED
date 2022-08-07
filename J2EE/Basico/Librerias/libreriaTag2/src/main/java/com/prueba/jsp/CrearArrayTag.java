package com.prueba.jsp;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.*;

public class CrearArrayTag extends TagSupport{
	public int doStartTag(){
		String [] cadenas= new String[] {"Tome Lee Jones", "Samuel L. Jackson", "Will Smith"};
		this.pageContext.setAttribute("cadenas", cadenas);
		return this.SKIP_BODY;
	}
}