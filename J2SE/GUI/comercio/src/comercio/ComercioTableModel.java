/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package comercio;

import comercio.error.ErrorComercioException;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adorado
 */
public class ComercioTableModel extends DefaultTableModel {
    private Map<Integer,Comercio> comercios;
    private Map<Integer,Comercio> comerciosMod;
    private ComercioBBDD    bd;
    private int nuevo;      // Contador de nuevos comercios
	public static final int COLUMNAS = 3;
	private String NOMCOL[] = {"Código", "Nombre", "País"};

    public ComercioTableModel(String servidor, String bbdd, String usuario, String pass) {
        super();
        this.nuevo = 0;
        this.bd = new ComercioBBDD(servidor, bbdd, usuario, pass);
        this.cargarDatos();
    }

    public void cargarDatos() {
        this.comercios = this.bd.consultaComercios();
        this.comerciosMod = new TreeMap<Integer, Comercio>();
    }

    private void actualizaComercio(String nuevo, int row, int column){
        if (column > 0){
            Comercio comercio;
            // Obtengo la key original
            Integer key = (Integer) this.comercios.keySet().toArray()[row];
            comercio = this.comercios.get(key);

            switch (column){
                case 1:
                    // Está actualizando la columna 'Nombre' (co_nombre)
                    if (!comercio.get_co_name().equals(nuevo)) {
                        // Solo actualizamos si el valor es distinto al actual
                        comercio.set_co_name(nuevo);
                         // Añadir a la lista de comercios a 'modificar'
                        this.comerciosMod.put(key, comercio);
                    }
                    break;
                case 2:
                    // Está actualizando la columna 'Pais' (co_pais)
                    if (!comercio.get_co_pais().equals(nuevo)) {
                        // Solo actualizamos si el valor es distinto al actual
                        comercio.set_co_pais(nuevo);
                        // Añadir a la lista de comercios a 'modificar'
                        this.comerciosMod.put(key, comercio);
                    }
                    break;
            }
        }
    }

    // Redefinicion de DefaultTableModel
    @Override
    public boolean isCellEditable(int row, int column) {
        // No se permite modificar el identificador (co_id)
        if (column > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getColumnCount() {
        return ComercioTableModel.COLUMNAS;
    }

    @Override
    public int getRowCount() {
        if (this.comercios == null) {
            return 0;
        } else {
            return this.comercios.keySet().size();
        }
    }
    // Obtencion de contenidos de columnas
    @Override
    public String getColumnName(int column) {
        return this.NOMCOL[column].toString();
    }
    // Necesario para obtener el objeto referenciante
    @Override
    public Class<?> getColumnClass(int column) {
        return this.getValueAt(0, column).getClass();
    }
    @Override
    public Object getValueAt(int row, int column) {
        Comercio comercio = this.comercios.get(this.comercios.keySet().toArray()[row]);

		switch (column){
			case 0:
                int co_id = comercio.get_co_id();
                if (co_id < 0) {
                    return new Integer(0);
                    //return "";
                } else {
                    return new Integer(co_id);
                }
			case 1:
				return comercio.get_co_name();
			case 2:
				return comercio.get_co_pais();
			default:
				throw new ErrorComercioException("La columna no existe.");
		}
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        try {
            //int sizeKey = this.comercios.keySet().size();
            this.actualizaComercio((String) aValue, row, column);
            this.fireTableDataChanged();
        } catch(ClassCastException e) {
            throw new ErrorComercioException("Clase no encontrada "+e);
        }
    }

    // Indica si hay cambios pendientes de actualizar en la base de datos
    public boolean hayCambios() {
        return (this.comerciosMod.size() > 0);
    }

    // Acciones sobre el modelo
    public void aceptar() {
        if (this.hayCambios()) {
            this.bd.actualizaComercios(this.comerciosMod);
            this.comerciosMod = new TreeMap<Integer, Comercio>();
            this.cargarDatos();
            this.fireTableDataChanged();
        }
    }

    public void cancelar() {
        this.cargarDatos();
        this.fireTableDataChanged();
    }

    public void nuevo() {
        this.nuevo --;
        Comercio comercio = new Comercio(this.nuevo, "", "");
        this.comercios.put(new Integer(this.nuevo), comercio);
        this.comerciosMod.put(new Integer(this.nuevo), comercio);
        this.fireTableDataChanged();
    }
}
