package es.uned2013.parchis.test;

import static org.junit.Assert.*;

import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.Locale;

import org.junit.Test;

import es.uned2013.parchis.Modo;
import es.uned2013.parchis.Parchis;
import es.uned2013.parchis.ParchisClienteVista;
//import es.uned2013.parchis.ParchisClientevista[1];
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.ui.ParchisGUI;
import es.uned2013.parchis.ui.ParchisUIMode;

public class ParchisClienteVistaTest {
	
	public static void main(String args[]) throws RemoteException, ClassNotFoundException, InterruptedException, SocketException{
		Locale loc = new Locale("es");
		ParchisUIMode ui = null;
		ParchisClienteVista[] vista= new ParchisClienteVista[3];
		
		
		
		
		Parchis parchis = new Parchis();//inicia el juego de parchis
		
		parchis.setUI(ParchisUIMode.GUI, loc);
		//cambio -1 por numJugadores
		//ParchisClientevista[1] vista[1]1 = new ParchisClientevista[1](loc, parchisGUI, 0);
		for (int i=0; i<4; i++){
			ParchisGUI parchisGUI = new ParchisGUI(loc, parchis, 0 );
			vista[i] = new ParchisClienteVista(loc, parchisGUI, i);
			
			vista[i].inicializarFichas(4);
			Parchis.setModo(Modo.PRUEBA);// Modo Test
			parchis.setUI(ParchisUIMode.GUI, new Locale("es"));// Interfaz consola//gui
			Parchis.jugadaLimitePrueba = 132;// Jugadas totales de la partida Test
			parchis.inicioJuego(4);// Se prueba con todos los jugadores
		}
		//lanzamos el tablero grafico
		//ParchisClientevista[1] vista[1]2 = new ParchisClientevista[1](loc, parchisGUI, 2);
		//ParchisClientevista[1] vista[1]3 = new ParchisClientevista[1](loc, parchisGUI, 3);
		//ParchisClientevista[1] vista[1]4 = new ParchisClientevista[1](loc, parchisGUI, 4);
		
		/*
		Parchis.setModo(Modo.PRUEBA);// Modo Test
		parchis.setUI(ParchisUIMode.GUI, new Locale("es"));// Interfaz consola//gui
		Parchis.jugadaLimitePrueba = 132;// Jugadas totales de la partida Test
		parchis.inicioJuego(4);// Se prueba con todos los jugadores
		*/
		
		
		parchis.getFichasJuego();//ArrayList(<Fichas>) que contiene las fichas en juego
		System.out.println("El numero de fichas es: " +
			vista[1].getLblFichas().length);//numero de fichas en tablero grafico
				
				//coordenadas de cada una de las 16 fichas en el dibujo inicial
				for(int i = 0; i < 16; i++){
					System.out.println("La coordenada X de la ficha " + i + 
							" es: " + vista[1].getLblFichas()[i].getBounds().getX());
					System.out.println("La coordenada Y de la ficha " + i +
							" es: " + vista[1].getLblFichas()[i].getBounds().getY());
			}

		
		Tablero tablero = new Tablero(parchis.getFichasJuego()); //iniciamos el tablero
		//tablero.getJuego();
		
		//intento mostrar un mensaje por tablero grafico
		//parchisGUI.mostrarMensaje("hola");
		//vista[1].mostrarMensajes("hola2");//este funciona
		
		
		//comprobamos las coordenadas del tablero para las fichas, con 1 ficha,
		//con dos fichas o con 1 a 4 fichas para el caso de 
		//casilla casa y meta
		for(int i = 1; i <= 68; i++){
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene la posicion " + tablero.getCasilla(i).getNumero());
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene un numero de fichas igual a " + tablero.getCasilla(i).getTieneFicha());
			
			System.out.println("Si solo hay una ficha");
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene como coordenada X " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(0).getX());
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene como coordenada Y " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(0).getY());
			System.out.println("Si hay dos fichas -- La ficha 1");
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene como coordenada X " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(1).getX());
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene como coordenada Y " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(1).getY());
			System.out.println("Si hay dos fichas -- La ficha 2");
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() +
					" tiene como coordenada X " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(2).getX());
			System.out.println("La casilla " + tablero.getCasilla(i).getNumero() + 
					" tiene como coordenada Y " + vista[1].getPosicionCasilla(tablero.getCasilla(i).getNumero()).get(2).getY());
		
		}
		
