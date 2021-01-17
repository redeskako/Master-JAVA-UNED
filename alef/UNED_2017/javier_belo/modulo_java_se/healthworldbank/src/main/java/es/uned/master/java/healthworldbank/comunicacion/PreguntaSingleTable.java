/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

/**
 * Clase especializada para preguntas para una única tabla
 * @author jbelo
 */
public class PreguntaSingleTable extends PreguntaPaginatedTable {



    public PreguntaSingleTable(TipoPeticion tipoPeticion){
        super(tipoPeticion);
    }

	public PreguntaSingleTable(TipoPeticion tipoPeticion, ActionType actionType, int offset) {
		super(tipoPeticion, actionType, offset);

	}


}
