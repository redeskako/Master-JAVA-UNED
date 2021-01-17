package es.csc.biblioteca.jdbc;

import es.csc.biblioteca.util.Config;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author David Atencia & Alberto Gomez Gonzalez
 * @version 0.2
 * 
 * Se añade  posiblidad de ralizar la conexion contra una base de datos oracle
 */
public class ConnectionsManager {
    
    protected static final Config config = Config.getInstance();
    protected static final Logger logger = Logger.getLogger(ConnectionsManager.class);
    
    private static Driver driver = null;
    private static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private static final String JDBC_DRIVER_ORACLE = "oracle.jdbc.OracleDriver";
    private static final String JDBC_PARAMS = "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    public static String dbms;
    /**
     * Obtiene una conexión a la base de datos
     * 
     * @return
     * @throws SQLException 
     */
    public static synchronized Connection getConnection() throws SQLException {
        /* Obtenemos desde el fichero properties los datos de la conexión a la BD
            para seleccionar sobre que tipo de base de datos se trabajara, se
            indicara en el parametro database_dbms las opciones "mysql" u "oracle"
            segun convenga.
            Ademas se da la opcion de poder realizar la conexion a Oracle mediante
            "sid" o "servicename" indicandolo en el parametro database_oracle_tipo
            Por ultimo el valor de este dato se guardara en el parametro 
            "database_oracle_servicio"
        */
        dbms = config.getProperty("database_dbms", "mysql");
        String server = config.getProperty("database_server", "localhost");
        String port = config.getProperty("database_port", "3306");
        String ora_tipo = config.getProperty("database_oracle_tipo", "sid");
        String ora_serv = config.getProperty("database_oracle_servicio", "uned");
        String catalog = config.getProperty("database_catalog", "uned");
        String user = config.getProperty("database_user", "root");
        String password = config.getProperty("database_password", "root");
        String jdbc_driver;  
        String jdbc_url;
        
// Construimos la cadena de conexión a la BD
        if(dbms.equals("mysql")){
            jdbc_driver = JDBC_DRIVER_MYSQL;
            jdbc_url = "jdbc:" + dbms.toLowerCase() + "://" + server + ":" + port + "/" + catalog + JDBC_PARAMS;
        }
        else{ //oralce
            jdbc_driver = JDBC_DRIVER_ORACLE;
            
            if(ora_tipo.equals("sid")){
                jdbc_url ="jdbc:oracle:thin:@" + server + ":" + port +":" + ora_serv;   //sid
            } else{
                jdbc_url ="jdbc:oracle:thin:@//" + server + ":" + port +"/" + ora_serv; //service_name        
            }
        }
        
        // Inicializa la conexión a la BD
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(jdbc_driver);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver( driver );
            } catch (Exception ex) {
                logger.error("Error inicializando el driver JDBC: " + ex.getMessage());
            }
        }
        // Establecemos y devolvemos la conexión
        return DriverManager.getConnection(jdbc_url, user, password);
    }
    
    /**
     * Cierra una conexión a la base de datos
     * 
     * @param conn 
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * Cierra una PreparedStatement
     * 
     * @param stmt 
     */
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * Cierra un ResultSet
     * 
     * @param rs 
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
        }
    }
    
    
}