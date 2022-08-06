package es.uned2014.recursosAlpha.clasesCliente;

/**
 * Representa un recurso que se puede reservar por un usuario.
 * Sus propiedades son:
 * - idRecurso: int
 * - descripción: String
 * 
 * @author	Alpha UNED 2014
 * @version	RecursosWS 1.0
 * @since 	Septiembre 2014
 */
public class Recurso {

	private int idRecurso;
	private String descripcion;

	/**
	 * 	Método constructor: inicializa las variables de la clase.
	 */
	public Recurso() {
		this.idRecurso = 0;
		this.descripcion ="";

	}


	/**
	 * @return the idRecurso
	 */
	public int getIdRecurso() {
		return idRecurso;
	}


	/**
	 * @param idRecurso the idRecurso to set
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
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
