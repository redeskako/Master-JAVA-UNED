/*
 * Esta clase se utiliza para las tareas de conexión a la base de datos
 */

package beans;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class  BBDD {
	private Connection conn;
	private PreparedStatement stm;
	private boolean conectado;
	private String usuario;
	private String servidor;
	private String base;
	private String password;
	private String clave;
	
	// Total de filas resultantes de la última búsqueda.
	private int filas;	
	
	public BBDD(){		
		//no hace nada
		inicializa();
	}

	private void inicializa(){
		this.conn = null;
		this.servidor = "";
		this.base = "";
		this.usuario = "";
		this.password = "";
		this.conectado = false;
		this.filas = 0;
	}

	/*
	 * Método que nos indica si la conexión está abierta
	 */
	@SuppressWarnings("finally")
	public boolean abrirConexion(){
		this.conectado=false;
		Context envContext = null; //Contexto de la aplicación

		try {
			envContext = new InitialContext();
			//Busca el "resource jdbc/medlineDB" en el contexto de la aplicación
			Context initContext  = (Context)envContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)initContext.lookup("jdbc/medlineDB");
			//DataSource ds = (DataSource)initContext.lookup("jdbc/medlinebennett");
			//DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/medlineDB");
			this.conn = ds.getConnection();
			this.conectado=true;
		} catch (NamingException e) {
			System.out.println("Error JNDI.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al intentar acceder a la base de datos");
			e.printStackTrace();
		}
		finally{
			return conectado;
		}   
	}
	
	//Devuelve la conexión actual
	public Connection getConnection(){
		return conn;
	}
	
	public void cerrarConexion(){
		try{
			if (this.stm != null){
				this.stm.close();
			}
			if (this.conn != null){
				this.conn.close();
			}
		}catch (SQLException e){
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch (Exception e){
			throw new HealthtopicException("Error desconocido" + e.getMessage());
		}finally{
			inicializa();
		}
	}
	/**
	 * MySQL sólo admite como formato fecha la clase java.sql.Date. En el parseo del xml o del cvs la
	 * fecha aparece en diferentes formatoos. En el xml aparece como un String en formato yyyy-mm-dd.
	 * En el 'cvs' aparece tanto en formato americano (mm/dd/yy) como en formato europeo (dd/mm/yy),
	 * dentro del formato americano aparecen dos 'subformatos': mm/dd/yy y mm/dd/yyyy.
	 * En cualquier caso es necesario convertir el 'String' en tipo java.util.Date
	 * para pasarlo a formato long (milisegundos) que es uno de los parámetros que utiliza uno de los
	 * constructores de java.sql.Date.
	 * @param f : String que contiene la fecha a parsear.
	 * @param usformat: 'true' si es formato americano, 'false' si es europeoo
	 */
	public java.sql.Date castFecha(String f, boolean usformat){

		int tipo = f.length();
		Date fecd = new Date();
		SimpleDateFormat fecsdf = new SimpleDateFormat();
		SimpleDateFormat fecfin = new SimpleDateFormat();
		
		try {
			if(usformat){
				switch(tipo){
				case 8:	
					fecsdf.applyPattern("MM/dd/yy");
					fecd = fecsdf.parse(f);
					// -- Si no el formato quedaría 0010-09-08
					fecfin.applyPattern("MM/dd/yyyy hh:mm:ss");
					fecd = fecsdf.parse(fecfin.format(fecd));
					// --
				break;
				case 10:
					fecsdf.applyPattern("MM/dd/yyyy");
					fecd = fecsdf.parse(f);
				break; 
				default:
					System.out.println("Formato de fecha erróneo");
				break;
				}
			}else{
				switch(tipo){
				case 8:	
					fecsdf.applyPattern("dd/MM/yy");
					fecd = fecsdf.parse(f);
					fecfin.applyPattern("MM/dd/yyyy hh:mm:ss");
					fecd = fecsdf.parse(fecfin.format(fecd));
				break;
				case 10:
					fecsdf.applyPattern("dd/MM/yyyy");
					fecd = fecsdf.parse(f);
				break; 
				default:
					System.out.println("Formato de fecha erróneo");
				break;
				}
			}
		} catch (ParseException e) {
			System.out.println("Error en la conversión de la fecha");
			e.printStackTrace();
		}
		long mills = fecd.getTime();//Se pasa a milisegundos para poderlo pasar como parámetro en el constructor posterior
		java.sql.Date fecha = new java.sql.Date(mills);
		return fecha;
	}
	/*
	 * Método que nos da los datos del usuario, id, nombre, clave, ...
	 */
	public Usuario getUsuario(String sql, String usuario, String pwd){
		Usuario u = null;
		ResultSet rs;
		try{
			stm = conn.prepareStatement(sql);
			stm.setString(1, usuario);
			stm.setString(2, pwd);
			rs = stm.executeQuery();
			if (rs.next()){
				u = new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("clave"), rs.getInt("gestion"));
			}
			rs.close();
		}catch (SQLException e){
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			throw new HealthtopicException("Error indefinido. "+ e.getMessage());
		}finally{
			return u;
		}
	}
	/*
	 * Metodo que nos da el nombre del usuario
	 */
	public String getNombreUsuario(String sql, int idusuario){
		String u = "";
		ResultSet rs;
		try{
			stm = conn.prepareStatement(sql);
			stm.setInt(1, idusuario);
			rs = stm.executeQuery();
			if (rs.next()){
				u = rs.getString("usuario");
			}
			rs.close();
		}catch (SQLException e){
			throw new HealthtopicException("Error al cerrar la conexion: " +e.getMessage());
		}catch(Exception e){
			throw new HealthtopicException("Error indefinido. "+ e.getMessage());
		}finally{
			return u;
		}
	}
	
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Bennett' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param desp: int -> Desplazamiento sobre el resultado de la búsqueda de la 
	 * 						  primera fila que se va a mostrar en la página 
	 *                     correspondiente de la vista.
	 * @param filas: int -> Número de filas que se van a mostrar en la página
	 *                      correspondiente de la vista.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @return lista: ArrayList<Bennett> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	public ArrayList<Bennett> listadoBennett(String campo, 
			String palabra, int pag, int filas) {
		ArrayList<Bennett> lista = new ArrayList<Bennett>();
		ArrayList<String> sqlParams = new ArrayList<String>();
		String query = "", p;
		ResultSet rs = null;
		int i;
		
		// Si hemos escrito alguna palabra para la búsqueda.
		if (!palabra.equals("")) {
			// Comenzamos a generar una cadena con condiciones para 
			// una consulta SQL.
			query = " WHERE ";
			// Si estamos buscando esa palabara en cualquier campo.
			if (campo.equals("*")) {
				// Añadimos a la cadena comparaciones con todos los campos,
				// de la tabla, añadiendo también por cada comparación la palabra 
				// que se va a buscar a los parámetros que posteriormente 
				// pasaremos a la sentencia SELECT.
				for (i = 0; i < Bennett.camposBusqueda.length; i++) {
					query += "\u0060" + Bennett.camposBusqueda[i] + "\u0060 LIKE ?";
					sqlParams.add(palabra);
					if (i < (Bennett.camposBusqueda.length - 1))
						query += " OR ";
				}
			}
			else {
				// Si estamos buscando en un campo concreto
				query += "\u0060" + campo + "\u0060 LIKE ?";
				sqlParams.add(palabra);
			}
		}
		// Establecemos la cadena que define una sentencia SELECT completa.
		query = "SELECT SQL_CALC_FOUND_ROWS * FROM \u0060bennett\u0060" + query +
				" ORDER BY \u0060Organization\u0060 LIMIT " + ((pag - 1) * filas) + "," + filas;
				
		try {
			// Preparamos la sentencia para ser ejecutada con 'prepareStatement', 
			// aportando mayor seguridad a la consulta. Se establece que se
			// que se pueda realizar scroll sobre los registros y que el
			// resultado sea de solo lectura.
			stm = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			// Pasamos los parámetros necesarios a la sentencia
			for (i = 0; i < sqlParams.size(); i++)
				stm.setString(i + 1, "%" + sqlParams.get(i) + "%");
			
			// Ejecutamos la sentencia SELECT.
			rs = stm.executeQuery();
			
			// Mientras existan registros de la página actal.
			while (rs.next()) {
				// Leemos los campos de ese registro, y lo añadimos
				// a la lista que se va a mostrar en la página actual
				// de la vista.
				lista.add(new Bennett(rs.getString("Organization"), rs.getString("Beds"),
						rs.getString("Blog"), rs.getString("City"), 
						rs.getString("Compete"), rs.getString("Delicious"),
						rs.getString("FacebookURL"), rs.getString("Flickr"), 
						rs.getString("FourSquare"), rs.getString("FullOrgURL"),
						rs.getString("GooglePlus"), rs.getString("LinkedIn"),
						rs.getString("OrgType"), rs.getString("ParOrg"),
						rs.getString("Pinterest"), rs.getString("PriDomain"),
						rs.getString("QuantCast"), rs.getString("State"),
						rs.getString("TwitterAccount"), rs.getDate("TwitterFirstUp"),	
						rs.getString("TwitterURL"), rs.getString("USNewsDirectory"),
						rs.getString("YouTubeAccount"), rs.getDate("YouTubeDate"),
						rs.getString("YouTubeURL")));
			}
			rs.close();
			
			// Calculamos el número de filas totales que resultan de la búsqueda
			// actual desde la página actual y sin el límite de la página, 
			// almacenamos esa cantidad en la variable de instancia 'filasBennett'.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next()) this.filas = rs.getInt(1);
			
			rs.close();
		}
		catch(SQLException e) {
			lista = null;
			throw new HealthtopicException("Error en SQL: " + e.getMessage());
		}
		catch(Exception e) {
			lista = null;
			throw new HealthtopicException("Error desconocido: " + e.getMessage());
		}
		finally {
			return lista;
		}
	}
	
	public ArrayList<Bennett> listadoStartBennettS(String palabra,int limite){
		ArrayList<Bennett> lista = new ArrayList<Bennett>();
		
		String query = "", p;
		ResultSet rs = null;
		int i;
		
		query = "SELECT DISTINCT City FROM \u0060bennett\u0060" +
				" WHERE City LIKE '"+palabra+"%' " +
				" ORDER BY \u0060Organization\u0060 LIMIT " + limite;
				
		try {
			// Preparamos la sentencia para ser ejecutada con 'prepareStatement', 
			// aportando mayor seguridad a la consulta. Se establece que se
			// que se pueda realizar scroll sobre los registros y que el
			// resultado sea de solo lectura.
			stm = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			// Ejecutamos la sentencia SELECT.
			rs = stm.executeQuery();
			
			// Mientras existan registros de la página actal.
			while (rs.next()) {
				// Leemos los campos de ese registro, y lo añadimos
				// a la lista que se va a mostrar en la página actual
				// de la vista.
//				lista.add(new Bennett(rs.getString("Organization"), rs.getString("Beds"),
//						rs.getString("Blog"), rs.getString("City"), 
//						rs.getString("Compete"), rs.getString("Delicious"),
//						rs.getString("FacebookURL"), rs.getString("Flickr"), 
//						rs.getString("FourSquare"), rs.getString("FullOrgURL"),
//						rs.getString("GooglePlus"), rs.getString("LinkedIn"),
//						rs.getString("OrgType"), rs.getString("ParOrg"),
//						rs.getString("Pinterest"), rs.getString("PriDomain"),
//						rs.getString("QuantCast"), rs.getString("State"),
//						rs.getString("TwitterAccount"), rs.getDate("TwitterFirstUp"),	
//						rs.getString("TwitterURL"), rs.getString("USNewsDirectory"),
//						rs.getString("YouTubeAccount"), rs.getDate("YouTubeDate"),
//						rs.getString("YouTubeURL")));
				Bennett b = new Bennett();
				b.setCity(rs.getString("City"));
				lista.add(b);
			}
			rs.close();									
			
		}
		catch(SQLException e) {
			lista = null;
			throw new HealthtopicException("Error en SQL: " + e.getMessage());
		}
		catch(Exception e) {
			lista = null;
			throw new HealthtopicException("Error desconocido: " + e.getMessage());
		}
		finally {
			
		}
		
		return lista;
	}
	
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Bennett' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param desp: int -> Desplazamiento sobre el resultado de la búsqueda de la 
	 * 						  primera fila que se va a mostrar en la página 
	 *                     correspondiente de la vista.
	 * @param filas: int -> Número de filas que se van a mostrar en la página
	 *                      correspondiente de la vista.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra por la que debe empezar lo que se busca.
	 * @return lista: ArrayList<Bennett> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	public ArrayList<Bennett> listadoBennettPrincipio(int pag, int filas, String campo, 
			String palabra) {
		ArrayList<Bennett> lista = new ArrayList<Bennett>();
		ArrayList<String> sqlParams = new ArrayList<String>();
		String query = "", p;
		ResultSet rs = null;
		int i;
		
		// Si hemos escrito alguna palabra para la búsqueda.
		if (!palabra.equals("")) {
			// Comenzamos a generar una cadena con condiciones para 
			// una consulta SQL.
			query = " WHERE ";
			// Si estamos buscando esa palabara en cualquier campo.
			if (campo.equals("*")) {
				// Añadimos a la cadena comparaciones con todos los campos,
				// de la tabla, añadiendo también por cada comparación la palabra 
				// que se va a buscar a los parámetros que posteriormente 
				// pasaremos a la sentencia SELECT.
				for (i = 0; i < Bennett.camposBusqueda.length; i++) {
					query += "\u0060" + Bennett.camposBusqueda[i] + "\u0060 LIKE ?";
					sqlParams.add(palabra);
					if (i < (Bennett.camposBusqueda.length - 1))
						query += " OR ";
				}
			}
			else {
				// Si estamos buscando en un campo concreto
				query += "\u0060" + campo + "\u0060 LIKE ?";
				sqlParams.add(palabra);
			}
		}
		// Establecemos la cadena que define una sentencia SELECT completa.
		query = "SELECT SQL_CALC_FOUND_ROWS * FROM \u0060bennett\u0060" + query +
				" ORDER BY \u0060Organization\u0060 LIMIT " + ((pag - 1) * filas) + "," + filas;
				
		try {
			// Preparamos la sentencia para ser ejecutada con 'prepareStatement', 
			// aportando mayor seguridad a la consulta. Se establece que se
			// que se pueda realizar scroll sobre los registros y que el
			// resultado sea de solo lectura.
			stm = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			// Pasamos los parámetros necesarios a la sentencia
			for (i = 0; i < sqlParams.size(); i++)
				stm.setString(i + 1, sqlParams.get(i) + "%");
			
			// Ejecutamos la sentencia SELECT.
			rs = stm.executeQuery();
			
			// Mientras existan registros de la página actal.
			while (rs.next()) {
				// Leemos los campos de ese registro, y lo añadimos
				// a la lista que se va a mostrar en la página actual
				// de la vista.
				lista.add(new Bennett(rs.getString("Organization"), rs.getString("Beds"),
						rs.getString("Blog"), rs.getString("City"), 
						rs.getString("Compete"), rs.getString("Delicious"),
						rs.getString("FacebookURL"), rs.getString("Flickr"), 
						rs.getString("FourSquare"), rs.getString("FullOrgURL"),
						rs.getString("GooglePlus"), rs.getString("LinkedIn"),
						rs.getString("OrgType"), rs.getString("ParOrg"),
						rs.getString("Pinterest"), rs.getString("PriDomain"),
						rs.getString("QuantCast"), rs.getString("State"),
						rs.getString("TwitterAccount"), rs.getDate("TwitterFirstUp"),	
						rs.getString("TwitterURL"), rs.getString("USNewsDirectory"),
						rs.getString("YouTubeAccount"), rs.getDate("YouTubeDate"),
						rs.getString("YouTubeURL")));
			}
			rs.close();
			
			// Calculamos el número de filas totales que resultan de la búsqueda
			// actual desde la página actual y sin el límite de la página, 
			// almacenamos esa cantidad en la variable de instancia 'filasBennett'.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next()) this.filas = rs.getInt(1);
			
			rs.close();
		}
		catch(SQLException e) {
			lista = null;
			throw new HealthtopicException("Error en SQL: " + e.getMessage());
		}
		catch(Exception e) {
			lista = null;
			throw new HealthtopicException("Error desconocido: " + e.getMessage());
		}
		finally {
			return lista;
		}
	}
	
	/**
	 * Devuelve las filas totales resultantes de la última búsqueda.
	 * @return filas: int
	 */
	public int getFilas() {
		return filas;
	}
	
	/**
	 * Metodo que recibe una lista de 'sites' de un 'healthtopic' y determina si existen
	 * equivalencias de los mismos en la tabla 'bennett' de la BD 'medlinebennett' de MySQL.
	 * @param sites: List<HealtTopics.HealthTopic.Site> -> Lista de 'sites' de un 'healthtopic'.
	 * @param campo: Sring -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @return: boolean (true or false) 
	 */
	public ArrayList<Bennett> buscaSitesEnBennett(List<HealthTopics.HealthTopic.Site> sites, String campo, String palabra) {
		ArrayList<Bennett> lista = new ArrayList<Bennett>();
		ArrayList<String> sqlParams = new ArrayList<String>();
		HealthTopics.HealthTopic.Site s;
		List<String> organizations;
		String query = "", priDomain;
		ResultSet rs = null;
		int i, j, x;
		
		// Si hemos escrito alguna palabra para la búsqueda.
		if (!palabra.equals("")) {
			// Iniciamos la construcción de la sentencia SELECT.
			// Se debe cumplir que exista equivalencia de algún 'site'
			// con algún registro de la tabla 'bennett' y que, además,
			// se cumplan los criterios de búsqueda sobre los campos.
			query = "SELECT * FROM \u0060bennett\u0060 WHERE ";
			// Recorremos primero la lista de 'sites'
			if (sites.size() > 0) {
				query += "(";
				for (i = 0; i < sites.size(); i++) {
					s = sites.get(i);
					organizations = s.getOrganization();
					// Añadimos comparaciones de las organizaciones asociadas al 'site'
					// con el campo 'Organization' de la tabla 'bennett', añadiendo
					// también cada organización a los parámetros que posteriormente 
					// pasaremos a la sentencia SELECT.
					for (j = 0; j < organizations.size(); j++) {
						query += "\u0060Organization\u0060 LIKE ?";
						sqlParams.add(organizations.get(j));
						if (j < (organizations.size() - 1))
							query += " OR ";
					}
					// Por si la comparación de cadenas anterior no fuese exacta,
					// añadimos una comparación con el campo 'PriDomain' de la tabla 
					// 'bennett', extraemos también el dominio de la URL del site y
					// lo añadimos a los parámetros que posteriormente pasaremos 
					// a la sentencia SELECT.
					if (organizations.size() > 0) query += " OR ";
					query += "\u0060PriDomain\u0060 LIKE ?";
					priDomain = s.getUrl().replaceFirst("http://", "");
					priDomain = priDomain.replaceFirst("www.", "");
					if ((x = priDomain.indexOf("/")) != -1)
						priDomain = priDomain.substring(0, x);
					sqlParams.add(priDomain);
					if (i < (sites.size() - 1))
						query += " OR ";
				}
				query += ") AND ";
			}
			
			query += "(";
			// Si estamos buscando esa palabara en cualquier campo.
			if (campo.equals("*")) {
				// Añadimos a la cadena comparaciones con todos los campos,
				// de la tabla, añadiendo también por cada comparación la palabra 
				// que se va a buscar a los parámetros que posteriormente 
				// pasaremos a la sentencia SELECT.
				for (i = 0; i < Bennett.camposBusqueda.length; i++) {
					query += "\u0060" + Bennett.camposBusqueda[i] + "\u0060 LIKE ?";
					sqlParams.add(palabra);
					if (i < (Bennett.camposBusqueda.length - 1))
						query += " OR ";
				}
			}
			else {
				// Si estamos buscando en un campo concreto
				query += "\u0060" + campo + "\u0060 LIKE ?";
				sqlParams.add(palabra);
			}
			query += ")";
			
			try {
				// Preparamos la sentencia para ser ejecutada con 'prepareStatement', 
				// aportando mayor seguridad a la consulta. Se establece que se
				// que se pueda realizar scroll sobre los registros y que el
				// resultado sea de solo lectura.
				stm = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				
				// Pasamos los parámetros necesarios a la sentencia
				for (i = 0; i < sqlParams.size(); i++)
					stm.setString(i + 1, "%" + sqlParams.get(i) + "%");
				
				// Ejecutamos la sentencia SELECT.
				rs = stm.executeQuery();
				
				// Mientras existan registros de la página actal.
				while (rs.next()) {
					// Leemos los campos de ese registro, y lo añadimos
					// a la lista que se va a mostrar en la página actual
					// de la vista.
					lista.add(new Bennett(rs.getString("Organization"), rs.getString("Beds"),
							rs.getString("Blog"), rs.getString("City"), 
							rs.getString("Compete"), rs.getString("Delicious"),
							rs.getString("FacebookURL"), rs.getString("Flickr"), 
							rs.getString("FourSquare"), rs.getString("FullOrgURL"),
							rs.getString("GooglePlus"), rs.getString("LinkedIn"),
							rs.getString("OrgType"), rs.getString("ParOrg"),
							rs.getString("Pinterest"), rs.getString("PriDomain"),
							rs.getString("QuantCast"), rs.getString("State"),
							rs.getString("TwitterAccount"), rs.getDate("TwitterFirstUp"),	
							rs.getString("TwitterURL"), rs.getString("USNewsDirectory"),
							rs.getString("YouTubeAccount"), rs.getDate("YouTubeDate"),
							rs.getString("YouTubeURL")));
				}
				rs.close();
			}
			catch(SQLException e) {
				lista = null;
				throw new HealthtopicException("Error en SQL: " + e.getMessage());
			}
			catch(Exception e) {
				lista = null;
				throw new HealthtopicException("Error desconocido: " + e.getMessage());
			}
			finally {
				return lista;
			}
		}
		else
			return lista;
	}
	
}
	
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'AlsoCalled' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca.
	 * @return lista: ArrayList<AlsoCalled> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Alsocalled> listadoAlsoCalled(String sql, int id) {
		ArrayList <Alsocalled> lista = new ArrayList<Alsocalled>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Alsocalled(rs.getInt("HealthTopic_Id"), rs.getString("AlsoCalled")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
	*/

	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Healthtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param busqueda: string -> Valor que se busca en cualquier campo de la tabla.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Healthtopic> listadoHealthtopic(String sql, String busqueda) {
		ArrayList <Healthtopic> lista = new ArrayList<Healthtopic>();
		
		ResultSet rs;
		try{
			//Ahora realizamos la búsqueda: utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + busqueda + "%");
			stm.setString(2, "%" + busqueda + "%");
			stm.setString(3, "%" + busqueda + "%");
			stm.setString(4, "%" + busqueda + "%");
			stm.setString(5, "%" + busqueda + "%");
			stm.setString(6, "%" + busqueda + "%");
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Healthtopic(rs.getInt("id"), rs.getString("healthTopic"),
									  rs.getString ("language"), rs.getDate("dateCreated"),
									  rs.getString("url"), rs.getString("summary"),
									  rs.getString("primaryInst"), rs.getString("primaryInstURL")));
			}
			rs.close();
			// Calculamos el número de filas totales que resultan de la búsqueda.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next())
				this.filas = rs.getInt(1);
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
			
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Healthtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param busqueda: string -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Healthtopic> listadoHealthtopicCampo(String sql,
			String busqueda) {
		ArrayList <Healthtopic> lista = new ArrayList<Healthtopic>();
		
		ResultSet rs;
		
		try{
			//Ahora realizamos la búsqueda: utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + busqueda + "%");
			rs = stm.executeQuery();
			
			while (rs.next()){
				lista.add(new Healthtopic(rs.getInt("Id"), rs.getString("HealthTopic"),
									  rs.getString ("Language"), rs.getDate("Datecreated"),
									  rs.getString("URL"), rs.getString("Summary"),
									  rs.getString("PrimaryInst"), rs.getString("PrimaryInstURL")));
			}
			
			rs.close();
			// Calculamos el número de filas totales que resultan de la búsqueda.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next())
				this.filas = rs.getInt(1);
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
			
		return lista;
	}
	*/

	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Healthtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Healthtopic> listadoHealthtopicCampo(String sql, int id) {
		ArrayList <Healthtopic> lista = new ArrayList<Healthtopic>();
		
		ResultSet rs;
		try{
			//Realizamos la búsqueda: utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			
			while (rs.next()){
				lista.add(new Healthtopic(rs.getInt("Id"), rs.getString("HealthTopic"),
									  rs.getString ("Language"), rs.getDate("Datecreated"),
									  rs.getString("URL"), rs.getString("Summary"),
									  rs.getString("PrimaryInst"), rs.getString("PrimaryInstURL")));
			}
			
			rs.close();
			// Calculamos el número de filas totales que resultan de la búsqueda.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next())
				this.filas = rs.getInt(1);
			
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
			
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Healthtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param date: date -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Healthtopic> listadoHealthtopicCampo(String sql, java.sql.Date date) {
		ArrayList <Healthtopic> lista = new ArrayList<Healthtopic>();
		
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setDate(1, date);
			rs = stm.executeQuery();
			
			while (rs.next()){
				lista.add(new Healthtopic(rs.getInt("Id"), rs.getString("HealthTopic"),
									  rs.getString ("Language"), rs.getDate("Datecreated"),
									  rs.getString("URL"), rs.getString("Summary"),
									  rs.getString("PrimaryInst"), rs.getString("PrimaryInstURL")));
			}
			
			rs.close();
			// Calculamos el número de filas totales que resultan de la búsqueda.
			stm = conn.prepareStatement("SELECT FOUND_ROWS()");
			rs = stm.executeQuery();
			if(rs.next())
				this.filas = rs.getInt(1);
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
			
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Group' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int  -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Group> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Group> listadoGroup(String sql, int id) {
		ArrayList <Group> lista = new ArrayList<Group>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Group(rs.getInt("HealthTopic_Id"), rs.getInt("idGroup"), rs.getString("name"), rs.getString("url")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
	*/

	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Languagemappedtopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<languagemappedtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Languagemappedtopic> listadoAlsoMapped(String sql,
			int id) {
		ArrayList <Languagemappedtopic> lista = new ArrayList<Languagemappedtopic>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Languagemappedtopic(rs.getInt("HealthTopics_Id"), rs.getInt("idLanguageMappedTopic"), rs.getString("URL"), rs.getString("Language"), rs.getString("Name")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
	*/

	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Meshheading' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Meshheading> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Meshheading> listadoMeshheading(String sql, int id) {
		ArrayList <Meshheading> lista = new ArrayList<Meshheading>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Meshheading(rs.getInt("HealthTopic_Id"), rs.getString("idMeshHeading"), rs.getString("Descriptor")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'OtherLanguage' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Otherlanguage> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Otherlanguage> listadoOtherlanguage(String sql, int id) {
		ArrayList <Otherlanguage> lista = new ArrayList<Otherlanguage>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Otherlanguage(rs.getInt("HealthTopic_Id"), rs.getString("Name"), rs.getString("VernacularName"), rs.getString("URL")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'RelatedTopic' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Relatedtopic> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Relatedtopic> listadoRelatedtopic(String sql, int id) {
		ArrayList <Relatedtopic> lista = new ArrayList<Relatedtopic>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Relatedtopic(rs.getInt("HealthTopic_Id"), rs.getInt("idRelatedTopic"), rs.getString("Name"), rs.getString("URL")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Seereference' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<seereference> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Seereference> listadoSeereference(String sql, int id) {
		ArrayList <Seereference> lista = new ArrayList<Seereference>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Seereference(rs.getInt("HealthTopic_Id"), rs.getString("Reference")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Site' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<site> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Site> listadoSite(String sql, int id) {
		ArrayList <Site> lista = new ArrayList<Site>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Site(rs.getInt("URL_Id"), rs.getInt("HealthTopic_Id"), rs.getString("URL"), rs.getString("Name"), rs.getString("LanguageMappedURL")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Sitedescription' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<sitedescription> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Sitedescription> listadoSiteDescription(String sql,
			int id) {
		ArrayList <Sitedescription> lista = new ArrayList<Sitedescription>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Sitedescription(rs.getInt("Site_URL_Id"), rs.getString("Description")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Siteinfocategory' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Siteinfocategory> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Siteinfocategory> listadoSiteinfocategory(String sql,
			int id) {
		ArrayList <Siteinfocategory> lista = new ArrayList<Siteinfocategory>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Siteinfocategory(rs.getInt("Site_URL_Id"), rs.getString("InfoCategory")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
*/
	/**
	 * Método que, dados unas criterios de búsqueda concretos sobre la tabla 
	 * 'Siteorganization' de la BD 'medlinebennett' de mySQL, devuelve una lista que
	 * corresponde a los registros de esa tabla que serán mostrados en una
	 * página de la vista asociada a esa búsqueda.
	 * @param sql: String -> Sentencia SQL
	 * @param id: int -> Valor que se busca en un campo determinado.
	 * @return lista: ArrayList<Siteorganization> -> Lista de registros para mostrar en la
	 *                                      página actual de la vista.
	 */
	/*
	public ArrayList<Siteorganization> listadoSiteorganization(String sql,
			int id) {
		ArrayList <Siteorganization> lista = new ArrayList<Siteorganization>();
		ResultSet rs;
		try{
			//Utilizamos prepareStatement para tener mucha más seguridad.
			stm = conn.prepareStatement(sql);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while (rs.next()){
				lista.add(new Siteorganization(rs.getInt("Site_URL_Id"), rs.getString("Organization")));
			}
			rs.close();
		}catch (SQLException e){
			lista = null;
			throw new HealthtopicException("Error al cerrar la conexion: " + e.getMessage());
		}catch(Exception e){
			lista = null;
			throw new HealthtopicException("Error indefinido. " + e.getMessage());
		}
		return lista;
	}
}
	*/