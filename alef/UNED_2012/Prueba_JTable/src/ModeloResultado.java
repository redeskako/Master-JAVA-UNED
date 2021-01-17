import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;
public class ModeloResultado  extends AbstractTableModel{

	 final String[] columnNames = {"Nombre",
            "Apellido",
            "Pasatiempo",
            "Años de Practica",
            "Soltero(a)"};
final Object[][] data = {
{"Mary", "Campione",
 "Esquiar", new Integer(5), new Boolean(false)},
{"Lhucas", "Huml",
   "Patinar", new Integer(3), new Boolean(true)},
{"Kathya", "Walrath",
  "Escalar", new Integer(2), new Boolean(false)},
{"Marcus", "Andrews",
 "Correr", new Integer(7), new Boolean(true)},
{"Angela", "Lalth",
   "Nadar", new Integer(4), new Boolean(false)}
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