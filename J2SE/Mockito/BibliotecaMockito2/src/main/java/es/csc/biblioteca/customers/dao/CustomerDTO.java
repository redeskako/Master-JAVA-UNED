package es.csc.biblioteca.customers.dao;

import java.sql.Date;
import java.util.Objects;

/**
 * Envoltorio para una fila de la tabla Socios.
 * 
 * @author David Atencia
 * @version 0.1
 */
public class CustomerDTO {
    
    protected int idSocio;
    protected String dni;
    protected String nombre;
    protected String apellidos;
    protected String direccion;
    protected Date fechaAlta;
    
    protected boolean idSocioModified = false;
    protected boolean dniModified = false;
    protected boolean apellidosModified = false;
    protected boolean nombreModified = false;
    protected boolean direccionModified = false;
    protected boolean fechaAltaModified = false;

    public CustomerDTO() {
    }

    public int getIdSocio() {
        return this.idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
        this.idSocioModified = true;
    }

    public boolean isIdSocioModified() {
        return this.idSocioModified;
    }

    public void setIdSocioModified(boolean idSocioModified) {
        this.idSocioModified = idSocioModified;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
        this.dniModified = true;
    }

    public boolean isDniModified() {
        return this.dniModified;
    }

    public void setDniModified(boolean dniModified) {
        this.dniModified = dniModified;
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

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
        this.apellidosModified = true;
    }

    public boolean isApellidosModified() {
        return this.apellidosModified;
    }

    public void setApellidosModified(boolean apellidosModified) {
        this.apellidosModified = apellidosModified;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        this.direccionModified = true;
    }

    public boolean isDireccionModified() {
        return this.direccionModified;
    }

    public void setDireccionModified(boolean direccionModified) {
        this.direccionModified = direccionModified;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
        this.fechaAltaModified = true;
    }

    public boolean isFechaAltaModified() {
        return this.fechaAltaModified;
    }

    public void setFechaAltaModified(boolean fechaAltaModified) {
        this.fechaAltaModified = fechaAltaModified;
    }
    
    public String getFullName() {
        return this.nombre + " " + this.apellidos;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.idSocio;
        hash = 43 * hash + Objects.hashCode(this.dni);
        hash = 43 * hash + Objects.hashCode(this.nombre);
        hash = 43 * hash + Objects.hashCode(this.apellidos);
        hash = 43 * hash + Objects.hashCode(this.direccion);
        hash = 43 * hash + Objects.hashCode(this.fechaAlta);
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
        final CustomerDTO other = (CustomerDTO) obj;
        if (this.idSocio != other.idSocio) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "idSocio=" + idSocio + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + ", fechaAlta=" + fechaAlta + '}';
    }
    
}
