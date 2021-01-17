import javax.swing.table.AbstractTableModel;
public class ModeloIndicadores  extends AbstractTableModel{

final String[] columnNames = {"indicator_code","indicator_name","source_note", "source_organization"};
	 
final Object[][] data = {
{"Mary", "Campione",
 "Esquiar", new Integer(5)},
{"Lhucas", "Huml",
   "Patinar", new Integer(3)},
{"Kathya", "Walrath",
  "Escalar", new Integer(2)},
{"Marcus", "Andrews",
 "Correr", new Integer(7)},
{"Angela", "Lalth",
   "Nadar", new Integer(4)}
};

//únicamente retornamos el numero de elementos del
//array de los nombres de las columnas
public int getColumnCount() {
   return columnNames.length;
}
//retormanos el numero de elementos
//del array de datos
public int getRowCount() {
   return data.length;
}
//retornamos el elemento indicado
public String getColumnName(int col) {
   return columnNames[col];
}
//y lo mismo para las celdas
public Object getValueAt(int row, int col) {
   return data[row][col];
}
/*
 * Este metodo sirve para determinar el editor predeterminado
 * para cada columna de celdas
 */
public Class getColumnClass(int c) {
   return getValueAt(0, c).getClass();
}
/*
 * No tienes que implementar este método a menos que
 * las celdas de tu tabla sean Editables
 */
public boolean isCellEditable(int row, int col) {
       return true;
}
/*
 * No tienes que implementar este método a menos que
 * los datos de tu tabla cambien
 */
public void setValueAt(Object value, int row, int col) {
   data[row][col] = value;
   fireTableCellUpdated(row, col);
}

}
