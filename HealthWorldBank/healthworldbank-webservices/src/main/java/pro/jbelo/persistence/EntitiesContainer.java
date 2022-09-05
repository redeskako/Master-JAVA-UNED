package pro.jbelo.persistence;

import java.util.ArrayList;

/**
 * Almazena las entidades solicitadas a la base de datos para ser mostradas en la vista
 * @author jbelo
 * @date 2017 05.
 */
public class EntitiesContainer {

    /**
     * offset actual de los registros de la base de datos
     */
    private int offset = 0;

    /**
     * Arraay con las entidades recuperadas
     */
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    /**
     * Getter entities
     * @return entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Setter entiti
     * @param entities
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * Getter offset
     * @return
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Setter offset
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
