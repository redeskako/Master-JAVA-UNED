package es.uned2014.recursosAlpha.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.uned2014.recursosAlpha.excepciones.*;
import es.uned2014.recursosAlpha.recurso.Recurso;
import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.*;

/**
 * Clase BaseDatos es un javabean que se encarga de abrir/cerrar conexión con la base de datos,
 * así como de realizar diferentes consultas a base de datos. 
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class BaseDatos {
	
	private Connection connection;
	private Statement statement;
	private boolean conectado; // Controla si se ha establecido o no conexión con la base de datos
	
	/**
	 * 	Método constructor inicializa las variables de la clase
	 */
	public BaseDatos(){
		this.inicializar();
	}
	
	/**
	 * Pone a cero / null los valores de las variables de la clase
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
	 * Se abre la conexión con la base de datos.
	 * @param null
	 * @return boolean true si se ha abierto la conexión, false si no se ha abierto
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public boolean abrirConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/recursos_alpha?user=root&password=admin");
            this.statement = connection.createStatement();
            this.conectado=true;
		} catch (SQLException | ClassNotFoundException e){
			throw new ExcepcionRecursos("Error al abrir la conexi�n: " + e.getMessage());
		} catch (Exception e){
			throw new ExcepcionRecursos("Error indefinido: " + e.getMessage());
		} finally {
			return conectado;
		}	
	}
	
	/**
	 * Se cierra la conexión con la base de datos.
	 * @param null
	 * @return null
	 * @throws excepciones si se produce algún error
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
	 * Se realiza una consulta a la base de datos, y se obtiene un array de reservas
	 * @param consulta sql
	 * @return el array de Reservas, resultado de la consulta
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public ArrayList<Reserva> consultarReservas(String sql){
		
		ArrayList <Reserva> array = new ArrayList <Reserva>();
		
		try{
			ResultSet result = this.statement.executeQuery(sql);

			// Se itera por la tabla para convertirlo en array:
			while (result.next()){			
				Reserva reserva = new Reserva(result.getInt("IdReserva"), result.getInt("IdUsuario"), 
						result.getInt("IdRecurso"), result.getTimestamp("Inicio"), result.getTimestamp("Final"), 
						result.getInt("IdEstado"), result.getString("NombreUsuario"), 
						result.getString("Descripcion"), result.getString("Estado"));
				array.add(reserva);
			}		
		}catch (SQLException e){
			throw new ExcepcionRecursos("Error en el SQL: " + e.getMessage());
		}catch(Exception e){
			throw new ExcepcionRecursos("Error indefinido. "+ e.getMessage());
		}finally{
			return array;
		}

	}

	/**
	 * Se realiza una consulta a la base de datos, y se obtiene un array de reservas que coinciden
	 * en fechas para el recurso de otra reserva.
	 * Si se devuelve un array vacío, no hay reservas incompatibles: validada OK
	 * Si se devuelve un array con algún elemento, hay reservas incompatibles: validada MAL
	 * @param consulta sql
	 * @return el array de Reservas, resultado de la consulta
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public ArrayList<Reserva> validarReservas(String sql){
		
		ArrayList <Reserva> array = new ArrayList <Reserva>();
		
		try{
			ResultSet result = this.statement.executeQuery(sql);

			// Se itera por la tabla para convertirlo en array:
			while (result.next()){			
				Reserva reserva = new Reserva(result.getInt("IdReserva"));
				array.add(reserva);
			}		
		}catch (SQLException e){
			throw new ExcepcionRecursos("Error en el SQL: " + e.getMessage());
		}catch(Exception e){
			throw new ExcepcionRecursos("Error indefinido. "+ e.getMessage());
		}finally{
			return array;
		}

	}
	
	/**
	 * Se realiza una consulta a la base de datos, y se obtiene un array de recursos
	 * @param consulta sql
	 * @return el array de Recursos, resultado de la consulta
	 * @throws excepciones si se produce algún error
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
	 * Se realiza una consulta a la base de datos, y se obtiene un array de usuarios
	 * @param consulta sql
	 * @return el array de Usuarios, resultado de la consulta
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public ArrayList<Usuario> consultarUsuarios(String sql) {
		
		ArrayList<Usuario> arrayUsuario = new ArrayList<Usuario>();
		
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			
			// Se itera por la tabla para convertirlo en array
			while ( rs.next() ) {
				Usuario u = new Usuario (rs.getInt("IdUsuario"), rs.getString("NombreUsuario"), 
						rs.getString("Nombre"), rs.getString("Apellidos"), rs.getString("Contrasena"), 
						rs.getInt("rol.IdRol"), rs.getString("rol.Rol"));
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

	/**
	 * Se realiza una consulta a la base de datos, para obtener todos los roles posibles.
	 * Se devuelve un array de usuarios estándar con todos los roles de usuarios posibles.
	 * @param consulta sql
	 * @return el array de Usuarios, resultado de la consulta
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public ArrayList<Usuario> consultarRoles(String sql) {
		
		ArrayList<Usuario> arrayUsuario = new ArrayList<Usuario>();
		
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			
			// Se itera por la tabla para convertirlo en array
			while ( rs.next() ) {
				Usuario u = new Usuario (rs.getInt("IdRol"), rs.getString("Rol"));
				arrayUsuario.add(u);
			} // fin while
		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return arrayUsuario;
		}
	} 
	
	/**
	 * Se realiza una consulta a la base de datos, para obtener todos los estados posibles.
	 * Se devuelve un array de reservas estándar con todos los estados posibles.
	 * @param consulta sql
	 * @return el array de Reservas, resultado de la consulta
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public ArrayList<Reserva> consultarEstados(String sql) {
		
		ArrayList<Reserva> arrayEstados = new ArrayList<Reserva>();
		
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			
			// Se itera por la tabla para convertirlo en array
			while ( rs.next() ) {
				Reserva r = new Reserva (rs.getInt("IdEstado"), rs.getString("Estado"));
				arrayEstados.add(r);
			} // fin while
		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return arrayEstados;
		}	
	} 
	
	/**
	 * Se obtiene el número de filas obtenidas al realizar una consulta a la base de datos.
	 * @param consulta sql
	 * @return número de filas
	 * @throws excepciones si se produce algún error
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
	
	/**
	 * Se ejecuta una acción sobre la base de datos para crear una nueva fila en una tabla.
	 * @param consulta sql
	 * @return nuevo id de la fila creada
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public int crear(String sql){
		
		// Por defecto, nuevo id = 0 (si se produce un error, se queda en cero)
		int idNuevo = 0;
				
		try {		
			// Se ejecuta la consulta y se obtiene la key de la fila creada
			this.statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			// Se recupera la key (idNuevo)
			ResultSet rs = this.statement.getGeneratedKeys();
			rs.next();
			idNuevo = rs.getInt(1);

		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return idNuevo;
		}
	}
	
	/**
	 * Se ejecuta una acción sobre la base de datos para editar una fila en una tabla.
	 * @param id de la fila a editar
	 * @param consulta sql
	 * @return nuevo id de la fila modificada
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public int editar(int id, String sql){
		
		// Por defecto, nuevo id = 0 (si se produce un error, se queda en cero)
		int idEditado = 0;
				
		try {		
			// Se ejecuta la consulta y se obtiene la key de la fila creada
			this.statement.executeUpdate(sql);
			
			// Si no da error, se recupera el id del elemento editado
			idEditado = id;

		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return idEditado;
		}
	}
	
	/**
	 * Se ejecuta una acción sobre la base de datos para eliminaruna fila en una tabla.
	 * @param consulta sql
	 * @return true si se elimina, false si da error
	 * @throws excepciones si se produce algún error
	 */
	@SuppressWarnings("finally")
	public boolean eliminar(String sql){
		
		// Por defecto, nuevo id = 0 (si se produce un error, se queda en cero)
		boolean eliminado = false;
				
		try {		
			// Se ejecuta la consulta y se obtiene la key de la fila creada
			this.statement.executeUpdate(sql);
			
			// Si no da error, se recupera el id del elemento editado
			eliminado = true;

		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		} finally {
			return eliminado;
		}
	}
	
	/**
	 * Se ejecuta una acción sobre la base de datos.
	 * @param consulta sql
	 * @return null
	 * @throws excepciones si se produce algún error
	 */
	public void ejecutar(String sql){
						
		try {		
			// Se ejecuta la consulta y se obtiene la key de la fila creada
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() );
		} catch (Exception e) {
			throw new ExcepcionRecursos( "Error en el SQL: " + e.getMessage() ); 
		}
	}
}
