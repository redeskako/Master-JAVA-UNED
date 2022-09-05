package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Canal;

public class CanalDao
{	
	/*
	 * Esta clase asume la existencia de una tabla de nombre canales con los siguientes campos:
	 * 
	 *	id (texto): identificador del canal según la nomenclatura de Youtube.
	 *	nombre (texto): nombre del canal.
	 *	descripcion (texto): descripcion del canal.	
	 */
	
	Connection con;

	/**
	 * Obtiene la conexión a la BD.
	 * @return conexión a la BD.
	 */
	public void openConnection()
	{
		try
        {
        	Context envContext = new InitialContext();
        	Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/ytb");
            con = ds.getConnection();            
        }
        catch (SQLException e)
        {
        	e.printStackTrace();
        }
		catch (NamingException e)
		{
			e.printStackTrace();
		}              
	}
	
	/**
	 * Introduce un canal en la BD.
	 * @param canal.
	 */
	public void insertCanal(Canal canal)
	{
		String sql;
		Statement statement;
			
		sql = "INSERT INTO canales(id, nombre, descripcion) VALUES ('"
			+ canal.getId() + "', '" + canal.getNombre() + "', '" + canal.getDescripcion() + "')"; 

		try
		{
			statement = con.createStatement();
		    statement.executeUpdate(sql);
		    statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * Introduce una lista de canales en la BD.
	 * @param lst lista de canales.
	 */
	public void insert(List<Canal> lst)
	{
		String sql;
		
		Statement statement;
		
		for (Canal canal : lst)
		{
			sql = "INSERT INTO canal(id, nombre, descripcion) VALUES ('"
					+ canal.getId() + "', '" + canal.getNombre() + "', '" + canal.getDescripcion() + "')"; 
			try
			{
				statement = con.createStatement();
		        statement.executeUpdate(sql);
		        statement.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}			
		}	
	}

	/**
	 * Obtiene los canales existentes.
	 * @return lista de canales.
	 */
	public List<Canal> select()
	{
		List<Canal> canales = new ArrayList<Canal>();
		Canal canal;
		
		try
		{
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM canales");
	
			while(resultSet.next())
			{
				canal = new Canal();
				canal.setId(resultSet.getString("id"));
				canal.setNombre(resultSet.getString("nombre"));
				canal.setDescripcion(resultSet.getString("descripcion"));
				canales.add(canal);
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return canales;
	}

	/**
	 * Obtiene los datos de un canal a partir de su clave.
	 * @return canal.
	 */
	public Canal select(String id)
	{
		Canal canal = new Canal();
		
		try
		{
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM canales WHERE id = '" + id+ "'");
	
			if (resultSet.next())
			{
				canal.setId(resultSet.getString("id"));
				canal.setNombre(resultSet.getString("nombre"));
				canal.setDescripcion(resultSet.getString("descripcion"));
			}

			resultSet.close();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return canal;
	}

	public void closeConnection()
	{		
		try
		{
			if (con != null)
				con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}