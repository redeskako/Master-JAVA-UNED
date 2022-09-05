package uned.java2016.spring.servlets;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uned.java2016.spring.entidad.GrupoJava2016;


public class indexView extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	private List<GrupoJava2016> miLista;

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
		EntityManagerFactory emfactory=Persistence.createEntityManagerFactory("PruebaJPA_JPQL");
	    EntityManager entitymanager=emfactory.createEntityManager();
	    
	    String queOperacion=request.getParameter("operacion");
	    switch(queOperacion){
	    	case "MostrarTodos": 
	    		Query query=entitymanager.createNamedQuery("Mostrar todos");
	    		 miLista=query.getResultList();
	    		break;
	    	case "MostrarMenores95": 
	    		Query query2=entitymanager.createNamedQuery("Mostrar menores de");
	    		query2.setParameter("edad",95);
	    		miLista=query2.getResultList();
	    		break;
	    }
 
	    /*for(GrupoJava2016 i:miLista)
	    {
	    	System.out.print("ID: "+i.getId());
	    	System.out.print("\t Nombre:"+i.getNombre());
	    	System.out.print("\t Rol:"+i.getRol());
	        System.out.println("\t Edad:"+i.getEdad());
	    }*/
	    
	    request.setAttribute("miListaHTML",miLista);
	    getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
	}
}
