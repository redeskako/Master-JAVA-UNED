package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utiles.YoutubeCrawler;
import beans.Canal;
import beans.Video;
import beans.VistaCanales;
import beans.VistaVideo;
import beans.VistaVideos;
import dao.CanalDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private final int NUM_VIDEOS_POR_PAGINA = 25;
	
    public Controlador()
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			doService(request, response);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}	      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			doService(request, response);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
	}	
	
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException
	{
		HttpSession sesion;  
	    RequestDispatcher requestDispatcher; 
	    PrintWriter out = response.getWriter();  
	    
	    response.setContentType("text/html");
	    String operation = request.getParameter("operacion");
	
	    // Login.
	    if (operation.equals("login"))
	    {
	        String login =request.getParameter("user");  		    
	        String password =request.getParameter("password");
	      
	        UsuarioDao usuario = new UsuarioDao();			

	        // Si la autentificación es correcta.
	        if (password.equals(usuario.getPassword(login)))
	        {
            	sesion = request.getSession();			    
    			sesion.setAttribute("usuario", login);
    			
    			// Si es el usuario administrador.
    			if (login.equals("adm"))
    				requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vista_admin.jsp");
    			else
    				requestDispatcher = request.getRequestDispatcher("Controlador?operacion=lista_canales");
    				
    			requestDispatcher.forward(request, response);	           
		    }
	        
	        // Si la autentificación es fallida.
	        else
	        {		       
			   requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login_error.jsp");
			   requestDispatcher.forward(request, response);
		    }
	    }

	    // Logout.
	    else if (operation.equals("logout"))
	    {  		
	    	sesion = request.getSession(); 
            sesion.invalidate(); 
            response.sendRedirect("./index.jsp"); 
        }
	    
	    // Lista los videos de un determinado canal.
	    else if (operation.equals("lista_videos"))
	    {
	    	// Canal de Youtube sobre el que se va a operar.
	    	String canal = request.getParameter("canal");

	    	// Bean ayudante de controlador.
	    	YoutubeCrawler crawler = new YoutubeCrawler();

	    	// Página a mostrar.
	    	int nPag = 1;
	    	if (request.getParameter("pag") != null)
	    		nPag = Integer.parseInt(request.getParameter("pag"));
	    	else
		        // Inserta en la BD los videos de ese canal que no estén ya incluidos en la misma.
		        crawler.insertaVideosEnBD(canal);

	    	// Bean ayudante de vista.
	    	VistaVideos vista = new VistaVideos();
	    	vista.setNombreCanal(crawler.recuperaCanalDeBD(canal).getNombre());

	        // Recupera los videos de la bd correspondientes a la página y el número total de videos del canal.
	        List<Video> lst = crawler.recuperaVideosDeBD(canal, nPag, NUM_VIDEOS_POR_PAGINA);
	        int nNumVideosCanal = crawler.getNumVideosCanal(canal);

	        // Maqueta los datos de los vídeos y del índice para su presentación.
	        vista.setLista(crawler.getListaVideos(lst));
	        vista.setIndice(crawler.getIndice(canal, nPag, nNumVideosCanal, NUM_VIDEOS_POR_PAGINA));	        
			request.setAttribute("vistaVideos", vista);

			// Muestra la página.
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lista_videos.jsp");
			requestDispatcher.forward(request, response);	           	    	
	    }

	    // Muestra un video.
	    else if (operation.equals("muestra_video"))
	    {
	    	// Id del video.
	    	String id = request.getParameter("id");

	    	// Bean ayudante de controlador.
	    	YoutubeCrawler crawler = new YoutubeCrawler();

	    	// Bean ayudante de vista.
	    	VistaVideo vista = new VistaVideo();
	    	vista.setIdVideo(id);

	    	// Recupera el video a mostrar de la BD.
	    	Video video = crawler.recuperaVideoDeBD(id);
	    	vista.setNombreVideo(video.getTitulo());

	        // Maqueta los datos de los vídeos y del índice para su presentación.
	        vista.setDatosVideo(crawler.getDatosVideo(video));	        
			request.setAttribute("vistaVideo", vista);

			// Muestra la página.
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/muestra_video.jsp");
			requestDispatcher.forward(request, response);	           	    	
	    }

	    // Lista los canales.
	    else if (operation.equals("lista_canales"))
	    {
	    	// Bean ayudante de controlador.
	    	YoutubeCrawler crawler = new YoutubeCrawler();
	    	
	    	// Bean ayudante de vista.
	    	VistaCanales vista = new VistaCanales();

	        // Recupera los canales de la bd.
	        List<Canal> lst = crawler.recuperaCanalesDeBD();

	        // Maqueta los datos de los canales.
	        vista.setLista(crawler.getListaCanales(lst));       
			request.setAttribute("vistaCanales", vista);

			// Muestra la página.
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lista_canales.jsp");
			requestDispatcher.forward(request, response);	           	    	
	    }
	    
	    // Añade canal a BD.
	    else if (operation.equals("anadir_canal"))
	    {
	    	// Recuperamos datos a añadir en BD.
	    	String id = request.getParameter("id");
	    	String nombre = request.getParameter("nombre");
	    	String descripcion = request.getParameter("descripcion");
	    	int longitud = id.length();
	    	
	    	// Control para añadir canales correctos.
	    	if (longitud == 24)
	    	{
	    		// Bean ayudante Canales.
	    		Canal canal = new Canal();
	    		canal.setId(id);
	    		canal.setNombre(nombre);
	    		canal.setDescripcion(descripcion);

	    		// Añade canal a BD.
	    		CanalDao canalDao = new CanalDao();
	    		canalDao.openConnection();
	    		canalDao.insertCanal(canal);
	    		canalDao.closeConnection();
	        
	    		// Muestra la página nuevamente para añadir nuevo canal.
	    		requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vista_admin_anade.jsp");
	    		requestDispatcher.forward(request, response);
	    		
	    	}else{
	    		// Muestra la página de error al añadir canal.
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vista_admin_error.jsp");
				requestDispatcher.forward(request, response);
	    	}

	    }
	    
	    // Dirige a añadir otro canal.
	    else if (operation.equals("anade_otro_canal"))
	    {   
			// Muestra la página nuevamente para añadir nuevo canal.
			requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/vista_admin.jsp");
			requestDispatcher.forward(request, response);
	    }

	    out.close();
	}
}