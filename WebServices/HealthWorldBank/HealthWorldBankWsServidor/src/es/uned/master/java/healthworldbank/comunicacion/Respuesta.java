/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.uned.master.java.healthworldbank.datos.Registro;

/**
 * Clase que representa una respuesta dada por el servidor a una peticion hecha desde el cliente.
 * El cliente crear� una Pregunta y la enviar� al servidor. El servidor la procesar� y enviar� la Respuesta que corresponda  
 * 
 * @author grupo alef (Jos� Torrecilla) 
 * @date 7/4/2012 
 */
public class Respuesta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoPeticion tipoPeticion; 	//Tipo de petici�n de la Pregunta que origina esta Respuesta
	private int limite; 				//N�mero m�ximo de registros que se indicaba en la Pregunta que deb�a contener la Respuesta
	private int primerRegistro; 		//Posici�n del primer registro devuelto en la Respuesta
	//private int numeroRegistros; 		//CALCULADO. N�mero de registros que contiene la Respuesta
	//private int registroHasta;		//CALCULADO. Posici�n del �ltimo registro que est� incluido en las Respuesta
	private int totalRegistros; 		//N�mero total de registros que tendr�a la petici�n original completa (es decir, si no se hubiera limitado el n�mero de registros)
	//private int pagina; 				//CALCULADO. N�mero de p�gina actual teniendo en cuenta el l�mite de n�mero de registros devueltos y la posici�n del primer registro devuelto 
	//private int totalPaginas; 		//CALCULADO. N�mero de p�ginas totales atendiendo al l�mite actual de registros en la Respuesta
	private ArrayList<Registro> datos;	//Datos (registros) incluidos en la Respuesta
	
	
	//CONSTRUCTOR
	/**
	 * Constructor de la clase Respuesta
	 * 
	 * @param tipoPeticion Tipo de petici�n de la Pregunta que origina esta Respuesta
	 * @param limite N�mero m�ximo de registros que se indicaba en la Pregunta que deb�a contener la Respuesta
	 * @param primerRegistro Posici�n del primer registro devuelto en la Respuesta
	 * @param totalRegistros N�mero total de registros que tendr�a la petici�n original completa (es decir, si no se hubiera limitado el n�mero de registros)
	 * @param datos Datos (registros) incluidos en la Respuesta
	 */
	public Respuesta(TipoPeticion tipoPeticion, int limite, int primerRegistro, int totalRegistros, List<Registro> datos) {
		this.tipoPeticion = tipoPeticion;
		this.limite = limite;
		this.primerRegistro = primerRegistro;
		this.totalRegistros = totalRegistros;
		if (datos != null) {
			this.datos = new ArrayList<Registro>(datos);
		} else {
			this.datos = null;
		}
	}


	//GETTERS
	/**
	 * @return Tipo de petici�n de la Pregunta que origina esta Respuesta
	 */
	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}


	/**
	 * @return N�mero m�ximo de registros que se indicaba en la Pregunta que deb�a contener la Respuesta
	 */
	public int getLimite() {
		return limite;
	}


	/**
	 * @return Posici�n del primer registro devuelto en la Respuesta
	 */
	public int getPrimerRegistro() {
		return primerRegistro;
	}


	/**
	 * @return N�mero total de registros que tendr�a la petici�n original completa (es decir, si no se hubiera limitado el n�mero de registros)
	 */
	public int getTotalRegistros() {
		return totalRegistros;
	}


	/**
	 * @return Datos (registros) incluidos en la Respuesta
	 */
	public List<Registro> getDatos() {
		return datos;
	}
	
	
	//M�TODOS
	/**
	 * @return N�mero de registros que contiene la Respuesta
	 */
	public int numeroRegistros() {
		return datos.size();
	}
	

	/**
	 * @return Posici�n del �ltimo registro que est� incluido en las Respuesta
	 */
	public int registroHasta() {
		return primerRegistro + numeroRegistros() - 1;
	}
	
	
	/**
	 * Calcula el n�mero de p�gina actual en funci�n de los datos de paginaci�n informados en la Pregunta y los datos obtenidos en la consulta
	 * 
	 * @return N�mero de p�gina actual teniendo en cuenta el l�mite de n�mero de registros devueltos y la posici�n del primer registro devuelto
	 */
	public int pagina() {
		//Si 'limite' es 0, devolvemos 1 (estamos en la p�gina 1, la �nica que hay)
		if (limite == 0) {
			return 1;
		}
		
		//Vemos cuantos registros ten�amos "antes" de los devueltos en la consulta
		int registrosAnteriores = primerRegistro - 1;
		
		int paginasAnteriores = registrosAnteriores / limite;
		//Si el resto de la anterior divisi�n no es cero, hay que a�adir una p�gina m�s
		if ( (registrosAnteriores % limite) != 0) {
			paginasAnteriores++;
		}
		
		//Devolvemos las paginasAnteriores + 1, que ser�a la actual
		return paginasAnteriores + 1;
	}
	
	
	/**
	 * Calcula el n�mero de p�ginas totales en funci�n de los datos de paginaci�n informados en la Pregunta y los datos obtenidos en la consulta
	 * 
	 * @return N�mero de p�ginas totales atendiendo al l�mite actual de registros en la Respuesta
	 */
	public int totalPaginas() {
		//Si 'limite' es 0, devolvemos 1 (S�lo hay una p�gina)
		if (limite == 0) {
			return 1;
		}
		
		//Vemos cuantos registros tenemos "despu�s" de los devueltos en la consulta
		int registrosPosteriores = totalRegistros - registroHasta();
		
		int paginasPosteriores = registrosPosteriores / limite;
		//Si el resto de la anterior divisi�n no es cero, hay que a�adir una p�gina m�s
		if ( (registrosPosteriores % limite) != 0) {
			paginasPosteriores++;
		}
		
		//Devolvemos la suma de todas las p�ginas hasta la actual (o sea, 'pagina()') m�s paginasPosteriores
		return pagina() + paginasPosteriores;
	}
}
