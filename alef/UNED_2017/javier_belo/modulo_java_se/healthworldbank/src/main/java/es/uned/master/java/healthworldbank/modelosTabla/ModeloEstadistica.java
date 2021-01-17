package es.uned.master.java.healthworldbank.modelosTabla;

import es.uned.master.java.healthworldbank.datos.Estadistica;
import es.uned.master.java.healthworldbank.datos.Registro;

import java.util.ArrayList;


/**
 * Clase especializada en el modelo de estadística
 *
 */
public class ModeloEstadistica  extends CustomAbstractTableModel {

	private static final long serialVersionUID = -819212710612555154L;

    /**
     * Datos de estadística
     */
    private ArrayList<Estadistica> data = new ArrayList<Estadistica>();

    /**
     * Columna presentes en la table
     */
    private String columnNames[] = {"indicator_code", "country_code", "year", "valor"};

    /**
     * Filtro a aplicar
     */
    private String filtro=null;

		
		
    public String getFiltro(){
	 		return filtro;
	 	}

    public void setFiltro(String filtro){
	 		this.filtro=filtro;
	 	}



	//Metodo obligatorio que devuelve un entero con el numero de columnas de JTable
	public int getColumnCount() {
	    return columnNames.length;
	}
	//Metodo obligatorio que devuelve el numero de filas
	public int getRowCount() {
	    return data.size();
	}
	//Metodo opcional que devuelve el nombre de cada columna
	public String getColumnName(int col) {
	    return columnNames[col];
	}
	//Metodo obligatorio que devuelve el valor de la celda indiada como argumento (fila, calumna)
	public Object getValueAt(int fila, int columna) {
		Object[]datosArray=data.toArray();
		Object valor=null;
		Estadistica estadistica=(Estadistica)datosArray[fila];
		switch (columna) {
		case 0:
			valor=estadistica.getCodigoIndicador();
			break;
		case 1:
			valor=estadistica.getCodigoPais();
			break;
		case 2:
			valor=estadistica.getAno();
			break;
		case 3:
			valor=estadistica.getDato();
			break;
	 	}
		return valor;
	}
	//Metodo que convierte todas las celdas en No editables
	public boolean isCellEditable(int row, int col) {
	        return false;
	}
	//Sobreescribe el metodo addRow para a�adir una fila con los datos que se le pasan como argumento
	public void addRow(Estadistica row) {
	    //data = new ArrayList<Object[]>();
		 int numRows = data.size();
		 data.add(row);
	     this.fireTableRowsInserted(numRows, numRows+1);//Metodo que indica a la tabla las filas que tiene que actualizar
	}
	//Metodo que agrega a la tabla un arrayList de datos
    @Override
	public void addAllRows(ArrayList<Registro> datos) {
	     ArrayList<Estadistica> ale = (ArrayList<Estadistica>)(ArrayList<?>) datos;
		 int numRows = ale.size();
		 data.addAll(ale);
	     this.fireTableRowsInserted(numRows, numRows);

	}
	//Sobreescribe el metodo removeRow para que borre la ultima fila
	public void removeRow() {
		int numRows = data.size()-1;
		if(numRows>=0){
	    data.remove(numRows);
	    this.fireTableRowsDeleted(numRows, numRows);//Metodo que indica a la tabla las filas que tiene que actualizar
		}
	}
	//Metodo que borra todo el contenido de la tabla
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