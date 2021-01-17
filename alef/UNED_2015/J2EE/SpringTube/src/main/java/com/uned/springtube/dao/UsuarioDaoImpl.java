package com.uned.springtube.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uned.springtube.model.Usuario;

/**
 * Implementación de las operaciones sobre la BD.
 */

@Repository("usuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Integer, Usuario> implements UsuarioDao
{ 
    public Usuario findUsuario(String sUser)
    {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user", sUser));
        return (Usuario) criteria.uniqueResult();
    }
}