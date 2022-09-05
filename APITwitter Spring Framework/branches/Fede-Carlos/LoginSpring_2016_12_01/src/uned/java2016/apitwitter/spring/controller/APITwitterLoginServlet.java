package uned.java2016.apitwitter.spring.controller;



	import java.util.List;
	import java.io.IOException;
	import java.io.PrintWriter;

	import javax.naming.NamingException;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	import javax.servlet.http.Cookie;

	import uned.java2016.apitwitter.dao.UsuariosDAOImpl;
	import uned.java2016.apitwitter.dao.ConnDAOImpl;


     import org.springframework.beans.factory.annotation.Autowired;
	 import org.springframework.stereotype.Controller;
	 import org.springframework.web.bind.annotation.RequestMapping;
	


	/**
	 * Servlet para atender el acceso a la aplicación desde Spring.
	 * Se instancia en el aaranque de la aplicación.
	 *
	 * @author	Java UNED 2016
	 * @version	Recursos 1.0
	 * @fecha 	Noviembre- 2016
	 */
	@Controller
	public class APITwitterLoginServlet {
		 
		private static final long serialVersionUID = 1L;
		
		@Autowired
		private ConnDAOImpl conexion;
		@Autowired
	    private UsuariosDAOImpl u;
		
	    public APITwitterLoginServlet() {
	        super();
	        System.out.println("Instanciamos SERVLET CONTROLLER APITwitterLoginServlet SPRING");
	    }

	    @RequestMapping("APITwitterLogin")
	    
	    
        public String Login(HttpServletRequest request, HttpServletResponse response)throws IOException{
	    	
			HttpSession sesion;  

		    response.setContentType("text/html");
		    String operation = request.getParameter("operacion");
		    
		  System.out.println("Se requiere atención al Login y usamos SERVLET CONTROLLER APITwitterLoginServlet SPRING");  
		    // Login.
		    if (operation.equals("login"))
		   {
		    	  	
		    	String sUsuario =request.getParameter("txtUsuario");  		    
		        String sPassword =request.getParameter("txtPassword");
		        
		     // Si usuario y contraseña no vacíos: 	
				if (sUsuario!=null && sPassword!=null)
				{
					// Se crea un usuario como respuesta de comprobar los valores enviados 
					// (se comprueba en BBDD)
					
					conexion.abrirConexion();
					u.setConnection(conexion.getConnection()); 
					
					if (u.getRol(sUsuario, sPassword) != null){
						
						// Se crea la cookie:
						Cookie miCookie = new Cookie("idCookie", sUsuario);
						miCookie.setMaxAge(15*60);
						response.addCookie(miCookie);
						
						// Se inicia la sesión:
						HttpSession session = request.getSession();
						session.setAttribute("idSesion", sUsuario);
						
						// Se almacena el rol del usuario en la sesión
						String rol = u.getRol(sUsuario, sPassword);
						session.setAttribute("rolSesion", rol);
			
						conexion.cerrarConexion();
						// Se carga la vista administrador/Usuario en función del rol
						if (rol.equals("adm")){
							System.out.println("Usuario Administrador: Control fuera de Spring.");
							return ("AdminIndex");
						} else {
							System.out.println("Usuario Visualizador: Control fuera de Spring.");
							return ("ViewIndex");
						}
					}
					else {
						// Si el usuario no aparece en la base de datos:
						System.out.println("Usuario no válido: usuario no registrado.");
						conexion.cerrarConexion();
						return ("forward:/index.jsp?estado=invalido");
					}
					//conexion.cerrarConexion();
				}
				
			}
			// Logout.
			else if (operation.equals("logout"))
			{
				if (operation.equals("logout"))
			    {  		
			    	sesion = request.getSession(); 
			        sesion.invalidate(); 
			        return("index");
			    }
				    
			    //Si no se han rellenado los campos del formulario de acceso:
				else 
				{
					return("index");
				}
			}
		    //Intento de acceso no logado.
			else if (operation.equals("sinsesion"))
			//else if (estado.equals("ilegal"))
			{	
				System.out.println("Acceso ilegal.");
				return ("forward:/index.jsp?estado=ilegal");
			}
		    return ("forward:/index.jsp?estado=ilegal");
			
		}	
	}
