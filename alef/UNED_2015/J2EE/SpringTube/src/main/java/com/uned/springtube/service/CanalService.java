package com.uned.springtube.service;

import java.util.List;

import com.uned.springtube.model.Canal;

/**
 * Interface con las operaciones de la lógica de negocio.
 */
public interface CanalService
{ 
	void add(Canal canal);
	public List<Canal> getCanales();
}