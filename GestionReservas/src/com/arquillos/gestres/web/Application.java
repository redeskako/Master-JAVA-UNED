/*
 * 
 * 
 */

package com.arquillos.gestres.web;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.web.page.PaginaCalendario;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class Application extends WebApplication {  
  /**
	 * 
	 */
  private UsuarioDao usuarioDao;  
  private String titulo;
  private String autor;
  private String adminEmail;
  private String homeUrl;
  private String version;

  public Application() {
  }

  @Override
  protected void init() {
    super.init();
    addComponentInstantiationListener(new SpringComponentInjector(this));        
    getSecuritySettings().setAuthorizationStrategy(new EstrategiaAutorizacion());    
    usuarioDao.comprobarExisteAdmin();    
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new Session(request);
  }

  @Override
  public Class<? extends Page> getHomePage() {
    return PaginaCalendario.class;
  }

  public UsuarioDao getUsuarioDao() {
    return usuarioDao;
  }

  public void setUsuarioDao(UsuarioDao usuarioDao) {
    this.usuarioDao = usuarioDao;
  }

  public String getAdminEmail() {
    return adminEmail;
  }

  public void setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
  }

  public String getHomeUrl() {
    return homeUrl;
  }

  public void setHomeUrl(String homeUrl) {
    this.homeUrl = homeUrl;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}