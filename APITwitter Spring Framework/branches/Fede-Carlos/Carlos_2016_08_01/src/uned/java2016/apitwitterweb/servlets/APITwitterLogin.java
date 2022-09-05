package uned.java2016.apitwitterweb.servlets;

import java.util.List;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import uned.java2016.apitwitter.dao.UsuariosDAOImpl;


/**
 * Servlet implementation class APITwitterLogin.
 * Se ejecuta cuando el cliente envía el formulario de acceso a la aplicación.
 * Si el formulario se ha cumplimentado, se comprueba si existe un usuario con 
 * esos nombre de usuario y contraseña en la base de datos. 
 * Si es así, se accede en calidad de administrador o de usuario, según sea su rol.
 *
 * @author	Ignacio UNED 2016
 * @version	Recursos 1.0
 * @fecha 	Mayo- 2016
 */
/*@WebServlet("/index.jsp")*/
public class APITwitterLogin extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	
	private List<UsuariosDAOImpl> miLista;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APITwitterLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Get.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("ServedLOGIN at: ").append(request.getContextPath());
    	this.doService(request, response);
	}

	/**
	 * Método que se ejecuta si se recibe una petición de tipo Post.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doService(request, response);
	}
	

	protected void doService(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession sesion;  

	    RequestDispatcher requestDispatcher; 
	   // PrintWriter out = response.getWriter();  

	    response.setContentType("text/html");
	    String operation = request.getParameter("operacion");

	    String papel=null;
	    
	    // Login.
	    if (operation.equals("login"))
	    {
	    	  	
	    	String sUsuario=request.getParameter("txtUsuario");  		    
	        String sPassword=request.getParameter("txtPassword");
	        
	     // Si usuario y contraseña no vacíos: 	
			if (sUsuario!=null && sPassword!=null)
			{				
				/* AQUI COMIENZA EL JPA-JPQL */
				EntityManagerFactory emfactory=Persistence.createEntityManagerFactory("APITwitterSpring_Carlos_V00");
			    EntityManager entitymanager=emfactory.createEntityManager();

			    Query query=entitymanager.createNamedQuery("Buscar");
			    query.setParameter("usuario",sUsuario);
			    query.setParameter("contra",sPassword);
			    miLista=query.getResultList();
			    
			    for(UsuariosDAOImpl i:miLista)
			    {
			    	/*System.out.print("Usuario: "+i.getUser());
			    	System.out.print("\t Contraseña:"+i.getPasswd());
			    	System.out.println("\t Rol:"+i.getRol());*/
			    	papel=i.getRol();
			    }
			    if(papel!=null){
			    	// Se crea la cookie:
					Cookie miCookie=new Cookie("idCookie",sUsuario);
					miCookie.setMaxAge(15*60);
					response.addCookie(miCookie);
					
					// Se inicia la sesión:
					HttpSession session=request.getSession();
					session.setAttribute("idSesion",sUsuario);
					
					// Se almacena el rol del usuario en la sesión
					session.setAttribute("rolSesion",papel);
		
					// Se carga la vista administrador/Usuario en función del rol
					if (papel.equals("adm")){
						response.sendRedirect("AdminIndex.jsp");
					} else {
						response.sendRedirect("ViewIndex.jsp");
					}
			    }
				else{
					requestDispatcher=request.getRequestDispatcher("/index.jsp?estado=invalido");
					requestDispatcher.forward(request,response);
				}	
			/* AQUI FINALIZA EL JPA-JPQL */	
	    	}
		}
		// Logout.
		else if (operation.equals("logout"))
		{
			if (operation.equals("logout"))
		    {  		
		    	sesion = request.getSession(); 
		        sesion.invalidate(); 
		        response.sendRedirect("index.jsp?estado=logout"); 
		    }
			    
		    //Si no se han rellenado los campos del formulario de acceso:
			else 
			{
			   	response.sendRedirect("index.jsp?estado=invalido");
			}
		}
	    //Intento de acceso no logado.
		else if (operation.equals("sinsesion"))
		{
		    response.sendRedirect("index.jsp?estado=ilegal"); 
		}
		
	}	
}