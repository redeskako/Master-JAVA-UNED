package es.uned.masterJava2015.domain;


/**
 * Modelo de la tabla videos de la BD.
 */

public class Videos
{
	
    private String id;  
    private String id_canal;  
    private String titulo;   
    private String descripcion;     
    private String fecha_publicacion;  
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