package es.uned2013.parchis.parchis0_1;


import java.util.*;

import es.uned2013.parchis.ui.ParchisUIMode;


public class Driver {

	public static void main(String[] args) {
		
		Parchis parchis = new Parchis();
		
		/* El segundo parámetro determina el idioma en el que el
		 * programa se comunicará con el usuario. En modo "real"
		 * el idioma se seleccionaría en función del contexto del
		 * usuario. De esta manera, como entendemos que en todas
		 * las máquinas en las que se ejecutará tendrá como idioma
		 * predeterminado español, basta con sustituir el parámetro
		 * de Locale 'es' por 'en', para que la comunicación con
		 * el usuario sea en inglés.
		 */
		parchis.setUI(ParchisUIMode.CONSOLE, new Locale("es"));
		parchis.inicioJuego(parchis.numJugadores());
	}

}
