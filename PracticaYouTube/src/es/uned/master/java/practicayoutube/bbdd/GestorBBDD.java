/**
 * 
 */
package es.uned.master.java.practicayoutube.bbdd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gdata.util.ResourceNotFoundException;

import es.uned.master.java.practicayoutube.Canal;
import es.uned.master.java.practicayoutube.Tag;
import es.uned.master.java.practicayoutube.Video;

/**
 * Clase encargada de gestionar la Base de Datos. 
 * Todos los accesos a Base de Datos se realizan desde esta clase
 * 
 * @author Grupo Alef (José Torrecila)
 *
 */
public class GestorBBDD {

	/**
	 * Constante que permite acceder al recurso en el que se define la conexión a la Base de Datos
	 */
	private static final String DATASOURCE_CONTEXT = "java:comp/env/jdbc/PracticaYoutubeDB";
	
	/**
	 * Constante en la que se almacena la ruta para el fichero PracticaYouTubeInicializaBBDD.sql.
	 * Este fichero define la estructura de la Base de Datos
	 */
	private static final String FICHERO_INICIALIZA_BBDD = "resources/PracticaYouTubeInicializaBBDD.sql";
	
	/**
	 * Al ejecutar la aplicación, se crea una única instancia de esta clase.
	 * En esta variable se almacena esta única instancia, que será la usada cada vez que sea necesario acceder a la Base de Datos 
	 */
	private static GestorBBDD gestor = null;

	/**
	 * Conexión utilizada para acceder a la Base de Datos.
	 * Siempre que sea posible (salvo que se cierre la conexión por algún motivo) se utilizará siempre la misma 
	 */
	private Connection conexion;

	/**
	 *  Variable en la que se almacena la memoria caché de la tabla Canales.
	 *  Se relaciona un String (el Identificador del Canal - canalId) con un WeakReference a ese canal como objeto Java.
	 *  De esta forma, si existe un objeto Canal previo con ese mismo canalId, se 'reutiliza', evitando acceder de nuevo a la Base de Datos
	 */
	private Map<String,WeakReference<Canal>> cacheCanales = new HashMap<String,WeakReference<Canal>>();
	
	/**
	 *  Variable en la que se almacena la memoria caché de la tabla Videos.
	 *  Se relaciona un String (el Identificador del Video - videoId) con un WeakReference a ese vídeo como objeto Java.
	 *  De esta forma, si existe un objeto Video previo con ese mismo videoId, se 'reutiliza', evitando acceder de nuevo a la Base de Datos
	 */
	private Map<String,WeakReference<Video>> cacheVideos = new HashMap<String,WeakReference<Video>>();
	
	/**
	 *  Variable en la que se almacena la memoria caché de la tabla Tags.
	 *  Se relaciona un String (el Identificador del Tag - tagId) con un WeakReference a ese tag como objeto Java.
	 *  De esta forma, si existe un objeto Tag previo con ese mismo tagId, se 'reutiliza', evitando acceder de nuevo a la Base de Datos
	 */
	private Map<String,WeakReference<Tag>> cacheTags = new HashMap<String,WeakReference<Tag>>();
	
	/**
	 * Método estático que devuelve un objeto GestorBBDD.
	 * Normalmente este objeto será siempre el mismo, se crea en la primera llamada y después se devuelve siempre ese mismo objeto.
	 * Este método es el que debe usarse cuando necisitamos acceder directamente a la Base de Datos
	 * @return Una instancia de GestorBBDD. Normalmente siempre será la misma
	 * @throws NamingException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ResourceNotFoundException 
	 */
	public static GestorBBDD obtenerGestorBBDD() throws NamingException, SQLException, IOException, ResourceNotFoundException {
		if (gestor == null) {
			gestor = new GestorBBDD();
		}
		System.out.println("Obtenida la instancia de GestorBDDD");
		return gestor;
	}

	/**
	 * Constructor (privado) de la clase.
	 * Para utilizar una instancia de GestorBBDD se debe usar el método estático obtenerGestorBBDD(), no este constructor.
	 * Esto es así para asegurar que siempre se acceda a la Base de Datos con el mismo GestorBBDD, ya que no es necesario tener varios
	 * @throws NamingException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	private GestorBBDD() throws NamingException, SQLException, IOException, ResourceNotFoundException {
		String iniciaSQL = leerFicheroInicializaBBDD();
		List<String> querysIniciaSQL = trocearScriptSQL(iniciaSQL);
		for (String query:querysIniciaSQL) {
			ejecutarQuerySinResultSet(query);
		}
	}

	/**
	 * Método finalize() que cierra la 'conexion' si todavía no se ha cerrado
	 */
	protected void finalize() throws Throwable {
	    // aquí va el código de limpieza de esta clase
		if (conexion != null && !conexion.isClosed()) {
			conexion.close();
		}
	    super.finalize();
	}
	
