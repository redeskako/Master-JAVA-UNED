package es.uned.master.java.oca.partida;

import java.util.Random;

public class Dado {
	public static int tirarDado(){
		// Se crea una instancia de Random (java.util)
		Random dado = new Random();
		// Se solicita un número entero aleatorio entre 1 y 6
		return dado.nextInt(6)+1;
	}
}
