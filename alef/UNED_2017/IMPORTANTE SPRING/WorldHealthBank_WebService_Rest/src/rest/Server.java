package rest;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JSONProvider;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import dao.CountryDao;
import dao.CountryDaoI;
import dao.UserDaoI;
import dao.UserDao;

public class Server {

	public Server() throws Exception {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses( CountryDaoI.class );
		//sf.setResourceClasses( CountryDaoI.class, UserDaoI.class );
		sf.setResourceProvider( CountryDaoI.class, new SingletonResourceProvider( new CountryDao() ) );
		//sf.setResourceProvider( UserDaoI.class, new SingletonResourceProvider( new UserDao() ) );
		//Añado el jsonProvider para trabajar con JSON
		sf.setProvider(new JacksonJsonProvider());
		sf.setAddress( "http://localhost:9001/" );
		 
		sf.create();
	}
}
