package es.uned2013.parchis.server.databaseDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *Este metodo contendrá cada una de las clases para insertar la info de las estadisticas en la base de datos
 * @author alef
 */
public class insertaDatos  {
	
	public void creaTablas  () throws ClassNotFoundException {
		//crea las tablas y la base de datos
		 
		  Class.forName("org.sqlite.JDBC");
		  
		  Connection conexion=null;
		  Statement comando=null;
		 
		  try {
		 
		   // ESTABLECER LA CONEXIÓN
		   
		   conexion = DriverManager.getConnection("jdbc:sqlite:dbparchis.db");
		 
		   // CREAR comando
		   
		   comando = conexion.createStatement();
		 
		   // CREAR TABLA partida  EN CASO DE QUE NO EXISTA
		   comando.execute("CREATE TABLE IF NOT EXISTS partida (id_partida integer primary key AUTOINCREMENT not null, fecha_inicio datetime not null, fecha_fin datetime);");
		   
		   // CREAR TABLA jugadorpartida  EN CASO DE QUE NO EXISTA
		   comando.execute("CREATE TABLE IF NOT EXISTS jugadorPartida (id_partida integer not null, id_jugador integer not null, FOREIGN KEY(id_partida) REFERENCES partida(id_partida), PRIMARY KEY ( id_partida,id_jugador));");
		   
		   // CREAR TABLA tirada  EN CASO DE QUE NO EXISTA
		  comando.execute("CREATE TABLE IF NOT EXISTS tirada (id_jugador integer not null, id_partida integer not null, id_tirada integer not null, hora_tirada datetime, id_ficha integer not null, id_casilla_ini integer not null, id_casilla_fin integer not null, valor_tirada integer not null, FOREIGN KEY(id_partida) REFERENCES jugadorPartida(id_partida), FOREIGN KEY(id_jugador) REFERENCES jugadorPartida(id_jugador),  PRIMARY KEY ( id_jugador, id_partida, id_tirada));");
		   
		   // CREAR TABLA comidas  EN CASO DE QUE NO EXISTA
		  comando.execute("CREATE TABLE IF NOT EXISTS comidas (id_jugador integer not null, id_partida integer not null, id_tirada integer not null,  id_ficha_comida integer not null, FOREIGN KEY(id_partida) REFERENCES tirada(id_partida), FOREIGN KEY(id_jugador) REFERENCES tirada(id_jugador), FOREIGN KEY(id_tirada) REFERENCES tirada(id_tirada), PRIMARY KEY ( id_jugador, id_partida, id_tirada));");
		   
		   
		 
		  } catch (Exception e) {
		   System.out.println("ERROR: " + e.getMessage());
		  }finally{
			  // Cerrar base de datos
			  try{
				  if(comando != null){
					  comando.close();
				  }
				  if (conexion !=null){
					  conexion.close();
				  }
			  }catch (Exception e) {
				   System.out.println("ERROR: " + e.getMessage());
			  }
		  }
			  
		  }
	
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
	
	public String insertaPartidaNueva (int numJugadores) throws ClassNotFoundException{
		/**
		 *da de alta la nueva partida en base al numero de jugadores y te devuelve el id de la misma
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;
		String devolver= null;
		// CONSULTA DATOS
		   ResultSet resultados;
		// CREAR comando
		try {
			
			comando = conexion.createStatement();
			//generamos la query de insertar nueva partida con la fecha establecida
			comando.execute("insert into partida values(null,datetime('now'),null);");
			//obtenemos el id de la partida
			resultados = comando.executeQuery("SELECT MAX(id_partida) FROM partida;");
			//insertamos los jugadores en la partida
			devolver = resultados.getString(1);
			for (int i = 1; i < (numJugadores + 1); i++) {
				comando.execute("insert into jugadorPartida values(" + devolver + "," + i + ");");
			}
			resultados.close();
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return devolver;
	}
	
	public void insertaTirada (String idPartida, String idJugador, String idTirada, String idFicha, String casillaIni, String casillaFin, String valorTirada) throws ClassNotFoundException{
	
	/**
		 *introduce una nueva tirada
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;

		// CREAR comando
		try {
			comando = conexion.createStatement();
			//metemos los datos en la tabla
			comando.execute("insert into Tirada values(" + idJugador + "," + idPartida + "," + idTirada + ",datetime('now')," + idFicha + "," + casillaIni + "," + casillaFin + "," + valorTirada +" );");
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertaFichaComida (String idPartida, String idJugador, String idTirada, String idFicha) throws ClassNotFoundException{
		/**
		 *introduce una nueva ficha comida en una tirada
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;

		// CREAR comando
		try {
			comando = conexion.createStatement();
			//metemos los datos en la tabla
			comando.execute("insert into Comidas values (" + idJugador + "," + idPartida + "," + idTirada + "," + idFicha + " );");
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizaPartida (String idPartida) throws ClassNotFoundException{
		/**
		 *finaliza la partida introduciendo la hora final en la partida
		 * @author alef
		 */
		
		Connection conexion = this.abreConexion();
		Statement comando;

		// CREAR comando
		try {
			comando = conexion.createStatement();
			//metemos los datos en la tabla
			comando.execute("update partida set fecha_fin = datetime ('now') where id_partida = " + idPartida + ";");
			comando.close();
			this.cierraConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
