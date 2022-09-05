/**
 * Modelo de la tabla videos de la BD.
 */

package com.uned.springmvcrestclient.model;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video
{
	@Size(min=11, max=11, message = "El id de video ha de tener 11 caracteres.")
	@JsonProperty("id")
    private String id;  
	
	@JsonProperty("id_canal")
    private String idCanal;
	
	@JsonProperty("titulo")
    private String titulo;
	
	@JsonProperty("descripcion")
    private String descripcion;
	
	@JsonProperty("fecha_publicacion")
    private String fechaPublicacion;
	
	@JsonProperty("thumbnail")
    private String thumbnail;

    public String getId()
    {
        return id;
    }
 
    public void setId(String id)
    {
        this.id = id;
    }
 
    public String getIdCanal()
    {
        return idCanal;
    }
 
    public void setIdCanal(String idCanal)
    {
        this.idCanal = idCanal;
    }
    
    public String getTitulo()
    {
        return titulo;
    }
 
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
 
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    public String getFechaPublicacion()
    {
        return fechaPublicacion;
    }
 
    public void setFechaPublicacion(String fechaPublicacion)
    {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public String getThumbnail()
    {
        return thumbnail;
    }
 
    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }   
}