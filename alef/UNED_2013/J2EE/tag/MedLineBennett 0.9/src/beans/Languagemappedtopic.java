package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Languagemappedtopic es el bean para la tabla de mismo nombre.
 * En Languagemappedtopic se almacena al url para el mismo
 * Healthtopic en español si el Healthtopic está en inglés
 * y viceversa.
 * Cada 'Healthtopic' puede tener '0' o un valor.
 * @author alef
 */
/*
public class Languagemappedtopic implements Serializable {

	private static final long serialVersionUID = 1L;

	private int healthTopics_Id;//Id. del Healthtopic referenciado

	private int idLanguageMappedTopic;//Id. del LanguagemappedTopic

	private String language;//Idioma del Healthtopic

	private String url;//URL

	private String name;//Nombre del Healthtopic en el idioma del Languagemappedtopic

	//Constructores
	public Languagemappedtopic() {
	}

	public Languagemappedtopic(int ht_id, int lang_id, String u, String lang, String n){
		this.healthTopics_Id = ht_id;
		this.idLanguageMappedTopic = lang_id;
		this.url = u;
		this.language = lang;
		this.name = n;
	}
	//Getters and Setters
	public int getHealthTopics_Id() {
		return this.healthTopics_Id;
	}
	public void setHealthTopics_Id(int healthTopics_Id) {
		this.healthTopics_Id = healthTopics_Id;
	}
	public int getIdLanguageMappedTopic() {
		return this.idLanguageMappedTopic;
	}
	public void setIdLanguageMappedTopic(int idLanguageMappedTopic) {
		this.idLanguageMappedTopic = idLanguageMappedTopic;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`languagemappedtopic`(`HealthTopics_Id`," +
					"`idLanguageMappedTopic`,`URL`,`Language`,`Name`)VALUES(?,?,?,?,?);");
			ps.setInt(1, this.healthTopics_Id);
			ps.setInt(2, this.idLanguageMappedTopic);
			ps.setString(3, this.url);
			ps.setString(4, this.language);
			ps.setString(5, this.name);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla LanguageMappedTopic");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD LanguageMapped según el
	//criterio de búsqueda id.
	public ArrayList<Languagemappedtopic> listadoMapped(int id) throws Exception{
		ArrayList<Languagemappedtopic> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoAlsoMapped("Select * from languagemappedtopic where HealthTopics_Id = ?", id);
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
		if (!(other instanceof Languagemappedtopic)) {
			return false;
		}
		Languagemappedtopic castOther = (Languagemappedtopic)other;
		return 
				(this.healthTopics_Id == castOther.healthTopics_Id)
				&& (this.idLanguageMappedTopic == castOther.idLanguageMappedTopic);
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
		hash = hash * prime + this.healthTopics_Id;
		hash = hash * prime + this.idLanguageMappedTopic;

		return hash;
	}
}
*/
