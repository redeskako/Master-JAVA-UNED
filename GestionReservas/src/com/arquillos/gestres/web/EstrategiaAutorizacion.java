/*
 * 
 * 
 */
package com.arquillos.gestres.web;

import com.arquillos.gestres.web.page.PaginaLogin;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.CompoundAuthorizationStrategy;

/**
 *
 * @author Antonio Jes�s Arquillos �lvarez
 */
public class EstrategiaAutorizacion extends CompoundAuthorizationStrategy {

  /**
   * Comprueba que las p�ginas marcadas con {@link Authorized} tienen un usuario v�lido.
   */
  @SuppressWarnings("unchecked")
  private static final class ValidadorComponenteProtegido implements IAuthorizationStrategy {

    @SuppressWarnings("rawtypes")
		public boolean isInstantiationAuthorized( final Class claseComponente ) {      
      // System.out.println( "Intentando cargar " + claseComponente.getNombre() );

      if ( claseComponente.getAnnotation( Autorizado.class ) != null 
           && ( ( Session )Session.get()).getUsuarioActual() == null) {
        // System.out.println( "Se necesita autorizaci�n. El usuario se ha logado.");
        throw new RestartResponseAtInterceptPageException( PaginaLogin.class );
      }
      // System.out.println( "El usuario se ha logado o no se necesita autorizaci�n" );
      return true;
    }

    public boolean isActionAuthorized( final Component componente, final Action accion ) {     
      return true;      
    }
  }

  public EstrategiaAutorizacion() {
    add( new ValidadorComponenteProtegido() );
  }
}