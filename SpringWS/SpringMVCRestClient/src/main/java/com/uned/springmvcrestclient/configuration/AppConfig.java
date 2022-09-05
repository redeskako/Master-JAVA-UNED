package com.uned.springmvcrestclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuraci�n de la aplicaci�n.
 */

@Configuration // Indica que se trata de una clase de configuraci�n que va a definir managed beans.
@EnableWebMvc // Habilita Modelo Vista Controlador.
@ComponentScan(basePackages = "com.uned.springmvcrestclient") // Paquete ra�z donde buscar los managed beans.
public class AppConfig
{
	/**
	 * Compone el nombre de la p�gina a presentar a�adiendo un prefijo y un sufijo.
	 * @return p�gina a presentar.
	 */
    @Bean // Managed bean.
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}