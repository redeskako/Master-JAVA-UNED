package uned.java2016.apitwitterweb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

import au.com.bytecode.opencsv.CSVReader;

import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.HashtagAdm;
import uned.java2016.apitwitter.dao.HashtagAdmDAOImpl;

import java.io.*;

/**
 * Servlet implementation class APITwitterAdm
 */
/*@WebServlet("/AdmIndex.jsp")*/

public class APITwitterAdm extends HttpServlet {
	
	/** Constantes */
	/** Variables */
	private HashtagAdmDAOImpl miHashtagAdmDAOImpl;
	ArrayList<HashtagAdm> miListaHashtagAdm;
	ArrayList<String> miListaHashtags; 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APITwitterAdm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{   
		    doService(request, response);
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{   
		    doService(request, response);
			
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	
	/** El método sdoService hace tres tareas, como controlador que es conecta con la BBDD
	 * crea el objeto que realizará la consulta y desconecta la BBDD así mantenemos el 
	 * modelo conectado ya que se independiza la conexión y desconexión de la consulta
	 * @ in 
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * @ out void
	 * @ error   throws ServletException, IOException, NamingException si hay tiempo
	 *   se podrían personalizar en bloque try catch
	 */
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException
	{
		//Esta parte de código atiende los botonazos del administrador.
		String tipo = request.getParameter("boton");
		
		//Creamos un objeto Hashtag:
		//HashtagAdm Hash = new HashtagAdm();

		//Hay que incluir una lectura de sesión!!!!!
		
		if (tipo!=null){
		
		    switch (tipo) {
		        //Si la actuación ha sido pulsar el botón Eliminar Hashtag.
			    case "Eliminar":
					// Obtenemos los datos del request:
			    	String eliminaid = request.getParameter("buscadorHastags");
			        //Se elimina el Hashtag
				    eliminar(eliminaid);
			        System.out.println("Se ha eliminado el hashtag "+eliminaid);
				break;
				
			    case "Sumar":
			        //Añadir nuevo Hastag
			    	String sumaid = request.getParameter("txt_sumar");
	    	        //Se llama al método Añadir Hashtag.
				    sumar(sumaid);
			        System.out.println("Se ha añadido un nuevo hashtag a consultar "+sumaid);
				break;
			    case "Fichero":
                               //Añadir los Hastag que proceden del fichero.csv.
				String obtfich = request.getParameter("txt_fichero");
    	                       //Se llama al método lectura de fichero.
			       lecturafich(new File(obtfich));
		               System.out.println("Se procede a leer los hashtags del fichero "+obtfich);
				break;

			default:
				break;
			}
		} 
		
		//Esta parte recupera la información de los hashtags administrados desde BD.
		//Porque siempre se pinta en el XHTML.
		String queHashtag=request.getParameter("queHashtag");
		
		// Cargamos los valores del combo.
		
		//cargarHashtags(request,response);
		cargarHashtags();
		request.setAttribute("miListaHashtagsHTML", miListaHashtags);
		request.setAttribute("miTablaHashtagsHTML", miListaHashtagAdm);
		getServletContext().getRequestDispatcher("/AdminIndex.jsp").forward(request,response);
		
		
		
	}
	
	/** El método cargarHashtags se encarga de cargar los hashtags del combo
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out void
	 * @ error 
	 */
//	public void cargarHashtags (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	public void cargarHashtags () {
		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		HashtagAdmDAOImpl hashtagadmbd =new HashtagAdmDAOImpl(miBd.getConnection());


		//this.miListaHashtags=new ArrayList<String>();

		try {
			this.miListaHashtags= hashtagadmbd.selectHashtagIds();
            this.miListaHashtagAdm = hashtagadmbd.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		miBd.cerrarConexion();	
	}

        private void eliminar(String unhashtag){
		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		HashtagAdmDAOImpl hashtagadmbd =new HashtagAdmDAOImpl(miBd.getConnection());
		try {
			hashtagadmbd.deleteHahstagAdm(unhashtag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                miBd.cerrarConexion();
	}
     

        private void sumar(String unhashtag){
		ConnDAOImpl miBd = new ConnDAOImpl();
		HashtagAdm hashtag = new HashtagAdm(unhashtag,"adm",new Date());
		miBd.abrirConexion(); 
		HashtagAdmDAOImpl hashtagadmbd =new HashtagAdmDAOImpl(miBd.getConnection());
		try {
			hashtagadmbd.insertHashtagAdm(hashtag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                miBd.cerrarConexion();
	}

        private void lecturafich(File ruta) throws IOException{
	        try 
		{
		   CSVReader reader = null;
		   //List<String[]> fileContents = new ArrayList<String[]>();
		   if (ruta.exists()) {  
		       //Establecemos la conexión con BBDD
		       ConnDAOImpl miBd = new ConnDAOImpl();
		       miBd.abrirConexion(); 
		       HashtagAdmDAOImpl hashtagadmbd =new HashtagAdmDAOImpl(miBd.getConnection());
		       //Obtener la instancia CSVReader especificando el delimitador para ser utilizado
		       reader = new CSVReader(new FileReader(ruta),'#');
		       String [] nextLine;
		       while((nextLine = reader.readNext()) != null) {
		           for(String token : nextLine){
			       //Print all tokens
			       System.out.println(token);
			       HashtagAdm hashtag = new HashtagAdm(token,"adm",new Date());
			       hashtagadmbd.insertHashtagAdm(hashtag);   
			   }
		       }
		       miBd.cerrarConexion();
		    }
		    else {
			   throw new FileNotFoundException("No se ha especificado el nombre del fichero " + ruta.getName() + " en la ruta " + ruta.getAbsolutePath());
		   }
                } catch (Exception e) {
		    e.printStackTrace();
	        }
        }



}
