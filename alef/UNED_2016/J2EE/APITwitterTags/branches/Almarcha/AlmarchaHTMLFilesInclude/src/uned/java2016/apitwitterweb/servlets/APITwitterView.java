/** Controlador de la parte de cliente
 * @ autor equipo UNED 2016
 * @ version 1.0
 * @ fecha 2016/05/12
 * @ licencia
 */
package uned.java2016.apitwitterweb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uned.java2016.apitwitter.dao.ClinicalStudy;
import uned.java2016.apitwitter.dao.ClinicalStudyDAOImpl;
import uned.java2016.apitwitter.dao.ConnDAOImpl;
import uned.java2016.apitwitter.dao.HashtagAdm;
import uned.java2016.apitwitter.dao.HashtagAdmDAOImpl;
import uned.java2016.apitwitter.dao.NeighborsDAOImpl;
import uned.java2016.apitwitter.dao.Tweet;
import uned.java2016.apitwitter.dao.TweetsDAOImpl;

/**
 * Servlet implementation class APITwitterView
 */
/* @WebServlet("/ViewIndex.jsp")*/
public class APITwitterView extends HttpServlet {
	/** Atributos */
		/** Constantes */
		/** Variables */
	private HashtagAdmDAOImpl miHashtagAdmDAOImpl;
	ArrayList<HashtagAdm> miListaHashtagAdm;
	ArrayList<String> miListaHashtags;
	private TweetsDAOImpl miTweetsDAOImpl;
	private ArrayList<Tweet> misTweets;
	private ArrayList<String> miListaVecinos;
	private NeighborsDAOImpl miNeighborsDAOImpl;
	private String hayErrorenPrimero;
	private String quedanMas;
	private ArrayList<ClinicalStudy> misEstudios;
	private int primero;
	private int cuantos;
	private int pagina;
	private ClinicalStudyDAOImpl miClinicalStudyDAOImpl;
	
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APITwitterView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
	/** El método doService hace tres tareas, como controlador que es conecta con la BBDD
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
		String queHashtag=request.getParameter("queHashtag");
		String queEnlace=request.getParameter("enlace");

		if(queEnlace!=null){
			switch(queEnlace){
				case "Tweets":
					if(request.getParameter("primero")==null){
						primero=1;
					}
					else{
						primero=Integer.parseInt(request.getParameter("primero"));
					}
					if(request.getParameter("cuantos")==null){
						cuantos=5;
					}
					else{
						cuantos=Integer.parseInt(request.getParameter("cuantos"));
					}
					if(request.getParameter("pagina")==null){
						pagina=0;
					}
					else{
						pagina=Integer.parseInt(request.getParameter("pagina"));
					}
					cargarTweets(request,response,queHashtag,primero,cuantos);
					cargarHashtags(request,response);
					request.setAttribute("miListaTweets", misTweets);
					request.setAttribute("miListaHashtagsHTML", miListaHashtags);
					request.setAttribute("queHashtagElegido", queHashtag);
					request.setAttribute("quePrimeroElegido", String.valueOf(primero));
					request.setAttribute("queCuantosElegido", String.valueOf(cuantos));
					request.setAttribute("quePagina", String.valueOf(pagina));
					request.setAttribute("hayErrorenPrimero",hayErrorenPrimero);
					getServletContext().getRequestDispatcher("/ViewIndex.jsp").forward(request,response);
					break;
				case "Vecinos":
					cargarVecinos(request,response,queHashtag);
					cargarHashtags(request,response);
					request.setAttribute("Neighbors", miListaVecinos);
					request.setAttribute("miListaHashtagsHTML", miListaHashtags);
					request.setAttribute("queHashtagElegido", queHashtag);
					getServletContext().getRequestDispatcher("/ViewIndex.jsp").forward(request,response);
					break;
				case "Estudios":
					if(request.getParameter("primero")==null){
						primero=1;
					}
					else{
						primero=Integer.parseInt(request.getParameter("primero"));
					}
					if(request.getParameter("cuantos")==null){
						cuantos=5;
					}
					else{
						cuantos=Integer.parseInt(request.getParameter("cuantos"));
					}
					if(request.getParameter("pagina")==null){
						pagina=0;
					}
					else{
						pagina=Integer.parseInt(request.getParameter("pagina"));
					}
					cargarEstudios(request,response,queHashtag,primero,cuantos);
					cargarHashtags(request,response);
					request.setAttribute("Estudios", misEstudios);
					request.setAttribute("miListaHashtagsHTML", miListaHashtags);
					request.setAttribute("queHashtagElegido", queHashtag);
					request.setAttribute("quePrimeroElegido", String.valueOf(primero));
					request.setAttribute("queCuantosElegido", String.valueOf(cuantos));
					request.setAttribute("quePagina", String.valueOf(pagina));
					request.setAttribute("hayErrorenPrimero",hayErrorenPrimero);
					getServletContext().getRequestDispatcher("/ViewIndex.jsp").forward(request,response);
					break;
			}
		}
		else{
			cargarHashtags(request,response);
			request.setAttribute("miListaHashtagsHTML", miListaHashtags);
			getServletContext().getRequestDispatcher("/ViewIndex.jsp").forward(request,response);
		}
	}
	/** El método cargarHashtags se encarga de cargar los hashtags del combo
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out void
	 * @ error 
	 */
	public void cargarHashtags(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.miListaHashtags=new ArrayList<String>();

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		
		miHashtagAdmDAOImpl=new HashtagAdmDAOImpl(miBd.getConnection());
		try {
			miListaHashtags=miHashtagAdmDAOImpl.selectHashtagIds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		miBd.cerrarConexion();

	}
	/** El método cargarTweets se encarga de cargar los Tweets que contengan el hashtag elegido 
	 *  por el usuario
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * 		String queHashtag
	 * 		int primero
	 * 		int cuantos
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out void
	 * @ error 
	 */
	protected void cargarTweets(HttpServletRequest request, HttpServletResponse response,String queHashtag,int primero,int cuantos) throws ServletException, IOException{
		this.misTweets=new ArrayList<Tweet>();

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		
		miTweetsDAOImpl=new TweetsDAOImpl(miBd.getConnection());
		misTweets=miTweetsDAOImpl.leerTweetsconHashtagDeterminado(queHashtag,primero,cuantos);
		hayErrorenPrimero=miTweetsDAOImpl.getErrorenPrimero();
		//Vamos a sacar por consola a ver que pasa, funciona !!!!!
		/*for(int i=0;i<misTweets.size();++i){
			System.out.print(i+"  "+misTweets.get(i).getId());
			System.out.print("  "+misTweets.get(i).getText());
			System.out.print("  "+misTweets.get(i).getUser().getName());
			System.out.println("  "+misTweets.get(i).getUrl().getUrl());
		}*/
		
		miBd.cerrarConexion();
	}
	
	/** El método cargarVecinos se encarga de cargar hashtags vecinos a partir de uno dado
	 *  por el usuario
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * 		String queHashtag
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out void
	 * @ error 
	 */
	protected void cargarVecinos(HttpServletRequest request, HttpServletResponse response,String queHashtag) throws ServletException, IOException{
		this.miListaVecinos=new ArrayList<String>();

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		
		miNeighborsDAOImpl=new NeighborsDAOImpl(miBd.getConnection());
		miListaVecinos=miNeighborsDAOImpl.selectNeighbors(queHashtag);
		//Vamos a sacar por consola a ver que pasa, funciona!!!!!
		/*for(int i=0;i<miListaVecinos.size();++i){
			System.out.println(i+"  "+miListaVecinos.get(i));
		}*/
		
		miBd.cerrarConexion();
	}
	
	/** El método cargarEstudios se encarga de cargar los estudios clínicos relacionados con el 
	 *  hashtag que haya seleccionado el usuario
	 * 		HttpServletRequest request
	 * 		HttpServletResponse response
	 * 		String queHashtag
	 * @throws IOException 
	 * @throws ServletException 
	 * @ out void
	 * @ error 
	 */
	protected void cargarEstudios(HttpServletRequest request, HttpServletResponse response,String queHashtag,int primero,int cuantos) throws ServletException, IOException{
		this.misEstudios=new ArrayList<ClinicalStudy>();

		ConnDAOImpl miBd = new ConnDAOImpl();
		miBd.abrirConexion(); 
		miClinicalStudyDAOImpl=new ClinicalStudyDAOImpl(miBd.getConnection());
		//Utiliza otro sistema, no offset sino min_max
		misEstudios=miClinicalStudyDAOImpl.selectClinicalStudies(queHashtag,primero,cuantos);
		/*hayErrorenPrimero=miTweetsDAOImpl.getErrorenPrimero();
		quedanMas=miTweetsDAOImpl.getQuedanMas();*/
		//Vamos a sacar por consola a ver que pasa, funciona oh yeha!!!!!
		/*for(int i=0;i<misEstudios.size();++i){
			System.out.println(i+"  "+misEstudios.get(i).getNctId());
			System.out.println("  "+misEstudios.get(i).getBriefTitle());
			System.out.println("  "+misEstudios.get(i).getBriefSummary());
			System.out.println("  "+misEstudios.get(i).getOfficialTitle());
			System.out.println("  "+misEstudios.get(i).getDetailedDescription());
			System.out.println("  "+misEstudios.get(i).getStudyDesign());
			System.out.println("  "+misEstudios.get(i).getPrimaryOutcomeMeasure());
			System.out.println("  "+misEstudios.get(i).getOverallStatus());
			System.out.println("  "+misEstudios.get(i).getVerificationDate());
			System.out.println("  "+misEstudios.get(i).getLastChangedDate());
			System.out.println("  "+misEstudios.get(i).getFirstReceivedDate());
			System.out.println("  "+misEstudios.get(i).getLocationFacilityFame());
			System.out.println("  "+misEstudios.get(i).getOrganization());
			System.out.println("  "+misEstudios.get(i).getHashtags());
			System.out.println("  "+misEstudios.get(i).getSecondaryOutcomes());
			System.out.println("  "+misEstudios.get(i).getConditions());
			System.out.println("  "+misEstudios.get(i).getKeywords());
			System.out.println("  "+misEstudios.get(i).getResponsibleParty());
			System.out.println("  "+misEstudios.get(i).getIntervention());
		}*/
		
		miBd.cerrarConexion();
	}
}
