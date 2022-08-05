package antonio.j2se.practica4bbdd.servidor.modelo;

import java.io.Serializable;
/**
 * Representa una Estadistica
 * Es la abstraccion de una tupla de la tabla Estadistica
 * @author asanton
 *
 */
public class Estadistica implements Serializable {
	private static final long serialVersionUID = -8537822244413263018L;
    protected String codigoPais;
    protected String codigoIndicador;
    protected int year;
    protected float valor;

    /**
     * Constructor
     * @param codigo
     * @param descripcion
     */
    public Estadistica(String codigoPais, String codigoIndicador, int year,float valor) {
		this.codigoPais = codigoPais;
		this.codigoIndicador = codigoIndicador;
		this.year = year;
		this.valor = valor;
	}


	/**
	 * Retorna el codigo del pais
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Retorna el codigo del indicador
	 * @return
	 */
	public String getCodigoIndicador() {
		return codigoIndicador;
	}

	/**
	 * Retorna el año
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Retorna el valor
	 * @return
	 */
	public float getValor() {
		return valor;
	}
    
    
    

}