		//contenido del metodo que permite escoger una coordenada distinta en funcion de si hay
		//una ficha o dos en la casilla
		int pos = 0;
		
		
		for(int i = 300; i <= 308; i++){
			
			if(tablero.getCasilla(i).getNumero()>0 & tablero.getCasilla(i).getNumero() <=68){
				if(tablero.getCasilla(i).getTieneFicha() == 1 )
					//pos1 = 1;
					System.out.println("Solo hay una ficha en la casilla " + 
					tablero.getCasilla(i).getNumero());
				
				if(tablero.getCasilla(i).getTieneFicha() == 2)
					//pos2 = 2;
					System.out.println("Hay dos fichas en la casilla " + 
					tablero.getCasilla(i).getNumero() );
			}else if (tablero.getCasilla(i).getNumero() == 100 ||
					tablero.getCasilla(i).getNumero() == 200 ||
					tablero.getCasilla(i).getNumero() == 300 ||
					tablero.getCasilla(i).getNumero() == 400 ||
					tablero.getCasilla(i).getNumero()== 108 ||
					tablero.getCasilla(i).getNumero()==208 ||
					tablero.getCasilla(i).getNumero()==308 ||
					tablero.getCasilla(i).getNumero()==408){
						if(tablero.getCasilla(i).getTieneFicha()==1)
							//pos3 = 3;
							System.out.println("Solo hay una ficha en la casilla " + 
							tablero.getCasilla(i).getNumero());
						if(tablero.getCasilla(i).getTieneFicha()==2)
							//pos4 = 4;
							System.out.println("Hay dos fichas en la casilla " + 
									tablero.getCasilla(i).getNumero());
						if(tablero.getCasilla(i).getTieneFicha()==3)
							//pos5 = 5;
							System.out.println("Hay tres fichas en la casilla " + 
									tablero.getCasilla(i).getNumero());
						if(tablero.getCasilla(i).getTieneFicha()==4)
							//pos6 = 6;
							System.out.println("Hay cuatro fichas en la casilla " + 
									tablero.getCasilla(i).getNumero());
			}
		}
		
		
		//numero de las casillas que hay en el tablero
		int[] casillasParchis = {1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,
								26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,
								51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,
								101,102,103,104,105,106,107,201,202,203,204,205,206,207,
				                301,302,303,304,305,306,307,401,402,403,404,405,406,407};
		
		//mover una casilla por todo el tablero
		
		Thread t = Thread.currentThread(); //hilo principal
		System.out.println("Current thread: " + t);
		
		
		
		try{
			
				for(int i=0; i<96; i++){
					System.out.println("La ficha " + (i+1) + " es " + casillasParchis[i]);
				
						System.out.println("hola");		
						vista[1].moverFicha(tablero.getCasilla(casillasParchis[i]), tablero.getCasilla(casillasParchis[i]).getFicha1());
						
						
						System.out.println(i);
						Thread.sleep(1000); //pone el hilo en espera durante 1000 ms
						
	
					}
					
				
					
				
			
	}catch (InterruptedException e){
		System.out.println("Hilo principal interrumpido");
	}
		//vista[1].moverFicha(tablero.getCasilla(casillasParchis[10]), tablero.getCasilla(casillasParchis[21]).getFicha1());
		//Thread.sleep(1000); //pone el hilo en espera durante 1000 ms
		//vista[1].comerFicha(tablero.getCasilla(casillasParchis[21]).getFicha1().getIdentificador());	
		
		
	}
	
	@Test
	public void testParchisClienteVista() {
		ParchisTest parchisTest = new ParchisTest();
		//parchis.
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetDado() {
		fail("Not yet implemented");
	}

	@Test
	public void testTirarDado() {
		fail("Not yet implemented");
	}

	@Test
	public void testCambiarTurno() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostrarMensajes() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoverFichaJLabelIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoverFichaJLabelIntIntBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetlblFichas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLblFichas() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLblFichas() {
		fail("Not yet implemented");
	}

	@Test
	public void testComerFicha() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFicha() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFicha() {
		fail("Not yet implemented");
	}

}
