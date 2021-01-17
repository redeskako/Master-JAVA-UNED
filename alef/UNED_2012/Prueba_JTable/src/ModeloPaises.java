import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class ModeloPaises extends AbstractTableModel{

	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	private int numColumns;
    private String columnNames[] = {"country_code","country_name"};



//Metodo obligatorio que debe devolver un entero con el numero de columnas de JTable
public int getColumnCount() {
    return columnNames.length;
}
//retormanos el numero de elementos
//del array de datos
public int getRowCount() {
    return data.size();
}
//retornamos el elemento indicado
public String getColumnName(int col) {
    return columnNames[col];
}
//y lo mismo para las celdas
public Object getValueAt(int row, int col) {
    return data.get(row);
}
 /*
  * Este metodo sirve para determinar el editor predeterminado
  * para cada columna de celdas
  */
public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
}
//Convierte las celdas indicadas en editables
public boolean isCellEditable(int row, int col) {
        return false;
}
//En el caso de que se modifique una celda es llamado este metodo para cambiar el valor en el modelo
/*public void setValueAt(Object value, int row, int col) {
    data[row][col] = value;
    fireTableCellUpdated(row, col);
}*/
public void addRow(Object[] row) {
    //data = new ArrayList<Object[]>();
	 int numRows = data.size();
     data.add(row);
     this.fireTableRowsInserted(numRows, numRows+1);
}

}
