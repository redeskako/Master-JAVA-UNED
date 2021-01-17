package es.uned.master.java;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloWorldController {
	String message = "Bienvenido al Spring MVC";
	
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, 
						  defaultValue = "Mundo")
		String name){
		ModelAndView mv  = new ModelAndView("hellowworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
}
