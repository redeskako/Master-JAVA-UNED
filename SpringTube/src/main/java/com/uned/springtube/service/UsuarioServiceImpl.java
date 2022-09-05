package com.uned.springtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uned.springtube.dao.UsuarioDao;
import com.uned.springtube.model.Usuario;

/**
 * Implementación de las operaciones de lógica de negocio.
 * Básicamente ejecutan llamadas a los métodos dao.
 */

@Service("usuarioService")
@Transactional // Comienza la transacción en cada método start, y ejecuta el commit en cada método exit (o rollback si la transacción falla).
public class UsuarioServiceImpl implements UsuarioService
{ 
    @Autowired
    private UsuarioDao dao;

    public Usuario findUsuario(String sUser)
    {
        return dao.findUsuario(sUser);
    }
 
    public boolean esUsuarioAutorizado(String sUser, String sPassword)
    {
        Usuario usuario = findUsuario(sUser);

        if (usuario == null)
        	return false;
        else
        	return usuario.getPassword().equals(sPassword);
    }
}