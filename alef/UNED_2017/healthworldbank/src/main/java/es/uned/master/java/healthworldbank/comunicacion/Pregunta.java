/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

import java.io.Serializable;


/**
 * Clase con atributos comunes para las preguntas
 * @author jbelo
 */
public abstract class Pregunta implements Serializable {

    private static final long serialVersionUID = 0L;

    /**
     * Indica el tipo de pregunta realizada
     */
    private TipoPeticion tipoPeticion;

	public Pregunta(){};

	public Pregunta(TipoPeticion tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}



	public TipoPeticion getTipoPeticion() {
		return tipoPeticion;
	}

    public void setTipoPeticion(TipoPeticion tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }
}
