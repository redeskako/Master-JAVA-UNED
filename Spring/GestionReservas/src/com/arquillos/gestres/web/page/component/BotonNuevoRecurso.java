/*
 *
 *
 */

package com.arquillos.gestres.web.page.component;

import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.web.page.EditarRecurso;
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
public class BotonNuevoRecurso extends Panel {

	public BotonNuevoRecurso( String id, IModel<Page> modelo ) {
		super( id, modelo );
		Form formNuevoRecurso = new Form( "formNuevoRecurso" );
    
    
		formNuevoRecurso.add( new Button( "botonNuevoRecurso", new Model( "Añadir Nuevo Recurso" ) ){
			@Override
			public void onSubmit() {
				Page pagina = ( Page ) BotonNuevoRecurso.this.getDefaultModelObject();
				setResponsePage( new EditarRecurso( new Model( new Recurso()), pagina ) );
          }          
        });
        
		add( formNuevoRecurso );
	}
}