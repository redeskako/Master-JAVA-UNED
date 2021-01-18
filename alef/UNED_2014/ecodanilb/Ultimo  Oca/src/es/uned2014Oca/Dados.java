/*
 * Metodo A Traves del cual obtenemos numeros aleatorios de 12 (Dos Dados) y sumamos uno para evitar
 * la aparicion del cero y la falta del 12
 * @autor: Daniel Lozano
 * @Fecha: 05/04/14
 * @Version: 3
 * <docente> ESTUPENDO!</docente>
 */



package es.uned2014Oca;
import java.util.Random;

public class Dados {

	private static Random dosdados=new Random();
	private static int sumadados=0;

	/* <docente> Falta explicitar algún comentario sobre este método constructor*/
	public static int dados()
	
	{
		
		sumadados=dosdados.nextInt(12)+1; //<docente> No es mejor considerar el valor de un dado como Constante? y si quiero usar más datdos? usas dos dados?
		return sumadados;
		
	}
}
