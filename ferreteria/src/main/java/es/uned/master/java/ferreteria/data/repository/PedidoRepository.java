package es.uned.master.java.ferreteria.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.uned.master.java.ferreteria.data.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Iterable<Pedido> findAllByClienteId(long customerId);
}
