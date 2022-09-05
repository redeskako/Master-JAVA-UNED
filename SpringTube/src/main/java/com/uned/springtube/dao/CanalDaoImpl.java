package com.uned.springtube.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.uned.springtube.model.Canal;

@Repository("canalDao")
public class CanalDaoImpl extends AbstractDao<Integer, Canal> implements CanalDao{

	public List<Canal> getCanalList() {		
		 return findAll();
	}

	public void saveCanal(Canal canal) {
		persist(canal);
		
	}

}
