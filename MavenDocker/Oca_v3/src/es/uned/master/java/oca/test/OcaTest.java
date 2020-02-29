package es.uned.master.java.oca.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uned.master.java.oca.drivers.*;
import es.uned.master.java.oca.excepciones.*;
import es.uned.master.java.oca.jugador.Jugador;
import es.uned.master.java.oca.partida.*;
import es.uned.master.java.oca.servidor.*;
import es.uned.master.java.oca.tablero.Casilla;
import es.uned.master.java.oca.tablero.Tablero;
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