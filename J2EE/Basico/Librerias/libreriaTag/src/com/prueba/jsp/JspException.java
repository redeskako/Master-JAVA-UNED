package com.prueba.jsp;

public class JspException extends RuntimeException{
	public JspException(){
		super("ErrorJSP\n\t");
	}
	public JspException(String err){
		super("ErrorJSP\n\t"+err);
	}
}