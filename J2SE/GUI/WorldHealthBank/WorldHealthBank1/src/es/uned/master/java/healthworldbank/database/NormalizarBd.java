package es.uned.master.java.healthworldbank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Normaliza la base de datos Datos.db, del directorio raíz de la aplicación
 * 
 * Espera que las tablas estén creadas y los datos CSV importados a las tablas Hoja1 y Hoja2
 * 
 *  @autor grupo alef (Juan Sánchez)
 *  @date 5/4/2012
 *  													  
 */

public class NormalizarBd {
	static final String CLASE_CONEXION = "org.sqlite.JDBC";			// clase de conexión	
	static final String DRIVER_MANAGER = "jdbc:sqlite:Datos.db";	// driver para conectar a Datos.db (la crea si no existe)
	static final boolean BORRAR_HOJAS = true;						// si está a true intenta borrar las tablas Hoja1 y Hoja2 cuando termina de normalizar

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement insercion = null;
		int iExcepciones = 0;
		
		try {
			Class.forName(CLASE_CONEXION);									// clase de conexión
			conn = DriverManager.getConnection(DRIVER_MANAGER);				// driver para conectar a la base de datos

			System.out.println("==> Normalizando base de datos...");
			// Países: normaliza la tabla COUNTRY lehendo los datos de HOJA1
			try {
				System.out.println("Insertando datos de países (COUNTRY)...");
				insercion = conn.createStatement();													// crea un statement
				insercion.executeUpdate("INSERT INTO COUNTRY(COUNTRY_CODE, COUNTRY_NAME) " +
						"SELECT DISTINCT COUNTRY_CODE, COUNTRY_NAME FROM HOJA1;");					// ejecuta un update en la tabla
			} catch (SQLException sqle) {
				System.out.println("Excepción al insertar datos de COUNTRY: " + sqle);				// execpción
				iExcepciones++;																		// se incrementa el contador de excepciones 
			} finally {
				try {
					insercion.close();																// se cierra el statement
				} catch (SQLException e) {
					System.out.println("Excepción al cerrar el statement: " + e);					// excepción al cerrar el statement
					iExcepciones++;
				}
			}
			// Indicadores: normaliza la tabla HEALTH_INDICATOR con los datos de HOJA2, igual que Países
			try {
				System.out.println("Insertando datos de indicadores (HEALTH_INDICATOR)...");
				insercion = conn.createStatement();
				insercion.executeUpdate("INSERT INTO HEALTH_INDICATOR " +
						"(INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION) " +
						"SELECT DISTINCT INDICATOR_CODE, INDICATOR_NAME, SOURCE_NOTE, SOURCE_ORGANIZATION " +
						"FROM HOJA2;");
			} catch (SQLException sqle) {
				System.out.println("Excepción al insertar datos de HEALTH_INDICATOR: " + sqle);
				iExcepciones++;
			} finally {
				try {
					insercion.close();
				} catch (SQLException e) {
					System.out.println("Excepción al cerrar el statement: " + e);
					iExcepciones++;
				}
			}
			// Datos: normaloza la tabla DATA con los datos de HOJA1
			for (int anio = 1960; anio <= 2011; anio++) {					// por cada año desde 1960 hasta 2011
				try {
					System.out.println("Insertando datos estadísticos de " + anio + " (DATA)...");	
					insercion = conn.createStatement();						// crea el statement
					// inserta los datos. 
					// Selecciona todos los registros de HOJA1 que no tengan el año anio en blanco y los inserta en DATA
					// el nombre del campo de HOJA1 es la letra "A" seguida del año
					String sentencia = "INSERT INTO DATA (INDICATOR_CODE, COUNTRY_CODE, YEAR, PERCENTAGE) " +
							"SELECT INDICATOR_CODE, COUNTRY_CODE, '" + anio + 
							"', REPLACE(A" + anio + ", ',', '.')  " +
							"FROM HOJA1 WHERE A" + anio +"  != '';";		
					insercion.executeUpdate(sentencia);
				} catch (SQLException sqle) {
					iExcepciones++;
					System.out.println("Excepción al insertar datos de DATA "+ anio + ": " + sqle);
				} finally {
					try {
						insercion.close();
					} catch (SQLException e) {
						iExcepciones++;
						System.out.println("Excepción al cerrar el statement: " + e);
					}
				}
			}
			if (iExcepciones == 0 && BORRAR_HOJAS) {				// si no se produjeron errores y BORRAR_HOJAS está a true
				try {												// borra las tablas temporales
					System.out.println("Borrando tabla HOJA1...");		// primero Hoja1
					insercion = conn.createStatement();
					String sentencia = "DROP TABLE HOJA1;";
					insercion.executeUpdate(sentencia);
				} catch (SQLException sqle) {
					iExcepciones++;
					System.out.println("Excepción al borrar HOJA1");
				} finally {
					try {
						insercion.close();
					} catch (SQLException e) {
						iExcepciones++;
						System.out.println("Excepción al cerrar el statement: " + e);
					}
				}
				try {	
					System.out.println("Borrando tabla HOJA1...");		// después Hoja2
					insercion = conn.createStatement();
					String sentencia = "DROP TABLE HOJA2;";
					insercion.executeUpdate(sentencia);
				} catch (SQLException sqle) {
					iExcepciones++;
					System.out.println("Excepción al borrar HOJA2");
				} finally {
					try {
						insercion.close();
					} catch (SQLException e) {
						iExcepciones++;
						System.out.println("Excepción al cerrar el statement: " + e);
					}
				}
			}
			
			try {														// ahora comprime la base de datos
				System.out.println("Comprimiendo la base de datos...");	
				insercion = conn.createStatement();
				String sentencia = "VACUUM;";
				insercion.executeUpdate(sentencia);
			} catch (SQLException sqle) {
				iExcepciones++;
				System.out.println("Excepción al comrimir la based de datos");
			} finally {
				try {
					insercion.close();
				} catch (SQLException e) {
					iExcepciones++;
					System.out.println("Excepción al cerrar el statement: " + e);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Fin de proceso");
		}
	}
}
