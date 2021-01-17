
/*
 * Recurso
 * Objeto de acceso a datos
 * Definición de interfaz
 */

package com.arquillos.gestres.dao;

import com.arquillos.gestres.data.Recurso;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public interface RecursoDao {
	Recurso getRecurso(int id);

	List<Recurso> getRecursos();

	List<Recurso> getRecursos(
		int primero, int registros, 
		String campoOrdenacion, boolean ascendente);

	int getTotalRecursos();

	@Transactional
	void salvarRecurso(Recurso recurso);

	@Transactional
	void borrarRecurso(Recurso recurso);
}