package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;

/**
 * Alsocalled es el bean para la tabla de mismo nombre en
 * la que se guardan los diferentes nombres que pueda tener
 * una enfermedad o 'HealthTopic'.
 * Cada 'Healthtopic' puede tener '0' o varios valores.
 * @author DAVID
 *
 */
/*
public class Alsocalled implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int healthTopic_Id;//Id del HealthTopic referenciado

	private String alsoCalled;//Otro nombre del HealthTopic

	public Alsocalled() {
	}
	
	//Constructor
	public Alsocalled(int id, String alsoCalled) {
		healthTopic_Id = id;
		this.alsoCalled = alsoCalled;
	}
	
	//Getters and Setters
	public int getHealthTopic_Id() {
		return this.healthTopic_Id;
	}
	public void setHealthTopic_Id(int healthTopic_Id) {
		this.healthTopic_Id = healthTopic_Id;
	}
	public String getAlsoCalled() {
		return this.alsoCalled;
	}
	public void setAlsoCalled(String alsoCalled) {
		this.alsoCalled = alsoCalled;
	}
	*/
	/**
	 * Inserta en la tabla 'alsocalled' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;
				
		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`alsocalled` (`HealthTopic_Id`, `AlsoCalled`) " +
					"VALUES (?,?);");
			ps.setInt(1, this.healthTopic_Id);
			ps.setString(2, this.alsoCalled);
			ps.executeUpdate();
			ps.close();
		} catch (SQLTimeoutException e){
			System.out.println("Error de conexión. El tiempo de espera se ha sobrepasado");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla AlsoCalled");
			e.printStackTrace();
		}
		
	}

	
	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD AlsoCalled según el
	//criterio de búsqueda id.
	public ArrayList<Alsocalled> listadoAlsoCalled(int id) throws Exception{
		ArrayList<Alsocalled> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PreparedStatement
		miLista=miBd.listadoAlsoCalled("Select * from alsocalled where HealthTopic_Id = ?", id);
		miBd.cerrarConexion();
		return miLista;		
	}
	*/
	/*
	 *  Según la implementeción de equals() por defecto en la clase Object,
	 *  lo que hace es comparar las referencias de los objetos,
	 *  es decir sus direcciones de memoria. 
	 *  Para que el método equals() funcione de manera correcto es necesario
	 *  reimplementarlo.
	 */
/*
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Alsocalled)) {
			return false;
		}
		Alsocalled castOther = (Alsocalled)other;
		return 
			(this.healthTopic_Id == castOther.healthTopic_Id)
			&& this.alsoCalled.equals(castOther.alsoCalled);
	}
*/
	/*
	 * La función de dispersión permite localizar de forma rápida 
	 * un elemento dentro del array sin tener que recorrerlo entero.
	 * si reimplementamos el método equals, es obligatorio
	 * reimplementar el método hashCode.
	 */
/*
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.healthTopic_Id;
		hash = hash * prime + this.alsoCalled.hashCode();
		
		return hash;
	}
	
}
*/
