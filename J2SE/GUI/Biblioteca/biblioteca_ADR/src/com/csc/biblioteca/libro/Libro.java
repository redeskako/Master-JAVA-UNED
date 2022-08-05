/**
 * Gestión básica de una biblioteca.
 * @author Antonio Dorado
 * @version 1.5 - 04/11/2015
 */

package com.csc.biblioteca.libro;

import java.util.Objects;

/** Clase de datos para la gestión de un libro de la biblioteca. */
public class Libro implements Comparable<Libro> {
    private Integer idLibro;
    private String titulo;
    private String autor;
    private String tema;

    /** Constructor vacío de un libro. */
    public Libro() {}
    
    /**
     * Constructor de un libro.
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param tema Tema en que se agrupa el libro.
     */
    public Libro(String titulo, String autor, String tema) {
        this.titulo = titulo;
        this.autor = autor;
        this.tema = tema;
    }
    
    /**
     * Obtener el identificador del libro.
     * @return Identificador del libro.
     */
    public Integer getIdLibro() {return idLibro;}

    /**
     * Establecer el identificador del libro.
     * @param idLibro Identificador del libro.
     */
    public void setIdLibro(Integer idLibro) {this.idLibro = idLibro;}

    /**
     * Obtener el título del libro.
     * @return Título del libro.
     */
    public String getTitulo() {return titulo;}

    /**
     * Establecer el título del libro.
     * @param titulo Título del libro.
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Obtener el autor del libro.
     * @return Autor del libro.
     */
    public String getAutor() {return autor;}

    /**
     * Establecer el autor del libro.
     * @param autor Autor del libro.
     */
    public void setAutor(String autor) {this.autor = autor;}

    /**
     * Obtener el tema del libro.
     * @return Tema del libro.
     */
    public String getTema() {return tema;}

    /**
     * Establecer el tema del libro.
     * @param tema Tema del libro.
     */
    public void setTema(String tema) {this.tema = tema;}

    @Override
    public String toString() {
        return "Libro{" + idLibro + "|" + titulo + "|" + autor + "|" + tema + "}";
    }

    @Override
    public int compareTo(Libro libro) {
        return this.titulo.compareToIgnoreCase(libro.getTitulo());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idLibro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final Libro other = (Libro) obj;
        return Objects.equals(this.idLibro, other.idLibro);
    }
}
