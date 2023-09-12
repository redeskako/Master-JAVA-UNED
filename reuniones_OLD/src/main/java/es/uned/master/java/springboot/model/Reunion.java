package es.uned.master.java.springboot.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
// Esta anotación me permitirá los principales comunes métodos get, set y toString
@Entity
@Table(name="REUNION")
@Order(2) // Indica el orden a mostrar.
public class Reunion implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="ASUNTO", nullable=false)
	private String asunto;
	@Column(name="FECHA", nullable=false)
	private ZonedDateTime fecha;
	// Vamos a contemplar una lista de asistentes en la reunion, usaremos Personas
	// Al igual vamos con Reuniones definir la entidades como he indicado anteriormente
	/*
	 * Con la lista debemos pensar el tipo de relacion Mucho a Muchos con cargo a la 
	 * relación entidad debil ASISTENTES(persona_id, reunion_id)
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			  name = "ASISTENTE", 
			  joinColumns = @JoinColumn(name = "persona_id"), 
			  inverseJoinColumns = @JoinColumn(name = "reunion_id"))
	private List<Persona> asistentes;

	public Reunion() {this.asistentes = new ArrayList();}
	public Reunion(Long id, String asunto, ZonedDateTime fecha) {
		this.id = id;
		this.asunto = asunto;
		this.fecha = fecha;
		this.asistentes = new ArrayList();
	}
	public Reunion(Long id, String asunto, ZonedDateTime fecha, List<Persona> asistentes) {
		this(id,asunto,fecha);
		this.asistentes = asistentes;
	}
	// Con Lombok ya dispondríamos de getAsistentes, pero quiero apadir una persona a la lista
	// Creo ese método
	public void addAsistente(Persona asistente) {
		this.asistentes.add(asistente);
	}
}