	/**
	 * Método que devuelve una conexión a la Base de Datos
	 * Normalmente será siempre la misma conexión que se reutiliza
	 * @return Objeto Connection conextado a la Base de Datos. Normalmente será siempre el mismo objeto
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ResourceNotFoundException
	 */
	private Connection getConexion() throws NamingException, SQLException, ResourceNotFoundException {
		if (conexion != null && !conexion.isClosed()) {
			return conexion;
		}

		Connection resultado = null;
		Context initialContext = new InitialContext();
		DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
		if (datasource != null) {
			resultado = datasource.getConnection();
			conexion = resultado;
		} else {
			System.out.println("Fallo al buscar datasource");
			throw new ResourceNotFoundException("Fallo al buscar datasource");
		}

		return resultado;
	}

	/**
	 * Método que lee el fichero sql que define la estructura de la Base de Datos
	 * @return String con el contenido del fichero sql que define la estructura de la Base de Datos 
	 * @throws IOException
	 */
	private String leerFicheroInicializaBBDD() throws IOException {
		StringBuffer resultado = new StringBuffer();
		String subCadena;

		BufferedReader bf = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(FICHERO_INICIALIZA_BBDD)));

		while ((subCadena = bf.readLine())!=null) {
			resultado = resultado.append(subCadena + "\n"); 
		}
		return resultado.toString();
	}

	/**
	 * Ejecuta una query contra la Base de Datos
	 * Útil sólo cuando no necesitas el ResultSet para nada (update, insert, delete...)
	 * @param query Query que se va a ejecutar
	 * @return Devuelve lo mismo que el método executeUpdate de Statement (es decir, el número de registros afectados)
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	private int ejecutarQuerySinResultSet (String query) throws SQLException, NamingException, ResourceNotFoundException {
		System.out.println("Se va a ajecutar la query: " + query);
		Connection c = null;
		Statement consulta = null;
		try	{
			c = getConexion();
			consulta = c.createStatement();
			return consulta.executeUpdate(query);
		} catch(SQLException e){
			throw new SQLException(query);
		} catch (NamingException e) {
			throw e;
		} finally {
			if (consulta != null) {
				consulta.close();
			}
		}
	}

	/**
	 * Método que toma un String que contiene varias sentencias sql (acabadas en ';') 
	 * y devuelve una colección de String con las sentencias individuales
	 * @param script String que contiene varias sentencias sql (acabadas en ';')
	 * @return Colección de String con las sentencias individuales incluídas en 'script'
	 */
	private List<String> trocearScriptSQL(String script){
        // here is our splitter ! We use ";" as a delimiter for each request  
        // then we are sure to have well formed statements  
        String[] querys = script.toString().split(";");
        ArrayList<String> resultado = new ArrayList<String>();
        
        for(String query:querys) {  
        	//"limpiamos" las querys
        	query = query.trim();
            if(!query.equals("")) {
            	//Si está vacía no hay que tenerla en cuenta
                resultado.add(query);    
            }  
        }  
		return resultado;
	}
	
	/**
	 * Método privado que modifica una cadena sustituyento las comillas simples por \' para que los INSERT no fallen la introducir los datos en la Base de Datos
	 * @param cadena cadena en la que se realiza la sustitución
	 */
	private String prepararStringParaSql(String cadena) {
		return cadena.replaceAll("'","''");
	}
	
	/**
	 * Método que graba un Canal en la tabla CANALES de la Base de Datos.
	 * Graba sólo en la tabla CANALES, no graba las relaciones con los videos del canal, ni los videos en sí 
	 * @param canal Canal que se graba en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un INSERT, 'false' si ya existía el registro y se ha hecho UPDATE
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarEnTablaCanales(Canal canal) throws SQLException, NamingException, ResourceNotFoundException {
		String channelId = canal.getCanalId();
		Canal canalEnBBDD = buscarEnTablaCanales(channelId);
		
		boolean insert;
		String sql;
		if (canalEnBBDD != null) {
			// Debemos hacer update, por lo que insert = false;
			insert = false;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Canal (que se modificará)
			//Como ahora mismo el único dato que hay en el channelId, que es la clave... En realidad no hay que actualizar nada
			//Cuando se graben más datos, sí habrá que actualizar
			
		} else {
			// canalEnBBDD no existe, luego debemos hacer INSERT
			insert = true;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Canal (que se modificará)
			sql = "INSERT INTO CANALES (CHANNEL_ID_PK) VALUES ('" + prepararStringParaSql(channelId) + "');";
			ejecutarQuerySinResultSet(sql);
		}
		
		//Debemos actualizar la caché:
		cacheCanales.put(channelId, new WeakReference<Canal>(canal));
		
		return insert;
	}
	

	/**
	 * Método que graba un Video en la tabla VIDEOS de la Base de Datos.
	 * Graba sólo en la tabla VIDEOS, no graba las relaciones con el Canal, ni con los Tags 
	 * @param video Video que se graba en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un INSERT, 'false' si ya existía el registro y se ha hecho UPDATE
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarEnTablaVideos(Video video) throws SQLException, NamingException, ResourceNotFoundException {
		String videoId = video.getVideoId();
		Video videoEnBBDD = buscarEnTablaVideos(videoId);
		
		boolean insert;
		String sql;
		if (videoEnBBDD != null) {
			// Debemos hacer update, por lo que insert = false;
			insert = false;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Video (que se modificará)
			sql = "UPDATE VIDEOS SET TITULO = '" + prepararStringParaSql(video.getTitulo()) + "' WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		} else {
			// videoEnBBDD no existe, luego debemos hacer INSERT
			insert = true;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Canal (que se modificará)
			sql = "INSERT INTO VIDEOS (VIDEO_ID_PK,TITULO) VALUES ('" + prepararStringParaSql(videoId) + "', '" + prepararStringParaSql(video.getTitulo()) + "');";
		}
		ejecutarQuerySinResultSet(sql);
		
		//Debemos actualizar la caché:
		cacheVideos.put(videoId, new WeakReference<Video>(video));
		
		return insert;
	}
	
	/**
	 * Método que graba un Tag en la tabla TAGS de la Base de Datos.
	 * Graba sólo en la tabla TAGS, no graba las relaciones los videos, ni los videos en sí
	 * @param tag Tag que se graba en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un INSERT, 'false' si ya existía el registro y se ha hecho UPDATE
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarEnTablaTags(Tag tag) throws SQLException, NamingException, ResourceNotFoundException {
		String tagId = tag.getTagId();
		Tag tagEnBBDD = buscarEnTablaTags(tagId);
		
		boolean insert;
		String sql;
		if (tagEnBBDD != null) {
			// Debemos hacer update, por lo que insert = false;
			insert = false;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Tag (que se modificará)
			// Como ahora mismo el único dato que hay en el tagId, que es la clave... En realidad no hay que actualizar nada
			// Cuando se graben más datos, sí habrá que actualizar
		} else {
			// tagEnBBDD no existe, luego debemos hacer INSERT
			insert = true;
			// TODO: Habrá que cambiar cosas cuando se modifique la clase Tag (que se modificará)
			sql = "INSERT INTO TAGS (TAG_ID_PK) VALUES ('" + prepararStringParaSql(tagId) + "');";
			ejecutarQuerySinResultSet(sql);
		}
		
		//Debemos actualizar la caché:
		cacheTags.put(tagId, new WeakReference<Tag>(tag));
		
		return insert;
	}
	
	/**
	 * Método que graba la asociación entre un Video y su Canal en la tabla VIDEOS de la Base de Datos.
	 * También comprueba que existen en la Base de Datos el video y el canal, para evitar problemas de que alguno de ellos no exista (la Base de Datos quedaría incoherente)
	 * @param video Video que se graba en la Base de Datos
	 * @param canal Canal que se graba en la Base de Datos
	 * @return 'true' si la asociación se ha dado de alta en la Base de Datos sin problema. 'false' si no se ha dado de alta la asociación porque el canal o el vídeo no existían en la Base de Datos previamente
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarAsociacionVideoCanal(Video video, Canal canal) throws SQLException, NamingException, ResourceNotFoundException {
		//Los guardo en la BBDD por si no existían
		//grabarEnTablaCanales(canal);
		//grabarEnTablaVideos(video);
		
		String videoId = video.getVideoId();
		String canalId = canal.getCanalId();

		if (!existeEnTablaVideos(videoId) || !existeEnTablaCanales(canalId) ) {
			return false;
		}
		
		String sql = "UPDATE VIDEOS SET CHANNEL_ID_FK = '" + prepararStringParaSql(canalId) + "' WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		ejecutarQuerySinResultSet(sql);
		return true;
	}


	/**
	 * Método que graba la asociación entre un Video y un Tag en la tabla VIDEO_TAG_MAPS de la Base de Datos.
	 * También comprueba que existen en la Base de Datos el video y el tag, para evitar problemas de que alguno de ellos no exista (la Base de Datos quedaría incoherente)
	 * @param video Video que se graba en la Base de Datos
	 * @param tag Tag que se graba en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un INSERT, 'false' si no se ha hecho el INSERT porque ya existía la asociación en la Base de Datos o porque alguno de los componentes de la asociación (vídeo o tag) no existían previamente en la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean grabarAsociacionVideoTag(Video video, Tag tag) throws SQLException, NamingException, ResourceNotFoundException {
		//Los guardo en la BBDD por si no existían
		//grabarEnTablaVideos(video);
		//grabarEnTablaTags(tag);
		
		String videoId = video.getVideoId();
		String tagId = tag.getTagId();
		
		if (!existeEnTablaVideos(videoId) || !existeEnTablaTags(tagId) || existeAsociacionVideoTag(videoId,tagId)) {
			return false;
		}
		
		String sql = "INSERT INTO VIDEO_TAG_MAPS (VIDEO_ID_FK,TAG_ID_FK) VALUES ('" + prepararStringParaSql(videoId) + "','" + prepararStringParaSql(tagId) + "');";
		ejecutarQuerySinResultSet(sql);
		return true;
	}

	/**
	 * Método que busca en la tabla CANALES todos los registros y los convierte en instancias de la clase Canal.
	 * Admite paginación 
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de instancias de la clase Canal obtenidas de la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Canal> buscarTodosLosCanales(int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Canal> listaCanales = new ArrayList<Canal>();
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT CHANNEL_ID_PK FROM CANALES "
				+ "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String canalIdEnBBDD = rs.getString("CHANNEL_ID_PK");
				Canal canal = buscarEnTablaCanales(canalIdEnBBDD);
				if (canal != null) {
					listaCanales.add(canal);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaCanales;
	}
	
	/**
	 * Método que cuenta el número de registros de la tabla CANALES en la Base de Datos.
	 * Es útil para utilizar la paginación en el método buscarTodosLosCanales()
	 * @return Número de registros de la tabla CANALES en la Base de Datos.
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarTodosLosCanales() throws SQLException, NamingException, ResourceNotFoundException {

		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;

		String query = "SELECT COUNT(*) as numero FROM CANALES;";  
				
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");
					
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}

	}
	
	/**
	 * Método que busca en la tabla VIDEOS todos los registros y los convierte en instancias de la clase Video.
	 * Admite paginación
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de instancias de la clase Video obtenidas de la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> buscarTodosLosVideos(int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Video> listaVideos = new ArrayList<Video>();
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT VIDEO_ID_PK FROM VIDEOS "
				+ "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String videoIdEnBBDD = rs.getString("VIDEO_ID_PK");
				Video video = buscarEnTablaVideos(videoIdEnBBDD);
				if (video != null) {
					listaVideos.add(video);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaVideos;
	}
	
	
	/**
	 * Método que cuenta el número de registros de la tabla VIDEOS en la Base de Datos.
	 * Es útil para utilizar la paginación en el método buscarTodosLosVideos()
	 * @return Número de registros de la tabla VIDEOS en la Base de Datos.
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarTodosLosVideos() throws SQLException, NamingException, ResourceNotFoundException {

		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;

		String query = "SELECT COUNT(*) as numero FROM VIDEOS;";  
				
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");
					
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}

	}
	
	/**
	 * Método que busca en la tabla TAGS todos los registros y los convierte en instancias de la clase Tag.
	 * Admite paginación
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de instancias de la clase Tag obtenidas de la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Tag> buscarTodosLosTags(int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Tag> listaTags = new ArrayList<Tag>();
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT TAG_ID_PK FROM TAGS "
				+ "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String tagIdEnBBDD = rs.getString("TAG_ID_PK");
				Tag tag = buscarEnTablaTags(tagIdEnBBDD);
				if (tag != null) {
					listaTags.add(tag);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaTags;
	}

	/**
	 * Método que cuenta el número de registros de la tabla TAGS en la Base de Datos.
	 * Es útil para utilizar la paginación en el método buscarTodosLosTags()
	 * @return Número de registros de la tabla TAGS en la Base de Datos.
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarTodosLosTags() throws SQLException, NamingException, ResourceNotFoundException {

		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;

		String query = "SELECT COUNT(*) as numero FROM TAGS;";  
				
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");
					
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}

	}
	
	/**
	 * Método que busca en la tabla CANALES si existe el un canal con el canalId que se pasa como parámetro
	 * Si existe, crea el objeto Canal correspondiente. Si no existe, devuelve 'null' 
	 * @param channelId canalId (CHANNEL_ID_PK) que se busca en la tabla CANALES 
	 * @return Instancia de Canal correspondiente al canalId pasado como parámetro. Si no existe en la Base de Datos, devuelve 'null'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public Canal buscarEnTablaCanales(String channelId) throws SQLException, NamingException, ResourceNotFoundException {
		
		if (!existeEnTablaCanales(channelId)) {
			//Si no existe en la tabla, devolvemos null
			return null;
		}
		
		if (cacheCanales.containsKey(channelId) && cacheCanales.get(channelId).get() != null) {
			//Si existe en la caché, devolvemos el objeto sin más
			return cacheCanales.get(channelId).get();
		}
		
		//Ahora tenemos que consultar en la BBDD por el channelId que nos han pasado, y contruir un Canal a partir de los datos
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT CHANNEL_ID_PK, TITULO FROM CANALES WHERE CHANNEL_ID_PK = '" + prepararStringParaSql(channelId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			if (!rs.next()) {
				// No hemos encontrado ningún registro
				return null;
			}

			// TODO: Cuando haya cambios en la clase Canal (que los habrá) hay que modificar esta parte en la que se crea el Canal.
			// También habrá que crear las relaciones dentro del nuevo Canal creado
			String channelIdEnBBDD = rs.getString("CHANNEL_ID_PK");
			Canal canalEnBBDD = new Canal(channelIdEnBBDD);


			//Añadimos canalEnBBDD a la caché, por si hace falta dentro de poco no tener que volver a consultar en la BBDD
			cacheCanales.put(channelId, new WeakReference<Canal>(canalEnBBDD));
			return canalEnBBDD;			
			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	/**
	 * Método que busca en la tabla Videos si existe el un video con el videoId que se pasa como parámetro
	 * Si existe, crea el objeto Video correspondiente. Si no existe, devuelve 'null' 
	 * @param videoId videoId (VIDEO_ID_PK) que se busca en la tabla VIDEOS
	 * @return Instancia de Video correspondiente al videoId pasado como parámetro. Si no existe en la Base de Datos, devuelve 'null'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public Video buscarEnTablaVideos(String videoId) throws SQLException, NamingException, ResourceNotFoundException {
		
		if (!existeEnTablaVideos(videoId)) {
			//Si no existe en la tabla, devolvemos null
			return null;
		}
		
		if (cacheVideos.containsKey(videoId) && cacheVideos.get(videoId).get() != null) {
			//Si existe en la caché, devolvemos el objeto sin más
			return cacheVideos.get(videoId).get();
		}
		
		//Ahora tenemos que consultar en la BBDD por el channelId que nos han pasado, y contruir un Video a partir de los datos
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT VIDEO_ID_PK, CHANNEL_ID_FK, TITULO FROM VIDEOS WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			if (!rs.next()) {
				// No hemos encontrado ningún registro
				return null;
			}
			
			// TODO: Cuando haya cambios en la clase Video (que los habrá) hay que modificar esta parte en la que se crea el Video.
			// También habrá que crear las relaciones dentro del nuevo Video creado
			String tituloEnBBDD = rs.getString("TITULO");
			Video videoEnBBDD = new Video(videoId,tituloEnBBDD);
			
			
			//Añadimos videoEnBBDD a la caché, por si hace falta dentro de poco no tener que volver a consultar en la BBDD
			cacheVideos.put(videoId, new WeakReference<Video>(videoEnBBDD));
			return videoEnBBDD;
			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	/**
	 * Método que busca en la tabla Tags si existe el un tag con el tagId que se pasa como parámetro
	 * Si existe, crea el objeto Tag correspondiente. Si no existe, devuelve 'null' 
	 * @param tagId tagId (TAG_ID_PK) que se busca en la tabla TAGS
	 * @return Instancia de Tag correspondiente al tagId pasado como parámetro. Si no existe en la Base de Datos, devuelve 'null'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public Tag buscarEnTablaTags(String tagId) throws SQLException, NamingException, ResourceNotFoundException {
		
		if (!existeEnTablaTags(tagId)) {
			//Si no existe en la tabla, devolvemos null
			return null;
		}
		
		if (cacheTags.containsKey(tagId) && cacheTags.get(tagId).get() != null) {
			//Si existe en la caché, devolvemos el objeto sin más
			return cacheTags.get(tagId).get();
		}
		
		//Ahora tenemos que consultar en la BBDD por el channelId que nos han pasado, y contruir un Video a partir de los datos
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT TAG_ID_PK FROM TAGS WHERE TAG_ID_PK = '" + prepararStringParaSql(tagId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			if (!rs.next()) {
				// No hemos encontrado ningún registro
				return null;
			}
			
			// TODO: Cuando haya cambios en la clase Tag (que los habrá) hay que modificar esta parte en la que se crea el Tag.
			// También habrá que crear las relaciones dentro del nuevo Tag creado
			String tagIdEnBBDD = rs.getString("TAG_ID_PK");
			Tag tagEnBBDD = new Tag(tagIdEnBBDD);
			
			
			//Añadimos tagEnBBDD a la caché, por si hace falta dentro de poco no tener que volver a consultar en la BBDD
			cacheTags.put(tagId, new WeakReference<Tag>(tagEnBBDD));
			return tagEnBBDD;
			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	
	/**
	 * Busca en la BBDD el canal asociado al video que se pasa como parámetro. Devuelve 'null' si no lo encuentra en la Base de Datos
	 * @param video video para el que se busca su Canal
	 * @return Instancia de la clase Canal que correspondiente al video que se ha pasado como parámetro 
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public Canal buscarCanalDeVideo(Video video) throws SQLException, NamingException, ResourceNotFoundException {
		Canal canal = null;
		String videoId = video.getVideoId();
		
		//Ahora tenemos que consultar en la BBDD por el videolId que nos han pasado, y obtener el channelId asociado de la tabla VIDEOS
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT CHANNEL_ID_FK FROM VIDEOS WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			if (!rs.next()) {
				// No hemos encontrado ningún registro
				return null;
			}
			
			String channelIdEnBBDD = rs.getString("CHANNEL_ID_FK");
			canal = buscarEnTablaCanales(channelIdEnBBDD);			
			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return canal;
	}
	
	/**
	 * Busca en la BBDD los videos asociados al canal que se pasa como parámetro
	 * @param canal canal para el que se buscan sus videos
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de videos asociados al canal que se ha pasado como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> buscarVideosDeCanal(Canal canal, int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Video> listaVideos = new ArrayList<Video>();
		String canalId = canal.getCanalId();
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT VIDEO_ID_PK FROM VIDEOS WHERE CHANNEL_ID_FK = '" + prepararStringParaSql(canalId) + "' "  
				 + "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String videoIdEnBBDD = rs.getString("VIDEO_ID_PK");
				Video video = buscarEnTablaVideos(videoIdEnBBDD);
				if (video != null) {
					listaVideos.add(video);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaVideos;
	}
	
	/**
	 * Método que cuenta el número de Videos en la Base de Datos que tienen como canal el que se pasa como parámetro.
	 * Es útil para utilizar la paginación en el método buscarVideosDeCanal(Canal,int,int)
	 * @param canal canal para el que se cuentan sus videos
	 * @return número de Videos en la Base de Datos que tienen como canal el que se pasa como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarVideosDeCanal(Canal canal) throws SQLException, NamingException, ResourceNotFoundException {
		String canalId = canal.getCanalId();

		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;

		String query = "SELECT COUNT(*) as numero FROM VIDEOS WHERE CHANNEL_ID_FK = '" + prepararStringParaSql(canalId) + "';";  
				
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");
					
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}

	}
	
	/**
	 * Busca en la BBDD los tags asociados al video que se pasa como parámetro
	 * @param video video para el que se buscan sus tags
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de tags asociados al video que se ha pasado como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Tag> buscarTagsDeVideo(Video video, int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Tag> listaTags = new ArrayList<Tag>();
		String videoId = video.getVideoId();
		
		//Ahora tenemos que consultar en la BBDD por el videolId que nos han pasado, y obtener los tagId asociados de la tabla VIDEO_TAG_MAPS
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT TAG_ID_FK FROM VIDEO_TAG_MAPS WHERE VIDEO_ID_FK = '" + prepararStringParaSql(videoId) + "' " 
				+ "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String tagIdEnBBDD = rs.getString("TAG_ID_FK");
				Tag tag = buscarEnTablaTags(tagIdEnBBDD);
				if (tag != null) {
					listaTags.add(tag);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaTags;
	}

	/**
	 * Método que cuenta el número de Tags en la Base de Datos asociados al video que se pasa como parámetro.
	 * Es útil para utilizar la paginación en el método buscarTagsDeVideo(Video,int,int)
	 * @param video video para el que se cuentan sus tags
	 * @return número de Tags en la Base de Datos asociados al video que se pasa como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarTagsDeVideo(Video video) throws SQLException, NamingException, ResourceNotFoundException {

		String videoId = video.getVideoId();

		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;

		String query = "SELECT COUNT(*) AS numero FROM VIDEO_TAG_MAPS WHERE VIDEO_ID_FK = '" + prepararStringParaSql(videoId) + "';"; 

		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");			
	} catch(SQLException e){
		throw e;
	} catch (NamingException e) {
		throw e;
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (consulta != null) {
			consulta.close();
		}
	}
	}	
	
	/**
	 * Busca en la BBDD los videos asociados al tag que se pasa como parámetro
	 * @param tag tag para el que se buscan sus videos
	 * @param primerRegistro Primer registro devuelto (paginación)
	 * @param limite Número máximo de registros devueltos (paginación)
	 * @return Colección de videos asociados al tag que se ha pasado como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public List<Video> buscarVideosDeTag(Tag tag, int primerRegistro, int limite) throws SQLException, NamingException, ResourceNotFoundException {
		List<Video> listaVideos = new ArrayList<Video>();
		String tagId = tag.getTagId();
		
		//Ahora tenemos que consultar en la BBDD por el tagId que nos han pasado, y obtener los videoId asociados de la tabla VIDEO_TAG_MAPS
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT VIDEO_ID_FK FROM VIDEO_TAG_MAPS WHERE TAG_ID_FK = '" + prepararStringParaSql(tagId) + "' "
				+ "LIMIT " + primerRegistro + "," + limite + ";";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			while (rs.next()) {
				String videoIdEnBBDD = rs.getString("VIDEO_ID_FK");
				Video video = buscarEnTablaVideos(videoIdEnBBDD);
				if (video != null) {
					listaVideos.add(video);
				}
			}			
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
		return listaVideos;
	}
	
	/**
	 * Método que cuenta el número de Videos en la Base de Datos asociados al tag que se pasa como parámetro.
	 * Es útil para utilizar la paginación en el método buscarVideossDeTag(Tag,int,int)	
	 * @param tag tag para el que se cuentan sus videos
	 * @return número de Videos en la Base de Datos asociados al tag que se pasa como parámetro
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public int contarVideosDeTag(Tag tag) throws SQLException, NamingException, ResourceNotFoundException {

		String tagId = tag.getTagId();
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT COUNT(*) AS numero FROM VIDEO_TAG_MAPS WHERE TAG_ID_FK = '" + prepararStringParaSql(tagId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			return rs.getInt("numero");
					
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
		
	}
	
	/**
	 * Método que comprueba si existe una registro en la tabla CANALES con el canalId que se pasa como parámetro
	 * @param channelId canalId que se busca en la tabla CANALES  
	 * @return 'true' si en la tabla CANALES existe un canal con el canalId que se pasa como parámetro. Si no existe, devuelve 'false'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean existeEnTablaCanales(String channelId) throws SQLException, NamingException, ResourceNotFoundException {
		if (cacheCanales.containsKey(channelId) && cacheCanales.get(channelId).get() != null) {
			//Si existe en la caché, es que existe en la BBDD
			return true;
		}
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT COUNT(*) as numero FROM CANALES WHERE CHANNEL_ID_PK = '" + prepararStringParaSql(channelId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			int numero = rs.getInt("numero");
			if (numero > 0) {
				return true;
			}
			return false;	
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	/**
	 * Método que comprueba si existe una registro en la tabla VIDEOS con el videoId que se pasa como parámetro
	 * @param videoId videoId que se busca en la tabla VIDEOS  
	 * @return 'true' si en la tabla VIDEOS existe un video con el videoId que se pasa como parámetro. Si no existe, devuelve 'false'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean existeEnTablaVideos(String videoId) throws SQLException, NamingException, ResourceNotFoundException {
		if (cacheVideos.containsKey(videoId) && cacheVideos.get(videoId).get() != null) {
			//Si existe en la caché, es que existe en la BBDD
			return true;
		}
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT COUNT(*) as numero FROM VIDEOS WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			int numero = rs.getInt("numero");
			if (numero > 0) {
				return true;
			}
			return false;	
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	/**
	 * Método que comprueba si existe una registro en la tabla TAGS con el tagId que se pasa como parámetro
	 * @param tagId tagId que se busca en la tabla TAGS  
	 * @return 'true' si en la tabla TAGS existe un tag con el tagId que se pasa como parámetro. Si no existe, devuelve 'false'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean existeEnTablaTags(String tagId) throws SQLException, NamingException, ResourceNotFoundException {
		if (cacheTags.containsKey(tagId) && cacheTags.get(tagId).get() != null) {
			//Si existe en la caché, es que existe en la BBDD
			return true;
		}
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT COUNT(*) as numero FROM TAGS WHERE TAG_ID_PK = '" + prepararStringParaSql(tagId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			int numero = rs.getInt("numero");
			if (numero > 0) {
				return true;
			}
			return false;	
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}

	/**
	 * Método que comprueba si existe en la Base de Datos la relación entre el video representado por 'videoId' y el tag representado por 'tagId' que se pasan como parámetro.
	 * Para ello se consulta la tabla VIDEO_TAG_MAPS
	 * @param videoId videoId que se busca en la Base de Datos
	 * @param tagId tagId que se busca en la Base de Datos
	 * @return 'true' si en la tabla VIDEO_TAG_MAPS existe la relación entre 'videoId' y 'tagId'. Si no existe, devuelve 'false'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean existeAsociacionVideoTag(String videoId, String tagId) throws SQLException, NamingException, ResourceNotFoundException {
		
		Connection c = null;
		ResultSet rs = null;
		Statement consulta = null;
		
		String query = "SELECT COUNT(*) as numero FROM VIDEO_TAG_MAPS WHERE VIDEO_ID_FK = '" + prepararStringParaSql(videoId) + "' AND TAG_ID_FK = '" + prepararStringParaSql(tagId) + "';";
		
		try	{
			c = getConexion();
			consulta = c.createStatement();
			rs = consulta.executeQuery(query);
			rs.next();
			int numero = rs.getInt("numero");
			if (numero > 0) {
				return true;
			}
			return false;	
		} catch(SQLException e){
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (consulta != null) {
				consulta.close();
			}
		}
	}
	
	
	/**
	 * Método que borra la asociación entre un Video y un Tag en la tabla VIDEO_TAG_MAPS de la Base de Datos.
	 * @param video Video que se borra (la relación) en la Base de Datos
	 * @param tag Tag que se borra (la relación) en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un DELETE, 'false' si no se ha hecho el DELETE porque no existía la asociación previamente en la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean borrarAsociacionVideoTag(Video video, Tag tag) throws ResourceNotFoundException, SQLException, NamingException {	
		String videoId = video.getVideoId();
		String tagId = tag.getTagId();
		
		if (!existeAsociacionVideoTag(videoId,tagId)) {
			return false;
		}
		
		String sql = "DELETE FROM VIDEO_TAG_MAPS WHERE VIDEO_ID_FK = '" + prepararStringParaSql(videoId) + "' " 
				+ " AND TAG_ID_FK = '" + prepararStringParaSql(tagId) + "';";
		ejecutarQuerySinResultSet(sql);
		return true;
	}
	
	/**
	 * Método que borra un tag en la tabla TAGS de la Base de Datos.
	 * También borra las relaciones de este tag con los vídeos de la tabla VIDEO_TAG_MAPS
	 * @param tag Tag que se borra en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un DELETE, 'false' si no se ha hecho el DELETE porque no existía el tag previamente en la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean borrarTag(Tag tag) throws ResourceNotFoundException, SQLException, NamingException {	
		String tagId = tag.getTagId();
		
		if (!existeEnTablaTags(tagId)) {
			return false;
		}
		
		String sql;
		
		//Primero borramos todas las relaciones de ese tag
		if (contarVideosDeTag(tag)>0) {
			sql = "DELETE FROM VIDEO_TAG_MAPS WHERE TAG_ID_FK = '" + prepararStringParaSql(tagId) + "';";
			ejecutarQuerySinResultSet(sql);
		}
		
		sql = "DELETE FROM TAGS WHERE TAG_ID_PK = '" + prepararStringParaSql(tagId) + "';";
		ejecutarQuerySinResultSet(sql);
		return true;
	}
	
	/**
	 * Método que borra un video en la tabla VIDEOS de la Base de Datos.
	 * También borra las relaciones de este video con los tags de la tabla VIDEO_TAG_MAPS
	 * @param video Video que se borra en la Base de Datos
	 * @return Devuelve 'true' si se ha hecho un DELETE, 'false' si no se ha hecho el DELETE porque no existía el video previamente en la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean borrarVideo(Video video) throws ResourceNotFoundException, SQLException, NamingException {	
		String videoId = video.getVideoId();
		
		if (!existeEnTablaVideos(videoId)) {
			return false;
		}
		
		String sql;
		
		//Primero borramos todas las relaciones de ese video
		if (contarTagsDeVideo(video)>0) {
			sql = "DELETE FROM VIDEO_TAG_MAPS WHERE VIDEO_ID_FK = '" + prepararStringParaSql(videoId) + "';";
			ejecutarQuerySinResultSet(sql);
		}
		
		sql = "DELETE FROM VIDEOS WHERE VIDEO_ID_PK = '" + prepararStringParaSql(videoId) + "';";
		ejecutarQuerySinResultSet(sql);
		return true;
	}
	
	/**
	 * Método que borra un canal en la tabla CANALES de la Base de Datos.
	 * También borra TODOS LOS VIDEOS de ese canal.
	 * @param canal Canal que se borra en la Base de Datos (junto con todos sus vídeos)
	 * @return Devuelve 'true' si se ha hecho un DELETE, 'false' si no se ha hecho el DELETE porque no existía el canal previamente en la Base de Datos
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ResourceNotFoundException
	 */
	public boolean borrarCanal(Canal canal) throws ResourceNotFoundException, SQLException, NamingException {	
		String canalId = canal.getCanalId();
		
		if (!existeEnTablaCanales(canalId)) {
			return false;
		}
		
		String sql;
		
		//Primero borramos todos las vídeos de ese canal
		int numVideos =contarVideosDeCanal(canal);
		if (numVideos>0) {
			List<Video> videos = buscarVideosDeCanal(canal, 1, numVideos);
			for (Video video:videos) {
				borrarVideo(video);
			}
		}
		
		sql = "DELETE FROM CANALES WHERE CANAL_ID_PK = '" + prepararStringParaSql(canalId) + "';";
		ejecutarQuerySinResultSet(sql);
		return true;
	}
}
