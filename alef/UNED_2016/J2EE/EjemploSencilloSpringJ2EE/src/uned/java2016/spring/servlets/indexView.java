
package uned.java2016.spring.servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uned.java2016.spring.dao.Camisa;
import uned.java2016.spring.dao.Pantalones;

public class indexView extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	private ApplicationContext context;
	
	private String mensajePantalonesColor;
	private String mensajePantalonesTalla;
	private String mensajePantalonesPrecio;
	private String mensajeCamisaColor;
	private String mensajeCamisaTalla;
	private String mensajeCamisaPrecio;

    public indexView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			doService(request,response);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			doService(request,response);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException
	{		
		context = new ClassPathXmlApplicationContext("beans.xml");

	    Pantalones misPantalonesSpring = (Pantalones) context.getBean("pantalones");
	    mensajePantalonesColor=misPantalonesSpring.mensajeColorPrenda();
	    mensajePantalonesTalla=misPantalonesSpring.mensajeTallaPrenda();
	    mensajePantalonesPrecio=misPantalonesSpring.mensajePrecioPrenda();
	    
	    Camisa miCamisaSpring = (Camisa) context.getBean("camisa");
	    mensajeCamisaColor=miCamisaSpring.mensajeColorPrenda();
	    mensajeCamisaTalla=miCamisaSpring.mensajeTallaPrenda();
	    mensajeCamisaPrecio=miCamisaSpring.mensajePrecioPrenda();
	    
	    request.setAttribute("mensajePantalonesColorHTML",mensajePantalonesColor);
	    request.setAttribute("mensajePantalonesTallaHTML",mensajePantalonesTalla);
	    request.setAttribute("mensajePantalonesPrecioHTML",mensajePantalonesPrecio);
	    request.setAttribute("mensajeCamisaColorHTML",mensajeCamisaColor);
	    request.setAttribute("mensajeCamisaTallaHTML",mensajeCamisaTalla);
	    request.setAttribute("mensajeCamisaPrecioHTML",mensajeCamisaPrecio);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
