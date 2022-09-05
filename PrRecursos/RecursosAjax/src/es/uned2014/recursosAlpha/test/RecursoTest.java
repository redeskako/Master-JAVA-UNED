/**
 * 
 */
package es.uned2014.recursosAlpha.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import es.uned2014.recursosAlpha.recurso.Recurso;

/**
 * Clase RecursoTest para realizar pruebas unitarias sobre Recurso
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */
public class RecursoTest {

	/**
	 * Tal como está la base de datos actual, podemos probar que en la página 100 del 
	 * listado de recursos no hay nada, y en la 1 hay recursos.
	 * Si ponemos 0 a la página, debe devolver todos los recursos.
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#recursoListado(int)}.
	 */
	@Test
	public void testRecursoListado() {
		Recurso r = new Recurso();		
		ArrayList<Recurso> a = new ArrayList<Recurso>();
		
		a = r.recursoListado(100);
		assertTrue(a.size() == 0);
		
		a = r.recursoListado(1);
		assertTrue(a.size() != 0);
		
		a = r.recursoListado(0);
		int i = r.totalFilasRecurso();
		assertTrue(a.size() == i);
	}

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#totalFilasRecurso()}.
	 */
/*	@Test
	public void testTotalFilasRecurso() {
	}*/

	/**
	 * Se crea un nuevo recurso y se comprueba que se crea sin problema
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#nuevo(java.lang.String)}.
	 */
	@Test
	public void testNuevo() {
		Recurso r = new Recurso();	
		int id = r.nuevo("Alfombre voladora");
		
		assertTrue (id != 0);
		
		r.eliminar(id); // Se elimina el recurso de prueba
	}

	/**
	 * Se crea un recurso de prueba, y se comprueba que se edita sin problema.
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#editarRecurso(int, java.lang.String)}.
	 */
	@Test
	public void testEditarRecurso() {
		Recurso r = new Recurso();	
		int id = r.nuevo("Alfombre voladora");
		
		int editado = r.editarRecurso(id, "Lámpara maravillosa");
		
		assertTrue (editado != 0);
		
		r.eliminar(id); // Se elimina el recurso de prueba
		
	}

	/**
	 * Se comrpueba que una vez eliminado un recurso, no se encuentra si se realiza una búsqueda
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#eliminar(int)}.
	 */
	@Test
	public void testEliminar() {
		Recurso r = new Recurso();	
		int id = r.nuevo("Alfombre voladora");
		
		r.eliminar(id); // se elimina el recurso
		
		String sql = " WHERE IdRecurso=" + id;
		ArrayList<Recurso> a = r.recursosBuscar(0, sql); // se busca si existe
		
		assertTrue(a.size() == 0);	
	}

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#sqlBuscadorRecursos(java.lang.String)}.
	 */
/*	@Test
	public void testSqlBuscadorRecursos() {
	}*/

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#recursosBuscar(int, java.lang.String)}.
	 */
/*	@Test
	public void testRecursosBuscar() {
	}*/

	/**
	 * Test method for {@link es.uned2014.recursosAlpha.recurso.Recurso#numeroFilasBuscador(java.lang.String)}.
	 */
/*	@Test
	public void testNumeroFilasBuscador() {
	}*/

}
