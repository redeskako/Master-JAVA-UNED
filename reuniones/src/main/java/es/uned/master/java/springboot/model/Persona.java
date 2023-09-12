package es.uned.master.java.springboot.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
/*
 * Debemos generar una entidad que es la figura que envuelve a una tabla en nuestro caso PERSONA
 * Para ello usaremos la anotacion Entity y Table
 */
@Entity
@Table(name="PERSONA")
public class Persona implements Serializable{
	// Ahora definimos las propiedades de la tabla asociada a cada una de la clase
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="NOMBRE", nullable=false)
	private String nombre;
	@Column(name="APELLIDOS", nullable=false)
	private String apellidos;

	public Persona() {}

	public Persona(int i, String nombre, String apellidos) {
		this.id = i;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
}