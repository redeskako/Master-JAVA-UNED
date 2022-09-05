package es.uned2014.oca.cliente;

import java.awt.event.*;

import es.uned2014.oca.comunicaciones.*;
import es.uned2014.oca.partida.Dado;

/**
 * Se define una clase de gesti�n de eventos asociados a la clase ClienteGui.
 * Implementa ActionListener.
 * 
 * @author Alef UNED 2014
 * @version Oca 3.0
 * @fecha Mayo 2014
 */

public class GestionEventos implements ActionListener {

	// Interfaz gr�fica del Cliente donde se generan los eventos
	private ClienteGui gui;
	// Cliente asociado a los eventos
	private Cliente cliente;
	
	/**
	 * M�todo constructor: recibe como par�metro la interfaz gr�fica  donde 
	 * se generan los eventos.
	 * @param ClienteGui, interfaz gr�fica de cliente
	 * @return null
	 * @throws null
	 */
	public GestionEventos(ClienteGui gui) {
		this.gui = gui;
	}

	/**
	 * Para definir la acci�n a realizar:
	 * 1. Se accede al objeto origen del evento por medio del m�todo getSource.
	 * 2. Se definen acciones en funci�n del origen del evento
	 * @param ClienteGui, interfaz gr�fica de cliente
	 * @return null
	 * @throws null
	 */
	public void actionPerformed(ActionEvent e) {

		// 1. Se accede al objeto origen del evento por medio del m�todo getSource.
		Object o = e.getSource();
		
		String mensaje = new String();

		// 2. Se definen acciones en funci�n del origen del evento
		// 2.1 Si se presiona Boton Comenzar
		if (o == gui.getComenzar()) {
			
			// Se crea nuevo cliente
			cliente = new Cliente(gui);
			
			// Se actualiza el estado de los botones de la GUI
			gui.actualizarEstadoComenzar(false);
			gui.jlMensaje.setText("");
			
			// Se inicia el cliente
			cliente.start();

		}

		// 2.2 Si se presiona Boton TirarDado
		if (o == gui.getTirarDado()) {
			
			// Se almacena en la variable "tirada" el valor obtenido de ejecutar el m�todo
			// tirarDado() de Dado.
			String tirada = String.valueOf(Dado.tirarDado());
			
			// Se actualiza el estado de los botones de la GUI
			gui.jlDado.setText("");
			gui.jlDado.setText("Has sacado un : " + tirada);
			gui.actualizarEstadoLanzamiento(false);

			// Se envia el resultado de la tirada hacia servidor
			mensaje = tirada;
			cliente.enviarMensaje(new Comunicacion(mensaje,
					TipoComunicacion.TIRADA_DADO));
		}

		// 2.3 Si se PResiona Boton Terminar
		if (o == gui.getTerminar()) {
			
			// Se informa en la GUI de que se ha solicitado la desconexi�n del cliente
			gui.escribirMensaje("Se ha solicitado la desconexi�n de este Cliente:");
			
			// Se actualiza el estado de los botones de la GUI: reiniciar
			gui.resetBotones();

			// Se desconecta el cliente
			cliente.desconectar();
		}
	}
	
	/**
	 * Se elimina de manera definitiva un cliente una vez se ha desconectado
	 * @param null
	 * @return null
	 * @throws null
	 */
	public void eliminarCliente() {
		this.cliente = null;
	}

}
