package es.uned2014.recursosAlpha.jsf.managed;

import java.util.*;

import javax.faces.bean.*;

import es.uned2014.recursosAlpha.jsf.modelo.GestionReservas;
import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.Usuario;

@ManagedBean (name="reservasUsuariosBean")
@RequestScoped
public class ReservasUsuariosBean {

	private int idUsuario;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public List<Usuario> getUsuarios(){
		GestionReservas gestion = new GestionReservas();
		List<Usuario> usuarios = gestion.getUsuarios();

		return usuarios;
	}
	
	public List<Reserva> getReservas(){
		GestionReservas gestion = new GestionReservas();
		return gestion.getReservasByUsuario(idUsuario);
	}
}
