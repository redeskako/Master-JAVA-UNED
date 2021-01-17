package uned.java2016.apitwitter.services.rs.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import uned.java2016.apitwitter.dao.*;
import uned.java2016.apitwitter.services.rs.jaxb.Tweets;


/**
 * Esta clase de EntityProvider permite hacer un 'unmarshall' de una lista de Tweets.
 * Quien lanza la ejecucion de este Provider es el cliente cuando se intenta recuperar
 * @author José Barba Martínez (jbarba63)
 *
 */
public class TweetsMessageBodyReader implements MessageBodyReader<List<Tweet>> 
  {

	/**
	 * Forzamos a que este reader sea el que recupere las instancias de List<Tweet>.
	 * @return true si type es una instancia de ParameterizedType con el generico List<Tweet> 
	 */
	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		boolean ret=false;
		if(arg1 instanceof ParameterizedType)
		{
			ParameterizedType pt=(ParameterizedType) arg1;
			ret=pt.getActualTypeArguments()[0].equals(Tweet.class)
					&& pt.getRawType().equals(List.class);
		}
		return ret;
	}

	/**
	 * Implementamos la de-serializacion de una lista de Tweets.
	 */
	@Override
	public List<Tweet> readFrom(Class<List<Tweet>> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream entityStream) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(Tweets.class);	        
	        JAXBElement<Tweets> element=jaxbContext.createUnmarshaller()	        		
		            .unmarshal((Source) new StreamSource(entityStream),Tweets.class);	        
	        return element.getValue().getTweets();
	    } catch (JAXBException jaxbException) {
	        throw new ProcessingException("Error des-serializando una lista de Tweets.",
	            jaxbException);
	    }
	}



}
