package es.csc.biblioteca.books.dao;

import java.util.Objects;

/**
 * Envoltorio para una fila de la tabla Libros.
 * 
 * @author David Atencia
 * @version 0.1
 */
public class BookDTO {
    
    protected int idLibro;
    protected String nombre;
    protected String autor;
    protected String tema;

    protected boolean idLibroModified = false;
    protected boolean nombreModified = false;
    protected boolean autorModified = false;
    protected boolean temaModified = false;

    public BookDTO() {
    }

    public int getIdLibro() {
        return this.idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
        this.idLibroModified = true;
    }
    
    public boolean isIdLibroModified() {
        return this.idLibroModified;
    }

    public void setIdLibroModified(boolean idLibroModified) {
        this.idLibroModified = idLibroModified;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.nombreModified = true;
    }

    public boolean isNombreModified() {
        return this.nombreModified;
    }

    public void setNombreModified(boolean nombreModified) {
        this.nombreModified = nombreModified;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
        this.autorModified = true;
    }

    public boolean isAutorModified() {
        return this.autorModified;
    }

    public void setAutorModified(boolean autorModified) {
        this.autorModified = autorModified;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
        this.temaModified = true;
    }

    public boolean isTemaModified() {
        return this.temaModified;
    }

    public void setTemaModified(boolean temaModified) {
        this.temaModified = temaModified;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idLibro;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.autor);
        hash = 23 * hash + Objects.hashCode(this.tema);
     
        
    return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookDTO other = (BookDTO) obj;
        if (this.idLibro != other.idLibro) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.tema, other.tema)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LibroDTO{" + "idLibro=" + idLibro + ", nombre=" + nombre + ", autor=" + autor + ", tema=" + tema + '}';
    }
    
}
