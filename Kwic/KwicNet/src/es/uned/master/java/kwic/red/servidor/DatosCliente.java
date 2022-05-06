package es.uned.master.java.kwic.red.servidor;

import java.io.Serializable;

/*Esta clase contiene los datos que se pasaran del Servidor al Cliente
 * */

/**
 * @author marcos
 * 
 */
public class DatosCliente implements Serializable {
	
	public String noClaves;
	public String titulo;
	public String kwicResultado;

	// Constructor

	/**
	 * @param noClaves
	 * @param titulo
	 * @param kwicResultado
	 */
	public DatosCliente(String noClaves, String titulo, String kwicResultado) {
		this.noClaves = noClaves;
		this.titulo = titulo;
		this.kwicResultado = kwicResultado;
	}

}