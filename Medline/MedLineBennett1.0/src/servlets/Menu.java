package servlets;

import beans.Usuario;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login_action
 */
public class Menu extends HttpServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String elegir = request.getParameter("tipoDB");
		String health = request.getParameter("health");
		String bennett = request.getParameter("bennett");
		String hebe = request.getParameter("hebe");
		String importar = request.getParameter("importar");
		//String elegir1 = request.getParameter("tipoDB1");
		//String elegir2 = request.getParameter("tipoDB2");
		//String elegir3 = request.getParameter("tipoDB3");
		//String[] elegir4 = request.getParameterValues("tipoBD");
		//String elegir2 = request.getParameter("hebe");
		//String sPwd = request.getParameter("txtPwd");
		System.out.println(elegir);
		//System.out.println(elegir1);
		//if (elegir == "Healthcare database" ){ //
			// ¡¡¡Falta acceso a la b.d. para consultar el login
		/*
		if (elegir.equals("health")){
			System.out.println("elegir == health");
			response.sendRedirect("medlineView.jsp");
			System.out.println("hoa");
		}else if(elegir.equals("bennett")){
			System.out.println("elegir = bennett");
			response.sendRedirect("bennettView.jsp");
		}else if(elegir.equals("hebe")){
			System.out.println("elegir == hebe");
			response.sendRedirect("mixSearch.jsp");
		}else{
			System.out.println("elegir == importar");
			response.sendRedirect("importar.jsp");
		
		}
		*/
		
		if (health != null){
			System.out.println("elegir == health");
			response.sendRedirect("medlineView.jsp");
			System.out.println("hoa");
		}else if(bennett != null){
			System.out.println("elegir = bennett");
			response.sendRedirect("bennettView.jsp");
		}else if(hebe != null){
			System.out.println("elegir == hebe");
			response.sendRedirect("mixView.jsp");
		}else if(importar != null){
			System.out.println("elegir == importar");
			response.sendRedirect("importar.jsp");
		
		}
					
		}
					
		
		//}
			
		//if (elegir == "bennett" ){ //
				// ¡¡¡Falta acceso a la b.d. para consultar el login
		//		response.sendRedirect("bennettView.jsp");
		//}
				
			
		//if (elegir == "hebe" ){ //
					// ¡¡¡Falta acceso a la b.d. para consultar el login
		//			response.sendRedirect("mixSearch.jsp");
		//}
		/*	
		String sUsuario = request.getParameter("txtUsuario");
		String sPwd = request.getParameter("txtPwd");

		if (sUsuario != null && sPwd != null){ //No han metido usuarios 'vacios'
			// ¡¡¡Falta acceso a la b.d. para consultar el login
			//Suponemos que se valida la entrada con la base de datos
			Usuario usu = new Usuario().validarUsuario(sUsuario, sPwd); //El controlador llama a un javabean para que gestione el usuario.
			// Si usuario es null es que no se ha validado....
			if (usu != null){
				//El usuario es un usuario válido
				System.out.println(usu.usuario()); //Imprimo en consola el usuario
				//COOKIE
				Cookie miCookie = new Cookie("nick",sUsuario); //Creo la cookie
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				HttpSession session = request.getSession(); //Genero la sesion. Importante para mantener el control de usuario en la web
				session.setAttribute("IdUsuario",usu.usuario()); //VARIABLE DE SESION el usuario
				//El atributo lo usaré a posteriori para gestionar que existe sesión.
				response.sendRedirect("medlineView.jsp"); // Cuando se ha validado y todo ok paso a redirigir la vista bennettView.jsp.
				System.out.println("creada la cookie y la variable de sesion desde login_action.java"); //Mando mensaje a consola de que todo ok
			}else{
				System.out.println("El usuario no existe desde login_action.java"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=invalido"); //Redirijo a index.jsp con el estado 'invalido' para que index lo procese.
			}
		}
		*/
	
		
		
	
}