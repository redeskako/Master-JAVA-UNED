package es.uned.master.java.juegoAhorcado.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Persona {

	private String dni;

	private String nombre;

	private String apellidos;
}