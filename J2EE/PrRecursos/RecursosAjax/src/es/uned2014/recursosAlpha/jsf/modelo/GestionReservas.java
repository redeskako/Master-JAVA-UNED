package es.uned2014.recursosAlpha.jsf.modelo;

import java.util.ArrayList;
import java.util.List;

import es.uned2014.recursosAlpha.reserva.Reserva;
import es.uned2014.recursosAlpha.usuario.Usuario;


public class GestionReservas {
	public List<Usuario> getUsuarios(){
		Usuario u = new Usuario();
		ArrayList<Usuario> usuarios = u.usuariosListado(0);
		
		return usuarios;
		
	}

	public List<Reserva> getReservasByUsuario(int idUsuario){

		Usuario u = new Usuario();
		u.setIdUsuario(idUsuario);
		String nombre = u.getNombreUsuario(u.getIdUsuario());	

		Reserva r = new Reserva();
		ArrayList<Reserva> reservas = r.misPeticiones(nombre, 0);
		ArrayList<Reserva> reservas2 = r.misReservas(nombre, 0);
		
		reservas.addAll(reservas2);
		
		return reservas;
		
	}
	
}
