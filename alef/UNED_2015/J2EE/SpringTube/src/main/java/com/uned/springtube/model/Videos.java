package com.uned.springtube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Modelo de la tabla videos de la BD.
 */

@Entity
@Table(name="videos")
public class Videos
{
	@Id
	@NotEmpty
    @Column(name = "id", nullable = false)
    private String id;

    @NotEmpty
    @Column(name = "id_canal", nullable = false)
    private String id_canal;
    
    @NotEmpty
    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @NotEmpty
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @NotEmpty
    @Column(name = "fecha_publicacion", nullable = false)
    private String fecha_publicacion;
    
    @NotEmpty
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;
    

    public String getid()
    {
        return id;
    }
 
    public void setid(String id)
    {
        this.id = id;
    }
 
    public String getid_canal()
    {
        return id_canal;
    }
 
    public void setid_canal(String id_canal)
    {
        this.id_canal = id_canal;
    }
    
    public String gettitulo()
    {
        return titulo;
    }
 
    public void settitulo(String titulo)
    {
        this.titulo = titulo;
    }
    
    public String getdescripcion()
    {
        return descripcion;
    }
 
    public void setdescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    public String getfecha_publicacion()
    {
        return fecha_publicacion;
    }
 
    public void setfecha_publicacion(String fecha_publicacion)
    {
        this.fecha_publicacion = fecha_publicacion;
    }
    
    public String getthumbnail()
    {
        return thumbnail;
    }
 
    public void setthumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }
    

    
}