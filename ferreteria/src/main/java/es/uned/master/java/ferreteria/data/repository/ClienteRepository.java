package es.uned.master.java.ferreteria.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.uned.master.java.ferreteria.data.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
