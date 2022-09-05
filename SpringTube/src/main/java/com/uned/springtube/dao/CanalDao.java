package com.uned.springtube.dao;

import java.util.List;

import com.uned.springtube.model.Canal;

public interface CanalDao {
	public List<Canal> getCanalList();

    public void saveCanal(Canal canal);


}
