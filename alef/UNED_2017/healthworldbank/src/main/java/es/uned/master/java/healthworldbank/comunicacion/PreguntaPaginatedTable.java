/**
 * 
 */
package es.uned.master.java.healthworldbank.comunicacion;

/**
 * Clase especilaizada para preguntas desde paginación
 * @author jbelo
 */
public abstract class PreguntaPaginatedTable extends Pregunta {

    /**
     * Offset con el que se realizó la pregunta que muetra los datos actuales
     */
    private int offset;

    /**
     * Tipo de acción de los botones, avance, neutral o atrás
     */
    private ActionType actionType;


    public PreguntaPaginatedTable(TipoPeticion tipoPeticion) {
        super(tipoPeticion);
    }

    public PreguntaPaginatedTable(TipoPeticion tipoPeticion, ActionType actionTypeType, int offset) {
		super(tipoPeticion);
		this.actionType = actionTypeType;
		this.offset = offset;

	}

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType requestType) {
        this.actionType = requestType;
    }
}
