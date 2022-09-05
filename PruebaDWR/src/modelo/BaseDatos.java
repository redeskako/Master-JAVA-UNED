package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




/**
 * Clase BaseDatos es un javabean que se encarga de abrir/cerrar la conexión con la base de datos,
 * así como de realizar diferentes consultas a base de datos. 
 * 
 * Para realizar pruebas, se ha dado de alta un usuario:
 * 	usuario: csanchez
 *  clave: alpha
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @since	Junio 2014
 */
public class BaseDatos {
	
	private Connection connection;
	private Statement statement;
	private boolean conectado; // Controla si se ha establecido o no conexión con la base de datos
	
	/**
	 * 	Método constructor: inicializa las variables de la clase.
	 */
	public BaseDatos(){
		this.inicializar();
	}
	
	/**
	 * Pone a cero/null los valores de las variables de la clase.
	 * @param null
	 * @return null
	 * @throws null
	 */
	private void inicializar(){
		this.connection = null;
		this.statement = null;
		this.conectado = false;
	}	
	
	/**
	 * Abre la conexión con la base de datos.
	 * @return boolean: true si se ha abierto la conexión, false si no se ha abierto
	 * @throws SQLException si existe algún error en el SQL
	 * @throws Exception si se produce algún error al realizar la conexión con la base de datos
	 */
	@SuppressWarnings("finally")
	public boolean abrirConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/recursos_alpha?user=alef&password=alef");
            this.statement = connection.createStatement();
            this.conectado=true;
		} catch (SQLException | ClassNotFoundException e){
			throw new ExcepcionRecursos("Error al abrir la conexión: " + e.getMessage());
		} catch (Exception e){
			throw new ExcepcionRecursos("Error indefinido: " + e.getMessage());
		} finally {
			return conectado;
		}	
	}
	
	/**
	 * Cierra la conexión con la base de datos.
	 * @throws SQLException si existe algún error en el SQL
	 * @throws Exception si se produce algún error al cerrar la conexión con la base de datos
	 */
	public void cerrarConexion(){
		try{
			// Se libera el Statement
			if (this.statement != null){
				this.statement.close();
			}
			
			// Se cierra la Connection
			if (this.connection != null){
				this.connection.close();
			}
		}catch (SQLException e){
			throw new ExcepcionRecursos("Error al cerrar la conexion: " + e.getMessage());
		}catch (Exception e){
			throw new ExcepcionRecursos("Error indefinido:" + e.getMessage());
		}finally{
			this.inicializar();
		}
	}
	
	/**
	 * Se realiza una consulta a la base de datos, y se obtiene un array de recursos
	 * @param sql String con la consulta sql
	 * @return el array de Recursos, resultado de la consulta
	 * @throws SQLException si existe algún error en el SQL
	 * @throws Exception si se produce algún error al realizar la consulta a la base de datos
	 */
	@SuppressWarnings("finally")
	public ArrayList<Recurso> consultarRecursos( String sql) {
		
		ArrayList<Recurso> array = new ArrayList<Recurso>();
		
		try {
			ResultSet result = this.statement.executeQuery(sql);
			
			// Se itera por la tabla para convertilo en array
			while ( result.next() ) {
				Recurso recurso = new Recurso(result.getInt("idRecurso"), result.getString("descripcion") );
				
				array.add(recurso);
			} // fin while
		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error indefinido. " + e.getMessage() );
		} finally {
			return array;
		}
	} // fin consultarRecursos
	

	
	/**
	 * Se obtiene el número de filas obtenidas al realizar una consulta a la base de datos.
	 * @param sql String con la consulta sql
	 * @return número de filas
	 * @throws SQLException si existe algún error en el SQL
	 * @throws Exception si se produce algún error al realizar la consulta a la base de datos
	 */
	@SuppressWarnings("finally")
	public int getNumeroFilas(String sql){
		int rowCount = 0;
		
		try{
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
	    	rowCount = rs.getInt(1);
		}catch(SQLException e){
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );	
		}catch (Exception e) {
			throw new ExcepcionRecursos( "Error indefinido. " + e.getMessage() );
		} finally {
			return rowCount;
		}
	}
	
}