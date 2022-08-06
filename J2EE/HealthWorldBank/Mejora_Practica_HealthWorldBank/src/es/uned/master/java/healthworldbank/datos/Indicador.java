/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un indicador de salud (un único registro de la tabla HEALTH_INDICATOR en la Base de Datos)
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public class Indicador implements Registro, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo; 			//El código del indicador de salud (indicator_code de la tabla HEALTH_INDICATOR en la Base de Datos)
	private String nombre; 			//El nombre del indicador de salud (indicator_name de la tabla HEALTH_INDICATOR en la Base de Datos)
	private String nota; 			//Nota acerca de la fuente de la que se obtuvo el indicador de salud (source_note de la tabla HEALTH_INDICATOR en la Base de Datos)
	private String organizacion; 	//Organizacion de la que se obtuvo el indicador de salud (source_organization de la tabla HEALTH_INDICATOR en la Base de Datos)
	
	
	/**
	 * Constructor de la clase Indicador
	 * 
	 * @param codigo El código del indicador de salud (indicator_code de la tabla HEALTH_INDICATOR en la Base de Datos)
	 * @param nombre El nombre del indicador de salud (indicator_name de la tabla HEALTH_INDICATOR en la Base de Datos)
	 * @param nota Nota acerca de la fuente de la que se obtuvo el indicador de salud (source_note de la tabla HEALTH_INDICATOR en la Base de Datos)
	 * @param organizacion Organizacion de la que se obtuvo el indicador de salud (source_organization de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public Indicador(String codigo, String nombre, String nota,
			String organizacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nota = nota;
		this.organizacion = organizacion;
	}


	/**
	 * Constructor de la clase Indicador sin parámetros
	 * Útil si se quiere crear la instancia y después establecer los valores con los métodos 'set...'
	 */
	public Indicador() {
	}


	/**
	 * @return El código del indicador de salud (indicator_code de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 * @return El nombre del indicador de salud (indicator_name de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @return Nota acerca de la fuente de la que se obtuvo el indicador de salud (source_note de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public String getNota() {
		return nota;
	}


	/**
	 * @return Organizacion de la que se obtuvo el indicador de salud (source_organization de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public String getOrganizacion() {
		return organizacion;
	}


	/**
	 * @param codigo El código del indicador de salud (indicator_code de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @param nombre El nombre del indicador de salud (indicator_name de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @param nota Nota acerca de la fuente de la que se obtuvo el indicador de salud (source_note de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}


	/**
	 * @param organizacion Organizacion de la que se obtuvo el indicador de salud (source_organization de la tabla HEALTH_INDICATOR en la Base de Datos)
	 */
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	
	
	
}
