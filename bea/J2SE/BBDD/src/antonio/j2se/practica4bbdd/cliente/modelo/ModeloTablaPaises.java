package antonio.j2se.practica4bbdd.cliente.modelo;


import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import antonio.j2se.practica4bbdd.servidor.modelo.Pais;


/**
 * Clase que extiende javax.swing.table.AbstractTableModel
 * Representa el modelo de tabla para contener Paises
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see javax.swing.table.AbstractTableModel
 */
public class ModeloTablaPaises extends AbstractTableModel {
	private static final long serialVersionUID = 5885193484578254010L;
	protected Collection<Pais> datos;
    protected Collection<String>columnas;
    //identifica el filtro que se uso para la obtencion de los datos o null si no hubo filtro
    protected String filtro;

    /**
     * Constructor utilizado para inicializar el GUI
     */
    public ModeloTablaPaises(){
    	super();
    	datos=new ArrayList<Pais>();
    	columnas=new ArrayList<String>();
    	columnas.add("COUNTRY CODE");
    	columnas.add("COUNTRY NAME");

    }
    
    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaPaises(Collection<Pais> datos,Collection<String> columnas){
    	super();
    	this.columnas=columnas;
    	this.datos=datos;
    }

    /**
     * Constructor a utilizar para formar la tabla tras consultar al servidor
     */   
    public ModeloTablaPaises(Collection<Pais> datos,String filtro){
    	super();
    	this.columnas=new ArrayList<String>();
    	this.columnas.add("COUNTRY CODE");
    	this.columnas.add("COUNTRY NAME");
    	this.datos=datos;
    	this.filtro=filtro;
    }

    
	public int getColumnCount() {
		return columnas.size();
	}


	public int getRowCount() {
		return datos.size();
	}


	public Object getValueAt(int fila, int columna) {
		Object[]datosArray=datos.toArray();
		Object retorno=null;
		Pais pais=(Pais)datosArray[fila];
		switch (columna) {
		case 0:
			retorno=pais.getCodigo();
			break;
		case 1:
			retorno=pais.getDescripcion();
			break;
     	}
		return retorno;

	}

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
		return filtro;
	}
	
	
}
