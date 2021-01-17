/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.page.EditarUsuario;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class BotonNuevoUsuario extends Panel {
	public BotonNuevoUsuario(String id, IModel<Page> model) {
    super(id, model);
    Form formNuevoUsuario = new Form( "formNuevoUsuario" );
        
    formNuevoUsuario.add(new Button("botonNuevoUsuario", new Model("Añadir nuevo usuario")){
          @Override
          public void onSubmit() {
            Page pagina = (Page) BotonNuevoUsuario.this.getDefaultModelObject();
            setResponsePage(
              new EditarUsuario(
                new Model(
                  new Usuario()), pagina));
          }          
        });
        
    add(formNuevoUsuario);
  }
}
