package es.csc.biblioteca.loans.dao;

import es.csc.biblioteca.books.dao.BookDTO;
import es.csc.biblioteca.customers.dao.CustomerDTO;
import java.sql.Date;
import java.util.Objects;

/**
 * Envoltorio para una fila de la tabla Prestamos.
 * 
 * @author David Atencia
 * @version 0.1
 */
public class LoanDTO {
    
    private int idPrestamo;
    private Date fechaInicio;
    private Date fechaFin;
    private CustomerDTO customer;
    private BookDTO book;

    protected boolean idPrestamoModified = false;
    protected boolean idSocioModified = false;
    protected boolean idLibroModified = false;
    protected boolean fechaInicioModified = false;
    protected boolean fechaFinModified = false;

    public LoanDTO() {
    }

    public int getIdPrestamo() {
        return this.idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
        this.idPrestamoModified = true;
    }

    public boolean isIdPrestamoModified() {
        return this.idPrestamoModified;
    }

    public void setIdPrestamoModified(boolean idPrestamoModified) {
        this.idPrestamoModified = idPrestamoModified;
    }

    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
        this.idSocioModified = true;
    }

    public boolean isIdSocioModified() {
        return this.idSocioModified;
    }

    public void setIdSocioModified(boolean idSocioModified) {
        this.idSocioModified = idSocioModified;
    }

    public BookDTO getBook() {
        return this.book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
        this.idLibroModified = true;
    }

    public boolean isIdLibroModified() {
        return this.idLibroModified;
    }

    public void setIdLibroModified(boolean idLibroModified) {
        this.idLibroModified = idLibroModified;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.fechaInicioModified = true;
    }

    public boolean isFechaInicioModified() {
        return this.fechaInicioModified;
    }

    public void setFechaInicioModified(boolean fechaInicioModified) {
        this.fechaInicioModified = fechaInicioModified;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
        this.fechaFinModified = true;
    }

    public boolean isFechaFinModified() {
        return this.fechaFinModified;
    }

    public void setFechaFinModified(boolean fechaFinModified) {
        this.fechaFinModified = fechaFinModified;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idPrestamo;
        hash = 61 * hash + Objects.hashCode(this.fechaInicio);
        hash = 61 * hash + Objects.hashCode(this.fechaFin);
        hash = 61 * hash + Objects.hashCode(this.customer);
        hash = 61 * hash + Objects.hashCode(this.book);
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
        final LoanDTO other = (LoanDTO) obj;
        if (this.idPrestamo != other.idPrestamo) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        return Objects.equals(this.book, other.book);
    }

    @Override
    public String toString() {
        return "PrestamoDTO{" + "idPrestamo=" + idPrestamo + ", idSocio=" + customer.toString() + ", idLibro=" + book.toString() + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }
    
}
