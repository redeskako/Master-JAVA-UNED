/*
 * 
 * 
 */

package com.arquillos.gestres.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable {    
	private static final long serialVersionUID = 1105386464230627466L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reserva_id;  
	@ManyToOne
	@JoinColumn(name="solicitante")
	private Usuario solicitante;
	@ManyToOne
	@JoinColumn(name="recurso_id")	
	private Recurso recurso;  
	@ManyToOne
	@JoinColumn(name="autorizador")	
	private Usuario autorizador;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date inicio;  
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_fin")
	private Date fin;
	@Column(name = "autorizacion", columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)
	private EstadoReserva autorizacion;  
	@Temporal(TemporalType.TIMESTAMP)
	private Date creacion;  
	@Column(name = "estado_inicial", columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)
	private EstadoRecurso estadoInicial;
	@Column(name = "estado_final", columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)
	private EstadoRecurso estadoFinal;

	public Reserva() {
		autorizacion = EstadoReserva.PENDIENTE;
		creacion = new Date();
		inicio = new Date();
		fin = new Date();		
	} 

	public Reserva(Usuario usuario) {
		solicitante = usuario;
		autorizacion = EstadoReserva.PENDIENTE;
		creacion = new Date();
		fin = new Date();
		inicio = new Date();
	}

	public Integer getId() {
		return reserva_id;
	}

	public void setId(Integer id) {
		this.reserva_id = id;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Usuario getAutorizador () {
		return autorizador;
	}

	public void setAutorizador(Usuario autorizador) {
		this.autorizador = autorizador;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	} 

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public EstadoReserva getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(EstadoReserva autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public EstadoRecurso getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoPrevio(EstadoRecurso estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public EstadoRecurso getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(EstadoRecurso estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	@Override
	public int hashCode() {
		return (reserva_id != null ? reserva_id.hashCode() : 0);
	}

	@Override
	public boolean equals(Object objeto) {
		// TODO: Este método no funcionará si no se establece id
		if (!(objeto instanceof Reserva)) {
			return false;
		}
		Reserva otra = (Reserva)objeto;
		if ((this.reserva_id == null && otra.reserva_id != null) || 
				(this.reserva_id != null && !this.reserva_id.equals(otra.reserva_id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.arquillos.reserv.data.Reserva[ id = " + reserva_id + "]";
	}

	public boolean isNueva() {
		return reserva_id == null || reserva_id == 0;
	}

	public boolean validarPeriodo(Date inicio, Date fin) {
		return inicio.before(this.fin) && fin.after(this.inicio);
	}
}