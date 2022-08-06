package dbconector;

import java.sql.SQLException;
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
                //connection = DriverManager.getConnection(url, user, password);
                log.info("Conexión realizada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}