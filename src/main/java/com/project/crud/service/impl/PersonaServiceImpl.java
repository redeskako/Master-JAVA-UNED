package com.project.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.project.crud.commons.GenericServiceImpl;
import com.project.crud.dao.api.PersonaDaoAPI;
import com.project.crud.model.Persona;
import com.project.crud.service.api.PersonaServiceAPI;

@Service
public class PersonaServiceImpl extends GenericServiceImpl <Persona, Long> implements PersonaServiceAPI {

	@Autowired
	private PersonaDaoAPI personaDaoAPI;

	@Override
	public CrudRepository<Persona, Long> getDao() {
		return personaDaoAPI;
	}
}