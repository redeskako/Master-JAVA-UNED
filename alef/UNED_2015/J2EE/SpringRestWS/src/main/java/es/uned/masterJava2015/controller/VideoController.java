package es.uned.masterJava2015.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

 


import es.uned.masterJava2015.dao.VideosService;
import es.uned.masterJava2015.domain.Videos;

@RestController

public class VideoController {
	 @Autowired
     private VideosService vs;
	@RequestMapping("/video/getInfo/{id}")    
	public Videos getInfoVideo(@PathVariable String id) { 		
		Videos video = vs.getVideoById(id);			
		return video;    
		}

}
