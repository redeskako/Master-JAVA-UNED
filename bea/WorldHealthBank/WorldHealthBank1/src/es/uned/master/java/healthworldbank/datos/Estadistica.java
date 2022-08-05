/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un dato numérico acerca de un determinado indicador de salud para un país determinado en un año determinado. 
 * Cada instancia representa un único registro de la tabla DATA en la Base de Datos.
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public class Estadistica implements Registro, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoIndicador; //El código del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	private String codigoPais; 		//El código del país (country_code de la tabla DATA en la Base de Datos)
	private int ano;				//El año al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	private double dato; 			//El dato numérico acerca del indicador de salud con código 'codigoIndicador' para el país con código 'codigoPais' en al año 'ano'
	
	
	/**
	 * Constructor de la clase Estadistica
	 * 
	 * @param codigoIndicador El código del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 * @param codigoPais El código del país (country_code de la tabla DATA en la Base de Datos)
	 * @param ano El año al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 * @param dato El dato numérico acerca del indicador de salud con código 'codigoIndicador' para el país con código 'codigoPais' en al año 'ano'
	 */
	public Estadistica(String codigoIndicador, String codigoPais, int ano, double dato) {
		this.codigoIndicador = codigoIndicador;
		this.codigoPais = codigoPais;
		this.ano = ano;
		this.dato = dato;
	}


	/**
	 * Constructor de la clase Estadistica sin parámetros
	 * Útil si se quiere crear la instancia y después establecer los valores con los métodos 'set...'
	 */
	public Estadistica() {
	}


	/**
	 * @return El código del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 */
	public String getCodigoIndicador() {
		return codigoIndicador;
	}


	/**
	 * @return El código del país (country_code de la tabla DATA en la Base de Datos)
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @return El año al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 */
	public int getAno() {
		return ano;
	}


	/**
	 * @return El dato numérico acerca del indicador de salud con código 'codigoIndicador' para el país con código 'codigoPais' en al año 'ano'
	 */
	public double getDato() {
		return dato;
	}


	/**
	 * @param codigoIndicador El código del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 */
	public void setCodigoIndicador(String codigoIndicador) {
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * @param codigoPais El código del país (country_code de la tabla DATA en la Base de Datos)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @param ano El año al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}


	/**
	 * @param dato El dato numérico acerca del indicador de salud con código 'codigoIndicador' para el país con código 'codigoPais' en al año 'ano'
	 */
	public void setDato(double dato) {
		this.dato = dato;
	}
}
