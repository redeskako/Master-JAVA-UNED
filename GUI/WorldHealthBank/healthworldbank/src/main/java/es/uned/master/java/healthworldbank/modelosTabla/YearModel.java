package es.uned.master.java.healthworldbank.modelosTabla;

import es.uned.master.java.healthworldbank.datos.Registro;
import es.uned.master.java.healthworldbank.datos.Year;

import java.util.ArrayList;

/**
 * Clase especializada en modelo años
 * @author jbelo
 * @date March 2017
 */
public class YearModel extends CustomAbstractTableModel {
	
	private static final long serialVersionUID = -170422963248L;
	private ArrayList<Year> data= new ArrayList<Year>();
	//private int numColumns;
    private String columnNames[]= {"year"};
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
    @Override
    public void setFiltro(String filtro){
    	this.filtro=filtro;
    }
    
    

	public YearModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		 return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[]datosArray=data.toArray();
		Object valor=null;
		Year year=(Year)datosArray[rowIndex];
		switch (columnIndex) {
		case 0:
			valor=year.getYear();
			break;
	 	}
		return valor;
	}
	
	public String getColumnName(int col) {
	    return columnNames[col];
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
	public void addRow(Year row) {
	    //data = new ArrayList<Object[]>();
		 int numRows = data.size();
		 data.add(row);
	     this.fireTableRowsInserted(numRows, numRows+1);//Metodo que indica a la tabla las filas que tiene que actualizar
	}
	/**
	 * Metodo que agrega a la tabla un arrayList de datos
	 */
	public void addAllRows(ArrayList<Registro> datos) {
        ArrayList<Year> ale = (ArrayList<Year>)(ArrayList<?>) datos;
        int numRows = ale.size();
        data.addAll(ale);
        this.fireTableRowsInserted(numRows, numRows);

	    //int numRows = datos.size();
        //data.addAll(datos);
        //this.fireTableRowsInserted(numRows, numRows);

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
