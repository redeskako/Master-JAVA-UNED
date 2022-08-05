/*
 * 
 */
package es.uned.master.java.empleados;

// TODO: Auto-generated Javadoc
/**
 * The Class Empleado.
 */
public class Empleado implements Comparable<Empleado> {

	/** The id. */
	private int id;

	/** The nombre. */
	private String nombre;

	/** The apellidos. */
	private String apellidos;

	/** The dni. */
	private String DNI;

	public Empleado(String DNI) {
		this.DNI = DNI.toUpperCase();
	}
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	private int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Sets the apellidos.
	 *
	 * @param apellidos the new apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dNI the new dni
	 */
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	/**
	 * Compare to.
	 *
	 * @param emp the emp
	 * @return the int
	 */
	@Override
	public int compareTo(Empleado emp) {
		return this.DNI.toUpperCase().compareToIgnoreCase(emp.getDNI());
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return this.DNI.toUpperCase().hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		try {
			Empleado emp = (Empleado) o;
			return this.DNI.toUpperCase().equals(emp.getDNI());
		} catch (ClassCastException e) {
			throw new EmpleadosError("Error en la comparación." + e);
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "\nEmpleado (nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI + ")\n";
	}

}