package org.Filtros;

import org.Otros.*;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.util.Date;


/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/Filtro")
public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {
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
	
		
		String ip = null; // IP del cliente

		Date fecha = new Date ();
		EmailUtility enviar=new EmailUtility();


		
		String asunto="Conexion a las " + fecha.toLocaleString();
		try {

            String contenido="El Usuario Conectado ha Accedido a Recursos";
			enviar.sendEmail(asunto, contenido);
		}
		catch (Exception e)
		{
			e.getStackTrace();
			System.out.println("Error envio Email: "+ e.getStackTrace().toString());
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
