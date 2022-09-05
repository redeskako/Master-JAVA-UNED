package com.uned.springmvcrestclient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.uned.springmvcrestclient.model.Video;

/**
 * Clase controladora.
 */

@Controller // Indica que la clase actúa como controlador.
@RequestMapping("/") // Controlador por defecto.
public class AppController
{ 
    // Mensajes de texto.
    @Autowired
    MessageSource messageSource;

    /*
     * Cada método detalla una acción a gestionar por el controlador. Asociada a cada uno de ellosva una directiva @RequestMapping
     * que indica el patrón de url al que va a responder el método en cuestión.
     */

    /**
     * Presenta la pantalla de login.
     */
    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String login(Video video, ModelMap model)
    {
		model.addAttribute("mostrarDatos", "false");
    	return "index";
    }
    
    /**
     * Pide al WS los datos del vídeo asociados al id introducido por el usuario.
     */
	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String getVideoData(@Valid Video video, BindingResult result, ModelMap model)
    {
    	// Si hay errores de validación.
    	if(result.hasErrors())
    		model.addAttribute("mostrarDatos", "false");

    	else
    	{
	    	// Crea la lista de convertidores de mensajes y le añade el Jackson.
		    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		    messageConverters.add(new MappingJackson2HttpMessageConverter());
			    	
		    // Formato de petición al WS.
		    final String uri = "http://localhost:8080/SpringRestWS/video/getInfo/" + video.getId();
			
		    // Obtiene los datos del vídeo.
		    RestTemplate restTemplate = new RestTemplate();
		    restTemplate.setMessageConverters(messageConverters);
		    Video retVideo = restTemplate.getForObject(uri, Video.class);
			
		    // Si el video no se encuentra registrado en la BD.
		    if (retVideo.getTitulo().contains("no registrado"))
		    {
		    	model.addAttribute("mostrarDatos", false);
		    	model.addAttribute("mensaje", "Video no registrado en la BD local.");
		    }
		    else
		    {
		    	model.addAttribute("mostrarDatos", true);
		    	model.addAttribute("video", retVideo);
		    }
    	}

		return "index";
    }
}