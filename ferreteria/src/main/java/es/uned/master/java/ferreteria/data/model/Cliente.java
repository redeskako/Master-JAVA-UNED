package es.uned.master.java.ferreteria.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cliente_id")
    private long id;
    @Column(name="nombre", nullable=false, unique=true)
    private String nombre;
    @Column(name="contacto", nullable=false)
    private String contacto;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="telefono")
    private String telefono;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(this.nombre).append('\'');
        sb.append(", contacto='").append(this.contacto).append('\'');
        sb.append(", email='").append(this.email).append('\'');
        sb.append(", teléfono='").append(this.telefono).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
