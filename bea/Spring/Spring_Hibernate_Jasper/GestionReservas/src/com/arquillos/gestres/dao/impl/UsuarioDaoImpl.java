/*
 * Entidad Usuario
 * Objeto de acceso a datos
 * Implementación de interfaz
 */

package com.arquillos.gestres.dao.impl;

import com.arquillos.gestres.dao.UsuarioDao;
import com.arquillos.gestres.data.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public class UsuarioDaoImpl implements UsuarioDao {
	private EntityManager manager;

	public Usuario getUsuarioPorLogin(String login) {
		Query query = 
			getEntityManager().createQuery(
				"select u from Usuario u" +
				" where u.username = :login" 
			);
		query.setParameter( "login", login );

		try {
			Usuario usuario = (Usuario)query.getSingleResult();
			return usuario;
		} catch (NonUniqueResultException nurex) {
			// System.out.println( "Más de un usuario con el mismo login" );
		} catch (NoResultException nrex) {       
			// System.out.println( "No se ha encontrado usuario ");
		}
		return null;
	}

	public Usuario getUsuarioPorId(int id) {
		return getEntityManager().find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios() {
	  Query query = 
	    getEntityManager().createQuery( 
	      "select u" +
	    	"  from Usuario u" 
	    );
	  return query.getResultList();       
	}  

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios (
		int primero, int maxRegistros, 
		String campoOrdenacion, boolean ascendente ) {

		// consulta
		Query query = 
			getEntityManager().createQuery(
				"select u" +
				"  from Usuario u" +
				" order by u." + campoOrdenacion + ( ascendente ? " ASC" : " DESC" ) 
		);		
		// paginación
		query.setFirstResult(primero);
		query.setMaxResults(maxRegistros);
		// ejecutamos consulta
		return query.getResultList();
	}

	public int totalUsuarios() {
		Query query = 
			getEntityManager().createQuery("" +
				"select COUNT( u )" +
				"  from Usuario u" 
		);		
		return ((Long)query.getSingleResult()).intValue();
	}

	public Usuario autenticarUsuario(String login, String password) {        
		Usuario usuario = getUsuarioPorLogin(login);      
		if (usuario != null) {
			BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();
			if (bpe.checkPassword(password, usuario.getPassword())) {
				return usuario;
			} 
			else {
				// System.out.println( "password incorrecta" );
			}
		}    
		return null;    
	}

	public void salvarUsuario(Usuario u) {    
		if (u.getId() == null || u.getId() == 0)
			// damos de alta nuevo usuario
			getEntityManager().persist(u);
		else
			// actualizamos usuario ya existente
			getEntityManager().merge(u);
	}

	public void comprobarExisteAdmin() {
		Usuario admin = getUsuarioPorLogin(Usuario.ADMIN_LOGIN_DEF);
		if (admin == null) {
			admin = Usuario.crearUsuarioAdministradorDefecto();      
			salvarUsuario(admin);
		}
	}

  public void borrarUsuario(Usuario usuario) {
  	getEntityManager().remove(usuario);
  }

  protected EntityManager getEntityManager() {
  	return manager;
  }

  @PersistenceContext
  public void setEntityManager( EntityManager em ) {
  	manager = em;
  }  
}