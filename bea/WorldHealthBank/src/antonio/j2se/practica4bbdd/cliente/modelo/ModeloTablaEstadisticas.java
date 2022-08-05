package antonio.j2se.practica4bbdd.cliente.modelo;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import antonio.j2se.practica4bbdd.servidor.modelo.Estadistica;


/**
 * Clase que extiende javax.swing.table.AbstractTableModel
 * Representa el modelo de tabla para contener Estadisticas
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see javax.swing.table.AbstractTableModel
 */
public class ModeloTablaEstadisticas extends AbstractTableModel{
	private static final long serialVersionUID = -4114329185563229370L;
	protected Collection<Estadistica> datos;
    protected Collection<String>columnas;

    /**
     * Constructor utilizado para inicializar el GUI
     */
    public ModeloTablaEstadisticas(){
    	super();
    	this.datos=new ArrayList<Estadistica>();
    	this.columnas=new ArrayList<String>();
    	this.columnas.add("INDICATOR CODE");
    	this.columnas.add("COUNTRY_CODE");
    	this.columnas.add("YEAR");
    	this.columnas.add("VALOR");

    }
    
    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaEstadisticas(Collection<Estadistica> datos,Collection<String> columnas){
    	super();
    	this.columnas=columnas;
    	this.datos=datos;
    }

    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaEstadisticas(Collection<Estadistica> datos){
    	super();
    	this.columnas=new ArrayList<String>();
    	this.columnas.add("INDICATOR CODE");
    	this.columnas.add("COUNTRY_CODE");
    	this.columnas.add("YEAR");
    	this.columnas.add("VALOR");
    	this.datos=datos;
    }

    /**
     * Devuelve el numero de columnas de la tabla
     */
	public int getColumnCount() {
		return columnas.size();
	}

    /**
     * Devuelve el numero de filas de la tabla
     */
	public int getRowCount() {
		return datos.size();
	}

    /**
     * Devuelve el valor correspondiente a fila y columna recibidas como parametros
     */
	public Object getValueAt(int fila, int columna) {
		Object[]datosArray=datos.toArray();
		Object retorno=null;
		Estadistica estadistica=(Estadistica)datosArray[fila];
		switch (columna) {
		case 0:
			retorno=estadistica.getCodigoIndicador();
			break;
		case 1:
			retorno=estadistica.getCodigoPais();
			break;
		case 2:
			retorno=estadistica.getYear();
			break;
		case 3:
			retorno=estadistica.getValor();
			break;			
     	}
		return retorno;

	}

	/**
	 * Devuelve el nombre de la columan recibida como parametro
	 */
	@Override
	public String getColumnName(int column) {
		Object[]columnasArray=columnas.toArray();
		return (String)columnasArray[column];
	}

}
