/*
 * Objeto			: Wicket, clase asociada a la página de login
 * Autor 			: Antonio Jesús Arquillos Álvarez
 * revisiones	:
 * 	· 2014/07/11, Versión inicial  
 */

package com.arquillos.gestres.web.page;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Usuario;
import com.arquillos.gestres.web.Application;
import com.arquillos.gestres.web.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

@SuppressWarnings({"unchecked", "rawtypes", "serial"})
/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class PaginaLogin extends WebPage { 
  
	public PaginaLogin() {        
		add(new LoginForm("loginForm"));       
		add(new Label("titulo", new PropertyModel(getApplication(), "titulo")));
		add(new Label("version", new PropertyModel(getApplication(), "version")));
		add(new Label("autor", new PropertyModel(getApplication(), "autor")));
		add( 
			new ExternalLink( 
				"enlaceEmailAdmin", 
				new Model("mailto:" + (( Application)getApplication()).getAdminEmail()),
				new PropertyModel(getApplication(), "adminEmail") 
			) 
		);    
	}
  
	public static class LoginForm extends StatelessForm {    
	  @SpringBean 
	  private UsuarioDao usuarioDao;
    
	  private String login;
	  private String password;
    
		public LoginForm(String id) {
		  super(id);                 
		  add(new FeedbackPanel("feedback"));      
		  add(new TextField("campoUsuario", new PropertyModel(this, "login")));
		  add(
		    new FormComponentFeedbackBorder("bordePassword").add(
		  	  new PasswordTextField("campoPassword", new PropertyModel(this, "password")).setLabel(
		  	  	new Model("password")
		  	  )
		  	) 
		  );
		  add( new Button("botonLogin") );
	  }   
    
	  @Override
	  protected void onSubmit() {      
		  Usuario usuario = usuarioDao.autenticarUsuario(login, password);
		  if (usuario != null) {
			  ((Session)getSession()).setUsuarioActual(usuario);
			  if (!continueToOriginalDestination()) {
				  setResponsePage(getApplication().getHomePage());
			  } 
			  else {
				  System.out.println("Continuando a la ubicación original ...");
			  }
		  } 
		  else {
			  error("Usuario/Password inválidos");
		  }
	  }    

	  public String getLogin() {
		  return login;
	  }

	  public void setLogin(String login) {
		  this.login = login;
	  }

	  public String getPassword() {
		  return password;
	  }

	  public void setPassword(String password) {
		  this.password = password;
	  }
  }
}