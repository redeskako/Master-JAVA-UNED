/**
 * 
 */
package es.uned.master.java.empleados;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author carlosl.sanchez
 *
 */
public class EmpleadoTest {

	@Test
	public void compareToTest() {
		Empleado emp = new Empleado("33F");
		assertEquals(0, emp.compareTo(new Empleado("33f")));
	}
	
	@Test
	public void equalsTest() {
		Empleado emp = new Empleado("33F");
		assertTrue("Comparación", emp.equals(new Empleado("33f")));
	}

}
