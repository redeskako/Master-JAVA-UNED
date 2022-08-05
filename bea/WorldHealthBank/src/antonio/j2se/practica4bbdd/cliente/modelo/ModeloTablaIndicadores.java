package antonio.j2se.practica4bbdd.cliente.modelo;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import antonio.j2se.practica4bbdd.servidor.modelo.Indicador;

/**
 * Clase que extiende javax.swing.table.AbstractTableModel
 * Representa el modelo de tabla para contener Indicadores
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see javax.swing.table.AbstractTableModel
 */
public class ModeloTablaIndicadores extends AbstractTableModel{
	private static final long serialVersionUID = -306320296869265609L;
	protected Collection<Indicador> datos;
    protected Collection<String>columnas;
  //identifica el filtro que se uso para la obtencion de los datos o null si no hubo filtro
    protected String filtroCarga;

    /**
     * Constructor utilizado para inicializar el GUI
     */
    public ModeloTablaIndicadores(){
    	super();
    	this.datos=new ArrayList<Indicador>();
    	this.columnas=new ArrayList<String>();
    	this.columnas.add("INDICATOR CODE");
    	this.columnas.add("INDICATOR NAME");

    }
    
    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaIndicadores(Collection<Indicador> datos,Collection<String> columnas){
    	super();
    	this.columnas=columnas;
    	this.datos=datos;
    }

    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaIndicadores(Collection<Indicador> datos,String pais){
    	super();
    	this.columnas=new ArrayList<String>();
    	this.columnas.add("INDICATOR CODE");
    	this.columnas.add("INDICATOR NAME");
    	this.datos=datos;
    	this.filtroCarga=pais;
    }

    /**
     * DEvuelve el numero de columnas
     */
	public int getColumnCount() {
		return columnas.size();
	}

    /**
     * DEvuelve el numero de filas
     */
	public int getRowCount() {
		return datos.size();
	}


	/**
	 * DEvuelve el valor correspondiente a la fila y coluna recibidos como parametros
	 */
	public Object getValueAt(int fila, int columna) {
		Object[]datosArray=datos.toArray();
		Object retorno=null;
		Indicador indicador=(Indicador)datosArray[fila];
		switch (columna) {
		case 0:
			retorno=indicador.getCodigo();
			break;
		case 1:
			retorno=indicador.getDescripcion();
			break;
     	}
		return retorno;

	}

	/**
	 * Devuelve el nombre de la columna recibida como parametro
	 */
	@Override
	public String getColumnName(int column) {
		Object[]columnasArray=columnas.toArray();
		return (String)columnasArray[column];
	}

	/**
	 * Devuelve el filtro con el que se obtuvieron los datos que forma el modelo tabla
	 * @return
	 */
	public String getFiltro() {
		return filtroCarga;
	}
	
	
}
