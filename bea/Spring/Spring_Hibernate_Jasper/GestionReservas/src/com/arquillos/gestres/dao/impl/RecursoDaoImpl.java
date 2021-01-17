/*
 * Entidad Recurso
 * Objeto de acceso a datos
 * Implementación de interfaz
 */

package com.arquillos.gestres.dao.impl;

import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.data.Recurso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class RecursoDaoImpl implements RecursoDao {
	private EntityManager manager;

	public Recurso getRecurso(int id) {
		return getEntityManager().find(Recurso.class, id);
	}

	public int getTotalRecursos() {
		Query query =
			getEntityManager().createQuery(
				"select COUNT( r )" +
				"  from Recurso r"
		);
		return ((Long)query.getSingleResult()).intValue();
	}

	public void salvarRecurso(Recurso r) {
		if (r.getId() == null || r.getId() == 0)
			getEntityManager().persist(r);
		else
			getEntityManager().merge(r);
	}

	@SuppressWarnings("unchecked")
	public List<Recurso> getRecursos() {
		Query query =
			getEntityManager().createQuery(
				"select r " +
				"  from Recurso r "
		);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Recurso> getRecursos(
		int primero, int maxRegistros,
		String campoOrdenacion, boolean ascendente) {

		Query query =
			getEntityManager().createQuery(
				"select r " +
				"  from Recurso r " +
				" order by r." + campoOrdenacion + ( ascendente ? " ASC" : " DESC" )
		);
		query.setFirstResult( primero );
		query.setMaxResults( maxRegistros );

		return (List<Recurso>)query.getResultList();
	}

	public void borrarRecurso(Recurso recurso) {
		getEntityManager().remove(recurso);
	}

	protected EntityManager getEntityManager() {
		return manager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		manager = em;
	}
}