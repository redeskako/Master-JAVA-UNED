/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import java.io.Serializable;

/**
 * Clase que representa una peticion hecha desde el cliente al servidor.
 * El cliente crear� una Pregunta y la enviar� al servidor. El servidor la procesar� y enviar� la Respuesta que corresponda  
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 7/4/2012 
 */
public class Pregunta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoPeticion tipoPeticion; 	//Tipo de petici�n de la Pregunta
	private int limite; 				//N�mero m�ximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay l�mite)
	private int primerRegistro; 		//Posici�n del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezar� con el primer registro)
	private String codigoPais; 			//C�digo de pa�s para la consulta (si aplica). Si no aplica, NULL
	private String codigoIndicador; 	//C�digo de indicador para la consulta (si aplica). Si no aplica, NULL
	
	
	/**
	 * Constructor de la clase Pregunta en el que �nicamente se informa el tipo de petici�n.
	 * Es �til en las peticiones en las que no es necesario paginar (no se informa 'limite' ni 'primerRegistro') ni tampoco se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petici�n de la Pregunta
	 */
	public Pregunta(TipoPeticion tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informa el tipo de petici�n y los datos relativos a paginaci�n.
	 * Es �til en las peticiones en las que s� es necesario paginar (se informa 'limite' y 'primerRegistro') pero no se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petici�n de la Pregunta
	 * @param limite N�mero m�ximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay l�mite)
	 * @param primerRegistro Posici�n del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezar� con el primer registro)
	 */
	public Pregunta(TipoPeticion tipoPeticion, int limite, int primerRegistro) {
		this.tipoPeticion = tipoPeticion;
		this.limite = limite;
		this.primerRegistro = primerRegistro;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informa el tipo de petici�n y los filtros para la consulta.
	 * Es �til en las peticiones en las que no es necesario paginar (no se informa informa 'limite' ni 'primerRegistro') pero s� se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petici�n de la Pregunta
	 * @param codigoPais C�digo de pa�s para la consulta (si aplica). Si no aplica, NULL
	 * @param codigoIndicador C�digo de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public Pregunta(TipoPeticion tipoPeticion, String codigoPais, String codigoIndicador) {
		this.tipoPeticion = tipoPeticion;
		this.codigoPais = codigoPais;
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informan todos los datos posibles
	 * 
	 * @param tipoPeticion Tipo de petici�n de la Pregunta
	 * @param limite N�mero m�ximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay l�mite)
	 * @param primerRegistro Posici�n del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezar� con el primer registro)
	 * @param codigoPais C�digo de pa�s para la consulta (si aplica). Si no aplica, NULL
	 * @param codigoIndicador C�digo de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public Pregunta(TipoPeticion tipoPeticion, int limite, int primerRegistro, String codigoPais, String codigoIndicador) {
		this.tipoPeticion = tipoPeticion;
		this.limite = limite;
		this.primerRegistro = primerRegistro;
		this.codigoPais = codigoPais;
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * @return Tipo de petici�n de la Pregunta
	 */
	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}


	/**
	 * @return N�mero m�ximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay l�mite)
	 */
	public int getLimite() {
		return limite;
	}


	/**
	 * @return Posici�n del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezar� con el primer registro)
	 */
	public int getPrimerRegistro() {
		return primerRegistro;
	}


	/**
	 * @return C�digo de pa�s para la consulta (si aplica). Si no aplica, NULL
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @return C�digo de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public String getCodigoIndicador() {
		return codigoIndicador;
	}
	
	
}
