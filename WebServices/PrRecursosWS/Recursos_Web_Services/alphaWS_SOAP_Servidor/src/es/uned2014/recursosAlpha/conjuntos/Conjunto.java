package es.uned2014.recursosAlpha.conjuntos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import es.uned2014.recursosAlpha.reserva.*;
import es.uned2014.recursosAlpha.recurso.*;
import es.uned2014.recursosAlpha.usuario.*;

/**
 * La clase Conjunto se utiliza para usar como envolvente para elementos que se
 * van a enviar por medio de servicio web.
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Septiembre 2014
 */

@XmlRootElement(name = "conjunto")
public class Conjunto {

	private ArrayList<Reserva> arrayReservas;
	private ArrayList<Usuario> arrayUsuarios;
	private ArrayList<Recurso> arrayRecursos;
	private ArrayList<Reserva> arrayEstados;
	private ArrayList<Reserva> arraySucursales;
	private int numeroFilas;

	/**
	 * Constructor: inicializa los campos de la clase.
	 */
	public Conjunto() {
		this.arrayReservas = new ArrayList<Reserva>();
		this.arrayUsuarios = new ArrayList<Usuario>();
		this.arrayRecursos = new ArrayList<Recurso>();
		this.arrayEstados = new ArrayList<Reserva>();
		this.arraySucursales = new ArrayList<Reserva>();
		this.numeroFilas = 0;
	}

	/**
	 * @return the arrayReservas
	 */
	public ArrayList<Reserva> getArrayReservas() {
		return arrayReservas;
	}

	/**
	 * @param arrayReservas
	 *            the arrayReservas to set
	 */
	public void setArrayReservas(ArrayList<Reserva> arrayReservas) {
		this.arrayReservas = arrayReservas;
	}

	/**
	 * @return the arrayUsuarios
	 */
	public ArrayList<Usuario> getArrayUsuarios() {
		return arrayUsuarios;
	}

	/**
	 * @param arrayUsuarios
	 *            the arrayUsuarios to set
	 */
	public void setArrayUsuarios(ArrayList<Usuario> arrayUsuarios) {
		this.arrayUsuarios = arrayUsuarios;
	}

	/**
	 * @return the arrayRecursos
	 */
	public ArrayList<Recurso> getArrayRecursos() {
		return arrayRecursos;
	}

	/**
	 * @param arrayRecursos
	 *            the arrayRecursos to set
	 */
	public void setArrayRecursos(ArrayList<Recurso> arrayRecursos) {
		this.arrayRecursos = arrayRecursos;
	}

	/**
	 * @return the arrayEstados
	 */
	public ArrayList<Reserva> getArrayEstados() {
		return arrayEstados;
	}

	/**
	 * @param arrayEstados
	 *            the arrayEstados to set
	 */
	public void setArrayEstados(ArrayList<Reserva> arrayEstados) {
		this.arrayEstados = arrayEstados;
	}

	/**
	 * @return the arraySucursales
	 */
	public ArrayList<Reserva> getArraySucursales() {
		return arraySucursales;
	}

	/**
	 * @param arraySucursales
	 *            the arraySucursales to set
	 */
	public void setArraySucursales(ArrayList<Reserva> arraySucursales) {
		this.arraySucursales = arraySucursales;
	}

	/**
	 * @return the numeroFilas
	 */
	public int getNumeroFilas() {
		return numeroFilas;
	}

	/**
	 * @param numeroFilas
	 *            the numeroFilas to set
	 */
	public void setNumeroFilas(int numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

}
