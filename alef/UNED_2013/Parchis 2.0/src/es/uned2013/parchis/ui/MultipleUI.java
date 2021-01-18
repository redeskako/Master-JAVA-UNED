package es.uned2013.parchis.ui;

import java.rmi.RemoteException;
import java.util.concurrent.BlockingQueue;

import es.uned2013.parchis.Casilla;
import es.uned2013.parchis.Colores;
import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.rmi.server.ParchisServer;

/**
 * Clase que se utiliza en el modo de juego ONLINE y se encarga de llamar
 * desde cualquier método definido por la interfaz ParchisUI al mismo método
 * de cada interfaz de usuario conectada al servidor del parchís, recorriendo
 * el listado de UIs almacenado en el servidor y realizando todas las
 * llamadas correspondientes por cada jugador conectado.
 * @author alef
 */
public class MultipleUI implements ParchisUI {

	ParchisServer parchis = null;
	
	public MultipleUI(ParchisServer parchis) throws RemoteException {
		this.parchis = parchis;
	}
	
	@Override
	public void mostrarMensaje(String mensaje) throws RemoteException {	
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		int jugadorTurno = parchis.getJugadorTurno();
		
		for (int i=0; i<uiList.size(); i++) {
			if (i != jugadorTurno)
			((ParchisUI)uiList.toArray()[i]).mostrarMensaje(mensaje);
		}
	}

	@Override
	public void mostrarMensajeString(String mensaje, String cadena)
			throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		int jugadorTurno = parchis.getJugadorTurno();
		
		for (int i=0; i<uiList.size(); i++) {
			if (i != jugadorTurno)
				((ParchisUI)uiList.toArray()[i]).
				mostrarMensajeString(mensaje, cadena);
		}
	}

	@Override
	public void mostrarMensajeInteger(String mensaje, int numero)
			throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		int jugadorTurno = parchis.getJugadorTurno();
		
		for (int i=0; i<uiList.size(); i++) {
			if (i != jugadorTurno)
				((ParchisUI)uiList.toArray()[i]).
				mostrarMensajeInteger(mensaje, numero);
		}
	}

	@Override
	public int solicitarEntero(String mensaje) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String solicitarCadena(String mensaje) throws RemoteException {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void mostrarTirada(String nombre, int tirada) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.mostrarTirada(nombre, tirada);
	}

	@Override
	public void mostrarTablero(Tablero tablero) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.mostrarTablero(tablero);
	}

	@Override
	public void mostrarFicha(Ficha ficha) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.mostrarFicha(ficha);
	}

	@Override
	public void moverFicha(Casilla destino, Ficha ficha) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.moverFicha(destino, ficha);
		
	}

	@Override
	public void comerFicha(Ficha ficha, Casilla destino) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.comerFicha(ficha, destino);
		
	}
	
	@Override
	public void cambiarTurno(Colores color) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.cambiarTurno(color);
		
	}
	
	@Override
	public void inicioJuegoUI() throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.inicioJuegoUI();
		
	}
	
	@Override
	public ParchisUIMode getUIMode() throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		ParchisUIMode uiMode = null;
		
		if (parchis.getUIList().size() > 0)
			uiMode = ((ParchisUI)uiList.toArray()[0]).getUIMode();
		
		return uiMode;
	}

	@Override
	public void abandonarPartida(Boolean esServidor, int index) throws RemoteException {
		BlockingQueue<ParchisUI> uiList = parchis.getUIList();
		
		for (ParchisUI ui: uiList)
			ui.abandonarPartida(esServidor, index);
	}
}
