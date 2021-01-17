/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.web.page.EditarRecurso;
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
@SuppressWarnings("serial")
public class PanelEditarEliminarRecurso extends Panel {

  @SpringBean
  private RecursoDao recursoDao;
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
	public PanelEditarEliminarRecurso(String id, IModel<Recurso> modelo) {
    super(id, modelo);    
    Link enlaceEditar = new Link("enlaceEditar"){
			@Override
      public void onClick() {
        setResponsePage(
            new EditarRecurso(
              new Model(
                (Recurso) PanelEditarEliminarRecurso.this.getDefaultModelObject()), 
                getPage()));
      }      
    };
    Link enlaceBorrar = new Link("enlaceBorrar"){
      @Override
      public void onClick() {
        recursoDao.borrarRecurso((Recurso) PanelEditarEliminarRecurso.this.getDefaultModelObject());
      }      
    };
    enlaceBorrar.add(
        new AttributeAppender(
          "onclick",
          new Model("confirmar('¿Borrar recurso " 
            + modelo.getObject().getNombre() + "?');"),
          ";"));
    
    add(enlaceEditar);
    add(enlaceBorrar);
  }  
}