package es.uned.masterJava2015.configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class VideoInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override    
	protected Class<?>[] getRootConfigClasses() {        
		return new Class[] { VideoControllerConfiguration.class };    
		}      
	
	@Override    
	protected Class<?>[] getServletConfigClasses() {        
		return null;    
	}  
	
	@Override    
	protected String[] getServletMappings() {        
		return new String[] { "/" };    
	}
}
