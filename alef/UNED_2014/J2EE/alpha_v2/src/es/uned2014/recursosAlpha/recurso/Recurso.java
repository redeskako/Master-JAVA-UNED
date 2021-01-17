package es.uned2014.recursosAlpha.recurso;

import java.util.*;

import es.uned2014.recursosAlpha.baseDatos.BaseDatos;


/**
 * Clase Recurso implementa Comparable
 * Representa un recurso que se puede reservar.
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
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
	 * @param entero (i) para el idRecurso
	 * @param String (s) para la descripción
	 */
	public Recurso(int i, String s) {
		this.idRecurso = i;
		this.descripcion = s;
	}
	
	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta obtiene todos los recursos.
	 * @param pagina
	 * @return array con todos los recursos
	 */
	public ArrayList<Recurso> recursoListado (int pagina) {
		// Se crea una base de datos y se establece la conexion
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();
		
		String sql;
		
		if(pagina != 0){		
			//solo mostramos 20 resultados por pagina 
			int limit = 0;
			if(pagina > 1){
				limit = (pagina*20)-20; 
			}		
					
			// String con la consulta
			sql = " SELECT * FROM `recursos` "
				+ " ORDER BY descripcion ASC "
				+ " LIMIT "+limit+",20 ";
		} else {
			// si pagina es 0, se muestran todos los resultados en un array
			sql = " SELECT * FROM `recursos` ORDER BY descripcion ASC";
		}		
		
		// Array con el resultado de la consulta
		ArrayList<Recurso> aRecursos = bbdd.consultarRecursos(sql);
		
		// Se cierra la conexion
		bbdd.cerrarConexion();
		return aRecursos;		

	} // fin 
	
	/**
	 * Se obtiene el número total de filas de la tabla de recursos
	 * @param null
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
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta crea una fila nueva en la tabla de recursos.
	 * @param descripción del nuevo recurso
	 * @return id del nuevo recurso
	 * @throws null
	 */
	public int nuevo(String s){
		String descripcion = s;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();
		
		// Consulta para crear nuevo recurso:
		String sql = "INSERT INTO recursos (Descripcion) VALUES ('" + descripcion + "')";
		
		// Se realiza la consulta y se recupera el nuevo id:
		int idNuevo = bbdd.crear(sql);
		
		// Se cierra la conexion
		bbdd.cerrarConexion();
		
		return idNuevo;
		
	}
	
	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta edita una fila en la tabla de recursos.
	 * @param id del recurso a editar
	 * @param s para la descripción
	 * @return id del recurso editado
	 * @throws null
	 */
	public int editarRecurso(int id, String s){
		int idRecurso = id;
		String descripcion = s;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();
		
		int idEditado; // id del Usuario modificado

		// Consulta para editar recurso:
		String sql = "UPDATE `recursos` SET `Descripcion` = '" + descripcion + "' "
				+ "WHERE `recursos`.`IdRecurso` = " + idRecurso;
		
		// Se realiza la modificación y se recupera el nuevo id:
		idEditado = bbdd.editar(idRecurso, sql);			

		// Se cierra la conexion
		bbdd.cerrarConexion();
		
		return idEditado;		
	}
	
	/**
	 * Crea un objeto BaseDatos, abre la conexión y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta elimina una fila en una tabla de la Base se datos.
	 * @param id del elemento a eliminar
	 * @return true si se elimina, false si da error
	 * @throws null
	 */
	public boolean eliminar(int i){
		int id = i;

		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();	
	
		// Consulta para eliminar todas las reservas que tengan este recurso:
		String sql1 = "DELETE FROM `reservas` WHERE `IdRecurso` = " + id;
		bbdd.eliminar(sql1);
		
		// Consulta para eliminar el recurso
		String sql2 = "DELETE FROM `recursos` WHERE `IdRecurso` = " + id;
		boolean eliminado = bbdd.eliminar(sql2);

		// Se cierra la conexion
		bbdd.cerrarConexion();
		
		return eliminado;		
	}	
	
	/**
	 * Crea un String con la consulta a realizar desde el buscador de recursos
	 * @param String con la búsqueda introducida
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
	 * @return array con todas las reservas
	 */
	public ArrayList<Recurso> recursosBuscar(int pagina, String sql){
	
		// Se crea un objeto BaseDatos y se abre la conexión:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();	
				
		//solo mostramos 20 resultados por pagina 
		int limit = 0;
		if(pagina > 1){
			limit = (pagina*20)-20; 
		}	
		
		// Se adapta el sql para la paginación

		String sql2 = "SELECT * FROM `recursos` " + sql + " LIMIT "+limit+",20 ";
		
		// Se obtiene un array con el resultado:
		ArrayList<Recurso> array = bbdd.consultarRecursos(sql2);
		
		// Se cierra la conexión a la base de datos:
		bbdd.cerrarConexion();
		
		return array;
	}
	
	/**
	 * Se obtiene el número total de filas de la tabla de reservas para una 
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

		// Para usar el método compareTo, se envuelven los int en Integer:
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
