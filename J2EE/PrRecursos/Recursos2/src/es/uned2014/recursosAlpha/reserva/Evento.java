package es.uned2014.recursosAlpha.reserva;

import java.util.Date;

import com.dhtmlx.planner.DHXEvent;

/***
 * Overwriting the DHXEvent class.
 * Hecho para ser capaz de adaptar un objeto DHXEvent al objeto reserva
 * Se añaden propiedades relevantes como el estado de la reserva 
 * 
 * @author	Alpha UNED 2014
 * @version	Recursos 1.0
 * @fecha 	November 2016
 *
 */
public class Evento extends DHXEvent {
	
	public String estado;
	public String usuario;
	
	public Evento() {
	}

	public Evento(Integer id, String start_date, String end_date, String text) {
		super(id, start_date, end_date, text);
	}

	public Evento(String id, String start_date, String end_date, String text) {
		super(id, start_date, end_date, text);
	}

	public Evento(Integer id, Date start_date, Date end_date, String text) {
		super(id, start_date, end_date, text);
	}

	public Evento(String id, Date start_date, Date end_date, String text) {
		super(id, start_date, end_date, text);
	}
	
	/*
	 * Obtener estado del evento
	 */
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String e) {
        this.estado = e;
    }
    
    /*
	 * Obtener el nombre de usuario
	 */
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String u) {
        this.usuario = u;
    }	
}
