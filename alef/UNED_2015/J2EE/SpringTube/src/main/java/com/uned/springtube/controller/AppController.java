package com.uned.springtube.controller;

import java.util.List;
import java.lang.Math; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uned.springtube.model.Usuario;
import com.uned.springtube.model.Canal;
import com.uned.springtube.model.Videos;
import com.uned.springtube.service.CanalService;
import com.uned.springtube.service.UsuarioService;
import com.uned.springtube.service.VideoService;


/**
 * Clase controladora.
 */
@Controller // Indica que la clase act�a como controlador.
@RequestMapping("/") // Controlador por defecto.
public class AppController
{ 
	// Interface bo.
    @Autowired
    UsuarioService service;
    
    @Autowired
    CanalService servicec;
   
    @Autowired
    VideoService videoservice;

    // Mensajes de texto.
    @Autowired
    MessageSource messageSource;

    /*
     * Cada m�todo detalla una acci�n a gestionar por el controlador. Asociada a cada uno de ellosva una directiva @RequestMapping
     * que indica el patr�n de url al que va a responder el m�todo en cuesti�n.
     * Aqu� se detallar�an las operaciones para insertar canal, listar canales, listar videos de canal, visualizar video, ...
     */

    /**
     * Presenta la pantalla de login.
     */
    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String login(ModelMap model)
    {
    	model.addAttribute("usuario", new Usuario());
    	return "index";
    }

    /**
     * Autentifica al usuario.
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.POST)
    public String saveEmployee(@Valid Usuario usuario, BindingResult result, ModelMap model)
    {
    	// Si hay errores de validaci�n.
        if (result.hasErrors())
        {
        	// Se vuelve a mostrar la pantalla de login.
            return "index";
        }

        // Si no hay errores, comprueba si el usuario tiene acceso.
        // En realidad para mostrar un texto est�tico no ser�a necesario pasarlo como atributo, pero es para probar.
        if (service.esUsuarioAutorizado(usuario.getUser(), usuario.getPassword()))
        {
        	if (usuario.getUser().equals("adm"))
        	{   
        		//El usuario logeado es el adm para a�adir nuevos canales
        	    model.addAttribute("canal", new Canal()); 
        	    model.addAttribute("mensaje", "");
     		    return "vista_admin";
        		
        	}
        	else
        	{   
        		//El usuario logeado es el de visualizacion de canales
        		//Obtenemos los canales de la BD y los pasamos a la vista
        		 List<Canal> canales= servicec.getCanales();
        	    //model.put("canales",servicec.getCanales());
        	    model.put("canales",canales);        	    
        		model.addAttribute("mensaje", "El usuario ha sido autenticado correctamente");
        		return "vista_canales";
        	}
        }
        else
        {  
        	model.addAttribute("mensaje", "El usuario y/o la contrase�a son incorrectos");
        	return "login_error";
        }
    }
    //Implemtacion de la accion de insertar un nuevo canal en la BF
    @RequestMapping(value = {"/addCanal" }, method = RequestMethod.POST)
    public  String addCanal(@Valid Canal canal, BindingResult result, ModelMap model)
    {      	 
    	servicec.add(canal);
    	model.addAttribute("mensaje", "El canal " + canal.getId() + " ha sido registrado correctamente");
    	model.addAttribute("canal", new Canal());
    	return "vista_admin";
    }
     
    /**
     * Lista los videos de un canal.
      */
    @RequestMapping(value = { "/videos" }, method = RequestMethod.GET) // Mapea las direcciones / (p�gina por defecto) y /list.
    public String listVideos(ModelMap modelvideos, @Valid Videos canales, BindingResult result, @RequestParam("pag") String pag, @RequestParam("id_canal") String id_canal)
    {    	
    	// Desde el controlador se invoca a los m�todos service y desde estos a los dao.
    	modelvideos.addAttribute("id_canal", id_canal);
    	modelvideos.addAttribute("pag", pag);
    	modelvideos.addAttribute("canales", new Videos());
    	List<Videos> videos = videoservice.ListAllVideos(canales.getid_canal(), pag);

        // Estos atributos a�adidos al model son a los que luego, en las p�ginas .jsp, se accede con ${atributo}.
        // Habr�a que incluir como atributo el bean de vista con los datos formateados en lugar de directamente la entidad.
        modelvideos.addAttribute("videos", videos);
        return "vista_videos"; // P�gina a presentar a continuaci�n.
    }
    
