/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un pa�s (un �nico registro de la tabla COUNTRY en la Base de Datos)
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 7/4/2012 
 */
public class Pais implements Registro, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo; 	//El c�digo del pa�s (country_code de la tabla COUNTRY en la Base de Datos)
	private String nombre; 	//El nombre del pa�s (country_name de la tabla COUNTRY en la Base de Datos)
	
	/**
	 * Constructor de la clase Pais
	 * 
	 * @param codigo El c�digo del pa�s (country_code de la tabla COUNTRY en la Base de Datos)
	 * @param nombre El nombre del pa�s (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public Pais(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
	/**
	 * Constructor de la clase Pais sin par�metros
	 * �til si se quiere crear la instancia y despu�s establecer los valores con los m�todos 'set...'
	 */
	public Pais() {
	}


	/**
	 * @return El c�digo del pa�s (country_code de la tabla COUNTRY en la Base de Datos)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @return El nombre del pa�s (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param codigo El c�digo del pa�s (country_code de la tabla COUNTRY en la Base de Datos)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @param nombre El nombre del pa�s (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
