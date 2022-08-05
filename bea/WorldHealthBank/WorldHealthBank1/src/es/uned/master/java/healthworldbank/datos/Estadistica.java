/**
 * 
 */
package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase que representa un dato num�rico acerca de un determinado indicador de salud para un pa�s determinado en un a�o determinado. 
 * Cada instancia representa un �nico registro de la tabla DATA en la Base de Datos.
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 7/4/2012 
 */
public class Estadistica implements Registro, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoIndicador; //El c�digo del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	private String codigoPais; 		//El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
	private int ano;				//El a�o al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	private double dato; 			//El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
	
	
	/**
	 * Constructor de la clase Estadistica
	 * 
	 * @param codigoIndicador El c�digo del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 * @param codigoPais El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
	 * @param ano El a�o al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 * @param dato El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
	 */
	public Estadistica(String codigoIndicador, String codigoPais, int ano, double dato) {
		this.codigoIndicador = codigoIndicador;
		this.codigoPais = codigoPais;
		this.ano = ano;
		this.dato = dato;
	}


	/**
	 * Constructor de la clase Estadistica sin par�metros
	 * �til si se quiere crear la instancia y despu�s establecer los valores con los m�todos 'set...'
	 */
	public Estadistica() {
	}


	/**
	 * @return El c�digo del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 */
	public String getCodigoIndicador() {
		return codigoIndicador;
	}


	/**
	 * @return El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @return El a�o al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 */
	public int getAno() {
		return ano;
	}


	/**
	 * @return El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
	 */
	public double getDato() {
		return dato;
	}


	/**
	 * @param codigoIndicador El c�digo del indicador de salud (indicator_code de la tabla DATA en la Base de Datos)
	 */
	public void setCodigoIndicador(String codigoIndicador) {
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * @param codigoPais El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @param ano El a�o al que se refiere el dato (year de la tabla DATA en la Base de Datos)
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}


	/**
	 * @param dato El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
	 */
	public void setDato(double dato) {
		this.dato = dato;
	}
}
