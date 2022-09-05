package com.uned.springtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uned.springtube.dao.UsuarioDao;
import com.uned.springtube.model.Usuario;

/**
 * Implementaci�n de las operaciones de l�gica de negocio.
 * B�sicamente ejecutan llamadas a los m�todos dao.
 */

@Service("usuarioService")
@Transactional // Comienza la transacci�n en cada m�todo start, y ejecuta el commit en cada m�todo exit (o rollback si la transacci�n falla).
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