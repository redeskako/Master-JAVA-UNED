package modelo;

import java.util.*;

/**
 * Representa un recurso que se puede reservar por un usuario.
 * Sus propiedades son:
 * - idRecurso: int
 * - descripción: String
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @since 	Junio 2014
 */
public class Recurso implements Comparable<Object> {

	private int idRecurso;
	private String descripcion;

	/**
	 * Método constructor, inicializa las variables a 0 o String vacío
	 */
	public Recurso() {
		this.idRecurso = 0;
		this.descripcion = "";
	}

	/**
	 * Método constructor, asigna valor a las variables:
	 * @param i entero (i) para el idRecurso
	 * @param s String (s) para la descripciÃ³n
	 */
	public Recurso(int i, String s) {
		this.idRecurso = i;
		this.descripcion = s;
	}
	

	/**
	 * Se obtiene el número total de filas de la tabla de recursos
	 * @return número de filas
	 * @throws null
	 */
	public int totalFilasRecurso(){
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();

		// String con la consulta
		String sql = " SELECT COUNT(*) FROM `recursos` ";

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sql);
		
		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}

	/**
	 * Crea un String con la consulta a realizar desde el buscador de recursos
	 * @param s String con la descripción introducida
	 * @return sql de la consulta
	 */
	public String sqlBuscadorRecursos(String s){	

		String sql = "";	
		
		if (s != null && !s.equals("")){
			sql = " WHERE Descripcion LIKE '%"+s+"%' ";
		} else {
			sql = "";
		}	
		
		return sql;
	}	
	
	
	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta obtiene todas los recursos que cumplen los requisitos indicados.
	 * @param pagina
	 * @param sql de la consulta
	 * @return array con todos los recursos
	 */
	public ArrayList<Recurso> recursosBuscar(int pagina, String sql){
	
		// Se crea un objeto BaseDatos y se abre la conexiÃ³n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();	
				
		//solo mostramos 20 resultados por pagina 
		int limit = 0;
		String sql2 = "";
		
		if(pagina > 1){
			limit = (pagina*20)-20; 
			// Se adapta el sql para la paginación
			sql2 = "SELECT * FROM `recursos` " + sql + " LIMIT "+limit+",20 ";
		} else {
			sql2 = "SELECT * FROM `recursos` " + sql;
		}
		
		// Se obtiene un array con el resultado:
		ArrayList<Recurso> arrayList = bbdd.consultarRecursos(sql2);
		
		// Se cierra la conexiÃ³n a la base de datos:
		bbdd.cerrarConexion();
		
		return arrayList;
	}
	
	/**
	 * Se obtiene el número total de filas de la tabla de recursos para una 
	 * búsqueda concreta
	 * @param sql con la consulta
	 * @return número de filas con ese estado
	 * @throws null
	 */
	public int numeroFilasBuscador(String sql){
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();
		
		// Se adapta el sql para que cuente:
		String sqlContar = " SELECT COUNT(*) FROM `recursos` AS rec ";
		sqlContar += sql;

		// Array con el resultado de la consulta
		int filas = bbdd.getNumeroFilas(sqlContar);
		
		// Se cierra la conexion
		bbdd.cerrarConexion();
		return filas;
	}		
	
	
	
	
	
	/**
	 * Representación en String de un recurso.
	 */
	public String toString() {
		String s = "IdRecurso: " + this.idRecurso + " | Descripcion: " + this.descripcion;
		return s;
	} // fin toString

	/**
	 * Para comparar dos Recursos, se comparan sus ids.
	 */
	public int compareTo(Object o) {
		Recurso r = (Recurso) o;

		// Para usar el mÃ©todo compareTo, se envuelven los int en Integer:
		Integer idThis = (Integer) this.idRecurso;
		Integer idCompare = (Integer) r.idRecurso;

		return idThis.compareTo(idCompare);
	}

	/**
	 * Dos Recursos son iguales si sus ids son iguales.
	 */
	public boolean equals(Object o) {
		Recurso r = (Recurso) o;

		return this.idRecurso == r.idRecurso;
	}

	/**
	 * @return the idRecurso
	 */
	public int getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso
	 *            the idRecurso to set
	 */
	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
