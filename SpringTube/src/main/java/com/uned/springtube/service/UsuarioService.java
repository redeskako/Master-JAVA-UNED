package com.uned.springtube.service;

/**
 * Interface con las operaciones de la l�gica de negocio.
 */
public interface UsuarioService
{ 
	boolean esUsuarioAutorizado(String sUser, String sPasword);
}