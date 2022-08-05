/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import java.io.Serializable;

/**
 * Clase (enum) que representa un tipo de peticion que se puede hacer desde el cliente al servidor
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public enum TipoPeticion implements Serializable{
	PAISES, INDICADORES, ESTADISTICAS, ERROR, FIN_COMUNICACION
}
