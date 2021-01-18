package es.uned.master.java.healthworldbank.modelosTabla;

import es.uned.master.java.healthworldbank.datos.Registro;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Superclase para tables model
 * @author jbelo
 * @date 2017 March
 */
public abstract class CustomAbstractTableModel extends AbstractTableModel {

    public abstract void removeAllRows();

    public abstract void addAllRows(ArrayList<Registro> mList);

    public abstract void setFiltro(String filtro);
}
