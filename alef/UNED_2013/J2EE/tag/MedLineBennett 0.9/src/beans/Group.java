package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;

/**
 * Group es el bean para la tabla de mismo nombre en
 * la que se guardan los diferentes grupos que pueda tener
 * una enfermedad o 'HealthTopic'.
 * Cada 'Healthtopic' puede tener '0' o varios valores.
 * @author alef
 */

/*

public class Group implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int healthTopic_Id;//Id del HealthTopic referenciado

	private int idGroup;//Id del grupo
	
	private String name;//Nombre del grupo
	
	private String url;//URL del grupo

	public Group() {
	}
	//Constructor	
	public Group(int healthTopic_Id, int idGroup, String name, String uRL) {
		this.healthTopic_Id = healthTopic_Id;
		this.idGroup = idGroup;
		this.name = name;
		url = uRL;
	}

	//Getters and Setters
	public int getHealthTopic_Id() {
		return this.healthTopic_Id;
	}
	public void setHealthTopic_Id(int healthTopic_Id) {
		this.healthTopic_Id = healthTopic_Id;
	}
	public int getIdGroup() {
		return this.idGroup;
	}
	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	*/
	/**
	 * Inserta en la tabla 'group' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`group`(`HealthTopic_Id`,`IdGroup`,`Name`," +
					"`URL`)VALUES(?,?,?,?);");
			ps.setInt(1, this.healthTopic_Id);
			ps.setInt(2, this.idGroup);
			ps.setString(3, this.name);
			ps.setString(4, this.url);
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
	
	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD según el
	//criterio de búsqueda id.
	public ArrayList<Group> listadoGroup(int id) throws Exception{
		ArrayList<Group> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoGroup("SELECT * FROM `group` where HealthTopic_Id = ?", id);
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
		if (!(other instanceof Group)) {
			return false;
		}
		Group castOther = (Group)other;
		return 
			(this.healthTopic_Id == castOther.healthTopic_Id)
			&& (this.idGroup == castOther.idGroup);
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
		hash = hash * prime + this.idGroup;
		
		return hash;
	}
	
}
*/