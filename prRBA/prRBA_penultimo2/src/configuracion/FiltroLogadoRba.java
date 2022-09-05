package configuracion;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class FiltroLogadoRba implements Filter{
	public void destroy(){

	}
	public void init(FilterConfig cfg){

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			              FilterChain chain) throws IOException, ServletException{
		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)){
			//Verificamos si se ha logado correctamente.
			HttpServletRequest r= (HttpServletRequest) request;
			HttpServletResponse respond= (HttpServletResponse) response;
			HttpSession sesion= r.getSession();
			if ((sesion.getAttribute("comprobarSesion")==null)){
				respond.sendRedirect("index.jsp?valido=-4");
			}else{
				chain.doFilter(request, response);
			}
		}
	}
}