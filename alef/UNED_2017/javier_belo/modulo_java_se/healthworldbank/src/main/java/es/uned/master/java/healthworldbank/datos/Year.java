package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Clase especializada para almecenar los datos de year
 * @author jbelo
 * @version 1.0
 */
public class Year implements Registro, Serializable {
	
	private static final long serialVersionUID = 2L;

    /**
     * Año
     */
    private Integer year;
	
	public Year(Integer year) {
		this.year = year;
	}
	
	public Year(){
		
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	

}
