package es.uned.masterJava2015.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc; 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "es.uned.masterJava2015")
public class VideoControllerConfiguration {

}
