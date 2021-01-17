/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlosl.sanchez
 */
@Local
public interface NoticiaEntidadFacadeLocal {

    void create(NoticiaEntidad noticiaEntidad);

    void edit(NoticiaEntidad noticiaEntidad);

    void remove(NoticiaEntidad noticiaEntidad);

    NoticiaEntidad find(Object id);

    List<NoticiaEntidad> findAll();

}
