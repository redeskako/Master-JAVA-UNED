package es.uned2013.parchis.parchis0_0;

import java.util.Locale;

import es.uned2013.parchis.ui.ParchisUIMode;

public class Driver {

	public static void main(String[] args) {
		Parchis parchis = new Parchis();
		int numJugadores = 4; //Por defecto 4 jugadores.
		/*
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String texto = br.readLine();
			numJugadores = Integer.parseInt(texto);
		}catch(NumberFormatException c){
			System.out.println("No es un número válido.");
			//System.exit(0);
			//numJugadores = 0; //Si no es un número válido se pasarían 4 jugadores, de esta manera se vuelve a preguntar al entrar en la clase Parchis
		}catch(Exception e){
			System.out.println("Error en IO.");
		}*/
		System.out.println("" + numJugadores);
		parchis.numJugadores(numJugadores);
		parchis.jugar();
		System.out.println("Adios");
	}

}

