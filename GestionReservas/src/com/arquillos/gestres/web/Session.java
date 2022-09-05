/*
 *
 *
 */

package com.arquillos.gestres.web;

import com.arquillos.gestres.data.Usuario;
import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
@SuppressWarnings("serial")
public class Session extends WebSession {
  private Usuario usuarioActual;

  public Session( Request request ) {
    super( request );
  }

  public Usuario getUsuarioActual() {
    return usuarioActual;
  }

  public void setUsuarioActual( Usuario usuarioActual ) {
    this.usuarioActual = usuarioActual;
  }

  public boolean isAutenticado() {
    return usuarioActual != null;
  }
}