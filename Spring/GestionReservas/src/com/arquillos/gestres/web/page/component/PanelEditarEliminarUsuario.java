/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.page.EditarUsuario;
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
@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class PanelEditarEliminarUsuario extends Panel {
  @SpringBean
  private UsuarioDao usuarioDao;
  
	public PanelEditarEliminarUsuario(String id, IModel<Usuario> modelo) {
    super(id, modelo);
    
    Link enlaceEditar = new Link("enlaceEditar"){
			@Override
      public void onClick() {
        setResponsePage(
           new EditarUsuario(
              new Model(
                (Usuario) PanelEditarEliminarUsuario.this.getDefaultModelObject()), 
                getPage()
           )
         );
      }      
    };
    Link enlaceBorrar = new Link("enlaceBorrar"){
      @Override
      public void onClick() {
        usuarioDao.borrarUsuario((Usuario) PanelEditarEliminarUsuario.this.getDefaultModelObject());
      }      
    };
    enlaceBorrar.add(
      new AttributeAppender(
        "onclick",
        new Model("confirmar('¿Borrar usuario " + modelo.getObject().getUserName() + "?');"),
        ";"));
    
    add(enlaceEditar);
    add(enlaceBorrar);
  }  
}