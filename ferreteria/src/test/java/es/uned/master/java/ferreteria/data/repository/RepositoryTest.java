package es.uned.master.java.ferreteria.data.repository;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.master.java.ferreteria.data.model.Cliente;
import es.uned.master.java.ferreteria.data.model.Pedido;
import es.uned.master.java.ferreteria.data.repository.ClienteRepository;
import es.uned.master.java.ferreteria.data.repository.PedidoRepository;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testGetAllPerdidos(){
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        assert(IterableUtils.size(pedidos)==3);
    }

    @Test
    public void testGetAllClientes(){
        Iterable<Cliente> clientes = clienteRepository.findAll();
        assert(IterableUtils.size(clientes)==7);
    }
}
