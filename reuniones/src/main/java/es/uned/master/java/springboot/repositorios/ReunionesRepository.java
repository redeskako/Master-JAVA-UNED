package es.uned.master.java.springboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uned.master.java.springboot.model.Reunion;

public interface ReunionesRepository extends JpaRepository<Reunion, Long> {
}
