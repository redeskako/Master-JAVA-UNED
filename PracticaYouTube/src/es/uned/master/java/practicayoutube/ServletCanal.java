package es.uned.master.java.practicayoutube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


import javax.servlet.http.HttpSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;

/**
 * Servlet implementation class ServletCanal
 * 
 * @author grupo alef (Juan S�nchez, Francisco Yag�e, Jose Torrecilla)
 */
@WebServlet("/ServletCanal")
public class ServletCanal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FICHERO_NO_CLAVES = "/META-INF/no-claves.txt";
	private static TreeSet<String> lasNoClaves = new TreeSet<String>();	
       
    /**
     * Constructor de clase
     * 
     * @see HttpServlet#HttpServlet()
     */
    public ServletCanal() {
        super();
    }

	/**
	 * M�todo Get
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Video> listaVideos = new ArrayList<Video>();
		String strCanal = request.getParameter("canal");		
		Canal cnlCanal;
		System.out.println("Servlet: " + strCanal);	// a efectos de depuraci�n

		if (strCanal.equals("")) {		// no se rellen� el campo canal
			String s="";
			s="No se especific� ning�n canal";
			System.out.println(s);
			ServletOutputStream salida = response.getOutputStream();
			response.setContentType("text/html");
			s+="</br></br><input type='button' id='volver' value='Volver' onclick='history.go(-1)' />";
			salida.println(s);
		} else {						// s� se rellen� el campo canal
			try {
				LeerFicheroNoClaves();	// lee el fichero de no claves
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			new Video(lasNoClaves);		// crea una objeto Video con el �nico objeto de inicializar la variables est�tica lasNoClaves
			cnlCanal = new Canal(strCanal);			// crea al objeto canal
			try {
				listaVideos = cnlCanal.obtenerVideos();		// obtiene los v�deos para este canal desde YouTube
				if (listaVideos.size() > 0) {		// solo graba datos si encuentra v�deos para ese canal
					cnlCanal.grabarEnBBDD();		// graba el canal
					for (Video v : listaVideos) {
						v.grabarEnBBDD();			// graba el v�deo
						v.asociarACanal(cnlCanal);	// graba la asociaci�n Canal -> Video
						for (Tag t : v.getTags()) {
							t.grabarEnBBDD();		// graba el tag
							t.asociarVideo(v);		// graba la asociaci�n Tag -> Video
						}
					}
				}
			} catch (ResourceNotFoundException rnf) {
				rnf.printStackTrace();  // a efectos de depuraci�n
				sendErrorRedirect(request,response,"/error.jsp",rnf);
			} catch (ServiceException se) {
				se.printStackTrace();	// a efectos de depuraci�n
				sendErrorRedirect(request,response,"/error.jsp",se);
			} catch (Exception e) {
				e.printStackTrace();	// a efectos de depuraci�n
				sendErrorRedirect(request,response,"/error.jsp",e);
			} finally {
				PrintWriter out = response.getWriter();	
				out.println(cnlCanal);	// a efectos de depuraci�n
			}
		}
	}
	
	/**
	 * Send error redirect.
	 *
	 * @param request the request
	 * @param response the response
	 * @param errorPageURL the error page url
	 * @param e the e
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void sendErrorRedirect(HttpServletRequest request, HttpServletResponse response, 
			String errorPageURL, Throwable e) throws ServletException, IOException{
		try{
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("ErrorSesion",e.getMessage());
			getServletConfig().getServletContext().getRequestDispatcher(errorPageURL).forward(request, response);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * adjunta un fichero de no claves
	 * 
	 * @throws Exception
	 */
	private void LeerFicheroNoClaves() throws Exception {

		String texto = leerFichero(FICHERO_NO_CLAVES);					// lee el fichero con el nombre especificado 
		if (texto.equals("")){											// si el fichero est� vac�o
			throw new Exception("El fichero no tiene contenido");		// genera un error
		} else {
			for (String palabra : texto.split(", ")) {
				lasNoClaves.add(palabra.toUpperCase());
			}
		}		
	}
	
	/**
	 * lee un fichero de texto y devuelve su contenido
	 * 
	 * @param nombre
	 * @return contenido del fichero
	 * @throws KwicException
	 */
	private String leerFichero(String nombre) throws Exception {	
		InputStreamReader isReader = null;
		BufferedReader buff = null;
		String retorno="";									// cadena a devolver	

		try {
			
			InputStream inputstream = getServletContext().getResourceAsStream(nombre);	// se abre el fichero como Stream
			isReader = new InputStreamReader(inputstream);					
			buff = new BufferedReader(isReader);			// asociar el b�fer al streamreader
			
			boolean eof = false;							// controla el fin dl fichero
			
			while(!eof) {
				int byteValue = buff.read();				// leer del buffer byte a byte
				if (byteValue == -1) {						// hasta fin de fichero
					eof=true;
				} else {
					if (esLegal(byteValue)) {				// comprueba si es legal el car�cter
						retorno += (char) byteValue;		// byte a byte
					} else {								// si no es legal, arroja una excepci�n
						throw new Exception("El fichero tiene contenido no textual " + (char) byteValue);
					}
				}
			}
			return retorno;										// todo ha ido bien: devuelve el contenido le�do
		} catch (IOException e) {								// Excepci�n de entrada/salida
			System.out.println("No se pudo leer el fichero: " + e.toString());			// lo escribe en la consola
			throw new Exception("El fichero \"" + nombre + "\"no se pudo leer. ");	// genera una KwicException para que se propague hacia arriba
		} finally {
			if(isReader != null) {								// si se ha abierto el stream
				try {
					buff.close();								// cerrar el buffer
					isReader.close();							// cerrar el stream
				} catch (Exception ex) {						// se produce un error
					throw new Exception(ex.getMessage());		// se propaga la excepci�n hacia arriba
				}
			}
		}
	}
	
	/**
	 * comprueba si un car�cter le�do del fichero de no-claves es legal
	 * para las no-claves valen letras, n�meros, signos de puntuaci�n, etc
	 * Se consideran legales desde el tabulador (9) hasta la tilde (126) adem�s de la � y las vocales acentuadas (tambi�n si el acento es grave)
	 * 
	 * @param numero
	 * @return 'true' si es legal, 'false' en caso contrario
	 */
	private boolean esLegal(int numero) {
	    
		char c = Character.toUpperCase((char) numero);	// lo pone en may�sculas
		
		switch (c) {
		case '�': case '�': case '�':	// primero comprueba las excepciones
		case '�': case '�': case '�':
		case '�': case '�': case '�':	// tambi�n se dan por v�lidos los acentos invertidos (graves)
		case '�': case '�':
		case '�':
			return true;
		default:						// si no es ninguna excepci�n, se comprueba el valor num�rico
			if (numero < 9 || c > 126) {	// se considera no v�lido si es menor de 9 (tabulador) o mayor de 126 (~)
				return false;
			}
		}
		return true;					// si llega aqu�, es v�lido
	}
	
}
