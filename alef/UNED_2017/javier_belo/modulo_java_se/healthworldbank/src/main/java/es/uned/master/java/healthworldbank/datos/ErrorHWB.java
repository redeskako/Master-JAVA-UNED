/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un un error producido al intentar procesar una Pregunta en el servidor 
 * Cada instancia representa un �nico error, que se env�a desde el servidor para notificarselo al cliente.
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 17/4/2012 
 */
public class ErrorHWB implements Serializable, Registro {
	
	private static final long serialVersionUID = 1L;
	
	private String descripcion;	// Descripci�n del error

	
	/**
	 * Constructor de la clase Error.
	 * 
	 * @param descripci�n Descripci�n del error que se ha producido
	 */
	public ErrorHWB(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * @return Descripci�n del error
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
}
