/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import es.uned.master.java.healthworldbank.datos.Registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una respuesta dada por el servidor a una peticion hecha desde el cliente.
 * El cliente crear� una Pregunta y la enviar� al servidor. El servidor la procesar� y enviar� la Respuesta que corresponda  
 * 
 * @author grupo alef (Jos� Torrecilla)
 * @author jbelo
 * @date 2017 March
 */
public class Respuesta implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Tipo petición con el que se realizó la pregunta
     */
    private TipoPeticion tipoPeticion;

    /**
     * Offset usado para los datos que se están devolviendo
     */
    private int offset;

    /**
     * Cantidad total de registros en las tablas para la petición realizada
     */
    private int totalRegistros;

    /**
     * Página que se está devolviendo
     */
    private String page;

    /**
     * Datos devueltos
     */
    private ArrayList<Registro> datos;
	
	
	public Respuesta(TipoPeticion tipoPeticion, String page, int offset, int totalRegistros, List<Registro> datos) {
		this.tipoPeticion = tipoPeticion;
		this.offset = offset;
		this.totalRegistros = totalRegistros;
		this.page = page;
		if (datos != null) {
			this.datos = new ArrayList<Registro>(datos);
		} else {
			this.datos = null;
		}
	}

	/**
	 * @return Tipo de petici�n de la Pregunta que origina esta Respuesta
	 */
	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}


	/**
	 * @return Posici�n del primer registro devuelto en la Respuesta
	 */
	public int getOffset() {
		return offset;
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

	/**
	 * @return N�mero de registros que contiene la Respuesta
	 */
	public int numeroRegistros() {
		return datos.size();
	}



    public void setTipoPeticion(TipoPeticion tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public void setDatos(ArrayList<Registro> datos) {
        this.datos = datos;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
