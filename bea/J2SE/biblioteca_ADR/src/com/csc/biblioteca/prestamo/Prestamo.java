/**
 * Clase de datos de un préstamo.
 * @author Antonio Dorado
 * @version 1.2 - 16/12/2015
 */

package com.csc.biblioteca.prestamo;

import com.csc.biblioteca.libro.Libro;
import com.csc.biblioteca.socio.Socio;
import java.sql.Date;

public class Prestamo {
    private Integer idPrestamo;
    private Socio socio;
    private Libro libro;
    private Date fechaInicio;
    private Date fechaFin;
    
    public Prestamo() {}
         
    public Prestamo(Socio socio, Libro libro, Date fechaInicio, Date fechaFin) {
        this.socio = socio;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public Integer getIdPrestamo() {return idPrestamo;}
    
    public void setIdPrestamo(Integer idPrestamo) {this.idPrestamo = idPrestamo;}
    
    public Socio getSocio() {return socio;}
    
    public void setSocio(Socio socio) {this.socio = socio;}
    
    public Libro getLibro() {return libro;}
    
    public void setLibro(Libro libro) {this.libro = libro;}
    
    public Date getFechaInicio() {return fechaInicio;}
    
    public void setFechaInicio(Date fechaInicio) {this.fechaInicio = fechaInicio;}
    
    public Date getFechaFin() {return fechaFin;}
    
    public void setFechaFin(Date fechaFin) {this.fechaFin = fechaFin;}
    
    
    public Integer getIdSocio() {return (socio != null ? socio.getIdSocio() : null);}
    
    public Integer getIdLibro() {return (libro != null ? this.libro.getIdLibro() : null);}
    
    public String getApellidoNombreSocio() {return socio.getApellidos()+", "+socio.getNombre();}
    
    public String getTituloLibro() {return libro.getTitulo();}
    
    @Override
    public String toString() {
        return "Prestamo{" + idPrestamo + "|" + socio + "|" + libro + "|" +
                fechaInicio + "|" + fechaFin + "}";
    }
}
