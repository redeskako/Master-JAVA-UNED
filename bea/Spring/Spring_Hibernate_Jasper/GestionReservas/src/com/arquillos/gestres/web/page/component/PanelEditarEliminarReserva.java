/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.web.page.PaginaCalendario;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author
 */
@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class PanelEditarEliminarReserva extends Panel {  
  @SpringBean
  private ReservaDao reservaDao;

	public PanelEditarEliminarReserva(String id, IModel<Reserva> modelo) {
    super(id, modelo);
    Link enlaceEditar = new Link("enlaceEditar"){
			@Override
      public void onClick() {
        setResponsePage(
          new PaginaCalendario(
            new Model((Reserva) PanelEditarEliminarReserva.this.getDefaultModelObject()), getPage())
        );
      }      
    };
    Link enlaceBorrar = new Link("enlaceBorrar"){
      @Override
      public void onClick() {
        reservaDao.borrarReserva((Reserva)PanelEditarEliminarReserva.this.getDefaultModelObject());
      }      
    };
    
    Reserva r = modelo.getObject();
    String message = 
      String.format(
        "return confirm('¿Desea realmete borrar la reserva de {0} para "
        + "{1} desde {2} hasta {3}?');",
          r.getSolicitante().getNombre(),
          r.getRecurso().getNombre(),
          r.getInicio(),
          r.getFin());
    
    enlaceBorrar.add(new AttributeAppender("onclick", new Model(message), ";"));
    
    add(enlaceEditar);
    add(enlaceBorrar);    
  }
}