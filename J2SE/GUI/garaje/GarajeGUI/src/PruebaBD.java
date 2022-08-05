/**
 * Clase para probar la BD.
 */

import java.sql.ResultSet;

import es.uned.master.java.grajeGUI.capaDatos.ConexionBD;

public class PruebaBD
{
	public static void main( String args[] )
	{
		ConexionBD cnx = null;
		ResultSet rs = null;
		String sql = "";
		Registro registro = null;

		try
		{
			// Crea la conexiÃ³n y abre la BD garaje.
			// Si no existiera, la crea.
			cnx = new ConexionBD();
			cnx.abre("garaje");

			
			// Crea la tabla.
		   /*sql = "CREATE TABLE registro (" +
		    			 	"id 			INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
		    			 	"planta         INT    	NOT NULL, " + 
		    			 	"plaza          INT     NOT NULL, " + 
		    			 	"fecha_entrada  TEXT   	NOT NULL, " + 
		    			 	"fecha_salida   TEXT)";
		    
		    cnx.actualiza(sql);
		    *
			
			// Si la tabla tiene registros.
			/*sql = "SELECT COUNT(*) FROM registro";
			if (cnx.consulta(sql).getInt(1) != 0)
			{
				// Los borra.
				sql = "DELETE FROM registro;";
				cnx.actualiza(sql);
			}
           */
			
		    // Introduce registros.
		    //cnx.setAutoCommit(false);

			/*
			registro = new Registro(1, 1, "2007-01-01 10:00:00", "2007-01-01 11:00:00");
		    sql = "INSERT INTO registro (planta, plaza, fecha_entrada, fecha_salida) " +
		    		"VALUES (" + registro.getPlanta() + ", " + registro.getPlaza() + ", '" + registro.getFechaEntrada() + "', '" + registro.getFechaSalida() + "');";
		    cnx.actualiza(sql);

			registro = new Registro(2, 2, "2008-01-01 20:00:00");
		    sql = "INSERT INTO registro (planta, plaza, fecha_entrada, fecha_salida) " +
		    		"VALUES (" + registro.getPlanta() + ", " + registro.getPlaza() + ", '" + registro.getFechaEntrada() + "', '" + registro.getFechaSalida() + "');";
		    
		    cnx.actualiza(sql);
		    */		    
		    
		    //cnx.commit();
		    
			// Actualiza un registro.
		    //sql = "UPDATE registro SET planta = 15 WHERE id = 1;"; 
		    //cnx.actualiza(sql);

			// Elimina un registro.
		    //sql = "DELETE FROM registro WHERE id = 1;"; 
		    //cnx.actualiza(sql);
		    
		    // Recupera los registros introducidos.
			
			// Añandi la plaza
		    /*rs = cnx.consulta("SELECT id, planta,plaza," + 
		    		" strftime('%d/%m/%Y %H:%M:%S', fecha_entrada) AS fecha_entrada," + 
		    		" strftime('%d/%m/%Y %H:%M:%S', fecha_salida) AS fecha_salida FROM registro;");
             */
			
			rs = cnx.consulta("SELECT id, planta,plaza,fecha_entrada,fecha_salida FROM registro;");
             
		    while (rs.next())
		    {
		    	System.out.println("Id: " + rs.getInt("id"));
		    	System.out.println("Planta: " + rs.getInt("planta"));
		    	System.out.println("Plaza: " + rs.getInt("plaza")); //Plaza añadida
		    	System.out.println("Entrada: " + rs.getString("fecha_entrada"));
		    	System.out.println("Salida: " + rs.getString("fecha_salida"));
		    }
		}
		catch ( Exception e )
		{
			//throw new ErrorGaraje(recursos.muestraMensaje("err4"));
			//throw new ErrorGaraje("Error trabajando con la BD.");
			e.printStackTrace();
		}
		finally
		{
			if (cnx != null)
				cnx.cierra();
		}
	}
}