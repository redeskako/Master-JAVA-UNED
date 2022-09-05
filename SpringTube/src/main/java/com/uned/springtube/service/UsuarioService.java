package com.uned.springtube.service;

/**
 * Interface con las operaciones de la lógica de negocio.
 */
public interface UsuarioService
{ 
	boolean esUsuarioAutorizado(String sUser, String sPasword);
}