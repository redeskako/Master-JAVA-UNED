/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.ReservaDao;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.PaginaCalendario;
import com.arquillos.gestres.web.page.EditarUsuario;
import com.arquillos.gestres.web.page.PaginaReservas;
import com.arquillos.gestres.web.page.PaginaRecursos;
import com.arquillos.gestres.web.page.PaginaUsuarios;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class TituloMenu extends Panel {
  @SpringBean
  private ReservaDao reservaDao;
  
	public TituloMenu(String id) {
    super(id);
    
    final Usuario user = ((Session)getSession()).getUsuarioActual();    
    add(new Label("titulo", new PropertyModel(getApplication(), "titulo")));    
    add(new Link("enlaceCalendario"){
      @Override
      public void onClick() {
        setResponsePage(PaginaCalendario.class);
      }      
    });
    
    WebMarkupContainer contenedorUsuarios = new WebMarkupContainer("contenedorUsuarios");
    contenedorUsuarios.add(new Link("enlaceUsuarios"){
      @Override
      public void onClick() {
        setResponsePage(PaginaUsuarios.class);
      }
    });    
    contenedorUsuarios.setVisible(((Session)getSession()).getUsuarioActual().isAdmin());    
    add(contenedorUsuarios);
    
    WebMarkupContainer contenedorRecursos = new WebMarkupContainer("contenedorRecursos");
    contenedorRecursos.add(new Link("enlaceRecursos"){
      @Override
      public void onClick() {
        setResponsePage(PaginaRecursos.class);
      }
    });
    contenedorRecursos.setVisible(user.isAdmin());    
    add(contenedorRecursos);
    
    Link enlaceReservas = new Link("enlaceReservas"){
      @Override
      public void onClick() {
        setResponsePage(PaginaReservas.class);
      }
    };
    
    int count = reservaDao.totalReservasAprobables(user);    
    enlaceReservas.add(new Label("precisanSupervision", 
        (count == 0 ? "" :
          count == 1 ? "(1 solicitud requiere aprobación)" 
            : "(" + count + " solicitudes requieren aprobación)")));
    
    add(enlaceReservas);
    
    
    Link enlaceEditarUsuarioActual = new Link("enlaceEditarUsuarioActual"){
			@Override
      public void onClick() {
        setResponsePage(new EditarUsuario(new Model(user), getPage()));
      }
    };
    enlaceEditarUsuarioActual.add(new Label("nombreUsuarioActual", 
        new PropertyModel(user, "nombre")));
    add(enlaceEditarUsuarioActual);
    
    add(new Link("enlaceLogout"){
      @Override
      public void onClick() {
        getSession().invalidate();        
        getRequestCycle().setResponsePage(PaginaCalendario.class);        
        getRequestCycle().setRedirect(true);
      }      
    });    
  }
}