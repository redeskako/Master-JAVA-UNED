package es.uned.master.java.healthworldbank.datos;

import java.io.Serializable;

/**
 * Created by javierbelogarcia on 23/02/2017.
 */
public class EstadisticaMap implements Registro, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String codigoPais; 		//El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
    private double dato; 			//El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'


    /**
     * Constructor de la clase Estadistica
     *
     *
     * @param codigoPais El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
     * @param dato El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
     */
    public EstadisticaMap(String codigoPais, double dato) {
        this.codigoPais = codigoPais;
        this.dato = dato;
    }


    /**
     * Constructor de la clase Estadistica sin par�metros
     * �til si se quiere crear la instancia y despu�s establecer los valores con los m�todos 'set...'
     */
    public EstadisticaMap() {
    }


    /**
     * @return El c�digo del indicador de salud (


    /**
     * @return El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
     */
    public String getCodigoPais() {
        return codigoPais;
    }



    /**
     * @return El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
     */
    public double getDato() {
        return dato;
    }


    /**
     * @param codigoPais El c�digo del pa�s (country_code de la tabla DATA en la Base de Datos)
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }


    /**
     * @param dato El dato num�rico acerca del indicador de salud con c�digo 'codigoIndicador' para el pa�s con c�digo 'codigoPais' en al a�o 'ano'
     */
    public void setDato(double dato) {
        this.dato = dato;
    }
}
