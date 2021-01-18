/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un país (un único registro de la tabla COUNTRY en la Base de Datos)
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public class Pais implements Registro, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo; 	//El código del país (country_code de la tabla COUNTRY en la Base de Datos)
	private String nombre; 	//El nombre del país (country_name de la tabla COUNTRY en la Base de Datos)
	
	/**
	 * Constructor de la clase Pais
	 * 
	 * @param codigo El código del país (country_code de la tabla COUNTRY en la Base de Datos)
	 * @param nombre El nombre del país (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public Pais(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	
	/**
	 * Constructor de la clase Pais sin parámetros
	 * Útil si se quiere crear la instancia y después establecer los valores con los métodos 'set...'
	 */
	public Pais() {
	}


	/**
	 * @return El código del país (country_code de la tabla COUNTRY en la Base de Datos)
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @return El nombre del país (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param codigo El código del país (country_code de la tabla COUNTRY en la Base de Datos)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @param nombre El nombre del país (country_name de la tabla COUNTRY en la Base de Datos)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
