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
public class Recurso implements Serializable {
	private static final long serialVersionUID = -2183621154319252991L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recurso_id;  
	@Column(nullable = false, unique = true)
	private String nombre;  
	private String descripcion;  
	@Column(columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)	
	private EstadoRecurso estado;  
	@ManyToOne
	@JoinColumn(name = "autorizador")
	private Usuario autorizador;  
	@Column(columnDefinition = "enum('DUMMY')")
	@Enumerated(EnumType.STRING)
	private Color color;  
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	public Recurso() {
		color = Color.DEFECTO;
		estado = EstadoRecurso.DESCONOCIDO;
		fechaActualizacion = new Date();
	} 

	public Integer getId() {
		return recurso_id;
	}

	public void setId(Integer id) {
		this.recurso_id = id;
	}

  public String getNombre() {
	  return nombre;
  }

  public void setNombre(String nombre) {
	  this.nombre = nombre;
  }

  public String getDescripcion() {
	  return descripcion;
  }

  public void setDescripcion(String descripcion) {
	  this.descripcion = descripcion;
  }

  public EstadoRecurso getEstado() {
	  return estado;
  }

  public void setEstado(EstadoRecurso estado) {
	  this.estado= estado;
  }

	public Usuario getAutorizador () {
		return autorizador;
  }

  public void setAutorizador(Usuario autorizador) {
	  this.autorizador = autorizador;
  }

  public Color getColor() {
	  return color;
  }

  public void setColor(Color color) {
	  this.color = color;
  }

  public Date getFechaActualizacion() {
    return fechaActualizacion;
  }

  public void setFechaActualizacion(Date ultimaActualizacionEstado) {
	  this.fechaActualizacion = ultimaActualizacionEstado;
  }

  @Override
  public int hashCode() {
	  return (recurso_id != null ? recurso_id.hashCode() : 0);
  }

  @Override
  public boolean equals( Object objeto ) {
	  // TODO: Este método no funcionará si no se establece un valor para id
	  if (!(objeto instanceof Recurso)) {
		  return false;
	  }
	  Recurso otro = (Recurso)objeto;
	  if ((this.recurso_id == null && otro.recurso_id != null) || 
	  		(this.recurso_id != null && !this.recurso_id.equals(otro.recurso_id))) {
		  return false;
	  }
	  return true;
  }

  @Override
  public String toString() {
	  return nombre;
  }

  public boolean isNuevo() {
	  return recurso_id == null || recurso_id == 0;
  }  
}