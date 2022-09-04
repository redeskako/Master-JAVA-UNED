/**
 * Implementa la funcionalidad de la conexión a la base de datos
 * permitiendo automatizar las acciones más comunes sobre la misma.
 * @author jrequeijo2, Jose lopez
 */

package es.uned.master.java.grajeGUI.capaDatos;

import java.sql.*;

import es.uned.master.java.grajeGUI.capaGestion.ErrorGaraje;

public class ConexionBD
{
	// Propiedades.
	private Connection conexion;

	/**
	 *  Constructor.
	 *  Registra el driver para la conexión a la BD.
	 */
	public ConexionBD()
	{
		try
		{
			DriverManager.registerDriver(new org.sqlite.JDBC());
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ErrorGaraje("Error de conexion con la BD.");
		}
	}

	/**
	 * Abre la conexión a la BD.
	 */
	public void abre(String sBD)
	{
		try
		{
			// Como usuario anónimo.
			conexion = DriverManager.getConnection("jdbc:sqlite:" + sBD + ".db");
			//conexion = DriverManager.getConnection("jdbc:sqlite:test.db", "usuario", "clave");
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ErrorGaraje("Error abriendo la BD.");
		}
	}

	/**
	 *  Cierra la conexión a la BD.
	 */
	public void cierra()
	{
		try
		{
			conexion.close();
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ErrorGaraje("Error cerrando  la BD.");
		}
	}
	
	public void creaBD(){
	
		Statement s;
		String sql;
		
		sql = "CREATE TABLE registro (" +
			 	"id 			INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
			 	"planta         INT    	NOT NULL, " + 
			 	"plaza          INT     NOT NULL, " + 
			 	"fecha_entrada  TEXT   	NOT NULL, " + 
			 	"fecha_salida   TEXT)";
		try
		{
		   s = conexion.createStatement();
		    s.executeUpdate(sql);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ErrorGaraje("Error creando la BD.");
		}
	}
	
	
	

	/**
	 *  Realiza una operación de actualización sobre la BD.
	 * @param sActualizacion instrucción SQL de actualización.
	 */
	public void actualiza(String sActualizacion)
	{
		Statement s;
		
		try
		{
			s = conexion.createStatement();
			s.executeUpdate(sActualizacion);
		}
		catch (Exception exc)
		{
			//exc.printStackTrace();
			throw new ErrorGaraje("Error actualizando la BD.");
		}
	}

	/**
	 *  Realiza una operación de consulta sobre la base de datos.
	 * @param sConsulta instrucción SQL de consulta.
	 * @return registros devueltos por la consulta.
	 */
	public ResultSet consulta(String sConsulta)
	{
		ResultSet rs;
		Statement s;

		try
		{
			s = conexion.createStatement();
			rs = s.executeQuery(sConsulta);
			return(rs);
		}
		catch (Exception exc)
		{
			//exc.printStackTrace();
			//return (null);
			throw new ErrorGaraje("Error ejecuntando la consulta en la BD.");
		}
	}
	
	
}