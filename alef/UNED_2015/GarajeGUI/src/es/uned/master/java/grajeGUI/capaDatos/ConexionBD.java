/**
 * Implementa la funcionalidad de la conexión a la base de datos
 * permitiendo automatizar las acciones más comunes sobre la misma.
 * @author jrequeijo2
 */

package es.uned.master.java.grajeGUI.capaDatos;

import java.sql.*;

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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			exc.printStackTrace();
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
			exc.printStackTrace();
			return(null);
		}
	}
}