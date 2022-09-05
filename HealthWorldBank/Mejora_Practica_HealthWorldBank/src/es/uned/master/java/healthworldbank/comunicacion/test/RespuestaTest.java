package es.uned.master.java.healthworldbank.comunicacion.test;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.*;

import es.uned.master.java.healthworldbank.comunicacion.*;
import es.uned.master.java.healthworldbank.datos.*;

/**
 * Clase que realiza pruebas unitarias sobre la clase Respuesta
 * 
 * @author grupo alef (José Torrecilla) 
 * @date 8/4/2012 
 */
public class RespuestaTest {

	
	/**
	 * Método que genera una respuesta ficticia.
	 * La respuesta ficticia contiene 10 registros de un total de 57, empezando en el 25.
	 * De esta forma, nos encontramos en la página 4 de un total de 7.
	 * 
	 * @return Una Respuesta ficticia
	 */
	private Respuesta respuestaEjemplo() {
		//Fabricamos una respuesta ficticia		
		List<Registro> datos = new ArrayList<Registro>(10);
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 1.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 2.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 3.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 4.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 5.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 6.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 7.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 8.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 9.0));
		datos.add(new Estadistica("codigoIndicador", "codigoPais", 2012, 10.0));
		
		return new Respuesta(TipoPeticion.ESTADISTICAS, 10, 25, 57, datos);
	}

	
	/**
	 * Test que comprueba el correcto funcionamiento del método numeroRegistros() de la clase Respuesta
	 */
	@Test
	public void testNumeroRegistros() {
		
		Respuesta respuesta = respuestaEjemplo();
		assertEquals(10,respuesta.numeroRegistros());
	}

	/**
	 * Test que comprueba el correcto funcionamiento del método registroHasta() de la clase Respuesta
	 */
	@Test
	public void testRegistroHasta() {
		
		Respuesta respuesta = respuestaEjemplo();
		assertEquals(34,respuesta.registroHasta());
	}
	
	/**
	 * Test que comprueba el correcto funcionamiento del método pagina() de la clase Respuesta
	 */
	@Test
	public void testPagina() {
		
		Respuesta respuesta = respuestaEjemplo();
		assertEquals(4,respuesta.pagina());
	}

	/**
	 * Test que comprueba el correcto funcionamiento del método totalPaginas() de la clase Respuesta
	 */
	@Test
	public void testTotalPaginas() {
		
		Respuesta respuesta = respuestaEjemplo();
		assertEquals(7,respuesta.totalPaginas());
	}
	

}
