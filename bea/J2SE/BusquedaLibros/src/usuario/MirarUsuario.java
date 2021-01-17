package usuario;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;



/**
 * Servlet implementation class for Servlet: MirarUsuario
 *
 */
 public class MirarUsuario extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MirarUsuario() {
		super();
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String pass = request.getParameter("pass").toString();
			String user = request.getParameter("user").toString();

			// Comprobacion que introducido algun dato
			if (pass.equals("") || user.equals(""))
			{
				HttpSession sesion = request.getSession(true);
				sesion.setAttribute("EstadoSesion","Rellenar correctamente el formulario");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp"));
			}else
			{

				boolean salida=this.existe_usuario(user, pass);
				if (salida){
					HttpSession sesion = request.getSession(true);
					sesion.setAttribute("EstadoSesion","Logado");

				// Todo correcto se va a listado libros

					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/libros/listaLibros.jsp"));
				}
				else
				{
					HttpSession sesion = request.getSession(true);
					sesion.setAttribute("EstadoSesion","Error:usuario no esta en base de datos");
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp")); }
			}


	}catch(Exception e){
		HttpSession sesion = request.getSession(true);
		sesion.setAttribute("EstadoSesion","Error:usuario no valido");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath () + "/index.jsp"));
		}


	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doService(request, response);
	}


	private  boolean existe_usuario(String user,String pass){
		Connection conn=null;
		Statement stm= null;
		boolean comprueba = false;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://pi-/autores", "autor", "autor");
			stm= conn.createStatement();
			String  sql = "SELECT * from usuario WHERE Usuario = '"+user+"' and Password ='"+pass+"'";
			ResultSet rst= stm.executeQuery(sql);

			while (rst.next()){
				comprueba = true;
			}

			stm.close();

		}catch(SQLException err){
			//throw new LibreriaException("Error consulta."+err);
			throw new UsuarioExcepcion("Error consulta.");
		}catch(Exception err){
			//throw new LibreriaException("Error indefinido."+err);
			throw new UsuarioExcepcion("Error en la base de datos.");
		}finally{
			if (conn!=null){
				try{
					conn.close();
				}catch(Exception e){}
			}
		}
		return comprueba;

	}
}