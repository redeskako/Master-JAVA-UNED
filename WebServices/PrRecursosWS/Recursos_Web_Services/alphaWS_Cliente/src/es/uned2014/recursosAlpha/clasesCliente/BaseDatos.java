package es.uned2014.recursosAlpha.clasesCliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.uned2014.recursosAlpha.clasesCliente.Usuario;

/**
 * Clase BaseDatos es un javabean que se encarga de abrir/cerrar la conexión con la base de datos,
 * así como de realizar diferentes consultas a base de datos. 
 * 
 * Para realizar pruebas, se ha dado de alta un usuario:
 * 	usuario: csanchez
 *  clave: alpha
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre-Octubre 2014
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
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/recursos_alpha_cliente?user=alef&password=alef");
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
	 * Se realiza una consulta a la base de datos, y se obtiene un array de usuarios
	 * @param sql String con la consulta sql
	 * @return el array de Usuarios, resultado de la consulta
	 * @throws SQLException si existe algún error en el SQL
	 * @throws Exception si se produce algún error al realizar la consulta a la base de datos
	 */
	@SuppressWarnings("finally")
	public ArrayList<Usuario> consultarUsuarios(String sql) {
		
		ArrayList<Usuario> arrayUsuario = new ArrayList<Usuario>();
		
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			
			// Se itera por la tabla para convertirlo en array
			while ( rs.next() ) {
				Usuario u = new Usuario (rs.getInt("IdUsuario"), rs.getString("NombreUsuario"), 
						rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Contrasena"));
				arrayUsuario.add(u);
			} // fin while
		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return arrayUsuario;
		}	
	} // fin consultarUsuarios

}
