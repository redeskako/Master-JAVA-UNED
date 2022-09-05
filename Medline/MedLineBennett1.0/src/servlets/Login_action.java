package servlets;
/**
 * Servlet Login_action
 * Comprueba el nombre de usuario y la contraseña introducida en el formulario index.jsp
 * Si son validos genera la sesion, crea la cookie y pasa a la vista menu.jsp
 * Si no son validos devuelve a la vista index.jsp con una mensaje
 * 
 */

import beans.Usuario;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Codificador;
import utils.CryptWithMD5;
import utils.CryptWithSHA512;


/**
 * Servlet implementation class login_action
 */
public class Login_action extends HttpServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_action() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Metodo doGet que llama al metodo doService
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			this.doService(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Metodo doPost que llama al metodo doService
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			this.doService(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo request que recibe los parametros recogidos del formulario de index.jsp, comprueba si son validos, 
	 * redirigiendo a menu.jsp o a index.jsp si son inválidos.
	 * @throws NoSuchAlgorithmException 
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException{
		String sUsuario = request.getParameter("txtUsuario");
		String sPwd = request.getParameter("txtPwd");

		if (sUsuario != null && sPwd != null){ //No han metido usuarios 'vacios'
			// Â¡Â¡Â¡Falta acceso a la b.d. para consultar el login
			//Suponemos que se valida la entrada con la base de datos
			
			/**
			 * NOTA: Para que funcione este cifrado la contraseña de la bbdd y 
			 * la que introduzca el usuario tienen que estar cifradas con el mismo
			 * algoritmo. En este caso hemos cambiado el obsoleto MD5 por SHA-512.
			*/
			//https://dev.mysql.com/doc/refman/5.5/en/encryption-functions.html#function_md5
			//String sPwdCifrado = CryptWithMD5.cryptWithMD5(sPwd);
			//System.out.println("usuario introducido es" + sUsuario);
			
			// Ciframos la contraseña con el algoritmo SHA-512
			String sPwdCifrado = Codificador.getEncoded(sPwd, "SHA-512");
			//System.out.println("la clave cifrada es: " +sPwdCifrado);
			
			//para trabajar con cifrado
			Usuario usu = new Usuario().validarUsuario(sUsuario, sPwdCifrado); //El controlador llama a un javabean para que gestione el usuario.
			
						
			//para trabajar sin cifrado
			//Usuario usu = new Usuario().validarUsuario(sUsuario, sPwd);//añadido en pruebas para poder trabajar sin cifrado
			
			// Si usuario es null es que no se ha validado....
			if (usu != null){
				//El usuario es un usuario vÃ¡lido
				System.out.println(usu.usuario()); //Imprim en consola el usuario
				//COOKIE
				Cookie miCookie = new Cookie("nick",sUsuario); //Crea la cookie
				miCookie.setMaxAge(15*60);
				response.addCookie(miCookie);
				
				HttpSession session = request.getSession(); //Genera la sesion. Importante para mantener el control de usuario en la web
				session.setAttribute("IdUsuario",usu.usuario()); //VARIABLE DE SESION el usuario
				//El atributo lo usarÃ© a posteriori para gestionar que existe sesiÃ³n.
				response.sendRedirect("menu.jsp"); // Cuando se ha validado y todo ok pasa a redirigir la vista menu.jsp.
				System.out.println("creada la cookie y la variable de sesion desde login_action.java"); //Manda mensaje a consola de que todo ok
			}else{
				System.out.println("El usuario no existe desde login_action.java"); //No se ha validado usuario 
				response.sendRedirect("index.jsp?estado=invalido"); //Redirijo a index.jsp con el estado 'invalido' para que index lo procese.
			}
		}
	}
}