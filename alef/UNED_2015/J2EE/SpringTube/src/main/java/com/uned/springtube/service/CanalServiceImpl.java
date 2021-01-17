package com.uned.springtube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uned.springtube.dao.CanalDao;
import com.uned.springtube.model.Canal;


/**
 * Implementación de las operaciones de lógica de negocio.
 * Básicamente ejecutan llamadas a los métodos dao.
 */

@Service("canalService")
@Transactional // Comienza la transacción en cada método start, y ejecuta el commit en cada método exit (o rollback si la transacción falla).
public class CanalServiceImpl implements CanalService
{ 
    @Autowired
    private CanalDao dao;

    public void add(Canal canal){
    	dao.saveCanal(canal);
    }
    public List<Canal> getCanales(){
    	return dao.getCanalList();
    }
}