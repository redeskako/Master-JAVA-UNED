package com.uned.springmvcrestclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuración de la aplicación.
 */

@Configuration // Indica que se trata de una clase de configuración que va a definir managed beans.
@EnableWebMvc // Habilita Modelo Vista Controlador.
@ComponentScan(basePackages = "com.uned.springmvcrestclient") // Paquete raíz donde buscar los managed beans.
public class AppConfig
{
	/**
	 * Compone el nombre de la página a presentar añadiendo un prefijo y un sufijo.
	 * @return página a presentar.
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