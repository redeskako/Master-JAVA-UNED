package es.uned.master.java.healthworldbank.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.icefaces.ace.model.table.LazyDataModel;
import org.icefaces.ace.model.table.SortCriteria;

import es.uned.master.java.healthworldbank.datos.Pais;
import es.uned.master.java.healthworldbankws.cliente.ServicioDatos;

/**
 * Modelo de la tabla de los Paises
 * 
 */
@ManagedBean
@ViewScoped
public class BeanPaises implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -118932026947706087L;
	private LazyDataModel<Pais> data;

	public BeanPaises() {
		setData(new LazyDataModel<Pais>() 
		        {
		            /**
					 * 
					 */
					private static final long serialVersionUID = -6481367997536947535L;

					@Override
		            public List<Pais> load(int first, int pageSize, SortCriteria[] criteria, Map<String, String> filters) 
		            {
		                List<Pais> losPaises;
		                ServicioDatos datos = ServicioDatos.getInstancia();
		                System.out.println("first: " + first + " - pageSize: " + pageSize); 
		                losPaises = datos.obtenerPaises(pageSize, first); 
		                return losPaises;
		            }

		        });
		        
		        data.setRowCount(ServicioDatos.getInstancia().obtenerNumPaises());
	}

	public LazyDataModel<Pais> getData() {
		return data;
	}

	public void setData(LazyDataModel<Pais> datosPaises) {
		this.data = datosPaises;
	}

	
}
