package org.Filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FiltroTiempoCarga
 */
@WebFilter("/FiltroTiempoCarga")
public class FiltroTiempoCarga implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroTiempoCarga() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		long tiempo_inicio=System.currentTimeMillis();
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		long tiempo_final=System.currentTimeMillis();
		
		System.out.println("Tiempo de Carga de la Pagina:  "+(tiempo_final-tiempo_inicio));
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
