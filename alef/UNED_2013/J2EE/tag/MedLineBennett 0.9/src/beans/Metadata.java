package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
/**
 * Metadata es el bean para la tabla de mismo nombre.
 * Almacena el encabezado del XML con la fecha de la
 * realización y el número total de Healthtopics que
 * almacena.
 * La tabla solo contendrá un registro.
 * @author alef
 */
/*
public class Metadata implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date fecha;

	private int total;

	public Metadata() {
	}
	//Getters and Setters
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
*/
	/**
	 * Inserta en la tabla 'metadata' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`metadata`(`Total`,`Fecha`)" +
					"VALUES(?,?);");
			ps.setInt(1, this.total);
			ps.setDate(2, (java.sql.Date) this.fecha);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Metadata");
			e.printStackTrace();
		}

	}
}
*/