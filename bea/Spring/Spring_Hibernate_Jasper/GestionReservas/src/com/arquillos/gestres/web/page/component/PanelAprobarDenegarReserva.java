/*
 * 
 * 
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.EstadoReserva;
import com.arquillos.gestres.service.ServicioCorreo;
import com.arquillos.gestres.web.Session;
import java.text.MessageFormat;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class PanelAprobarDenegarReserva extends Panel{
  @SpringBean
  private ReservaDao reservaDao;  
  @SpringBean
  private ServicioCorreo servicioCorreo;  

	public PanelAprobarDenegarReserva(String id, IModel<Reserva> modelo) {
    super(id, modelo);    
    Reserva r = modelo.getObject();
    
    Link enlaceAprobar = new Link("enlaceAprobar") {
      @Override
      public void onClick() {
        Reserva r = (Reserva) PanelAprobarDenegarReserva.this.getDefaultModelObject();
        r.setAutorizacion(EstadoReserva.APROBADA);
        r.setAutorizador(((Session)getSession()).getUsuarioActual());
        reservaDao.salvarReserva(r);
        servicioCorreo.notifReservaAprobada(r);
      }
    };
    String mensajeAprobar = 
        MessageFormat.format(
          "confirmar(''¿Aprobar reserva de {0} para "
          + "{1} desde {2,date,MMM dd, yyyy hh:mm} hasta {3,date,MMM dd, yyyy hh:mm}?'');",            
            r.getRecurso().getNombre(),
            r.getSolicitante().getNombre(),
            r.getInicio(),
            r.getFin());
    
    enlaceAprobar.add(new AttributeAppender("onclick", new Model(mensajeAprobar), ";"));
    add(enlaceAprobar);	
    
    Link enlaceDenegar = new Link("enlaceDenegar") {
      @Override
      public void onClick() {
        Reserva r = (Reserva) PanelAprobarDenegarReserva.this.getDefaultModelObject();
        r.setAutorizacion(EstadoReserva.DENEGADA);
        r.setAutorizador(((Session)getSession()).getUsuarioActual());
        reservaDao.salvarReserva(r);
        servicioCorreo.notifReservaDenegada(r);
      }
    };
    String mensajeDenegar = 
      MessageFormat.format(
        "confirmar(''¿Denegar reserva de{0} para "
        + "{1} desde {2,date,MMM dd, yyyy hh:mm} hasta {3,date,MMM dd, yyyy hh:mm}?'');",            
          r.getRecurso().getNombre(),
          r.getSolicitante().getNombre(),
          r.getInicio(),
          r.getFin());
    
    enlaceDenegar.add(new AttributeAppender("onclick", new Model(mensajeDenegar), ";"));
    add(enlaceDenegar);
  }
}