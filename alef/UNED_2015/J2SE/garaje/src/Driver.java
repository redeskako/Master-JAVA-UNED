import java.util.*;

import es.uned.master.java.garaje.*;

import es.uned.master.java.garaje.configuracion.GestorRecursos;
import es.uned.master.java.garaje.ErrorGaraje;

public class Driver
{
	public static void main(String[] arg)
	{
		/*
		 * Asignamos al tipo leyenda true porque queremos que nos pinte la
		 * cadena Garaje vacío que es lo primero que vamos a mostrar.
		 */
		boolean leyenda = true;
		
		// Recogen los parámetros de configuración obtenidos del fichero de propiedades.
		int nNumPlantas, nNumPlazas;
		String sIdioma;
		GestorRecursos recursos = new GestorRecursos();
		try
		{
			/*
			 * Leemos los datos del fichero de propiedades.
			 * 
			 */
									
			sIdioma = recursos.getPropiedad("idioma");
			if (sIdioma.equalsIgnoreCase("es") || sIdioma.equalsIgnoreCase("en"))
				recursos.setIdioma(sIdioma);
			else
				throw new ErrorGaraje(recursos.muestraMensaje("err4"));

			if ((nNumPlantas = Integer.parseInt(recursos.getPropiedad("num_plantas"))) < 1)
				throw new ErrorGaraje(recursos.muestraMensaje("err1"));

			if ((nNumPlazas = Integer.parseInt(recursos.getPropiedad("num_plazas"))) < 1)
				throw new ErrorGaraje(recursos.muestraMensaje("err2"));
			
			// Mostramos los parámetros obtenidos.
			System.out.println(recursos.muestraMensaje("msg1"));
			System.out.println(recursos.muestraMensaje("msg2"));
			System.out.println(recursos.muestraMensaje("msg3") + nNumPlantas);
			System.out.println(recursos.muestraMensaje("msg4") + nNumPlazas);
			System.out.println(recursos.muestraMensaje("msg5") + sIdioma);
			System.out.println();

			/*
			 * Inicializa un garaje de asignando colores de forma aleatoria.
			 * Cada planta debe de tener todos los colores.
			 */
			Garaje g = new Garaje(nNumPlantas, nNumPlazas, recursos);
	
			/*
			 * Mostramos la asignacion de colores a las plazas con el garaje vacío.
			 */ 
			g.pintarGaraje(leyenda);
	
			/*
			 * Si no caben más, devuelve el valor de los coches que no ha podido
			 * aparcar. Si devuelve un valor negativo (-1) es que el random ha sido
			 * 0, ningún coche a aparcar. Calculo el número de vehículos a entrar de
			 * forma aleatoria.
			 * 
			 * Establecemos el número aleatorio máximo en un 30% mayor que el número
			 * total de plazas.
			 */
			Random random = new Random();
			int aleatorio = random.nextInt((int)Math.round(nNumPlantas * nNumPlazas * 1.3));
			
			int sobra = g.entradaCoches(aleatorio);
			leyenda = false;
			
			// Si hay coches para aparcar.
			if (aleatorio > 0)
			{
				// Entra más de un coche.
				System.out.println(recursos.muestraMensaje("msg6"));
				g.pintarGaraje(leyenda);
				System.out.println(recursos.muestraMensaje("msg7") + " " + aleatorio
						+ " " + recursos.muestraMensaje("msg8"));
				
				// Han sobrado coches.
				if (sobra != 0)
					System.out.println(recursos.muestraMensaje("msg9") + " " +
				                         sobra + " " + recursos.muestraMensaje("msg8"));
			}
			else
				System.out.println(recursos.muestraMensaje("msg10"));
		}
		catch(NumberFormatException e)
		{
			throw new ErrorGaraje(recursos.muestraMensaje("err3"));
		}		 
	}
}
