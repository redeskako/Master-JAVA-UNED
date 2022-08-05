/*
 * Ejecucion del Programa a traves del Cual se <intenta obtener el numero de jugadores para el comienzo del Juego de La Oca
 * Encapsulamiento de Errores y en caso de que se produzcan que el nº de jugadores por defecto sea el 4
 * @autor: Daniel Lozano
 * @Fecha: 05/04/14
 * @Version: 3
 * 
 */

import java.util.*;
import java.io.*;
import es.uned2014Oca.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Oca oca=new Oca();
		
		int numJugadores=4;// por Defecto 4 Jugadores
		
		try
		{
			System.out.println("Introduzca Numero Jugadores (por defecto 4): ");// no hay limite a excepcion del marcado por una variable integer
			InputStreamReader isr=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(isr);
			String texto=br.readLine();
			numJugadores=Integer.parseInt(texto);
			
			if (numJugadores<=0)
			numJugadores=4;

		}
		
		catch(ClaseError err)
		{
		
			System.out.println("No es un numero Valido");
		}
		catch(Exception err)
		{
			System.out.println("Error en IO, Se considerara 4 Jugadores");
		}
		


		oca.numJugadores(numJugadores);
	
		oca.jugar();
		System.out.println("Fin");
		
	}

}
