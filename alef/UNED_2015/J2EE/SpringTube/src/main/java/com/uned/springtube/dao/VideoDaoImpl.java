package com.uned.springtube.dao;


import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uned.springtube.model.Videos;

/**
 * Implementaci�n de las operaciones sobre la BD.
 */

@Repository("videoDao")
public class VideoDaoImpl extends AbstractDao<Integer, Videos> implements VideoDao
{ 
    @SuppressWarnings("unchecked")
    public List<Videos> ListAllVideos(String id_canal, String pag)
    {
    	int enteropag = Integer.parseInt(pag);
    	int nPrimerVideo = ((enteropag - 1) * 20) + 1;
    	int nUltimoVideo = 20;
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id_canal", id_canal));
        criteria.setFirstResult(nPrimerVideo); 
        criteria.setMaxResults(nUltimoVideo);
        return (List<Videos>) criteria.list();
    }   
    
    
    @SuppressWarnings("unchecked")
    public int getNumPag(String id_canal)
	{
		int nNumPag = 0;
		int nNumVideos = 0;
		List<String> list = new ArrayList<String>();
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id_canal", id_canal));
		list = criteria.list();
		nNumVideos = list.size();
		nNumPag = nNumVideos/20;
		return nNumPag;
	}
    
    
}