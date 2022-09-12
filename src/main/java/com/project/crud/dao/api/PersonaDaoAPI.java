package com.project.crud.dao.api;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.project.crud.model.Persona;

public interface PersonaDaoAPI extends CrudRepository<Persona, Long> {

	List<Persona> getAll();

	Persona get(Long id);

	void delete(Long id);
}