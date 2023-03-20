/**
 * 
 */
package es.uned.master.java.JuegoAhorcado.tests;

import es.uned.master.java.juegoAhorcado.base.EstadoAhorcado;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author carlosl.sanchez
 *
 */
public class EstadoAhorcadoTest {


	@Test
	public void test() {
		EstadoAhorcado estado = EstadoAhorcado.PIERNA_IZQUIERDA;
		assertEquals(EstadoAhorcado.PIERNA_IZQUIERDA, estado);
		
		System.out.println(estado.getFigura());
		System.out.println(estado);
/*		assertEquals(estado.toString(),
				"+---+\n" +
		        "  | |\n" +
		        "    |\n" +
		        "    |\n" +
		        "    |\n" +
		        "    |\n" +
		        "=======\n");
*/
	}

}
