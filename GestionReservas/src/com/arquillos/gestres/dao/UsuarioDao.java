/*
 * Entidad Usuario
 * Objeto de acceso a datos
 * Definición de interfaz
 */

package com.arquillos.gestres.dao;

import com.arquillos.gestres.data.Usuario;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Antonio Jesús Arquillos Álvarez
 */
public interface UsuarioDao {  
	Usuario getUsuarioPorLogin(String login);

	Usuario getUsuarioPorId(int id);

	List<Usuario> getUsuarios();

	List<Usuario> getUsuarios( 
		int primero, int maxRegistros, 
		String campoOrdenacion, boolean ascendente);

	int totalUsuarios();

	@Transactional
	void salvarUsuario(Usuario usuario);

	@Transactional
	void borrarUsuario(Usuario usuario);

	Usuario autenticarUsuario(String login, String password);

	@Transactional
	void comprobarExisteAdmin();  
}