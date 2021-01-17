package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Otherlanguage es el bean para la tabla de mismo nombre.
 * Almacena los enlaces que hay para el mismo Healttopic
 * en otros idiomas.
 * Cada 'Healthtopic' puede tener '0' o más de un valor asociado.
 * @author alef
 */
/*
public class Otherlanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	private int healthTopic_Id;//Id. del Healthtopic

	private String name;//Nombre del idioma en inglés

	private String vernacularName;//Nombre del idioma en su propio idioma

	private String url;//URL

	//Constructor
	public Otherlanguage() {
	}
	
	public Otherlanguage(int ht_id, String n, String vern, String u){
		this.healthTopic_Id = ht_id;
		this.name = n;
		this.vernacularName = vern;
		this.url = u;
	}
	//Setters and getters
	public int getHealthTopic_Id() {
		return this.healthTopic_Id;
	}
	public void setHealthTopic_Id(int healthTopic_Id) {
		this.healthTopic_Id = healthTopic_Id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVernacularName() {
		return vernacularName;
	}
	public void setVernacularName(String vernacularName) {
		this.vernacularName = vernacularName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	*/

	/**
	 * Inserta en la tabla 'languagemappedtopic' los atributos de la clase.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`otherlanguage`(`HealthTopic_Id`," +
					"`Name`,`VernacularName`,`URL`)VALUES(?,?,?,?);");
			ps.setInt(1, this.healthTopic_Id);
			ps.setString(2, this.name);
			ps.setString(3, this.vernacularName);
			ps.setString(4, this.url);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Otherlanguage");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD AlsoCalled según el
	//criterio de búsqueda id.
	public ArrayList<Meshheading> listadoMeshheading(int id) throws Exception{
		ArrayList<Meshheading> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoMeshheading("Select * from meshheading where HealthTopic_Id = ?", id);
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
		if (!(other instanceof Otherlanguage)) {
			return false;
		}
		Otherlanguage castOther = (Otherlanguage)other;
		return 
				(this.healthTopic_Id == castOther.healthTopic_Id)
				&& this.name.equals(castOther.name);
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
		hash = hash * prime + this.name.hashCode();

		return hash;
	}
}
*/
