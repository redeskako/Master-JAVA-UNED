package com.uned.worldhealthbank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uned.worldhealthbank.domain.Country;
import com.uned.worldhealthbank.domain.User;
import com.uned.worldhealthbank.repository.CountryRepository;
import com.uned.worldhealthbank.repository.UserRepository;
import com.uned.worldhealthbank.servicio.MailController;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/worldhealthbank")
public class CountryResource {
	
	@Inject
    private CountryRepository countryRepository;
	

	
	
	@RequestMapping(value = "/countries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getAllCountries() {
		
		return countryRepository.findAll();
	}
	
}
