package es.uned2014.oca.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uned2014.oca.drivers.*;
import es.uned2014.oca.jugador.Jugador;
import es.uned2014.oca.servidor.*;
import es.uned2014.oca.tablero.Casilla;
import es.uned2014.oca.tablero.Tablero;
import es.uned2014.oca.partida.*;
import es.uned2014.oca.excepciones.*;
public class OcaTest {

 @BeforeClass
 public static void setUpBeforeClass() throws Exception {
 }

 @AfterClass
 public static void tearDownAfterClass() throws Exception {
 }
  

  
 @Before
 public void setUp() throws Exception {
 	 
 }

 @After
 public void tearDown() throws Exception {
 }

 @Test
 public void test() {
 	fail("Not yet implemented");
 }
  
  
 @Test(expected=ClaseError.class)
 public void testAnalizarTurno(){
 	int entero = 28;
  DriverServidor ds = new DriverServidor();
 	Servidor miServidor = new Servidor(ds, 2);
 	Oca miOca = new Oca(2,miServidor);
 	miOca.analizarTurno(entero);
 }
}