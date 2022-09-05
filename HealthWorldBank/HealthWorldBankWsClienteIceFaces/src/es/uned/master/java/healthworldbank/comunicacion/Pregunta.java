/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import java.io.Serializable;

/**
 * Clase que representa una peticion hecha desde el cliente al servidor.
 * El cliente creará una Pregunta y la enviará al servidor. El servidor la procesará y enviará la Respuesta que corresponda  
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public class Pregunta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoPeticion tipoPeticion; 	//Tipo de petición de la Pregunta
	private int limite; 				//Número máximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay límite)
	private int primerRegistro; 		//Posición del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezará con el primer registro)
	private String codigoPais; 			//Código de país para la consulta (si aplica). Si no aplica, NULL
	private String codigoIndicador; 	//Código de indicador para la consulta (si aplica). Si no aplica, NULL
	
	
	/**
	 * Constructor de la clase Pregunta en el que únicamente se informa el tipo de petición.
	 * Es útil en las peticiones en las que no es necesario paginar (no se informa 'limite' ni 'primerRegistro') ni tampoco se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petición de la Pregunta
	 */
	public Pregunta(TipoPeticion tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informa el tipo de petición y los datos relativos a paginación.
	 * Es útil en las peticiones en las que sí es necesario paginar (se informa 'limite' y 'primerRegistro') pero no se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petición de la Pregunta
	 * @param limite Número máximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay límite)
	 * @param primerRegistro Posición del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezará con el primer registro)
	 */
	public Pregunta(TipoPeticion tipoPeticion, int limite, int primerRegistro) {
		this.tipoPeticion = tipoPeticion;
		this.limite = limite;
		this.primerRegistro = primerRegistro;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informa el tipo de petición y los filtros para la consulta.
	 * Es útil en las peticiones en las que no es necesario paginar (no se informa informa 'limite' ni 'primerRegistro') pero sí se necesita filtro en la consulta (por 'codigoPais' o 'codigoIndicador').
	 * 
	 * @param tipoPeticion Tipo de petición de la Pregunta
	 * @param codigoPais Código de país para la consulta (si aplica). Si no aplica, NULL
	 * @param codigoIndicador Código de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public Pregunta(TipoPeticion tipoPeticion, String codigoPais, String codigoIndicador) {
		this.tipoPeticion = tipoPeticion;
		this.codigoPais = codigoPais;
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * Constructor de la clase Pregunta en el que se informan todos los datos posibles
	 * 
	 * @param tipoPeticion Tipo de petición de la Pregunta
	 * @param limite Número máximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay límite)
	 * @param primerRegistro Posición del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezará con el primer registro)
	 * @param codigoPais Código de país para la consulta (si aplica). Si no aplica, NULL
	 * @param codigoIndicador Código de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public Pregunta(TipoPeticion tipoPeticion, int limite, int primerRegistro, String codigoPais, String codigoIndicador) {
		this.tipoPeticion = tipoPeticion;
		this.limite = limite;
		this.primerRegistro = primerRegistro;
		this.codigoPais = codigoPais;
		this.codigoIndicador = codigoIndicador;
	}


	/**
	 * @return Tipo de petición de la Pregunta
	 */
	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}


	/**
	 * @return Número máximo de registros que debe contener la Respuesta (Si es menor o igual que 0 indica que no hay límite)
	 */
	public int getLimite() {
		return limite;
	}


	/**
	 * @return Posición del primer registro devuelto en la respuesta (si es menor o igual que 0 la respuesta empezará con el primer registro)
	 */
	public int getPrimerRegistro() {
		return primerRegistro;
	}


	/**
	 * @return Código de país para la consulta (si aplica). Si no aplica, NULL
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @return Código de indicador para la consulta (si aplica). Si no aplica, NULL
	 */
	public String getCodigoIndicador() {
		return codigoIndicador;
	}
	
	
}
