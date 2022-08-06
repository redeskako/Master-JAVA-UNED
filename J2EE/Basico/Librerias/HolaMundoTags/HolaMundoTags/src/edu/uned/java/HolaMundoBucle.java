package edu.uned.java;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HolaMundoBucle extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	int veces=0;
    BodyContent bc;
    
	public HolaMundoBucle(){
		super();
	}
	
	public void setVeces(int veces){
        this.veces=veces;
    }
	
    public void setBodyContent(BodyContent bc){
        this.bc=bc;
    }
    
    public int doAfterBody() throws JspException {
    	veces--;
        if(veces>0){
            try{
                JspWriter out=bc.getEnclosingWriter();
                out.println(bc.getString());
                bc.clearBody();
            }catch(IOException e){
                System.out.println("Error en Tag Bucle" +  e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        }else{
            return SKIP_BODY;
        }
    }
}
