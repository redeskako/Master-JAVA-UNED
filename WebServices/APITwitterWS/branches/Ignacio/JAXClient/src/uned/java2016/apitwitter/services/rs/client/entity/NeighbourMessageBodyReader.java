package uned.java2016.apitwitter.services.rs.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

import uned.java2016.apitwitter.services.rs.jaxb.Neighbours;
import uned.java2016.apitwitter.services.rs.jaxb.Tweets;

public class NeighbourMessageBodyReader implements MessageBodyReader<List<String>> 
{

	/**
	 * Forzamos a que este reader sea el que recupere las instancias de List<String>.
	 * @return true si type es una instancia de ParameterizedType con el generico List<String> 
	 */
	@Override
	public boolean isReadable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		boolean ret=false;
		if(arg1 instanceof ParameterizedType)
		{
			ParameterizedType pt=(ParameterizedType) arg1;
			ret=pt.getActualTypeArguments()[0].equals(String.class)
					&& pt.getRawType().equals(List.class);
		}
		return ret;
	}

	/**
	 * Implementamos la de-serializacion de una lista de Vecinos (Strings).
	 */
	@Override
	public List<String> readFrom(Class<List<String>> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream entityStream) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(Neighbours.class);	        
	        JAXBElement<Neighbours> element=jaxbContext.createUnmarshaller()	        		
		            .unmarshal((Source) new StreamSource(entityStream),Neighbours.class);	        
	        return element.getValue().getNeighbours();
	    } catch (JAXBException jaxbException) {
	        throw new ProcessingException("Error des-serializando una lista de Vecinos.",
	            jaxbException);
	    }
	}



}
