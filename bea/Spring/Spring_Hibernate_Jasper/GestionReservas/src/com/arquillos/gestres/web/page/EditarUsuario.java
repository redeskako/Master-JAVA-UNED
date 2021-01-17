/*
 *
 *
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Color;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.Autorizado;
import com.arquillos.gestres.web.Session;
import com.arquillos.gestres.web.page.component.TituloMenu;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
@Autorizado
public class EditarUsuario extends WebPage {  
	@SpringBean
	private UsuarioDao usuarioDao;  
	private Model<String> nuevaPassword;
  
	public EditarUsuario(IModel<Usuario> modelo, final Page previa) {
		super(modelo);
    
		add(new TituloMenu("tituloMenu"));
		nuevaPassword = new Model("");
    
		Form formEditarUsuario = new Form("formEditarUsuario"){
			@Override
			protected void onSubmit() {
				Usuario usuario = (Usuario)EditarUsuario.this.getDefaultModelObject();        
				if (nuevaPassword!= null 
					 && nuevaPassword.getObject() != null 
					 && !nuevaPassword.getObject().isEmpty()) {
					String encriptada = new BasicPasswordEncryptor().encryptPassword(nuevaPassword.getObject());
					usuario.setPassword(encriptada);
				}        
				usuarioDao.salvarUsuario(usuario);        
        setResponsePage(previa);
      }    
    };   
    
    formEditarUsuario.add(new FeedbackPanel("feedback"));
    // Login
    formEditarUsuario.add( 
    	new FormComponentFeedbackBorder("bordeLogin").add(
    		new RequiredTextField("login", new PropertyModel(modelo.getObject(), "username")){
    			@Override
    			public boolean isEnabled() {
    				return ((Session)getSession()).getUsuarioActual().isAdmin();
    			}      
    		}.setLabel(new Model("username")) 
    	) 
    );
    // Nombre
    formEditarUsuario.add(
    	new FormComponentFeedbackBorder("bordeNombre").add(
    		new RequiredTextField("nombre", new PropertyModel(modelo.getObject(), "nombre")).setLabel(new Model("nombre")) 
    	) 
    );
    // Nombre
    formEditarUsuario.add(
    	new FormComponentFeedbackBorder("bordeApellidos").add(
    		new RequiredTextField("apellidos", new PropertyModel(modelo.getObject(), "apellidos")).setLabel(new Model("apellidos")) 
    	) 
    );
    // Password 1
    PasswordTextField password1 = new PasswordTextField("pass1",  nuevaPassword);
    password1.setResetPassword(false);
    password1.setRequired(modelo.getObject().isNuevo());
    password1.setLabel(new Model("password"));
    // Password 2
    PasswordTextField password2 = new PasswordTextField("pass2");
    password2.setModel(password1.getModel());
    password2.setResetPassword(false);    
    password2.setRequired(false);
    password2.setLabel(new Model("confirmacion"));
    
    formEditarUsuario.add(new FormComponentFeedbackBorder("bordePass1").add(password1));
    formEditarUsuario.add(new FormComponentFeedbackBorder("bordePass2").add(password2));      
    formEditarUsuario.add(new EqualPasswordInputValidator(password1, password2));
    // Email
    formEditarUsuario.add(
    	new TextField( 
    		"email", 
        new PropertyModel(modelo.getObject(), "email") 
    	).add(EmailAddressValidator.getInstance()) 
    );
    // CheckBox Administrador
    formEditarUsuario.add(
      new CheckBox("admin", new PropertyModel(modelo.getObject(), "admin")) {
        @Override
        public boolean isEnabled() {
        	return ((Session)getSession()).getUsuarioActual().isAdmin();
        }
      }
    );
    // Teléfono
    formEditarUsuario.add(
    	new TextField("telefono", new PropertyModel( modelo.getObject(), "telefono"))
    );
    // Autorizador
    formEditarUsuario.add(
      new DropDownChoice( 
      	"autorizador", 
        new ModeloAutorizador((Usuario)modelo.getObject()),
        new ModeloSeleccionUsuario(usuarioDao), 
        new ComboSeleccionUsuarioRenderer()){
      	
      	@Override
      	public boolean isEnabled() {
      		return ((Session)getSession()).getUsuarioActual().isAdmin();
      	}
      }.setNullValid(false)
    );
    // Color
    formEditarUsuario.add(
      new DropDownChoice(
      	"color", 
        new PropertyModel(modelo.getObject(), "color"),
        Arrays.asList(Color.values())
      )
    );
    // Comentarios
    formEditarUsuario.add(
      new TextArea( 
      	"comentarios", 
      	new PropertyModel(modelo.getObject(), "comentarios")
      )
    );
    // Botón salvar
    formEditarUsuario.add(
      new Button("botonSalvar", new PropertyModel(this, "textoBotonSalvar"))
    );
    // Botón cancelar
    formEditarUsuario.add(
      new Button("botonCancelar", new PropertyModel(this, "textoBotonCancelar")) {
      	@Override
      	public void onSubmit() {
      		setResponsePage(previa);
      	}
      }.setDefaultFormProcessing(false) 
    );
    
    add(formEditarUsuario);    
  }  
  
  public String getTextoBotonSalvar() {
    Usuario u = (Usuario)getDefaultModelObject();
    
    if (u.isNuevo())
      return "Crear";
    else
      return "Actualizar";
  }
  
  public String getTextoBotonCancelar() {
    Usuario u = (Usuario)getDefaultModelObject();
    
    if (u.isNuevo())
    	return "Cancelar alta";
    else
      return "Cancelar edición";
  }
  
	public static class ModeloAutorizador extends Model<Usuario> {
    
    private Usuario usuario;
    private Usuario usuarioAuxiliar;
    
    public ModeloAutorizador( Usuario usuario ) {
      super(usuario.getAutorizador());
      this.usuario = usuario;
      this.usuarioAuxiliar = Usuario.crearUsuarioCentinela();
    }
    
    @Override
    public Usuario getObject() {
      if (usuario == null || usuario.getAutorizador() == null)
        return usuarioAuxiliar;
      else
        return usuario.getAutorizador();
    }

    @Override
    public void setObject(Usuario nuevoAutorizador) {      
      if (usuario != null) {        
        if (nuevoAutorizador == null || nuevoAutorizador.equals( usuarioAuxiliar))
          usuario.setAutorizador(null);
        else
          usuario.setAutorizador(nuevoAutorizador);
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