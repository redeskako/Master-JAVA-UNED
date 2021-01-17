/**
 * 
 */
package es.uned2013.parchis.server.databaseDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * deprecated. se borrará. finalmente no se utiliza
 * @author alef
 *
 */
public class extraeDatos {
	
	private Connection abreConexion () throws ClassNotFoundException {
		/**
		 * metodo para abrir conexion contra la base de datos
		 **/
		
		Class.forName("org.sqlite.JDBC");
		Statement comando=null; 
		 Connection conexion = null;
		  try {		 
		// ESTABLECER LA CONEXIÓN
			  conexion = DriverManager.getConnection("jdbc:sqlite:dbparchis.db");	
		 	 		 
		  } catch (Exception e) {
		   System.out.println("ERROR: " + e.getMessage());
		  }
		  finally{
			  return conexion;
		  }		
	}
	
	private void cierraConexion (Connection conexion ) throws ClassNotFoundException {
	/**
	 * metodo para cerrar conexion de la base de datos
	 **/
		  try {		 
			  if (conexion !=null){
				  conexion.close();
			  }	 
		  } catch (Exception e) {
		   System.out.println("ERROR: " + e.getMessage());
		  } 	
	}
	
	public ResultSet comidasXJugador (String numPartida) throws ClassNotFoundException{
		/**
		 *genera estadistica cantidad de fichas comidas por jugador
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;
		ResultSet resultados= null;
		// CREAR comando
		try {
			
			comando = conexion.createStatement();
			//obtenemos el id de la partida
			resultados = comando.executeQuery("select id_jugador, count(id_ficha_comida) from comidas group by id_jugador;");
			resultados.close();
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			return resultados;
		}
		
	}
	
	public ResultSet tiradaXJugador (String numPartida) throws ClassNotFoundException{
		/**
		 *genera estadistica cantidad total de tiradas x jugador
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;
		ResultSet resultados= null;
		// CREAR comando
		try {
			
			comando = conexion.createStatement();
			//obtenemos el id de la partida
			resultados = comando.executeQuery("pendiente");
			resultados.close();
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			return resultados;
		}
		
	}
	

}
