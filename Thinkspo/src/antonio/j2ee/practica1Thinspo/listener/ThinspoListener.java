package antonio.j2ee.practica1Thinspo.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 * Listener que obtiene el dataSource y lo mete en el contexto
 * @author  Antonio Sánchez Antón
 * @since  1.0
 * @see javax.servlet.ServletContextListener
 *
 */
public class ThinspoListener implements ServletContextListener {
    public static final String DATASOURCEJNDI="dataSourceJNDI";
	protected Context contexto;  
	
    /**
     * Llamado cuando se inicializa el contexto del Servlet
     */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext=sce.getServletContext();
        String dataSourceJNDI=servletContext.getInitParameter(DATASOURCEJNDI);
        try  {
                contexto=new InitialContext();
                DataSource dataSource=(DataSource)contexto.lookup(dataSourceJNDI);
                if(dataSource==null)
                {
                	System.out.println("No hay DataSource definido");
                	throw new RuntimeException("No hay DataSource definido");
                }
                servletContext.setAttribute("dataSource",dataSource);
                System.out.println("DataSource obtenido");
         }
         catch(NamingException e)
         {
                throw new RuntimeException(e.getMessage());
         }
         finally {
                try {
                   //cerrar el contexto
                   if(contexto!=null) {
                              contexto.close();
                   }
                 }catch(Exception e) {
                        System.out.println("¡Error en inicializarContexto!");
                        throw new RuntimeException("¡Error en inicializarContexto!");
                 }
        }

	}

    /**
     * Llamado cuando se destruye el contexto del Servlet
     */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
        try {
            //cerrar el contexto
            if(contexto!=null) {
                       contexto.close();
            }
          }catch(Exception e) {
                 System.out.println("¡Error en destruirContexto!");
                 throw new RuntimeException("¡Error en destruirContexto!");
          }

	}
	
}
