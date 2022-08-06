package uned.java2016.apitwitterweb.tag.adminindex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.ArrayList;

public class RolAdm extends TagSupport{
    
    public RolAdm(){
    	
    }
    
    public int doStartTag() throws JspException {
    	
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		
    	String usuario = (String) session.getAttribute("idSesion");
        String rol = (String) session.getAttribute("rolSesion");
        
        if (usuario != null){
    		if ((!rol.equals("adm"))){
    			try {
					response.sendRedirect("./index.jsp?estado=responsabilidad");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
        }else{
        	try {
				response.sendRedirect("./index.jsp?estado=ilegal");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return EVAL_PAGE;
    
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    
	

}
