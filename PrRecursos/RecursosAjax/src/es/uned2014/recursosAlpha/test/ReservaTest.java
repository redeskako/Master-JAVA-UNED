package es.uned2014.recursosAlpha.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

import es.uned2014.recursosAlpha.reserva.*;


/**
 * Clase ReservaTest para realizar pruebas unitarias sobre Reserva
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	Junio-Julio 2014
 */

public class ReservaTest {

	/**
	 * Se comprueba los peticiones asignado a un usuario y retornan un array con los reservas.
	 * Ademas se pasa una variable de la pagina que esta vieno el usuario
	 */
	@Test
	public void testMisPeticiones(){
		Reserva r = new Reserva();
		r.misPeticiones("loliva", 1);
	}
	
	/**
	 * Se comprueba los reservas asignado a un usuario y retornan un array con los reservas.
	 * Ademas se pasa una variable de la pagina que esta vieno el usuario
	 */
	@Test
	public void testMisReservas(){
		Reserva r = new Reserva();
		r.misPeticiones("cmorales", 2);		
	}
	
/*	@Test
	public void testTotalFilasReserva(){
		
	}*/
	
	/**
	 * Se comprueba si se puede obtener un listado de todo las reservas 
	 * añadiendo la pagina y el id de estado
	 */
	@Test
	public void testReservasListado(){
		Reserva r = new Reserva();
		ArrayList<Reserva> a = new ArrayList<Reserva>();		
		
		a = r.reservasListado(100,3);
		assertTrue(a.size() == 0);
		
		a = r.reservasListado(1,2);
		assertTrue(a.size() != 0);
		
	}
	
/*	@Test
	public void testReservasEstado(){
		
	}*/
	
	/**
	 * Se comprueba que si se puede crear una reserva nueva con estado pendiente.
	 */	
	@Test
	public void testNuevo(){
		Reserva r = new Reserva();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formato.parse("2014-11-01 10:00:00");
			fin = formato.parse("2014-11-03 14:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(r.nuevo(1, 1, inicio, fin, 1) != 0);
	}
	
/*	@Test
	public void testComprobarReserva(){
		
	}*/
	
	/**
	 * Se comprueba si se puede editar una reserva
	 */
	@Test
	public void testEditarReserva(){
		Reserva r = new Reserva();
	
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formato.parse("2014-10-01 09:30:00");
			fin = formato.parse("2014-10-03 15:45:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(r.editarReserva(1, 1, 1, inicio, fin, 3) != 0);
	}

/*	@Test
	public void testCambiarEstado(){

	}*/
	
	/**
	 * Se comprueba si se puede confirmar una reserva
	 */
	@Test
	public void testConfirmar(){
		Reserva r = new Reserva();

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formato.parse("2014-12-05 08:30:00");
			fin = formato.parse("2014-12-05 08:31:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		assertTrue(r.confirmar(3, 3, inicio, fin, 4) != 0);
	}

	/**
	 * Se comprueba si se puede eliminar una reserva
	 */
	@Test
	public void testElimiar(){
		Reserva r = new Reserva();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date inicio = null;
		Date fin = null;
		
		try {
			inicio = formato.parse("2014-11-01 10:00:00");
			fin = formato.parse("2014-11-03 14:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int idNuevo = r.nuevo(1, 1, inicio, fin, 1);

		assertTrue(r.eliminar(idNuevo));
	}	
	
/*	@Test
	public void testSqlBuscadorReservas(){
		
	}
	
	@Test
	public void testReservasBuscar(){
		
	}
	
	@Test
	public void testNumeroFilasBuscador(){
		
	}*/
	
}
