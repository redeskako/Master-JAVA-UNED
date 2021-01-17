package com.uned.worldhealthbank.controller;

import com.uned.worldhealthbank.domain.Health;
import com.uned.worldhealthbank.repository.HealthRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;


import java.util.List;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/worldhealthbank")
public class HealthResource {
	
	@Inject
    private HealthRepository healthRepository;
	

	
	
	@RequestMapping(value = "/healths",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Health> getAllhealths() {
		
		System.out.println(healthRepository.findAll());
		return healthRepository.findAll();
		
	}
	
}
