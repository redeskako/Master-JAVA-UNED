/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author carlosl.sanchez
 */
@Stateless
public class NoticiaEntidadFacade implements NoticiaEntidadFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(NoticiaEntidad noticiaEntidad) {
        em.persist(noticiaEntidad);
    }

    public void edit(NoticiaEntidad noticiaEntidad) {
        em.merge(noticiaEntidad);
    }

    public void remove(NoticiaEntidad noticiaEntidad) {
        em.remove(em.merge(noticiaEntidad));
    }

    public NoticiaEntidad find(Object id) {
        return em.find(ejb.NoticiaEntidad.class, id);
    }

    public List<NoticiaEntidad> findAll() {
        return em.createQuery("select object(o) from NoticiaEntidad as o").getResultList();
    }

}
