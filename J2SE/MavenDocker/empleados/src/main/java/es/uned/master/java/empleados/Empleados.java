/*
 * 
 */
package es.uned.master.java.empleados;

import java.util.Set;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Class Empleados.
 */
public class Empleados{
	
	/** The empleados. */
	public Set<Empleado> empleados;
	
	/**
	 * Instantiates a new empleados.
	 */
	public Empleados() {
		this.empleados = new TreeSet<Empleado>();
	}

	/**
	 * Adds the empleado.
	 *
	 * @param e the e
	 */
	public void addEmpleado (Empleado e) {
		this.empleados.add(e);
	}
	
	/**
	 * Del empleado.
	 *
	 * @param e the e
	 */
	public void delEmpleado (Empleado e) {
		this.empleados.remove(e);
	}
	
	/**
	 * Exist empleado.
	 *
	 * @param e the e
	 */
	public void existEmpleado(Empleado e) {
		this.empleados.contains(e);
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return this.empleados.isEmpty();
	}
	

	@Override
	public String toString() {
		return "Empleados [empleados=" + this.empleados +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empleados == null) ? 0 : empleados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleados other = (Empleados) obj;
		if (empleados == null) {
			if (other.empleados != null)
				return false;
		} else if (!empleados.equals(other.empleados))
			return false;
		return true;
	}

	public int size() {
		return empleados.size();
	}

	public void clear() {
		empleados.clear();
	}
}
