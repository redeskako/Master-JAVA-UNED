/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author carlosl.sanchez
 */
@Entity
public class NoticiaEntidad implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String noticia;
    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoticiaEntidad)) {
            return false;
        }
        NoticiaEntidad other = (NoticiaEntidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.NoticiaEntidad[id=" + id + "]";
    }

}
