package beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Representa un Site relacionado con el Healthtopic
 * principal.
 * Cada 'Healthtopic' puede tener '0' o más de un valor asociado.
 * @author alef
 */
/*
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	private int URL_Id;//Id. del Healthtopic principal

	private String languageMappedURL;// URL del site en español si el site está en inglés y viceversa

	private String name;//Nombre del Site

	private String url;//URL

	private List<Sitedescription> sitedescriptions;//Cada Site puede tener cero o más descripciones

	private List<Siteinfocategory> siteinfocategories;//Cada Site puede tener cero o más grupos de enlaces asociados

	private List<Siteorganization> siteorganizations;//Cada Site puede tener cero o más organizaciones asociadas

	private int healthTopic_Id;

	//Constructores
	public Site() {
	}
	
	public Site(int u_id, int ht_id, String u, String n, String lang){
		this.healthTopic_Id = ht_id;
		this.URL_Id = u_id;
		this.url = u;
		this.name = n;
		this.languageMappedURL = lang;
	}
	//Getters and Setters
	public int getURL_Id() {
		return this.URL_Id;
	}

	public void setURL_Id(int URL_Id) {
		this.URL_Id = URL_Id;
	}

	public String getLanguageMappedURL() {
		return this.languageMappedURL;
	}

	public void setLanguageMappedURL(String languageMappedURL) {
		this.languageMappedURL = languageMappedURL;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Sitedescription> getSitedescriptions() {
		return this.sitedescriptions;
	}

	public void setSitedescriptions(List<Sitedescription> sitedescriptions) {
		this.sitedescriptions = sitedescriptions;
	}

	public List<Siteinfocategory> getSiteinfocategories() {
		return this.siteinfocategories;
	}

	public void setSiteinfocategories(List<Siteinfocategory> siteinfocategories) {
		this.siteinfocategories = siteinfocategories;
	}

	public List<Siteorganization> getSiteorganizations() {
		return this.siteorganizations;
	}

	public void setSiteorganizations(List<Siteorganization> siteorganizations) {
		this.siteorganizations = siteorganizations;
	}

	public int getHealthtopic() {
		return this.healthTopic_Id;
	}

	public void setHealthtopic(int healthtopic) {
		this.healthTopic_Id = healthtopic;
	}
	*/

	/**
	 * Inserta en la tabla 'site' los atributos de la clase.
	 * La tabla 'Site' generará un 'id' automáticamente para cada
	 * 'Site' introducido.
	 * @param conn = conexión que se utiliza para ejecutar el sql.
	 */
/*
	public void insert(Connection conn){
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("INSERT INTO `medlinebennett`.`site`(`HealthTopic_Id`," +
					"`URL`,`Name`,`LanguageMappedURL`)VALUES(?,?,?,?);");
			ps.setInt(1, this.healthTopic_Id);
			ps.setString(2, this.url);
			ps.setString(3, this.name);
			ps.setString(4, this.languageMappedURL);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar el registro en la tabla Site");
			e.printStackTrace();
		}

	}

	//Método que devuelve una lista con los resultados de la búsqueda en la BBDD según el
	//criterio de búsqueda id.
	public ArrayList<Relatedtopic> listadoAlsoRelatedtopic(int id) throws Exception{
		ArrayList<Relatedtopic> miLista;
		BBDD miBd = new BBDD();
		miBd.abrirConexion(); 
		//Vamos a utilizar PrepareStatement
		miLista=miBd.listadoRelatedtopic("Select * from relatedtopic where HealthTopic_Id = ?", id);
		miBd.cerrarConexion();
		return miLista;		
	}

}
*/
