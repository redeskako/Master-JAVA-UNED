package com.prueba.jsp;

public class JspException extends RuntimeException{
	public JspException(){
		super("JSP Exception:\n\t");
	}
	public JspException(String err){
		super("JSP Exception:\n\t"+err);
	}
}