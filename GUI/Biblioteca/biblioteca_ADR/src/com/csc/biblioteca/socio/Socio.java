/**
 *
 * @author Antonio Dorado
 */

package com.csc.biblioteca.socio;

import java.sql.Date;
import java.util.Objects;

public class Socio implements Comparable<Socio> {
    private Integer idSocio;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private Date fechaAlta;

    public Socio() {
        this.idSocio = null;
        this.fechaAlta = null;
    }

    public Socio(String nombre, String apellidos, String dni, String direccion, Date fechaAlta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
    }
    
    public Integer getIdSocio() {return idSocio;}
    
    public void setIdSocio(Integer idSocio) {this.idSocio = idSocio;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellidos() {return apellidos;}

    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public Date getFechaAlta() {return fechaAlta;}

    public void setFechaAlta(Date fechaAlta) {this.fechaAlta = fechaAlta;}

    @Override
    public String toString() {
        return "Socio{" + idSocio + "|" + dni + "|" + nombre + "|" +
                apellidos + "|" + direccion + "|" + fechaAlta + "}";
//        return this.apellidos+", "+this.nombre;
    }

    @Override
    public int compareTo(Socio socio) {
        String texto = this.apellidos+", "+this.nombre;
        
        return texto.compareToIgnoreCase(socio.getApellidos()+", "+socio.getNombre());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idSocio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        final Socio other = (Socio) obj;
        return Objects.equals(this.idSocio, other.idSocio);
    }
}
