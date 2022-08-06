package es.uned2014.recursosAlpha.recurso;

import java.util.*;

import es.uned2014.recursosAlpha.baseDatos.BaseDatos;


/**
 * Representa un recurso que se puede reservar por un usuario.
 * Sus propiedades son:
 * - idRecurso: int
 * - descripci髇: String
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @since 	Junio 2014
 */
public class Recurso implements Comparable<Object> {

	private int idRecurso;
	private String descripcion;

	/**
	 * M閠odo constructor, inicializa las variables a 0 o String vac韔
	 */
	public Recurso() {
		this.idRecurso = 0;
		this.descripcion = "";
	}

	/**
	 * M閠odo constructor, asigna valor a las variables:
	 * @param i entero (i) para el idRecurso
	 * @param s String (s) para la descripci贸n
	 */
	public Recurso(int i, String s) {
		this.idRecurso = i;
		this.descripcion = s;
	}
	
	/**
	 * Crea un objeto BaseDatos, abre la conexi髇 y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta obtiene todos los recursos de la p醙ina indicada 
	 * (para obtener todos los recursos, pagina = 0)
	 * @param pagina
	 * @return array con todos los recursos de la p醙ina indicada
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
	 * Se obtiene el n鷐ero total de filas de la tabla de recursos
	 * @return n鷐ero de filas
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
	 * Crea un objeto BaseDatos, abre la conexi髇 y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta crea una fila nueva en la tabla de recursos.
	 * @param s String con la descripci髇 del nuevo recurso
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
	 * Crea un objeto BaseDatos, abre la conexi髇 y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta edita una fila en la tabla de recursos.
	 * @param id del recurso a editar
	 * @param s para la descripci髇
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
		
		// Se realiza la modificaci贸n y se recupera el nuevo id:
		idEditado = bbdd.editar(idRecurso, sql);			

		// Se cierra la conexion
		bbdd.cerrarConexion();
		
		return idEditado;		
	}
	
	/**
	 * Crea un objeto BaseDatos, abre la conexi髇 y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta elimina todas las reservas asociadas a un recurso, antes
	 * de eliminar el propio recurso.
	 * @param i id del recurso a eliminar
	 * @return boolean: true si se elimina, false si da error
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
	 * @param s String con la descripci髇 introducida
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
	 * Crea un objeto BaseDatos, abre la conexi髇 y solicita que se ejecute 
	 * una consulta antes de cerrar.
	 * Esta consulta obtiene todas los recursos que cumplen los requisitos indicados.
	 * @param pagina
	 * @param sql de la consulta
	 * @return array con todos los recursos
	 */
	public ArrayList<Recurso> recursosBuscar(int pagina, String sql){
	
		// Se crea un objeto BaseDatos y se abre la conexi贸n:
		BaseDatos bbdd = new BaseDatos();
		bbdd.abrirConexion();	
				
		//solo mostramos 20 resultados por pagina 
		int limit = 0;
		if(pagina > 1){
			limit = (pagina*20)-20; 
		}	
		
		// Se adapta el sql para la paginaci髇

		String sql2 = "SELECT * FROM `recursos` " + sql + " LIMIT "+limit+",20 ";
		
		// Se obtiene un array con el resultado:
		ArrayList<Recurso> array = bbdd.consultarRecursos(sql2);
		
		// Se cierra la conexi贸n a la base de datos:
		bbdd.cerrarConexion();
		
		return array;
	}
	
	/**
	 * Se obtiene el n鷐ero total de filas de la tabla de recursos para una 
	 * b鷖queda concreta
	 * @param sql con la consulta
	 * @return n鷐ero de filas con ese estado
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
	 * Representaci髇 en String de un recurso.
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

		// Para usar el m茅todo compareTo, se envuelven los int en Integer:
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
