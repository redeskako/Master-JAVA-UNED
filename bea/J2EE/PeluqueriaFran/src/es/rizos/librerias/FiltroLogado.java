package es.rizos.librerias;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class FiltroLogado implements Filter{
	public void destroy(){

	}
	public void init(FilterConfig cfg){

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			              FilterChain chain) throws IOException, ServletException{
			if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)){
			//Verificar si ha hecho un correcto logado.
				HttpServletRequest r= (HttpServletRequest) request;
				HttpServletResponse respond= (HttpServletResponse) response;
				HttpSession sesion= r.getSession();
				if ((sesion.getAttribute("AtributoSesionIniciada")==null)){
					respond.sendRedirect("Bienvenida.jsp?valido=-4");
				}else{
					chain.doFilter(request, response);
				}
			}
	}
}