package uned.java2016.apitwitter.services.rs.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import uned.java2016.apitwitter.dao.*;

/**
 * EntityProvider para deseralizar Tweets. Este Provider se inyecta en el cliente Jersey durante
 * la configuracion (metodo JAXRSClient.initialize) y se activa cuando se solicita 
 * recuperar una instancia de 'Tweet' de la respuesta obtenida de la llamada al WS 
 * (metodo JAXRSClient.getById)
 * @author José Barba Martínez (jbarba63)
 *
 */

public class TweetMessageBodyReader implements MessageBodyReader<Tweet> {

	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return type==Tweet.class;
	}

	@Override
	public Tweet readFrom(Class<Tweet> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream entityStream) throws IOException, WebApplicationException {
		try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(Tweet.class);	        
	        JAXBElement<Tweet> element=jaxbContext.createUnmarshaller()	        		
		            .unmarshal((Source) new StreamSource(entityStream),Tweet.class);	        
	        return element.getValue();
	    } catch (JAXBException jaxbException) {
	        throw new ProcessingException("Error des-serializando una instancia de Tweet.",
	            jaxbException);
	    }
	}

}
