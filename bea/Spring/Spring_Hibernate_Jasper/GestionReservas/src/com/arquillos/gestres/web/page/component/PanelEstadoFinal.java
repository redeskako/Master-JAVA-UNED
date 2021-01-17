/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.data.EstadoRecurso;
import com.arquillos.gestres.data.Reserva;
import com.arquillos.gestres.data.Recurso;
import java.util.Arrays;
import java.util.Date;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class PanelEstadoFinal extends Panel {  
  @SpringBean
  private ReservaDao reservaDao;  
  @SpringBean
  private RecursoDao recursoDao;

	public PanelEstadoFinal(String id, final IModel<Reserva> modelo) {
    super(id, modelo);
    
    Form formEstadoRecurso = new Form("formEstadoRecurso");
    
    formEstadoRecurso.add(new DropDownChoice("seleccionEstado",
      new PropertyModel(modelo, "estadoFinal"),
      Arrays.asList(EstadoRecurso.values())));
    
    formEstadoRecurso.add(new Button("salvarEstado"){
      @Override
      public void onSubmit() {
        Reserva r = modelo.getObject();        
        reservaDao.salvarReserva(r);        
        if (r.getRecurso().getFechaActualizacion().before(new Date())) {
          Recurso rec = r.getRecurso();
          rec.setEstado(r.getEstadoFinal());
          rec.setFechaActualizacion(new Date());
          recursoDao.salvarRecurso(rec);
        }
      }
    });
    
    add(formEstadoRecurso);
  }  
}