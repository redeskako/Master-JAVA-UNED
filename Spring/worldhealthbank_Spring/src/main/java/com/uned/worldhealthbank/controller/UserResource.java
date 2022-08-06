package com.uned.worldhealthbank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uned.worldhealthbank.domain.User;
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
public class UserResource {
	
	@Inject
    private UserRepository userRepository;
	
	@Inject 
	private MailController mailController;
	
	
	@RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/user/{login}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getLogin(@PathVariable String login) {
        
			try {
				User user = userRepository.findByLogin(login);
				if(user.getLogin().contentEquals(login))
					return true;
				else
					return false;
			}catch(Exception e) {
				return false;
			}
	    }
	

	//Crear usuario
	@RequestMapping(value = "/userSave",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean createAlarma(@RequestBody User user) throws URISyntaxException, MessagingException {
        user.setEstado(false);
		userRepository.save(user);
		mailController.sendMail(user);
        return true;
    	}
	
	
	//Activar usuario
	@RequestMapping(value = "/activarUser/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView activarUser(@PathVariable Long id) {
		
        User user = userRepository.findOne(id);
        user.setEstado(true);
        userRepository.save(user);
        return new ModelAndView("redirect:" + "http://localhost:8080/" );
    	}
	
	//Login
	
	}
