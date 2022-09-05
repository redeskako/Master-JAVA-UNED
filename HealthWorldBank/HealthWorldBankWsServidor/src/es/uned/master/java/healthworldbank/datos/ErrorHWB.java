/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un un error producido al intentar procesar una Pregunta en el servidor 
 * Cada instancia representa un único error, que se envía desde el servidor para notificarselo al cliente.
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 17/4/2012 
 */
public class ErrorHWB implements Serializable, Registro {
	
	private static final long serialVersionUID = 1L;
	
	private String descripción;	// Descripción del error

	
	/**
	 * Constructor de la clase Error.
	 * 
	 * @param descripción Descripción del error que se ha producido
	 */
	public ErrorHWB(String descripción) {
		this.descripción = descripción;
	}

	
	/**
	 * @return Descripción del error
	 */
	public String getDescripcion() {
		return descripción;
	}
	
	
}
