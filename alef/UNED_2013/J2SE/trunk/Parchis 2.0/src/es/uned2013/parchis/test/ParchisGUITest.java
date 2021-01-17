package es.uned2013.parchis.test;

import java.rmi.RemoteException;
import java.util.Locale;

import javax.swing.ImageIcon;

import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.ParchisClienteVista;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisUIMode;
import static org.junit.Assert.*;

import org.junit.Test;

	//metodo main para prueba
		
		
		

		public class ParchisGUITest {
			
			public static void main(String[] args) throws RemoteException {
				Locale loc = new Locale("es");
				Parchis parchis = new Parchis();//inicia el juego de parchis
				
				parchis.setUI(ParchisUIMode.GUI, loc);
				try {
					parchis.inicioJuego(4); //con 4 jugadores
				}
				catch (Exception e) {
					System.err.println("ERROR:" + e.getMessage());
					e.printStackTrace();
				}
				parchis.getFichasJuego();//ArrayList(<Fichas>) que contiene las fichas en juego
				Tablero tablero = new Tablero(parchis.getFichasJuego()); //iniciamos el tablero
				
				//ParchisServer parchis = new Parchis();
			}
			

			@Test
			public void testSolicitarEntero() {
				fail("Not yet implemented");
			}

			@Test
			public void testSolicitarCadena() {
				fail("Not yet implemented");
			}

			@Test
			public void testMostrarTirada() {
				fail("Not yet implemented");
			}

			@Test
			public void testMoverFicha() {
				fail("Not yet implemented");
			}

			@Test
			public void testComerFicha() {
				fail("Not yet implemented");
			}

			@Test
			public void testMouseClicked() {
				fail("Not yet implemented");
			}

			@Test
			/**
			 * Comprueba las seis imagenes del dado
			 */
			public static void testCambiaImagenDadoA() throws RemoteException {
				Thread t = Thread.currentThread();//crea una variable con el hilo principal
				
				System.out.println("Current thread: " + t);
				//Crea la interfaz grafica tablero
				Locale loc = new Locale("es");
				Parchis parchis = new Parchis();
				ParchisGUI controlador = new ParchisGUI(loc, parchis, -1);
				ParchisClienteVista vista = new ParchisClienteVista(loc, controlador, -1);
				
				try{
						for (int i = 0; i<6; i++){
								ImageIcon imagenDadoEsperado = new ImageIcon(ParchisClienteVista.rutaImg + 
										ParchisClienteVista.imgDados[i]); 
								vista.getDado().setIcon(imagenDadoEsperado);
								//vista.cambiaImagenDadoA(i);
								System.out.println(i);
								Thread.sleep(1000); //pone el hilo en espera durante 1000 ms
							}
						
				}catch (InterruptedException e){
					System.out.println("Hilo principal interrumpido");
				}
				
			}


}
