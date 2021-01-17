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
 * El cliente creará una Pregunta y la enviará al servidor. El servidor la procesará y enviará la Respuesta que corresponda  
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 7/4/2012 
 */
public class Respuesta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoPeticion tipoPeticion; 	//Tipo de petición de la Pregunta que origina esta Respuesta
	private int limite; 				//Número máximo de registros que se indicaba en la Pregunta que debía contener la Respuesta
	private int primerRegistro; 		//Posición del primer registro devuelto en la Respuesta
	//private int numeroRegistros; 		//CALCULADO. Número de registros que contiene la Respuesta
	//private int registroHasta;		//CALCULADO. Posición del último registro que está incluido en las Respuesta
	private int totalRegistros; 		//Número total de registros que tendría la petición original completa (es decir, si no se hubiera limitado el número de registros)
	//private int pagina; 				//CALCULADO. Número de página actual teniendo en cuenta el límite de número de registros devueltos y la posición del primer registro devuelto 
	//private int totalPaginas; 		//CALCULADO. Número de páginas totales atendiendo al límite actual de registros en la Respuesta
	private ArrayList<Registro> datos;	//Datos (registros) incluidos en la Respuesta
	
	
	//CONSTRUCTOR
	/**
	 * Constructor de la clase Respuesta
	 * 
	 * @param tipoPeticion Tipo de petición de la Pregunta que origina esta Respuesta
	 * @param limite Número máximo de registros que se indicaba en la Pregunta que debía contener la Respuesta
	 * @param primerRegistro Posición del primer registro devuelto en la Respuesta
	 * @param totalRegistros Número total de registros que tendría la petición original completa (es decir, si no se hubiera limitado el número de registros)
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
	 * @return Tipo de petición de la Pregunta que origina esta Respuesta
	 */
	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}


	/**
	 * @return Número máximo de registros que se indicaba en la Pregunta que debía contener la Respuesta
	 */
	public int getLimite() {
		return limite;
	}


	/**
	 * @return Posición del primer registro devuelto en la Respuesta
	 */
	public int getPrimerRegistro() {
		return primerRegistro;
	}


	/**
	 * @return Número total de registros que tendría la petición original completa (es decir, si no se hubiera limitado el número de registros)
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
	
	
	//MÉTODOS
	/**
	 * @return Número de registros que contiene la Respuesta
	 */
	public int numeroRegistros() {
		return datos.size();
	}
	

	/**
	 * @return Posición del último registro que está incluido en las Respuesta
	 */
	public int registroHasta() {
		return primerRegistro + numeroRegistros() - 1;
	}
	
	
	/**
	 * Calcula el número de página actual en función de los datos de paginación informados en la Pregunta y los datos obtenidos en la consulta
	 * 
	 * @return Número de página actual teniendo en cuenta el límite de número de registros devueltos y la posición del primer registro devuelto
	 */
	public int pagina() {
		//Si 'limite' es 0, devolvemos 1 (estamos en la página 1, la única que hay)
		if (limite == 0) {
			return 1;
		}
		
		//Vemos cuantos registros teníamos "antes" de los devueltos en la consulta
		int registrosAnteriores = primerRegistro - 1;
		
		int paginasAnteriores = registrosAnteriores / limite;
		//Si el resto de la anterior división no es cero, hay que añadir una página más
		if ( (registrosAnteriores % limite) != 0) {
			paginasAnteriores++;
		}
		
		//Devolvemos las paginasAnteriores + 1, que sería la actual
		return paginasAnteriores + 1;
	}
	
	
	/**
	 * Calcula el número de páginas totales en función de los datos de paginación informados en la Pregunta y los datos obtenidos en la consulta
	 * 
	 * @return Número de páginas totales atendiendo al límite actual de registros en la Respuesta
	 */
	public int totalPaginas() {
		//Si 'limite' es 0, devolvemos 1 (Sólo hay una página)
		if (limite == 0) {
			return 1;
		}
		
		//Vemos cuantos registros tenemos "después" de los devueltos en la consulta
		int registrosPosteriores = totalRegistros - registroHasta();
		
		int paginasPosteriores = registrosPosteriores / limite;
		//Si el resto de la anterior división no es cero, hay que añadir una página más
		if ( (registrosPosteriores % limite) != 0) {
			paginasPosteriores++;
		}
		
		//Devolvemos la suma de todas las páginas hasta la actual (o sea, 'pagina()') más paginasPosteriores
		return pagina() + paginasPosteriores;
	}
}
