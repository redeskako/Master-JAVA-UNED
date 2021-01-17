package com.uned.springtube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uned.springtube.dao.VideoDao;
import com.uned.springtube.model.Videos;


/**
 * Implementaci�n de las operaciones de l�gica de negocio.
 * B�sicamente ejecutan llamadas a los m�todos dao.
 */

@Service("videoService")
@Transactional // Comienza la transacci�n en cada m�todo start, y ejecuta el commit en cada m�todo exit (o rollback si la transacci�n falla).
public class VideoServiceImpl implements VideoService
{ 
    @Autowired
    private VideoDao dao;
 
    public List<Videos> ListAllVideos(String id_canal, String pag)
    {
    	return dao.ListAllVideos(id_canal, pag);
    }
    
    public int getNumPag(String id_canal)
    {
    	return dao.getNumPag(id_canal);
    }
}