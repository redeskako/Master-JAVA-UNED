package dbconector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conector {

	private static Connection connection = null;
	private static final Logger log = LoggerFactory.getLogger(Conector.class);

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/worldhealthbank?user=root");
                log.info("Conexión bbdd realizada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}