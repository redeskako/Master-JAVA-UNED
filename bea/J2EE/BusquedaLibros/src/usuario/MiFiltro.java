package usuario;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

public class MiFiltro implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession sesion;
		sesion= ((HttpServletRequest) request).getSession();

		if (request instanceof HttpServletRequest){

		    if (sesion.getAttribute("EstadoSesion")!="Logado"){

				sesion.setAttribute("EstadoSesion","Error:Debes iniciar sesion");
		    	((HttpServletResponse)response).sendRedirect("../index.jsp");
		    	}
		}
		chain.doFilter(request,response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

