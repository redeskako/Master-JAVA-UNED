package es.uned.master.java.healthworldbank.modelosTabla;

import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbank.datos.Registro;

import java.util.ArrayList;
/**
 * Modelo de la tabla de los Paises
 * 
 * @author grupo alef (Hector Hernandez)
 * @author jbelo
 * @date 2017 March
 */
public class ModeloPaises extends CustomAbstractTableModel {

	private static final long serialVersionUID = -1704229630201073248L;
	private ArrayList<Pais> data= new ArrayList<Pais>();
	//private int numColumns;
    private String columnNames[]= {"country_code","country_name"};
    private String filtro=null;
 /**
 * Metodo que devuelve el valor del filtro
 */
public String getFiltro(){
	return filtro;
}
/**
 * Metodo que define el valor del filtro
 */
public void setFiltro(String filtro){
	this.filtro=filtro;
}
/**
 * Metodo obligatorio que devuelve un entero con el numero de columnas de JTable
 */
public int getColumnCount() {
    return columnNames.length;
}
/**
 * Metodo obligatorio que devuelve el numero de filas
 */
public int getRowCount() {
    return data.size();
}
/**
 * Metodo opcional que devuelve el nombre de cada columna
 */
public String getColumnName(int col) {
    return columnNames[col];
}
/**
 * Metodo obligatorio que devuelve el valor de la celda indiada como argumento (fila, calumna)
 */
public Object getValueAt(int fila, int columna) {
	Object[]datosArray=data.toArray();
	Object valor=null;
	Pais pais=(Pais)datosArray[fila];
	switch (columna) {
	case 0:
		valor=pais.getCodigo();
		break;
	case 1:
		valor=pais.getNombre();
		break;
 	}
	return valor;
}
/**
 * Metodo que convierte todas las celdas en No editables
 */
public boolean isCellEditable(int row, int col) {
        return false;
}
/**
 * Sobreescribe el metodo addRow para a�adir una fila con los datos que se le pasan como argumento
 */
public void addRow(Pais row) {
    //data = new ArrayList<Object[]>();
	 int numRows = data.size();
	 data.add(row);
     this.fireTableRowsInserted(numRows, numRows+1);//Metodo que indica a la tabla las filas que tiene que actualizar
}
/**
 * Metodo que agrega a la tabla un arrayList de datos
 */
public void addAllRows(ArrayList<Registro> datos) {
    ArrayList<Pais> ale = (ArrayList<Pais>)(ArrayList<?>) datos;
    int numRows = ale.size();
    data.addAll(ale);
    this.fireTableRowsInserted(numRows, numRows);

    //int numRows = datos.size();
	// data.addAll(datos);
    // this.fireTableRowsInserted(numRows, numRows);

}
/**
 * Sobreescribe el metodo removeRow para que borre la ultima fila
 */
public void removeRow() {
	int numRows = data.size()-1;
	if(numRows>=0){
    data.remove(numRows);
    this.fireTableRowsDeleted(numRows, numRows);//Metodo que indica a la tabla las filas que tiene que actualizar
	}
}
/**
 * Metodo que borra todo el contenido de la tabla
 */
@Override
public void removeAllRows() {
	int numRows = data.size();
	//System.out.println("data.size= "+numRows);
	for(int i=0; i<numRows; i++){
	//	System.out.println("i= "+i);
		removeRow();
    }
	
}
}
