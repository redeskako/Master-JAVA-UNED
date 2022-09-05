package controller;

import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class index extends TagSupport {

	private String valor;

	public void setEstado(String estado) {
        this.valor = estado;
    }
	
	public int doEndTag(){
	
		HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
		HttpSession sesion = request.getSession(true); //Obtengo la variable sesión.
		//String usuarioAcceso = request.getParameter("username"); 
		//String passAcceso = request.getParameter("password"); 
		setEstado(request.getParameter("estado")); 
		
		if (this.valor != null){
			if(this.valor.equals("1")){				
				//<center><h2>Usuario inválido</h2></center>
				try {
					this.pageContext.getOut().print("<center><h2>Usuario inválido</h2></center>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (this.valor.equals("2")){
				//<center><h2>Has salido de la sesión</h2></center>
				try {
					sesion.invalidate();
					this.pageContext.getOut().print("<center><h2>Has salido de la sesión</h2></center>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//<center><h2>Situacion desconocida</h2></center>
				try {
					this.pageContext.getOut().print("<center><h2>Situacion desconocida</h2></center>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		} else {
			//<center><h2>Bienvenido</h2></center>
			try {
				this.pageContext.getOut().print("<center><h2>Bienvenido</h2></center>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.EVAL_PAGE;
	}
}
