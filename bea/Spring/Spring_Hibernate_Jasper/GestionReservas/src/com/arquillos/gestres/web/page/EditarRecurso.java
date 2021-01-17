/*
 *
 * 
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.RecursoDao;
import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Color;
import com.arquillos.gestres.data.EstadoRecurso;
import com.arquillos.gestres.data.Recurso;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.TituloMenu;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
@Autorizado
public class EditarRecurso extends WebPage {  
	@SpringBean
	private RecursoDao recursoDao;  
	@SpringBean
	private UsuarioDao usuarioDao;
      
	public EditarRecurso(IModel<Recurso> modelo, final Page previa) {
    super(modelo);
    
    add(new TituloMenu("tituloMenu"));    
    Form formEditarRecurso = new Form("formEditarRecurso"){
    	@Override
    	protected void onSubmit() {
    		Recurso recurso = (Recurso)EditarRecurso.this.getDefaultModelObject();        
    		recursoDao.salvarRecurso(recurso);        
    		setResponsePage(previa);
    	}    
    };
    
    formEditarRecurso.add(new FeedbackPanel("feedback"));    
    formEditarRecurso.add(
    	new FormComponentFeedbackBorder("bordeNombre").add(
    	  new RequiredTextField("nombre", new PropertyModel(modelo.getObject(),"nombre")).setLabel(new Model("nombre"))) 
    );
    
    formEditarRecurso.add(
      new TextArea("descripcion", new PropertyModel(modelo.getObject(), "descripcion")
      )
    );
    
    formEditarRecurso.add(
      new DropDownChoice("color", new PropertyModel(modelo.getObject(), "color"), Arrays.asList(Color.values()))
    );
    
    formEditarRecurso.add(
      new DropDownChoice("estado", new PropertyModel(modelo.getObject(), "estado"), Arrays.asList(EstadoRecurso.values()))
    );
    
    formEditarRecurso.add(
      new DropDownChoice("autorizador", 
        new ModeloAutorizador((Recurso)modelo.getObject()),
        new ModeloSeleccionUsuario(usuarioDao),
        new ComboSeleccionUsuarioRenderer()){
        	@Override
        	public boolean isEnabled() {
        		return ((Session)getSession()).getUsuarioActual().isAdmin();
        	}
        }.setNullValid(false) 
    );
    
    formEditarRecurso.add(
      new Button("botonSalvar", new PropertyModel(this, "textoBotonSalvar")) 
    );
    
    formEditarRecurso.add(
      new Button("botonCancelar", new PropertyModel(this, "textoBotonCancelar")) {
        @Override
        public void onSubmit() {
        	setResponsePage(previa);
        }
       }.setDefaultFormProcessing(false) 
    );
    
    add(formEditarRecurso);
  }
  
	public String getTextoBotonSalvar() {    
		if (((Recurso)getDefaultModelObject()).isNuevo())
			return "Crear";
		else
			return "Actualizar";
	}
  
	public String getTextoBotonCancelar() {
		if (((Recurso)getDefaultModelObject()).isNuevo())
			return "Cancelar alta";
		else
			return "Cancelar edición";
	}
  
	public static class ModeloAutorizador extends Model<Usuario> {
    
		private Recurso recurso;
		private Usuario usuarioAuxiliar;
    
		public ModeloAutorizador(Recurso recurso) {
			super(recurso.getAutorizador());
			this.recurso = recurso;
			usuarioAuxiliar = Usuario.crearUsuarioCentinela();
		}
    
		@Override
		public Usuario getObject() {
			if (recurso == null || recurso.getAutorizador() == null)
				return usuarioAuxiliar;
			else
				return recurso.getAutorizador();
		}

		@Override
		public void setObject(Usuario nuevoAutorizador) {      
			if (recurso != null) {        
				if (nuevoAutorizador == null || nuevoAutorizador.equals( usuarioAuxiliar))
					recurso.setAutorizador(null);
				else
					recurso.setAutorizador(nuevoAutorizador);
			}
		}        
	}
  
	public static class ModeloSeleccionUsuario extends LoadableDetachableModel<List<Usuario>> {
    
		private UsuarioDao usuarioDao;

		public ModeloSeleccionUsuario(UsuarioDao usuarioDao) {
			this.usuarioDao = usuarioDao;
		}
    
		@Override
		protected List<Usuario> load() {
			List<Usuario> usuarios = (List<Usuario>) usuarioDao.getUsuarios();
			usuarios.add(0, Usuario.crearUsuarioCentinela());
			return usuarios;
		}  
	}
  
	public static class ComboSeleccionUsuarioRenderer implements IChoiceRenderer<Usuario> {
  	public Object getDisplayValue(Usuario usuario) {
  		return usuario.getNombre();
  	}

  	public String getIdValue(Usuario usuario, int arg1) {
  		return usuario.getId().toString();      
  	}   
  }
}