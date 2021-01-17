package com.uned.worldhealthbank.migracion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uned.worldhealthbank.domain.Country;
import com.uned.worldhealthbank.domain.Data;
import com.uned.worldhealthbank.domain.Health;
import com.uned.worldhealthbank.domain.User;
import com.uned.worldhealthbank.repository.CountryRepository;
import com.uned.worldhealthbank.repository.DataRepository;
import com.uned.worldhealthbank.repository.HealthRepository;
import com.uned.worldhealthbank.repository.RolRepository;
import com.uned.worldhealthbank.repository.Rol_UserRepository;
import com.uned.worldhealthbank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;



@RestController
@RequestMapping("/worldhealthbank")
public class MigracionController {
	
		
	@Inject
	private CountryRepository countryRepository;

	@Inject
	private Country_OldRepository country_OldRepository;
	
	@Inject
	private Health_OldRepository health_OldRepository;

	@Inject
	private HealthRepository healthRepository;
	
	@Inject
	private Data_OldRepository data_OldRepository;

	@Inject
	private DataRepository dataRepository;
	
	@RequestMapping(value = "/migracion",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void migrar() {
		
		//MIGRAR COUNTRY
		
		//Borrar toda la tabla de COUNTRY
		for(Country country : countryRepository.findAll()) {
			countryRepository.delete(country);
		}
		
		//Copia la tabla COUNTRY
		for(Country_Old country_Old : country_OldRepository.findAll()) {
			Country country = new Country();
			country.setCountryCode(country_Old.getCountryCode());
			country.setCountryName(country_Old.getCountryName());
			countryRepository.save(country);
		}
		
		//MIGRAR HEALTH
		
		//Borrar toda la tabla de HEALTH
		for(Health health : healthRepository.findAll()) {
			healthRepository.delete(health);
		}
		
		//Copia la tabla HEALTH
		for(Health_Old health_Old : health_OldRepository.findAll()) {
			Health health = new Health();
			health.setIndicadorCode(health_Old.getIndicadorCode());
			health.setIndicadorName(health_Old.getIndicadorName());
			health.setSourceNote(health_Old.getSourceNote());
			health.setSourceOrganization(health_Old.getSourceOrganization());
			healthRepository.save(health);
		}
		
		
		//MIGRAR DATA
		
		//Borrar toda la tabla de DATA
		for(Data data : dataRepository.findAll()) {
			dataRepository.delete(data);
		}
		
		//Copia la tabla HEALTH
		for(Data_Old data_Old : data_OldRepository.findAll()) {
			Data data = new Data();
			data.setCountry(countryRepository.findByCountryCode(data_Old.getData_Old_Id().getCountryCode()));
			data.setHealth(healthRepository.findByIndicadorCode(data_Old.getData_Old_Id().getIndicadorCode()));
			data.setYear(data_Old.getData_Old_Id().getYear());
			data.setPercentage(data_Old.getPercentage());
			dataRepository.save(data);
		}
				
				
				
				
				
		
				System.err.println("----------------------------FIN---------------------------------");
		
	    	
	}

}
