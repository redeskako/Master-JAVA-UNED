package es.uned2014.recursosalpha.clasesCliente;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una petición o reserva que un usuario ha hecho para un recurso
 * durante un periodo de tiempo.
 * 
 * 
 * @author Alpha UNED 2014
 * @version RecursosWS 1.0
 * @since Septiembre 2014
 */
public class Reserva {
	private int idReserva;
	private int idUsuario;
	private int idRecurso;
	private Date inicio;
	private Date fin;
	private int idEstado;
	private int idSucursal;
	private String nombreUsuario;
	private String descripcionRecurso;
	private String estado;
	private String sucursal;

	/**
	 * Método constructor: inicializa las variables de la clase.
	 */
	public Reserva() {
		this.idReserva = 0;
		this.idUsuario = 0;
		this.idRecurso = 0;
		this.inicio = new Date();
		this.fin = new Date();
		this.idEstado = 0;
		this.idSucursal = 0;
		this.nombreUsuario = "";
		this.descripcionRecurso = "";
		this.estado = "";
		this.sucursal = "";
	}

	/**
	 * @return fecha de inicio con formato
	 */
	public String inicioToString() {
		String s = new SimpleDateFormat("yyyy-MM-dd (HH:mm)").format(inicio);
		return s;
	}

	/**
	 * @return fecha de final con formato
	 */
	public String finToString() {
		String s = new SimpleDateFormat("yyyy-MM-dd (HH:mm)").format(fin);
		return s;
	}

	/**
	 * @return the idReserva
	 */
	public int getIdReserva() {
		return idReserva;
	}

	/**
	 * @param idReserva
	 *            the idReserva to set
	 */
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idRecurso
	 */
	public int getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso
	 *            the idRecurso to set
	 */
	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the inicio
	 */
	public Date getInicio() {
		return inicio;
	}

	/**
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @param fin
	 *            the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @return the idEstado
	 */
	public int getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado
	 *            the idEstado to set
	 */
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal
	 *            the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * @param nombreUsuario
	 *            the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * @return the descripcionRecurso
	 */
	public String getDescripcionRecurso() {
		return descripcionRecurso;
	}

	/**
	 * @param descripcionRecurso
	 *            the descripcionRecurso to set
	 */
	public void setDescripcionRecurso(String descripcionRecurso) {
		this.descripcionRecurso = descripcionRecurso;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Representación en String de una Reserva
	 */
	public String toString() {
		String s = "IdReserva: " + this.idReserva + " | IdUsuario: "
				+ this.idUsuario + " | IdRecurso: " + this.idRecurso
				+ " | Inicio: " + this.inicio + " | Fin: " + this.fin
				+ " | IdEstado: " + this.idEstado + " | Usuario: "
				+ this.nombreUsuario + " | Recurso: " + this.descripcionRecurso
				+ " | Estado: " + this.estado + " | Sucursal: " + this.sucursal;

		return s;
	}

}
