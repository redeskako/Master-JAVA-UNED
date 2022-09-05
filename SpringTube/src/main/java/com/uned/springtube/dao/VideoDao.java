package com.uned.springtube.dao;

import java.util.List;

import com.uned.springtube.model.Videos;

/**
 * Interfaz con las operaciones sobre la BD.
 */
public interface VideoDao
{
	// Busca los videos.
	List<Videos> ListAllVideos(String id_canal, String pag);
	// Enumera los videos de un canal
	int getNumPag(String id_canal);
}