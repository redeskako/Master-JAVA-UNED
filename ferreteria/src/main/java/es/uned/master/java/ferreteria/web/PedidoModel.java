package es.uned.master.java.ferreteria.web;

import lombok.Data;

@Data
public class PedidoModel {
    private long pedidoId;
    private String cliente;
    private long clienteId;
    private String detalleCliente;

    public PedidoModel() {
    }
}
