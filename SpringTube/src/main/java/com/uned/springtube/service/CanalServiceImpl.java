package com.uned.springtube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uned.springtube.dao.CanalDao;
import com.uned.springtube.model.Canal;


/**
 * Implementaci�n de las operaciones de l�gica de negocio.
 * B�sicamente ejecutan llamadas a los m�todos dao.
 */

@Service("canalService")
@Transactional // Comienza la transacci�n en cada m�todo start, y ejecuta el commit en cada m�todo exit (o rollback si la transacci�n falla).
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