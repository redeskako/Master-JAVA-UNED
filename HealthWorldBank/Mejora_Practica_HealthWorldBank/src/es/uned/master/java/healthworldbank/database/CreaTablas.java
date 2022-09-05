package es.uned.master.java.healthworldbank.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Procesador de anotaciones que crea las tablas de la base de datos (borrándolas antes si existieran ya)
 * 
 * Está adaptado de Piensa en Java, de Bruce Eckel, 4ª ed. Capítulo 20. Anotaciones
 * 
 * @author grupo alef (Juan Sánchez)
 * @date 5/4/2012
 *
 */
public class CreaTablas {
	static ArrayList<String> sentenciasSql= new ArrayList<String>(); 	// colección de sentencias SQL para crear las tablas
	
	static String[] descripcionTablas = 								// nombres de ls clases de anotaciones
		{ "HealthIndicator", "Data", "Country", "Hoja1", "Hoja2" };		// que describen las tablas
	
	static final String CLASE_CONEXION = "org.sqlite.JDBC";			// clase de conexión	
	static final String DRIVER_MANAGER = "jdbc:sqlite:Datos.db";	// driver para conectar a Datos.db (la crea si no existe)
	static final String PACKAGE = "es.uned.master.java.healthworldbank.database.";		// paquete donde se ubican las clases

	/**
	 * Punto de entrada a la aplicación
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		definirTablas();		// procesar los archivos de anotaciones para definir las tablas
		crearTablas();			// crear las tablas en base a las sentencias SQL generadas
	}

	
	/**
	 * Procesar los archivos de anotaciones para definir las tablas
	 * 
	 * Los nombre de las clases están almacenadas en el ArrayList descripcionTablas
	 * Se comprueba si cada una de estas clases tiene anotaciones DBbTable y, si las tiene,
	 * se procesa para formar la sentencia SQL de creación de tabla 
	 * 
	 * @throws Exception
	 */
	private static void definirTablas() throws Exception {
		String creaUnaTabla="";				// almacena la cadena para creación de una tabla 
		String dropTabla = "";
		String indexTabla = "";
		boolean hayPKTabla = false;			// switch para ignorar la definición de clave primaria a nivel de columna si existe a nivel de tabla
		boolean hayPKColumna = false;
		
		/*
		 * recorre todas las clases de anotaciones del array descripcionTablas para procesarlas
		 */
		for (String className : descripcionTablas) {	// por cada clase de anotaciones
			className = PACKAGE + className;			// la busca en el paquete correspondiente 
			System.out.println("Clase: " + className);	// va mostrando las clases que procesa
			Class <?> cl = Class.forName(className);	// carga la clase
			
			DBTable dbTable = cl.getAnnotation(DBTable.class);	// almacena las anotaciones tipo DBTable
			if (dbTable == null) {								// si no las tiene, no es una clase de tabla y la ignora
				System.out.println("No hay anotaciones DBTable en la clase " + className);
				continue;
			}
			// procesa la anotaciones tipo DBTable
			String tableName = dbTable.name();						// nombre de la tabla
			String[] tablePrimaryKey = dbTable.primaryKey();		// definición de clave primaria a nivel de tabla

			hayPKTabla = (tablePrimaryKey.length > 0) ?				// si hay definida una clave primaria a nivel de tabla
					true :	false;									// activa un switch para ignorar las definidas a nivel de columna
			hayPKColumna = false;									// controla si ya hay definida una clave primaria a nivel de columna
			
			if (tableName.length() < 1) {							// si el nombre está vacío en la definición de @DBTable, 
				tableName = cl.getName().toUpperCase();				// utilizar el nombre de la clase
			}
			List<String> columnDefs = new ArrayList<String>();		// lista de definiciones de columnas	
			
			/* 
			 * recorre todos los campos declarados en la definición de tabla para procesarlos 
			 * 
			 */
			for (Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if (anns.length < 1) {
					continue;			// no tiene anotaciones, luego no es una columna de la base de datos
				}
				
				if (anns[0] instanceof SQLInteger) {			// comprueba si es un Integer
					SQLInteger sInt = (SQLInteger) anns[0];
					// Utilizar el nombre de campo si no se especifiga un nombre.
					if (sInt.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sInt.name();
					}
					// añade la columna con las restricciones que tenga
					columnDefs.add(columnName + " INTEGER" + getConstraints(sInt.constraints(),hayPKTabla || hayPKColumna));
					hayPKColumna = (hayPKColumna) ? 					// determina si ya hay definida una clave principal a nivel de columna
							true : sInt.constraints().primaryKey();		// para ignorar las subsiguientes
				}
				if (anns[0] instanceof SQLDouble) {				// comprueba si es un Double
					SQLDouble sDouble = (SQLDouble) anns[0];
					// Utilizar el nombre de campo si no se especifiga un nombre.
					if (sDouble.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sDouble.name();
					}
					columnDefs.add(columnName + " DOUBLE" + getConstraints(sDouble.constraints(),hayPKTabla||hayPKColumna));
					hayPKColumna = (hayPKColumna) ? 					// determina si ya hay definida una clave principal a nivel de columna
							true : sDouble.constraints().primaryKey();	// para ignorar las subsiguientes
				}
				if (anns[0] instanceof SQLString) {				// comprueba si es un String
					SQLString sString = (SQLString) anns[0];
					//Utilizar el nombre de campo si no se especifica un nombre
					if (sString.name().length() < 1) {
						columnName = field.getName().toUpperCase();
					} else {
						columnName = sString.name();
					}
					columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints(), hayPKTabla||hayPKColumna));
					hayPKColumna = (hayPKColumna) ? 					// determina si ya hay definida una clave principal a nivel de columna
							true : sString.constraints().primaryKey();	// para ignorar las subsiguientes
				}
			} // fin de bucle de columnas de la tabla
			