    /**
     * Lista los videos de la páguina anterior.
      */
    @RequestMapping(value = { "/pagant" }, method = RequestMethod.GET) // Mapea las direcciones / (p�gina por defecto) y /list.
    public String listpagant(ModelMap modelvideos, @Valid Videos canales, BindingResult result, @RequestParam("pag") String pag, @RequestParam("id_canal") String id_canal)
    {    	
    	//Calcula el número de pag
    	int pagant;
    	int auxpag = Integer.parseInt(pag);
    	if (auxpag == 1) {
    		pagant = 1;
    	} else {
    		pagant = auxpag - 1;
    	}
    	//Convierte entero a String
    	pag= Integer.toString(pagant);
    	
    	// Desde el controlador se invoca a los m�todos service y desde estos a los dao.
    	modelvideos.addAttribute("id_canal", id_canal);
    	modelvideos.addAttribute("pag", pagant);
    	modelvideos.addAttribute("canales", new Videos());
    	List<Videos> videos = videoservice.ListAllVideos(canales.getid_canal(), pag);

        // Estos atributos a�adidos al model son a los que luego, en las p�ginas .jsp, se accede con ${atributo}.
        // Habr�a que incluir como atributo el bean de vista con los datos formateados en lugar de directamente la entidad.
        modelvideos.addAttribute("videos", videos);
        return "vista_videos"; // P�gina a presentar a continuaci�n.
    }
    
    /**
     * Lista los videos de la páguina siguiente.
      */
    @RequestMapping(value = { "/pagsig" }, method = RequestMethod.GET) // Mapea las direcciones / (p�gina por defecto) y /list.
    public String listpagsig(ModelMap modelvideos, @Valid Videos canales, BindingResult result, @RequestParam("pag") String pag, @RequestParam("id_canal") String id_canal)
    {    	
    	//Calcula el número de pag
    	int pagsig;
    	int auxpag = Integer.parseInt(pag);
    	int numdepag = videoservice.getNumPag(id_canal);
    	int numdepagsup = (int) Math.ceil ( numdepag );
    	//Caso que el número de videos del canal sea menor que el número de videos por pág asignamos valor 1 al numero de páguinas a listar.
    	if (numdepagsup == 0) {
    		numdepagsup = 1;
    		} 
    	if (auxpag == numdepagsup) {
    		pagsig = numdepagsup;
    	} else {
    		pagsig = auxpag + 1;
    	}
    	//Convierte entero a String
    	pag= Integer.toString(pagsig);
    	
    	// Desde el controlador se invoca a los m�todos service y desde estos a los dao.
    	modelvideos.addAttribute("id_canal", id_canal);
    	modelvideos.addAttribute("pag", pagsig);
    	modelvideos.addAttribute("canales", new Videos());
    	List<Videos> videos = videoservice.ListAllVideos(canales.getid_canal(), pag);

        // Estos atributos a�adidos al model son a los que luego, en las p�ginas .jsp, se accede con ${atributo}.
        // Habr�a que incluir como atributo el bean de vista con los datos formateados en lugar de directamente la entidad.
        modelvideos.addAttribute("videos", videos);
        return "vista_videos"; // P�gina a presentar a continuaci�n.
    }

    /**
     * Presenta la pantalla de los canales.
     */
    @RequestMapping(value = { "/canales"}, method = RequestMethod.GET)
    public String canales(ModelMap model)
    {
		//Obtenemos los canales de la BD y los pasamos a la vista
		 List<Canal> canales= servicec.getCanales();
	    //model.put("canales",servicec.getCanales());
	    model.put("canales",canales); 
    	return "vista_canales";
    }
 
    /**
     * Presenta un video.
     */
    @RequestMapping(value = { "/muestra_video"}, method = RequestMethod.GET)
    public String videos(@RequestParam("id") String id, ModelMap model)
    {
    	model.addAttribute("id", id);
    	return "muestra_video";
    }
     
     
}