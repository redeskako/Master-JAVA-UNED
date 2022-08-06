package antonio.j2ee.practica1Thinspo.bbdd.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.struts2.ServletActionContext;
/**
 * Clase base para los DAO
 * Implementa la interfaz DAO,dando implementacion para recuperar una conexion del Datasource configurado
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see java.sql.Connection
 * @see javax.sql.DataSource
 * @see antonio.j2ee.practica1Thinspo.bbdd.modelo.DAO
 */
public class ModeloDAO implements DAO {
	//se corresponde con la deefinicion de Datasource en el contexto(server.xml)  
    public static final String DATASOURCE="dataSource"; 
	protected DataSource dataSource;
	
	@Override
    public Connection getConnection() {
	  Connection connection=null;	
      try{
    	  connection=getDatasource().getConnection();
      }catch(SQLException sqle){
    	  //TODO 
    	  sqle.printStackTrace(); 
      }
      return connection;
    }

	
	protected DataSource getDatasource() {
		ServletContext servletContext=ServletActionContext.getServletContext();	
	   //Si el dataSource es null lo cogemos del contexto del Servlet
	   if(dataSource==null) {
	        	dataSource=(DataSource)servletContext.getAttribute(DATASOURCE);
	   }
       return dataSource;
	}

	
	public void setDatasource(DataSource datasource) {
		this.dataSource = datasource;
	}

	

}
