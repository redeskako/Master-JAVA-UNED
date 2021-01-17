/*
 * Entidad Reserva
 * Objeto de acceso a datos
 * Implementación de interfaz
 */

package com.arquillos.gestres.dao.impl;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.data.EstadoReserva;
import com.arquillos.gestres.data.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class ReservaDaoImpl implements ReservaDao {	
	private EntityManager manager;

	public void salvarReserva(Reserva r) {
		if (r.isNueva())
			getEntityManager().persist(r);
		else
			getEntityManager().merge(r);
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> analizarConflictos (
		Reserva reserva,
		Recurso recurso,
		Date inicio,
		Date fin ) {

		Query query = 
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res " +
				" where " + (reserva.isNueva() ? "" : " res != :existente and ") +
				"       res.recurso = :recurso " +
				"   and (res.inicio < :fin and res.fin > :inicio) " +  
				"   and (res.autorizacion = :pendiente or res.autorizacion = :aprobada) " +
				" order by res.inicio asc"
        );

		if (!reserva.isNueva())
			query.setParameter("existente", reserva);
		query.setParameter("recurso", recurso);
		query.setParameter("inicio", inicio);
		query.setParameter("fin", fin);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);
		query.setParameter("aprobada", EstadoReserva.APROBADA);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		Query query =
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res "
		);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas(Date inicio, Date fin) {
		Query query =
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res " +
				" where (res.inicio < :fin and res.fin >= :inicio) " +
				"   and (res.autorizacion = :pendiente or res.autorizacion = :aprobada) " +
			  	" order by res.fin asc"
		);

		query.setParameter("inicio", inicio);
		query.setParameter("fin", fin);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);
		query.setParameter("aprobada", EstadoReserva.APROBADA);
 
		return query.getResultList();
	}

	public int totalReservas(Usuario usuario) {		
		Query query = 
			getEntityManager().createQuery(
				"select count(res) " + 
				"  from Reserva res " +
				( usuario.isAdmin() ? " " : 
				" where (res.solicitante = :usuario) " +
				"    or (res.autorizador = :usuario) " +
				"    or (res.autorizador is null and " +
				"       (res.recurso.autorizador =: usuario or " +
				"        res.solicitante.autorizador = :usuario))") 
		);

		if (!usuario.isAdmin()) query.setParameter("usuario", usuario);    
		return ((Long)query.getSingleResult()).intValue();
	}

	public int totalReservasAprobables(Usuario usuario) {		
		Query query = 
			getEntityManager().createQuery(
				"select count(res) " + 
				"  from Reserva res " +
				" where (res.fin > :ahora) " + 
				"   and (res.solicitante.autorizador = :usuario1 or " +
				"        res.recurso.autorizador = :usuario2) " +
				"   and (res.autorizacion = :pendiente)"
      );

		query.setParameter("ahora", new Date());
		query.setParameter("usuario1", usuario);
		query.setParameter("usuario2", usuario);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);

		return ((Long)query.getSingleResult()).intValue();
	}

	public int totalReservasPendientes(Usuario usuario) {		
		Query query = 
			getEntityManager().createQuery( 
				"select count(res) " + 
				"  from Reserva res " +
				" where (res.fin > :ahora) " +
				"   and (res.solicitante = :usuario) " +
				"   and (res.autorizacion = :pendiente) "
        );

		query.setParameter("ahora", new Date());
		query.setParameter("usuario", usuario);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);

		return ((Long)query.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas( 
		Usuario usuario, 
		int primerResultado, int maxResultados, 
		String campoOrdenacion, boolean ascendente ) {

		Query query = 
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res " +
				( usuario.isAdmin() ? " " : 
				" where (res.solicitante = :usuario) " +
				"    or (res.autorizador = :usuario) " +
				"    or (res.autorizador is null and " +
				"       (res.recurso.autorizador = :usuario or " +
				"        res.solicitante.autorizador = :usuario)) " ) +
				" order by res." + campoOrdenacion + (ascendente ? " ASC" : " DESC" )
        );

		query.setFirstResult(primerResultado);
		query.setMaxResults(maxResultados);
		if (!usuario.isAdmin())
			query.setParameter("usuario", usuario);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservasAprobables(
		Usuario usuario, 
		int primerResultado, int maxResultados, 
		String campoOrdenacion, boolean ascendente ) {

		Query query = 
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res " +
				" where (res.fin > :ahora) " +
				"   and (res.solicitante.autorizador = :usuario1 or " +
				"        res.recurso.autorizador = :usuario2) " +
				"   and (res.autorizacion = :pendiente) " +
				" order by res." + campoOrdenacion + (ascendente ? " ASC" : " DESC")
      );

		query.setFirstResult(primerResultado);
		query.setMaxResults(maxResultados);
		query.setParameter("ahora", new Date());
		query.setParameter("usuario1", usuario);
		query.setParameter("usuario2", usuario);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservasPendientes(
		Usuario usuario, 
		int primera, int maxRegistros, 
		String campoOrdenacion, boolean ascendente ) {

		Query query = 
			getEntityManager().createQuery(
				"select res " +
				"  from Reserva res " + 
				" where (res.fin > :ahora) " +
				"   and (res.solicitante = :usuario) " + 
				"   and (res.autorizacion = :pendiente) " +
				" order by res." + campoOrdenacion + (ascendente ? " ASC" : " DESC")
        );

		query.setFirstResult(primera);
		query.setMaxResults(maxRegistros);
		query.setParameter("ahora", new Date());
		query.setParameter("usuario", usuario);
		query.setParameter("pendiente", EstadoReserva.PENDIENTE);

		return query.getResultList();
	}

	public Reserva getReserva(int id) {
		return getEntityManager().find(Reserva.class, id);
	}

	public void borrarReserva(Reserva reserva) {
		getEntityManager().remove(reserva);
	}

	protected EntityManager getEntityManager() {
		return manager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		manager = em;
	}	
}