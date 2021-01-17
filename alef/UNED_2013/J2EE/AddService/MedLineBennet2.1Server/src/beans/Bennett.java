package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.Date;

//import au.com.bytecode.opencsv.CSVReader;


public class Bennett implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String organization;

	private String beds;

	private String blog;

	private String city;

	private String compete;

	private String delicious;

	private String facebookURL;

	private String flickr;

	private String fourSquare;

	private String fullOrgURL;

	private String googlePlus;

	private String linkedIn;

	private String orgType;

	private String parOrg;

	private String pinterest;

	private String priDomain;

	private String quantcast;

	private String state;

	private String twitterAccount;

	private Date twitterFirstUp;

	private String twitterURL;

	private String USNewsDirectory;

	private String youTubeAccount;

	private Date youTubeDate;

	private String youTubeURL;
	
	public static final int FILAS_POR_PAGINA = 12;
	
	public static final String[] camposBusqueda = {"Organization", "Beds", "Blog", "City",
		"Compete", "Delicious", "FacebookURL", "Flickr", "FourSquare", "FullOrgURL",
		"GooglePlus", "LinkedIn", "OrgType", "ParOrg", "Pinterest", "PriDomain", "QuantCast",
		"State", "TwitterAccount", "TwitterURL", "USNewsDirectory", "YouTubeAccount", "YouTubeURL"};
		
	public Bennett() {
	}
	
	public Bennett(String organization, String beds, String blog, String city, String compete,
					String delicious, String facebookURL, String flickr, String fourSquare, 
					String fullOrgURL, String googlePlus, String linkedIn, String orgType, 
					String parOrg, String pinterest, String priDomain, String quantcast, 
					String state, String twitterAccount, Date twitterFirstUp, String twitterURL, 
					String USNewsDirectory, String youTubeAccount, Date youTubeDate, String youTubeURL){
		
		this.organization = organization;
		this.beds = beds;
		this.blog = blog;
		this.city = city;
		this.compete = compete;
		this.delicious = delicious;
		this.facebookURL = facebookURL;
		this.flickr = flickr;
		this.fourSquare = fourSquare;
		this.fullOrgURL = fullOrgURL;
		this.googlePlus = googlePlus;
		this.linkedIn = linkedIn;
		this.orgType = orgType;
		this.parOrg = parOrg;
		this.pinterest = pinterest;
		this.priDomain = priDomain;
		this.quantcast = quantcast;
		this.state = state;
		this.twitterAccount = twitterAccount;
		this.twitterFirstUp = twitterFirstUp;
		this.twitterURL = twitterURL;
		this.USNewsDirectory = USNewsDirectory;
		this.youTubeAccount = youTubeAccount;
		this.youTubeDate = youTubeDate;
		this.youTubeURL = youTubeURL;
	}
	/*
	 * Constructor con los parámetros ordenados según el orden de las columnas de la tabla
	 */
	public Bennett(String orgType, String beds, String USNewsDirectory, String city, String state,
			String parOrg, String organization, String priDomain, String fullOrgURL, 
			String youTubeAccount, Date youTubeDate, String youTubeURL, Date twitterFirstUp, 
			String twitterAccount, String twitterURL, String facebookURL, String blog, 
			String fourSquare, String linkedIn, String pinterest, String googlePlus, 
			String delicious, String flickr, String quantcast, String compete){

		this.organization = organization;
		this.beds = beds;
		this.blog = blog;
		this.city = city;
		this.compete = compete;
		this.delicious = delicious;
		this.facebookURL = facebookURL;
		this.flickr = flickr;
		this.fourSquare = fourSquare;
		this.fullOrgURL = fullOrgURL;
		this.googlePlus = googlePlus;
		this.linkedIn = linkedIn;
		this.orgType = orgType;
		this.parOrg = parOrg;
		this.pinterest = pinterest;
		this.priDomain = priDomain;
		this.quantcast = quantcast;
		this.state = state;
		this.twitterAccount = twitterAccount;
		this.twitterFirstUp = twitterFirstUp;
		this.twitterURL = twitterURL;
		this.USNewsDirectory = USNewsDirectory;
		this.youTubeAccount = youTubeAccount;
		this.youTubeDate = youTubeDate;
		this.youTubeURL = youTubeURL;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getBeds() {
		return this.beds;
	}

	public void setBeds(String beds) {
		this.beds = beds;
	}

	public String getBlog() {
		return this.blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompete() {
		return this.compete;
	}

	public void setCompete(String compete) {
		this.compete = compete;
	}

	public String getDelicious() {
		return this.delicious;
	}

	public void setDelicious(String delicious) {
		this.delicious = delicious;
	}

	public String getFacebookURL() {
		return this.facebookURL;
	}

	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}

	public String getFlickr() {
		return this.flickr;
	}

	public void setFlickr(String flickr) {
		this.flickr = flickr;
	}

	public String getFourSquare() {
		return this.fourSquare;
	}

	public void setFourSquare(String fourSquare) {
		this.fourSquare = fourSquare;
	}

	public String getFullOrgURL() {
		return this.fullOrgURL;
	}

	public void setFullOrgURL(String fullOrgURL) {
		this.fullOrgURL = fullOrgURL;
	}

	public String getGooglePlus() {
		return this.googlePlus;
	}

	public void setGooglePlus(String googlePlus) {
		this.googlePlus = googlePlus;
	}

	public String getLinkedIn() {
		return this.linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getParOrg() {
		return this.parOrg;
	}

	public void setParOrg(String parOrg) {
		this.parOrg = parOrg;
	}

	public String getPinterest() {
		return this.pinterest;
	}

	public void setPinterest(String pinterest) {
		this.pinterest = pinterest;
	}

	public String getPriDomain() {
		return this.priDomain;
	}

	public void setPriDomain(String priDomain) {
		this.priDomain = priDomain;
	}

	public String getQuantcast() {
		return this.quantcast;
	}

	public void setQuantcast(String quantcast) {
		this.quantcast = quantcast;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTwitterAccount() {
		return this.twitterAccount;
	}

	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}

	public Date getTwitterFirstUp() {
		return this.twitterFirstUp;
	}

	public void setTwitterFirstUp(Date twitterFirstUp) {
		this.twitterFirstUp = twitterFirstUp;
	}

	public String getTwitterURL() {
		return this.twitterURL;
	}

	public void setTwitterURL(String twitterURL) {
		this.twitterURL = twitterURL;
	}

	public String getUSNewsDirectory() {
		return this.USNewsDirectory;
	}

	public void setUSNewsDirectory(String USNewsDirectory) {
		this.USNewsDirectory = USNewsDirectory;
	}

	public String getYouTubeAccount() {
		return this.youTubeAccount;
	}

	public void setYouTubeAccount(String youTubeAccount) {
		this.youTubeAccount = youTubeAccount;
	}

	public Date getYouTubeDate() {
		return this.youTubeDate;
	}

	public void setYouTubeDate(Date youTubeDate) {
		this.youTubeDate = youTubeDate;
	}

	public String getYouTubeURL() {
		return this.youTubeURL;
	}

	public void setYouTubeURL(String youTubeURL) {
		this.youTubeURL = youTubeURL;
	}
	
	/**
	 * Inserta en la tabla 'bennett' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
	public void insert(Connection conn){
		PreparedStatement ps = null;
						
		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`bennett`(`OrgType`,`Beds`,`USNewsDirectory`," +
					"`City`,`State`,`ParOrg`,`Organization`,`PriDomain`,`FullOrgURL`,`YouTubeAccount`,`YouTubeDate`," +
					"`YouTubeURL`,`TwitterFirstUp`,`TwitterAccount`,`TwitterURL`,`FacebookURL`,`Blog`,`FourSquare`," +
					"`LinkedIn`,`Pinterest`,`GooglePlus`,`Delicious`,`Flickr`,`Quantcast`,`Compete`)VALUES(?,?,?,?,?,?,?,?," +
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1, this.orgType);
			ps.setString(2, this.beds);
			ps.setString(3, this.USNewsDirectory);
			ps.setString(4, this.city);
			ps.setString(5, this.state);
			ps.setString(6, this.parOrg);
			ps.setString(7, this.organization);
			ps.setString(8, this.priDomain);
			ps.setString(9, this.fullOrgURL);
			ps.setString(10, this.youTubeAccount);
			ps.setDate(11, (java.sql.Date) this.youTubeDate);
			ps.setString(12, this.youTubeURL);
			ps.setDate(13, (java.sql.Date) this.twitterFirstUp);
			ps.setString(14, this.twitterAccount);
			ps.setString(15, this.twitterURL);
			ps.setString(16, this.facebookURL);
			ps.setString(17, this.blog);
			ps.setString(18, this.fourSquare);
			ps.setString(19, this.linkedIn);
			ps.setString(20, this.pinterest);
			ps.setString(21, this.googlePlus);
			ps.setString(22, this.delicious);
			ps.setString(23, this.flickr);
			ps.setString(24, this.quantcast);
			ps.setString(25, this.compete);
			ps.executeUpdate();
			ps.close();
		} catch (SQLTimeoutException e){
			System.out.println("Error de conexión. El tiempo de espera se ha sobrepasado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Bennett");
			e.printStackTrace();
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar al cerrar 'prepareStatement'");
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Bennett' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda. Para ello, llama internamente
	 * al método del mismo nombre de la clase que conecta con la base de datos.
	 * @param desp: int -> Desplazamiento sobre el resultado de la búsqueda de la 
	 * 						  primera fila que se va a mostrar en la página 
	 *                     actual de la vista.
	 * @param filas: int -> Número de filas que se van a mostrar en la página
	 *                      actual de la vista.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @return lista: ArrayList<Bennett> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	public static ArrayList<Bennett> listadoBennett(String campo, 
			String palabra, int pag, int filas) throws Exception{
		ArrayList<Bennett> miLista = new ArrayList<Bennett>();
		BBDD miBd = new BBDD();
		
		miBd.abrirConexion(); 
		miLista = miBd.listadoBennett(campo, palabra, pag, filas);
		miBd.cerrarConexion();
		return miLista;		
	}
	/**
	 * Carga un archivo CSV en la tabla 'bennett' de la base de datos. Necesita la libreria
	 * 'opencsv-2.3.jar'. Un objeto 'CSVReader' se encarga de leer el archivo e ir incluyendo
	 * en un array String los diferentes valores de cada columna. Por defecto toma como caracter
	 * separador ','.
	 * @param csv : Archivo CSV que se cargara
	 * @param bd  : Referencia a la base de datos. LA CONEXIÓN DEBE ESTAR ABIERTA.
	 */
	public void cargaCvs(File csv, BBDD bd){
//		
//		int numlinea = 1;//Utilizada para evitar que se cargue la primera línea del 'cvs' que contiene el nombre de las columnas
// 	//	CSVReader csvr = null;
// 		try {
// 	 
//// 	//		csvr = new CSVReader(new FileReader(csv));
//// 			String[] registro;//Almacena temporalmente el registro actual
// 			
// 			while ((registro = csvr.readNext()) != null) {
// 			  if(numlinea>1){
// 			    /* 				
// 				System.out.println("1. " + registro[0]);
// 				System.out.println("2. " + registro[1]);
// 				System.out.println("3. " + registro[2]);
// 				System.out.println("4. " + registro[3]);
// 				System.out.println("5. " + registro[4]);
// 				System.out.println("6. " + registro[5]);
// 				System.out.println("7. " + registro[6]);
// 				System.out.println("8. " + registro[7]);
// 				System.out.println("9. " + registro[8]);
// 				System.out.println("10. " + registro[9]);
// 				System.out.println("11. " + registro[10]);
// 				System.out.println("12. " + registro[11]);
// 				System.out.println("13. " + registro[12]);
// 				System.out.println("14. " + registro[13]);
// 				System.out.println("15. " + registro[14]);
// 				System.out.println("16. " + registro[15]);
// 				System.out.println("17. " + registro[16]);
// 				System.out.println("18. " + registro[17]);
// 				System.out.println("19. " + registro[18]);
// 				System.out.println("20. " + registro[19]);
// 				System.out.println("21. " + registro[20]);
// 				System.out.println("22. " + registro[21]);
// 				System.out.println("23. " + registro[22]);
// 				System.out.println("24. " + registro[23]);
// 				System.out.println("25. " + registro[24]);
// 				System.out.println("---------------------------------------------------------------");
// 				*/
// 				// -- Convierte a formato 'java.sql.Date' los campos String
// 				java.sql.Date twitfu = null;
// 				java.sql.Date youtubedate = null;
// 				if(registro[10].length()!=0) youtubedate = bd.castFecha(registro[10],true);
// 				if(registro[12].length()!=0) twitfu = bd.castFecha(registro[12],false);
// 				// --	
// 				// Se crea un objeto 'Bennett',cuyos atributos son los valores de las diferentes columnas de la tabla 'bennett'
// 				Bennett ben = new Bennett(registro[0],registro[1],registro[2],registro[3],
// 						registro[4],registro[5],registro[6],registro[7],registro[8],
// 						registro[9],youtubedate,registro[11],twitfu,registro[13],
// 						registro[14],registro[15],registro[16],registro[17],registro[18],
// 						registro[19],registro[20],registro[21],registro[22],registro[23],registro[24]);
// 				// ...y se inserta en la tabla
// 				ben.insert(bd.getConnection());
// 				
// 			  }
// 			  numlinea++;
// 	    	}
// 			
// 		} catch (FileNotFoundException e) {
// 			System.out.println("Error al intentar recuperar el fichero CSV");
// 			e.printStackTrace();
// 		} catch (IOException e) {
// 			System.out.println("Error al intentar recuperar el fichero CSV");
// 			e.printStackTrace();
// 		} finally {
// 			if(csvr!=null){
// 				try {
// 					csvr.close();
// 				} catch (IOException e) {
// 					System.out.println("Error al intentar cerrar el fichero CSV");
// 					e.printStackTrace();
// 				}
// 			}
// 		}
// 	 
 	}
}