			if (hayPKTabla) {									// si hay clave primaria definida a nivel de tabla					
				String pk = "PRIMARY KEY(";						// pk almacena la clave primaria a nivel de tabla
				for (int i = 0; i < tablePrimaryKey.length; i++) {
					pk += tablePrimaryKey[i] + ",";
				}
				// Eliminar coma final y añade paréntesis final
				pk = pk.substring(0, pk.length() -1) + ")";
				columnDefs.add(pk);
			}
			StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
			for (String columnDef : columnDefs) {
				createCommand.append("\n    " + columnDef + ",");
			}
			// Eliminar coma final
			creaUnaTabla = createCommand.substring(0, createCommand.length() -1) + ");";
			
			dropTabla = "DROP TABLE IF EXISTS " + tableName + ";"; 
			sentenciasSql.add(dropTabla);								// añade la sentencia de borrado de tabla 
			sentenciasSql.add(creaUnaTabla);							// añade la sentencia de creación de tabla
			
			DBIndex dbIndex = cl.getAnnotation(DBIndex.class);	// almacena las anotaciones tipo DBIndex
			if (dbIndex == null) {								// si no las tiene, no es una clase de tabla y la ignora
				System.out.println("No hay anotaciones DBIndex en la clase " + className);
			} else {
				// procesa la anotaciones tipo DBIndex
				String indexName = dbIndex.name();						// nombre del índice
				String[] tableIndex = dbIndex.index();					// definición del índice de tabla
	
				if (tableIndex.length > 0) {							// si hay contenido
					indexTabla = "CREATE INDEX " + indexName + " ON " + tableName + " (";
					for (int i = 0; i < tableIndex.length; i++) {		// el índice puede ser compuesto
						indexTabla += tableIndex[i] + ",";
					}
					indexTabla = indexTabla.substring(0, indexTabla.length() - 1) + ");";	// borra coma final y cierra la sentencia
					sentenciasSql.add(indexTabla);											// la añade 
				} else {
					indexTabla = "";									// y la resetea
				}
			}
			System.out.println("La creación de tabla SQL para " + className + " es:" + 		// muestra en la consola la construcción de la tabla
					((dropTabla.length() > 0) ? "\n" + dropTabla : "") +
					"\n" + creaUnaTabla +
					((indexTabla.length() > 0) ? "\n" + indexTabla : ""));
		}
	}
	
	/**
	 * Crea la tablas ejecutando las sentencias SQL en secuencia
	 * 
	 */
	private static void crearTablas() {
		Connection conn = null;				// conexión a base de datos
		Statement stmt = null;				// statement sql
		try {
			Class.forName(CLASE_CONEXION);		// carga la clase de conexión
			conn = DriverManager.getConnection(DRIVER_MANAGER);	// conecta con la bd
			stmt = conn.createStatement();		// crea un statement
			for (String s : sentenciasSql) {	// por cada línea de la colección de sentencias SQL
				try {
					stmt.executeUpdate(s);		// ejecuta la sentencia
				} catch (SQLException e) {		// muestra el error si se produce y sigue
					System.err.println("Error en creación de tabla: " + e.getMessage() + "(" + s + ")");
				}
			}
			System.out.println("Tablas creadas");	// ya ha terminado
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Obtiene las restricciones de la tabla y las devuelve en forma de String
	 * 
	 * @param con 			restricciones de campo
	 * @param ignorarPK		si está activo es que ya existe una clave primaria e ignora esta restricción
	 * @return
	 */
	private static String getConstraints(Constraints con, boolean ignorarPK) {
		String constraints = "";
		if (!con.allowNull()) {
			constraints += " NOT NULL";
		}
		if(con.primaryKey() && !ignorarPK) { 	// solo se aplica la clave primaria a nivel de campo
			constraints += " PRIMARY KEY";		// si no hay una definida a nivel de tabla ni otra ya definida a nivel de campo
		}
		if(con.unique()) {
			constraints += " UNIQUE";
		}
		if(con.autoIncrement()) {
			constraints += " AUTOINCREMENT";
		}
		return constraints;
	}
}
