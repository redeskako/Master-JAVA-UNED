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
	 import org.springframework.web.servlet.ModelAndView;
	 import org.springframework.stereotype.Controller;
	 import org.springframework.web.bind.annotation.RequestMapping;
	 import org.springframework.web.bind.annotation.*;
	 import org.springframework.ui.*;


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
	@Controller
	public class APITwitterLoginServlet2 {
		 
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public APITwitterLoginServlet2() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	    //@RequestMapping("/index.jsp")
	    @RequestMapping("APITwitterLogin2")
	    //@ResponseBody
		/*protected void Login(@RequestParam("operation") String operation,
				@RequestParam("txtUsuario") String sUsuario,
				@RequestParam("txtPassword") String sPassword
				) {*/
	    public String Login(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws IOException{
			
			HttpSession sesion;  

		    RequestDispatcher requestDispatcher; 
		   // PrintWriter out = response.getWriter();  

		    response.setContentType("text/html");
		    String operation = request.getParameter("operacion");
		    
		    String ErrorAcceso = null;
		  System.out.println("SPRINGGGGGGGGGGGGG");  
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
					
					ConnDAOImpl conexion = new ConnDAOImpl();
					conexion.abrirConexion();
					UsuariosDAOImpl u = new UsuariosDAOImpl(conexion.getConnection());
					
					
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
							//response.sendRedirect("AdminIndex.jsp");
							//return ("AdminIndex.jsp");
							return ("AdminIndex");
						} else {
							//response.sendRedirect("ViewIndex.jsp");
							//return ("ViewIndex.jsp");
							return ("ViewIndex");
						}
					}
					else {
						// Si el usuario no aparece en la base de datos:
						System.out.println("Usuario no válido: usuario no registrado.");
						//requestDispatcher = request.getRequestDispatcher("/index.jsp?estado=invalido");
						conexion.cerrarConexion();
						model.addAttribute("estado", "invalido");
						return("index");
						//return("/index.jsp?estado=invalido");
						//requestDispatcher.forward(request, response);
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
			        //response.sendRedirect("index.jsp?estado=logout");
			        model.addAttribute("estado", "logout");
			        return("index");
			    }
				    
			    //Si no se han rellenado los campos del formulario de acceso:
				else 
				{
					model.addAttribute("estado", "invalido");
					return("index");
				   	//response.sendRedirect("index.jsp?estado=invalido");
				}
			}
		    //Intento de acceso no logado.
			else if (operation.equals("sinsesion"))
			{	
				model.addAttribute("estado", "ilegal");
				return("index");
			    //response.sendRedirect("index.jsp?estado=ilegal"); 
			}
		    //return ("/index.jsp");
		    return ("index");
			
		}	
	}
