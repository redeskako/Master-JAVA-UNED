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
@Table(name="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pedido_id")
    private long id;
    @Column(name="cliente_id", nullable=false)
    private long clienteId;
    @Column(name="pedido_info", nullable=false)
    private String pedidoInfo;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pedido{");
        sb.append("id=").append(id);
        sb.append(", ClienteId=").append(this.clienteId);
        sb.append(", Pedido Info='").append(this.pedidoInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}