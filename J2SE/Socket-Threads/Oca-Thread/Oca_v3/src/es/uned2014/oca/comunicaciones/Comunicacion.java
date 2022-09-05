package es.uned2014.oca.comunicaciones;

import java.io.*;

/**
 * Se define una clase Comunicacion que representa los mensajes que se van 
 * a intercambiar a través de las conexiones Socket.
 * Sus variables son un String con el contenido del mensaje y un TipoComunicacion, 
 * que ayudará a definir las acciones a tomar
 * cuando re reciba una Comunicacion.
 * Esta clase implementa Serializable porque va a ser enviada por las conexiones Socket.
 * 
 * @author	Alef UNED 2014
 * @version	Oca 3.0
 * @fecha 	Mayo 2014
 */
public class Comunicacion implements Serializable {
	// Variable tipo String que representa el contenido del mensaje
	private String mensaje;
	// Variable tipo TipoComunicacion (enum)
	private TipoComunicacion tipo;
    
    /**
	 * Método constructor: se asigna mensaje y tipo a la Comunicacion.
	 * @param String con el contenido del mensaje
	 * @param TipoComunicacion con una constante del tipo de comunicación
	 * @return null
	 * @throws null
	 */
	public Comunicacion(String s, TipoComunicacion t){
		this.mensaje = s;
		this.tipo = t;
	}

	/**
	 * Método para obtener el contenido de la variable mensaje.
	 * @param null
	 * @return Devuelve el String mensaje
	 * @throws null
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Método para obtener el contenido de la variable tipo.
	 * @param null
	 * @return Devuelve el tipo
	 * @throws null
	 */
	public TipoComunicacion getTipo() {
		return tipo;
	}
	
}
