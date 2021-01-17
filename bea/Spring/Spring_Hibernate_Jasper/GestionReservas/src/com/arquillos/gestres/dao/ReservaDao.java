/*
 * Reserva
 * Objeto de acceso a datos
 * Definición de interfaz
 */

package com.arquillos.gestres.dao;

import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.data.Usuario;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public interface ReservaDao {
	@Transactional
	void salvarReserva(Reserva r);

	List<Reserva> analizarConflictos(
		Reserva reserva,
		Recurso recurso, 
		Date inicio, 
		Date fin );

	List<Reserva> getReservas();

	List<Reserva> getReservas(Date incio, Date fin);

	Reserva getReserva(int id);

	List<Reserva> getReservasAprobables (
		Usuario usuario, 
		int primera, int maxRegistros, 
    String campoOrdenacion, boolean isAscendente);

	int totalReservasAprobables(Usuario usuario);

	List<Reserva> getReservasPendientes (
		Usuario usuario, 
		int primera, int maxRegistros, 
    String campoOrdenacion, boolean ascendente);

	int totalReservasPendientes(Usuario usuario);
	  	
	List<Reserva> getReservas (
		Usuario usuario, 
		int primera, int maxRegistros, 
    String campoOrdenacion, boolean ascendente);

	int totalReservas(Usuario usuario);

	@Transactional
	void borrarReserva(Reserva reserva);	
}