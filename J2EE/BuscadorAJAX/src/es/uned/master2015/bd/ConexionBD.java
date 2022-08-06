/**
 * Encapsula la interacción con la BD.
 */

package es.uned.master2015.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import es.uned.master2015.excepcion.Excepcion;

public class ConexionBD
{
	private Connection cnx;
	
	/**
	 * 	Constructor.
	 */
	public ConexionBD()
	{
	}
	
	/**
	 * Abre la conexión con la BD.
	 */
	public void abre()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection("jdbc:mysql://localhost/youtube?user=root");
		}
		catch (Exception e)
		{
			throw new Excepcion("Error al abrir la conexión: " + e.getMessage());
		}
	}
	
	/**
	 * Cierra la conexión con la BD.
	 */
	public void cierra()
	{
		try
		{
			if (cnx != null)
				cnx.close();
		}
		catch (Exception e)
		{
			throw new Excepcion("Error al cerrar la conexion: " + e.getMessage());
		}
	}
	

	/**
	 * Realiza una consulta a la BD.
	 */
	public ResultSet consulta( String sql)
	{	
		try
		{
			return cnx.createStatement().executeQuery(sql);
		}
		catch (Exception e)
		{
			throw new Excepcion( "Error en el SQL: " + e.getMessage() );
		}
	}	
}