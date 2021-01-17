package es.uned2013.parchis.rmi.server;

import java.net.SocketException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import es.uned2013.parchis.Ficha;
import es.uned2013.parchis.Jugador;
import es.uned2013.parchis.Tablero;
import es.uned2013.parchis.ui.ParchisUI;

public interface ParchisServer extends Remote {

	public void registrarJugador(ParchisUI ui) throws RemoteException;
	public void abandonarPartida() throws RemoteException;
	public void inicioJuego(int jugs) throws RemoteException, InterruptedException,
		ClassNotFoundException, SocketException;
	public ArrayList<Jugador> getJugadores() throws RemoteException;
	public int getJugadorTurno() throws RemoteException;
	public Tablero getTablero() throws RemoteException;
	public Ficha getFicha(int num) throws RemoteException;
	public BlockingQueue<ParchisUI> getUIList() throws RemoteException;
	public void setEnJuego(Boolean enJuego) throws RemoteException;
	public Boolean isEnJuego() throws RemoteException;
}
