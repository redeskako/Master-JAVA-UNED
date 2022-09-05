package com.uned.springtube.dao;

import com.uned.springtube.model.Usuario;

/**
 * Interfaz con las operaciones sobre la BD.
 */
public interface UsuarioDao
{
	// Busca un usuario por su nombre de usuario.
	Usuario findUsuario(String sUser);
}