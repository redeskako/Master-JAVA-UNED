package com.prueba.jsp;

public class JspException extends RuntimeException{
	public JspException(){
		super("JSPException:\n\t");
	}

	public JspException(String err){
		super("JSPException:\n\t"+err);
	}
}