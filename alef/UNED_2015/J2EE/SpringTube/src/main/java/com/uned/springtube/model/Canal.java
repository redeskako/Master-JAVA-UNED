package com.uned.springtube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Modelo de la tabla canales de la BD.
 */

@Entity
@Table(name="canales")
public class Canal
{
	@Id
	@NotEmpty
    @Column(name = "id", nullable = false)
	private String id; // Id de canal de youtube.
	
	private String nombre;	
	private String descripcion;
	
	/**
	 * Constructor.
	 */
	public Canal()
	{	 
	}

	/**
	 * Obtiene el id del canal.
	 * @return id del canal.
	 */ 
	public String getId()
	{
		return id;
	}

	/**
	 * Asigna el id al video.
	 * @param id del video.
	 */ 	
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Obtiene el nombre del canal.
	 * @return nombre del canal.
	 */ 	
	public String getNombre()
	{
		return nombre;
	}
	
	/**
	 * Asigna el nombre al canal.
	 * @param nombre del canal.
	 */ 	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Obtiene la descripción del canal.
	 * @return descripción del canal.
	 */ 	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	/**
	 * Asigna la descripción al canal.
	 * @param descripción del canal.
	 */ 	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}