package es.uned.master.java.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.uned.master.java.springboot.model.Reunion;
import es.uned.master.java.springboot.repositorios.ReunionesRepository;

@Service
public class ReunionesService {
	private ReunionesRepository reunionesRepository;
	
	public ReunionesService(ReunionesRepository reunionesRepository) {
		this.reunionesRepository = reunionesRepository;
	}
	public List<Reunion> getAllReuniones(){
		return this.reunionesRepository.findAll();
	}
}
