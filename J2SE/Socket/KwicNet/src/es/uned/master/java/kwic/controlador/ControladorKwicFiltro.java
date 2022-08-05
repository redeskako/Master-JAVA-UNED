package es.uned.master.java.kwic.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControladorKwicFiltro implements KeyListener {

	//Autogeneramos los metodos del Key listener e implementamos el que nos interesa
	
	@Override
	public void keyPressed(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ev) {
		// TODO Auto-generated method stub
		char caracter = ev.getKeyChar();

		// Verificar si la tecla pulsada no es un digito
		if (((caracter < '0') || (caracter > '9'))
				&& (!Character.isLetter(caracter)) && (caracter != '\b') //BACK_SPACE
				&& (caracter != ' ')) {
			ev.consume(); // ignorar el evento de teclado
		}

	}

}
