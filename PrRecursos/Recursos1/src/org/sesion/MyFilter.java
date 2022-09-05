package org.sesion;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import org.basedatos.ErrorException;


//Se crea la clase MyFilter

public class MyFilter implements Filter{
	private String urlLogin;
	public void init (FilterConfig config){
		//Configua url desconexion
		this.urlLogin = config.getInitParameter("urlLogin");
		if (urlLogin == null || urlLogin.trim().length() == 0){
			//error al cargar la url de login
			try {
				throw new ErrorException("No se ha configurado URL de login");
			} catch (ErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
	
	public void destroy(){
	}
	
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);		
		//Extraer sesion
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("iduser")==null){
			//si no hay sesión con usuario
			RequestDispatcher dispatcher = request.getRequestDispatcher("/"+urlLogin);
			dispatcher.forward(request, response);
		}else{
			System.out.println("Se ha pasado el filtro de sesion");
		}
	}
}
 
	