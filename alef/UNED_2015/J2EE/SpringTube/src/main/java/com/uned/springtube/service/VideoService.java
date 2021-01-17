package com.uned.springtube.service;

import java.util.List;

import com.uned.springtube.model.Videos;

/**
 * Interface con las operaciones de la l�gica de negocio.
 */
public interface VideoService
{ 
	List<Videos> ListAllVideos(String id_canal, String pag);
	int getNumPag(String id_canal);
}
