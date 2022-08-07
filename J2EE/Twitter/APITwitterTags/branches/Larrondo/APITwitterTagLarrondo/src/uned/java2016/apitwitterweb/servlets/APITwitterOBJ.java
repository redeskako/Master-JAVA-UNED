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
import java.util.Objects;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
/**
 * @author carlosAlmarcha
 *
 */
public class APITwitterOBJ extends HttpServlet {
	/** Atributos */
		/** Constantes */
		/** Variables */
	
	private TweetsDAOImpl miTweetsDAOImpl;	
	private Tweet miTweet;
	
	private NeighborsDAOImpl miNeighborsDAOImpl;
	private ClinicalStudy miEstudio;
	private ClinicalStudyDAOImpl miClinicalStudyDAOImpl;
		
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APITwitterOBJ() {
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

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NamingException
	{	
		
		HttpSession session = request.getSession();
		String idSesion;	
		// String  rolSesion = (String)request.getAttribute("rolSesion");
		
		 if (session.getAttribute("idSesion") != null)
		 {
			 idSesion = session.getAttribute("idSesion").toString();
			 System.out.println("IDSESION="+idSesion);
			 
			 //Esperamos peticiones para un tweet o un estudio.
			 //long tweetID = -1;
			 String tweetID = request.getParameter("queTweet");
			 String estudioID = request.getParameter("queEstudio");
			 
			 //Primero vemos si es un tweet.
				
			 if((tweetID !="" )&&(tweetID!=null) && existElement(tweetID,null) )
			 {				
			     request.setAttribute("TweetID", tweetID);	
			     
				 miTweet = cargarTweetConID(Long.parseLong(tweetID));
				 request.setAttribute("tweetInfo", miTweet);
						//request.setAttribute("miListaHashtagsHTML", miListaHashtags);
						
				 getServletContext().getRequestDispatcher("/ObjIndex.jsp").forward(request,response);
						/*System.out.println("TWEET id="+miTweet.getId());
						System.out.println("TWEET user="+miTweet.getUser().getName().toString());
						System.out.println("TWEET user id="+miTweet.getUser().getId());
						System.out.println("TWEET texto="+miTweet.getText());*/
			}
			else if (( estudioID != "")&&(estudioID!=null)&& existElement(null,estudioID))
			{
				 request.setAttribute("ClinicalStudyID", estudioID);
				 miEstudio = GetClinicalStudy(estudioID);
				 request.setAttribute("studioInfo", miEstudio);
				 getServletContext().getRequestDispatcher("/ObjIndex.jsp").forward(request,response);
			}
			 else{
					getServletContext().getRequestDispatcher("/ObjIndex.jsp").forward(request,response);
			}
			 
		} else{
			response.sendRedirect("./index.jsp?estado=invalido");
		    System.out.println("No existe IDSesion es cliente, En esta linea envio a donde se me pide");
		}
	}

	protected boolean existElement (String TweetID, String clicnicalStudyID)
	{
		ConnDAOImpl miBd= new ConnDAOImpl();
		try {
		boolean exists = false;		 
		miBd.abrirConexion();		
		if (TweetID !=null && TweetID !="" )
		{	
			long tweetIDnumber = Long.parseLong(TweetID);
			miTweetsDAOImpl=new TweetsDAOImpl(miBd.getConnection());
			exists = miTweetsDAOImpl.exists(tweetIDnumber); 		
		}
		else
		{
			if ( clicnicalStudyID != null)
			{ 
			 miClinicalStudyDAOImpl = new ClinicalStudyDAOImpl(miBd.getConnection()); 
			 exists = miClinicalStudyDAOImpl.exists(clicnicalStudyID); 
			}
		}
		miBd.cerrarConexion();	
		return exists;
		}
		catch (Exception e)
		{
			if (miBd !=null &&  miBd.estaConectado())
			{
				miBd.cerrarConexion();	
			}
			e.printStackTrace();
			return false;
		}
	}	
	
	protected Tweet cargarTweetConID(long TweetID ){		
		ConnDAOImpl miBd= new ConnDAOImpl();
		Tweet elTweet = null;
		try {
		miBd.abrirConexion();
		miTweetsDAOImpl=new TweetsDAOImpl(miBd.getConnection());
		elTweet = miTweetsDAOImpl.selectTweet(TweetID);
		miBd.cerrarConexion();
		return(elTweet);
		}
		catch (Exception e)
		{
			if (miBd !=null &&  miBd.estaConectado())
			{
				miBd.cerrarConexion();	
			}
			e.printStackTrace();
			return null;
		}
	}
	protected ClinicalStudy  GetClinicalStudy (String clinicalStudyID){		
		ConnDAOImpl miBd= new ConnDAOImpl();
		ClinicalStudy  elClinicalStudy = null;
		try {
		miBd.abrirConexion();
		miClinicalStudyDAOImpl =new ClinicalStudyDAOImpl(miBd.getConnection());
		elClinicalStudy = miClinicalStudyDAOImpl.selectClinicalStudy(clinicalStudyID);
		miBd.cerrarConexion();
		return(elClinicalStudy);
		}
		catch (Exception e)
		{
			if (miBd !=null &&  miBd.estaConectado())
			{
				miBd.cerrarConexion();	
			}
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
