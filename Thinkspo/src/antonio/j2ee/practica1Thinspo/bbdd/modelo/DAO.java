package antonio.j2ee.practica1Thinspo.bbdd.modelo;

import java.sql.Connection;

/**
 * Interfaz que define las operaciones que tendra el DAO
 * Inicialmente una que devuelva una conexion de BBDD
 * @author  Antonio S�nchez Ant�n
 * @since  1.0
 */
public interface DAO {
	 Connection getConnection();
}
