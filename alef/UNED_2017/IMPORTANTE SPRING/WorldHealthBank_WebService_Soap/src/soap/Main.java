package soap;

import dao.Dao;
import model.User;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Main {
	
	public static void main(String[] args) {
		
		Dao dao = new Dao();	

		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		//No he conseguido tener dos clases asociadas en JaxWsServerFactoryBean!!
		//factory.setServiceClass(UserDao.class);
		//factory.setServiceClass(CountryDao.class);
		factory.setServiceClass(Dao.class);
		factory.setAddress("http://localhost:9000/HealthWorldBanck");
		factory.setServiceBean(dao);
		factory.create();

		System.out.println("Server ready...");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "Server exiting" );
		System.exit( 0 );
	}

}
