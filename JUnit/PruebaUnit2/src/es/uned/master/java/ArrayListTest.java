package es.uned.master.java;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
import org.junit.After;
import org.junit.Before;

public class ArrayListTest {

	List<Integer> testArray;

	@Before
	// Precondicion
	public void setUp() {
		testArray = new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1, 5));
	}


	@After
	// Postcondicion
	public void tearDown() {
		//no es necesaria validacion
	}

	@Test
	public void testAdd() {
		testArray.add(9);

		List<Integer> esperado = 
			new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1, 5, 9));

		assertEquals(testArray, esperado);
	}

	/**
	 * Elimina un valor de un array y verifica su correcta eliminacion.
	 */
	@Test
	public void testRemoveObject() {
		testArray.remove(new Integer(5));

		List<Integer> esperado = 
			new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1));

		assertEquals(testArray, esperado);
	}

	/**
	 * Test para verificar el valor devuelto.
	 */
	@Test
	public void testIndexOf() {
		assertEquals(testArray.indexOf(4), 2);
	}

}
